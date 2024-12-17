DROP SCHEMA IF EXISTS public;
CREATE SCHEMA public;
SET search_path TO public;

-- Tables reliées à la base de données.

CREATE TABLE profil (
    id PRIMARY KEY REFERENCES private.users(id) ON DELETE CASCADE,
    username VARCHAR(50) UNIQUE NOT NULL,
);

CREATE TABLE cards (
    id SERIAL PRIMARY KEY, 
    name VARCHAR(100) NOT NULL,
    description TEXT,
    rarity VARCHAR(20) NOT NULL CHECK (rarity IN ('common', 'gold', 'diamond')),
    energy_points INTEGER NOT NULL CHECK (energy_points >= 0),
    mhp INTEGER NOT NULL CHECK (mhp >= 0),
    image_url VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
)

CREATE TABLE inventory (
    id SERIAL PRIMARY KEY,
    profile_id INTEGER UNIQUE NOT NULL REFERENCES profil(id) ON DELETE CASCADE,
    card_id INTEGER UNIQUE NOT NULL REFERENCES cards(id) ON DELETE CASCADE,
    quantity INTEGER NOT NULL DEFAULT 1 CHECK (quantity >= 0),
    acquired_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
)

CREATE INDEX idx_cards_rarity ON cards(rarity);
CREATE INDEX idx_cards_energy_points ON cards(energy_points);
CREATE INDEX idx_cards_mhp ON cards(mhp);

CREATE INDEX idx_inventory_profile_id ON inventory(profile_id);
CREATE INDEX idx_inventory_card_id ON inventory(card_id);

CREATE INDEX idx_auth_logs_login_time ON user_authentification_logs(login_time);
CREATE INDEX idx_auth_logs_success ON user_authentification_logs(is_successful);

CREATE INDEX idx_wallet_currency_type ON user_wallet(currency_type);

