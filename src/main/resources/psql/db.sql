-- 1. Create the dedicated user
-- Replace 'your_password' with the value from your DB_PASSWORD env variable
CREATE USER hei_agricol WITH PASSWORD 'agriculture123';

-- 2. Create the database
-- Use the name defined in your DB_URL (e.g., federation_db)
CREATE DATABASE agri_federation;

-- 3. Grant privileges
GRANT ALL PRIVILEGES ON DATABASE agri_federation TO postgres;

-- 4. Connect to the database to grant schema permissions
\c agri_federation

GRANT ALL ON SCHEMA public TO postgres;