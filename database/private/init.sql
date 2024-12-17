DROP SCHEMA IF EXISTS private CASCADE;
CREATE SCHEMA private;
SET search_path TO private;

-- Tables reliées aux utilisateurs.

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    birth_date DATE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);  

CREATE TABLE user_authentification_logs (
    id SERIAL PRIMARY KEY,
    user_id INTEGER NOT NULL REFERENCES users(id),
    login_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    ip_address INET,
    device_info TEXT,
    is_successful BOOLEAN NOT NULL DEFAULT FALSE
)

CREATE TABLE user_wallet (
    id SERIAL PRIMARY KEY,
    user_id INTEGER NOT NULL UNIQUE REFERENCES users(id) ON DELETE CASCADE,
    balance INTEGER NOT NULL DEFAULT 0,
    currency_type VARCHAR(50) NOT NULL DEFAULT 'in-game-credits'
)

CREATE TABLE roles (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    description TEXT,
)

CREATE TABLE user_roles (
    user_id INTEGER NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    role_id INTEGER NOT NULL REFERENCES roles(id) ON DELETE CASCADE,
    assigned_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (user_id, role_id)
)

CREATE INDEX idx_users_email ON users(email);
CREATE INDEX idx_wallet_user_id ON user_wallet(user_id);
CREATE INDEX idx_auth_logs_user_id ON user_authentification_logs(user_id);

CREATE OR REPLACE FUNCTION insert_user(
    private_first_name VARCHAR(50),
    private_last_name VARCHAR(50),
    private_birth_date DATE,
    private_email VARCHAR(100),
    private_password VARCHAR(255)
    public_username VARCHAR(50),
) RETURNS INTEGER AS $$
DECLARE 
    user_id INTEGER;
    default_role_id INTEGER;
BEGIN

    SELECT id INTO default_role_id FROM roles WHERE name = 'user';
    IF default_role_id IS NULL THEN
        RAISE EXCEPTION 'Default role "user" not found.';
    END IF;

    INSERT INTO users (
        first_name,
        last_name,
        birth_date,
        email,
        password
    ) VALUES (
        private_first_name,
        private_last_name,
        private_birth_date,
        private_email,
        private_password
    ) RETURNING id INTO user_id;

    INSERT INTO public.profil (id, username) VALUES (user_id, public_username);
    INSERT INTO user_roles (user_id, role_id) VALUES (user_id, default_role_id);
    RETURN user_id;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION add_wallet_balance(
    target_user_id INTEGER,
    amount INTEGER
) RETURNS BOOLEAN AS $$
DECLARE
    current_balance INTEGER;
BEGIN
    SELECT balance INTO current_balance 
    FROM user_wallet 
    WHERE user_id = target_user_id 
    FOR UPDATE;

    IF current_balance IS NULL THEN
        RAISE EXCEPTION 'Wallet not found for user %', target_user_id;
    END IF;

    UPDATE user_wallet
    SET balance = current_balance + amount
    WHERE user_id = target_user_id;

    RETURN TRUE;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION sub_wallet_balance(
    target_user_id INTEGER,
    amount INTEGER
) RETURNS BOOLEAN AS $$
DECLARE
    current_balance INTEGER;
BEGIN
    SELECT balance INTO current_balance 
    FROM user_wallet 
    WHERE user_id = target_user_id 
    FOR UPDATE;

    IF current_balance IS NULL THEN
        RAISE EXCEPTION 'Wallet not found for user %', target_user_id;
    END IF;

    IF current_balance < amount THEN
        RAISE EXCEPTION 'Insufficient balance for user %', target_user_id;
    END IF;

    UPDATE user_wallet
    SET balance = current_balance - amount
    WHERE user_id = target_user_id;

    RETURN TRUE;
END;
$$ LANGUAGE plpgsql;

