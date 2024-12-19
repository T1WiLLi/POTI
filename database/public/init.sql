DROP SCHEMA IF EXISTS public CASCADE;
CREATE SCHEMA public;
SET search_path TO public;

-- Users Table:
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    birth_date DATE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL, -- Might change that to 128 as we will be using SH-512
    email_verified BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE users_email_verification (
    id PRIMARY KEY REFERENCES users(id),
    verification_code VARCHAR(255) NOT NULL,
    verification_code_expires_at TIMESTAMP NOT NULL
);

-- Wallet Table
CREATE TABLE user_wallet (
    id SERIAL PRIMARY KEY,
    user_id INTEGER NOT NULL UNIQUE REFERENCES users(id) ON DELETE CASCADE,
    balance INTEGER NOT NULL DEFAULT 0 CHECK (balance >= 0),
    currency_type VARCHAR(50) NOT NULL DEFAULT 'Poti'
);

-- User Authentication Logs
CREATE TABLE user_authentication_logs (
    id SERIAL PRIMARY KEY,
    user_id INTEGER NOT NULL REFERENCES users(id),
    login_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    ip_address INET,
    device_info TEXT,
    is_successful BOOLEAN NOT NULL DEFAULT FALSE
);

-- Roles Table
CREATE TABLE roles (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    description TEXT
);

-- User Roles: Many-to-many relationship
CREATE TABLE user_roles (
    user_id INTEGER NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    role_id INTEGER NOT NULL REFERENCES roles(id) ON DELETE CASCADE,
    assigned_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (user_id, role_id)
);

-- Cards Table
CREATE TABLE cards (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    rarity VARCHAR(20) NOT NULL CHECK (rarity IN ('common', 'gold', 'diamond')),
    energy_cost INTEGER NOT NULL CHECK (energy_cost >= 0),
    hp INTEGER NOT NULL CHECK (hp >= 0),
    damage INTEGER NOT NULL CHECK (damage >= 0),
    image_url VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Inventory Table: Tracks user-owned cards
CREATE TABLE inventory (
    id SERIAL PRIMARY KEY,
    user_id INTEGER NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    card_id INTEGER NOT NULL REFERENCES cards(id) ON DELETE CASCADE,
    quantity INTEGER NOT NULL DEFAULT 1 CHECK (quantity >= 0),
    acquired_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UNIQUE (user_id, card_id)
);

-- User Packs Table
CREATE TABLE user_packs (
    id SERIAL PRIMARY KEY,
    user_id INTEGER NOT NULL UNIQUE REFERENCES users(id) ON DELETE CASCADE,
    time_based_available_packs INTEGER NOT NULL DEFAULT 0 CHECK (time_based_available_packs >= 0 AND time_based_available_packs <= 2),
    total_available_packs INTEGER NOT NULL DEFAULT 0 CHECK (total_available_packs >= 0),
    next_pack_time TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Marketplace Table: Manages card listings for sale
CREATE TABLE marketplace (
    id SERIAL PRIMARY KEY,
    seller_id INTEGER NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    card_id INTEGER NOT NULL REFERENCES cards(id) ON DELETE CASCADE,
    price INTEGER NOT NULL CHECK (price > 0),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
);

-- Users
CREATE INDEX idx_users_email ON users(email);
CREATE INDEX idx_users_username ON users(username);

-- Cards
CREATE INDEX idx_cards_rarity ON cards(rarity);
CREATE INDEX idx_cards_energy_cost ON cards(energy_cost);
CREATE INDEX idx_cards_hp ON cards(hp);

-- Inventory
CREATE INDEX idx_inventory_user_id ON inventory(user_id);
CREATE INDEX idx_inventory_card_id ON inventory(card_id);

-- User Packs
CREATE INDEX idx_user_packs_user_id ON user_packs(user_id);

-- Marketplace
CREATE INDEX idx_marketplace_status ON marketplace(status);
CREATE INDEX idx_marketplace_seller_id ON marketplace(seller_id);