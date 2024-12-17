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