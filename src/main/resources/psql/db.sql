-- Database and user setup
CREATE DATABASE argiculturre_db;

CREATE USER argiculturre_user WITH PASSWORD 'change_me';
GRANT CONNECT, TEMPORARY ON DATABASE argiculturre_db TO argiculturre_user;

\connect argiculturre_db

GRANT USAGE, CREATE ON SCHEMA public TO argiculturre_user;
GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA public TO argiculturre_user;
GRANT USAGE, SELECT, UPDATE ON ALL SEQUENCES IN SCHEMA public TO argiculturre_user;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT SELECT, INSERT, UPDATE, DELETE ON TABLES TO argiculturre_user;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT USAGE, SELECT, UPDATE ON SEQUENCES TO argiculturre_user;

CREATE SEQUENCE IF NOT EXISTS collectivity_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS member_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS sponsor_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS mandate_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS contribution_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS account_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS activity_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS attendance_id_seq START WITH 1 INCREMENT BY 1;

ALTER TABLE collectivity ALTER COLUMN id SET DEFAULT nextval('collectivity_id_seq');
ALTER TABLE member ALTER COLUMN id SET DEFAULT nextval('member_id_seq');
ALTER TABLE sponsor ALTER COLUMN id SET DEFAULT nextval('sponsor_id_seq');
ALTER TABLE mandate ALTER COLUMN id SET DEFAULT nextval('mandate_id_seq');
ALTER TABLE contribution ALTER COLUMN id SET DEFAULT nextval('contribution_id_seq');
ALTER TABLE account ALTER COLUMN id SET DEFAULT nextval('account_id_seq');
ALTER TABLE activity ALTER COLUMN id SET DEFAULT nextval('activity_id_seq');
ALTER TABLE attendance ALTER COLUMN id SET DEFAULT nextval('attendance_id_seq');