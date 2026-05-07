DROP TABLE IF EXISTS transaction CASCADE;
DROP TABLE IF EXISTS membership_fee CASCADE;
DROP TABLE IF EXISTS reference CASCADE;
DROP TABLE IF EXISTS membership CASCADE;
DROP TABLE IF EXISTS bank_account CASCADE;
DROP TABLE IF EXISTS mobile_banking_account CASCADE;
DROP TABLE IF EXISTS cash_account CASCADE;
DROP TABLE IF EXISTS financial_account CASCADE;
DROP TABLE IF EXISTS collectivity CASCADE;
DROP TABLE IF EXISTS member CASCADE;

DROP TYPE IF EXISTS gender_enum CASCADE;
DROP TYPE IF EXISTS member_occupation_enum CASCADE;
DROP TYPE IF EXISTS frequency_enum CASCADE;
DROP TYPE IF EXISTS activity_status_enum CASCADE;
DROP TYPE IF EXISTS payment_mode_enum CASCADE;
DROP TYPE IF EXISTS mobile_banking_service_enum CASCADE;
DROP TYPE IF EXISTS bank_enum CASCADE;
DROP TYPE IF EXISTS financial_account_type_enum CASCADE;



CREATE TYPE gender_enum AS ENUM ('MALE', 'FEMALE');
CREATE TYPE member_occupation_enum AS ENUM ('JUNIOR', 'SENIOR', 'SECRETARY', 'TREASURER', 'VICE_PRESIDENT', 'PRESIDENT');
CREATE TYPE frequency_enum AS ENUM ('WEEKLY', 'MONTHLY', 'ANNUALLY', 'PUNCTUALLY');
CREATE TYPE activity_status_enum AS ENUM ('ACTIVE', 'INACTIVE');
CREATE TYPE payment_mode_enum AS ENUM ('CASH', 'MOBILE_BANKING', 'BANK_TRANSFER');
CREATE TYPE mobile_banking_service_enum AS ENUM ('AIRTEL_MONEY', 'MVOLA', 'ORANGE_MONEY');
CREATE TYPE bank_enum AS ENUM ('BRED', 'MCB', 'BMOI', 'BOA', 'BGFI', 'AFG', 'ACCES_BAQUE', 'BAOBAB', 'SIPEM');
CREATE TYPE financial_account_type_enum AS ENUM ('CASH', 'MOBILE_BANKING', 'BANK');

-- =====================================================
-- 3. CRÉER LES TABLES AVEC VARCHAR IDs
-- =====================================================

-- Table member (id en VARCHAR)
CREATE TABLE member (
                        id VARCHAR(50) PRIMARY KEY,
                        first_name VARCHAR(100) NOT NULL,
                        last_name VARCHAR(100) NOT NULL,
                        birth_date DATE NOT NULL,
                        gender gender_enum NOT NULL,
                        address TEXT,
                        profession VARCHAR(100),
                        phone_number VARCHAR(20),
                        email VARCHAR(255) UNIQUE NOT NULL,
                        date_adhesion_federation DATE NOT NULL
);

-- Table collectivity (id en VARCHAR)
CREATE TABLE collectivity (
                              id VARCHAR(50) PRIMARY KEY,
                              location VARCHAR(255) NOT NULL,
                              specialite_agricole VARCHAR(255) NOT NULL,
                              annual_dues_amount INTEGER NOT NULL,
                              date_creation DATE NOT NULL,
                              federation_approval BOOLEAN NOT NULL,
                              name VARCHAR(100) UNIQUE,
                              number INTEGER UNIQUE
);

-- Table membership (clés étrangères en VARCHAR)
CREATE TABLE membership (
                            member_id VARCHAR(50) NOT NULL,
                            collectivity_id VARCHAR(50) NOT NULL,
                            occupation member_occupation_enum NOT NULL,
                            registration_fee_paid BOOLEAN NOT NULL,
                            membership_dues_paid BOOLEAN NOT NULL,
                            date_adhesion DATE NOT NULL,
                            payment_date DATE,
                            PRIMARY KEY (member_id, collectivity_id),
                            FOREIGN KEY (member_id) REFERENCES member(id) ON DELETE CASCADE,
                            FOREIGN KEY (collectivity_id) REFERENCES collectivity(id) ON DELETE CASCADE
);

-- Table reference (parrainage)
CREATE TABLE reference (
                           candidate_id VARCHAR(50) NOT NULL,
                           sponsor_id VARCHAR(50) NOT NULL,
                           relation_nature VARCHAR(50) NOT NULL,
                           sponsorship_date DATE NOT NULL,
                           PRIMARY KEY (candidate_id, sponsor_id),
                           FOREIGN KEY (candidate_id) REFERENCES member(id) ON DELETE CASCADE,
                           FOREIGN KEY (sponsor_id) REFERENCES member(id) ON DELETE CASCADE,
                           CHECK (candidate_id != sponsor_id)
);

-- Table financial_account (id en VARCHAR)
CREATE TABLE financial_account (
                                   id VARCHAR(50) PRIMARY KEY,
                                   type financial_account_type_enum NOT NULL,
                                   amount DECIMAL(15,2) NOT NULL DEFAULT 0,
                                   collectivity_id VARCHAR(50) REFERENCES collectivity(id),
                                   created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tables filles
CREATE TABLE cash_account (
                              id VARCHAR(50) PRIMARY KEY,
                              FOREIGN KEY (id) REFERENCES financial_account(id) ON DELETE CASCADE
);

CREATE TABLE mobile_banking_account (
                                        id VARCHAR(50) PRIMARY KEY,
                                        holder_name VARCHAR(255),
                                        mobile_service mobile_banking_service_enum,
                                        mobile_number VARCHAR(20),
                                        FOREIGN KEY (id) REFERENCES financial_account(id) ON DELETE CASCADE
);

CREATE TABLE bank_account (
                              id VARCHAR(50) PRIMARY KEY,
                              holder_name VARCHAR(255),
                              bank_name bank_enum,
                              bank_code INTEGER,
                              branch_code INTEGER,
                              account_number INTEGER,
                              account_key INTEGER,
                              FOREIGN KEY (id) REFERENCES financial_account(id) ON DELETE CASCADE
);

-- Table membership_fee (id en VARCHAR)
CREATE TABLE membership_fee (
                                id VARCHAR(50) PRIMARY KEY,
                                collectivity_id VARCHAR(50) NOT NULL REFERENCES collectivity(id) ON DELETE CASCADE,
                                eligible_from DATE NOT NULL,
                                frequency frequency_enum NOT NULL,
                                amount DECIMAL(15,2) NOT NULL CHECK (amount >= 0),
                                label VARCHAR(255),
                                status activity_status_enum NOT NULL DEFAULT 'ACTIVE',
                                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Table transaction (id en VARCHAR)
CREATE TABLE transaction (
                             id VARCHAR(50) PRIMARY KEY,
                             member_id VARCHAR(50) NOT NULL REFERENCES member(id) ON DELETE CASCADE,
                             collectivity_id VARCHAR(50) NOT NULL REFERENCES collectivity(id) ON DELETE CASCADE,
                             amount DECIMAL(15,2) NOT NULL CHECK (amount > 0),
                             payment_mode payment_mode_enum NOT NULL,
                             account_credited_id VARCHAR(50) NOT NULL REFERENCES financial_account(id),
                             membership_fee_id VARCHAR(50) REFERENCES membership_fee(id),
                             creation_date DATE NOT NULL,
                             created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- =====================================================
-- 4. CRÉER LES INDEX
-- =====================================================
CREATE INDEX idx_member_email ON member(email);
CREATE INDEX idx_membership_collectivity ON membership(collectivity_id);
CREATE INDEX idx_reference_sponsor ON reference(sponsor_id);
CREATE INDEX idx_reference_candidate ON reference(candidate_id);
CREATE INDEX idx_membership_fee_collectivity ON membership_fee(collectivity_id);
CREATE INDEX idx_transaction_collectivity ON transaction(collectivity_id);
CREATE INDEX idx_transaction_member ON transaction(member_id);
CREATE INDEX idx_transaction_date ON transaction(creation_date);
CREATE INDEX idx_financial_account_type ON financial_account(type);
CREATE INDEX idx_financial_account_collectivity ON financial_account(collectivity_id);

-- =====================================================
-- 5. INSÉRER LES COLLECTIVITÉS
-- =====================================================
INSERT INTO collectivity (id, location, specialite_agricole, annual_dues_amount, date_creation, federation_approval, name, number) VALUES
                                                                                                                                       ('col-1', 'Ambatondrazaka', 'Riziculture', 100000, '2026-01-01', TRUE, 'Mpanorina', 1),
                                                                                                                                       ('col-2', 'Ambatondrazaka', 'Pisciculture', 100000, '2026-01-01', TRUE, 'Dobo voalohany', 2),
                                                                                                                                       ('col-3', 'Brickaville', 'Apiculture', 50000, '2026-01-01', TRUE, 'Tantely mamy', 3);

-- =====================================================
-- 6. INSÉRER LES MEMBRES
-- =====================================================

-- Membres Collectivité 1 (8 membres)
INSERT INTO member (id, first_name, last_name, birth_date, gender, address, profession, phone_number, email, date_adhesion_federation) VALUES
                                                                                                                                           ('C1-M1', 'Jean', 'Rakoto', '1980-02-01', 'MALE', 'Lot II V M Ambato.', 'Riziculteur', '0341234567', 'jean.rakoto@fed-agri.mg', '2025-10-01'),
                                                                                                                                           ('C1-M2', 'Marie', 'Rasoa', '1982-03-05', 'FEMALE', 'Lot II F Ambato.', 'Agriculteur', '0321234567', 'marie.rasoa@fed-agri.mg', '2025-10-01'),
                                                                                                                                           ('C1-M3', 'Pierre', 'Andrian', '1992-03-10', 'MALE', 'Lot II J Ambato.', 'Collecteur', '0331234567', 'pierre.andrian@fed-agri.mg', '2025-10-01'),
                                                                                                                                           ('C1-M4', 'Sophie', 'Rabe', '1988-05-22', 'FEMALE', 'Lot A K 50 Ambato.', 'Distributeur', '0381234567', 'sophie.rabe@fed-agri.mg', '2025-10-01'),
                                                                                                                                           ('C1-M5', 'Paul', 'Ranaivo', '1999-08-21', 'MALE', 'Lot UV 80 Ambato.', 'Riziculteur', '0373434567', 'paul.ranaivo@fed-agri.mg', '2025-10-01'),
                                                                                                                                           ('C1-M6', 'Claire', 'Razafy', '1998-08-22', 'FEMALE', 'Lot UV 6 Ambato.', 'Riziculteur', '0372234567', 'claire.razafy@fed-agri.mg', '2025-10-01'),
                                                                                                                                           ('C1-M7', 'Thomas', 'Rakotomalala', '1998-01-31', 'MALE', 'Lot UV 7 Ambato.', 'Riziculteur', '0374234567', 'thomas.rakoto@fed-agri.mg', '2025-10-01'),
                                                                                                                                           ('C1-M8', 'Lala', 'Ravoson', '1975-08-20', 'FEMALE', 'Lot UV 8 Ambato.', 'Riziculteur', '0370234567', 'lala.ravoson@fed-agri.mg', '2025-10-01');

-- Membres Collectivité 2 (8 membres)
INSERT INTO member (id, first_name, last_name, birth_date, gender, address, profession, phone_number, email, date_adhesion_federation) VALUES
                                                                                                                                           ('C2-M1', 'Mampionona', 'Rakoto', '1985-01-15', 'MALE', 'Lot 10 Ambatondrazaka', 'Pisciculteur', '0341234501', 'mampionona.rakoto@fed-agri.mg', '2025-10-01'),
                                                                                                                                           ('C2-M2', 'Hanta', 'Rasoa', '1987-02-20', 'FEMALE', 'Lot 11 Ambatondrazaka', 'Pisciculteur', '0341234502', 'hanta.rasoa@fed-agri.mg', '2025-10-01'),
                                                                                                                                           ('C2-M3', 'Rado', 'Andrian', '1990-06-15', 'MALE', 'Lot 12 Ambatondrazaka', 'Pisciculteur', '0341234503', 'rado.andrian@fed-agri.mg', '2025-10-01'),
                                                                                                                                           ('C2-M4', 'Tiana', 'Rabe', '1988-07-20', 'FEMALE', 'Lot 13 Ambatondrazaka', 'Pisciculteur', '0341234504', 'tiana.rabe@fed-agri.mg', '2025-10-01'),
                                                                                                                                           ('C2-M5', 'Faneva', 'Ranaivo', '1992-08-15', 'MALE', 'Lot 14 Ambatondrazaka', 'Pisciculteur', '0341234505', 'faneva.ranaivo@fed-agri.mg', '2025-10-01'),
                                                                                                                                           ('C2-M6', 'Voahangy', 'Razafy', '1995-09-20', 'FEMALE', 'Lot 15 Ambatondrazaka', 'Pisciculteur', '0341234506', 'voahangy.razafy@fed-agri.mg', '2025-10-01'),
                                                                                                                                           ('C2-M7', 'Andry', 'Rakotomalala', '1990-08-15', 'MALE', 'Lot 16 Ambatondrazaka', 'Pisciculteur', '0341234507', 'andry.rakoto@fed-agri.mg', '2025-10-01'),
                                                                                                                                           ('C2-M8', 'Vololona', 'Razafy', '1992-09-20', 'FEMALE', 'Lot 17 Ambatondrazaka', 'Pisciculteur', '0341234508', 'vololona.razafy@fed-agri.mg', '2025-10-01');

-- Membres Collectivité 3 (8 membres)
INSERT INTO member (id, first_name, last_name, birth_date, gender, address, profession, phone_number, email, date_adhesion_federation) VALUES
                                                                                                                                           ('C3-M1', 'Hery', 'Rakotovao', '1988-01-02', 'MALE', 'Lot 33 J Antsirabe', 'Apiculteur', '0340345678', 'hery.rakotovao@fed-agri.mg', '2025-10-01'),
                                                                                                                                           ('C3-M2', 'Mamy', 'Randria', '1982-05-03', 'FEMALE', 'Lot 2 J Antsirabe', 'Agriculteur', '0338634567', 'mamy.randria@fed-agri.mg', '2025-10-01'),
                                                                                                                                           ('C3-M3', 'Tovo', 'Rakoto', '1990-06-15', 'MALE', 'Lot 45 J Antsirabe', 'Apiculteur', '0341234568', 'tovo.rakoto@fed-agri.mg', '2025-10-01'),
                                                                                                                                           ('C3-M4', 'Nomena', 'Rasoa', '1985-07-20', 'FEMALE', 'Lot 67 J Antsirabe', 'Comptable', '0337654322', 'nomena.rasoa@fed-agri.mg', '2025-10-01'),
                                                                                                                                           ('C3-M5', 'Faneva', 'Andrian', '1992-09-10', 'MALE', 'Lot 89 J Antsirabe', 'Apiculteur', '0341122335', 'faneva.andrian@fed-agri.mg', '2025-10-01'),
                                                                                                                                           ('C3-M6', 'Voahangy', 'Rabe', '1988-11-25', 'FEMALE', 'Lot 12 J Antsirabe', 'Distributeur', '0388776656', 'voahangy.rabe@fed-agri.mg', '2025-10-01'),
                                                                                                                                           ('C3-M7', 'Tiana', 'Ranaivo', '1995-02-18', 'MALE', 'Lot 34 J Antsirabe', 'Apiculteur', '0345566779', 'tiana.ranaivo@fed-agri.mg', '2025-10-01'),
                                                                                                                                           ('C3-M8', 'Miora', 'Razafy', '1996-12-03', 'FEMALE', 'Lot 56 J Antsirabe', 'Apiculteur', '0388990012', 'miora.razafy@fed-agri.mg', '2025-10-01');

-- =====================================================
-- 7. INSÉRER LES MEMBERSHIPS
-- =====================================================

-- Collectivité 1
INSERT INTO membership (member_id, collectivity_id, occupation, registration_fee_paid, membership_dues_paid, date_adhesion, payment_date) VALUES
                                                                                                                                              ('C1-M1', 'col-1', 'PRESIDENT', TRUE, TRUE, '2026-01-01', '2026-01-01'),
                                                                                                                                              ('C1-M2', 'col-1', 'VICE_PRESIDENT', TRUE, TRUE, '2026-01-01', '2026-01-01'),
                                                                                                                                              ('C1-M3', 'col-1', 'SECRETARY', TRUE, TRUE, '2026-01-01', '2026-01-01'),
                                                                                                                                              ('C1-M4', 'col-1', 'TREASURER', TRUE, TRUE, '2026-01-01', '2026-01-01'),
                                                                                                                                              ('C1-M5', 'col-1', 'SENIOR', TRUE, TRUE, '2026-01-01', '2026-01-01'),
                                                                                                                                              ('C1-M6', 'col-1', 'SENIOR', TRUE, TRUE, '2026-01-01', '2026-01-01'),
                                                                                                                                              ('C1-M7', 'col-1', 'SENIOR', TRUE, TRUE, '2026-01-01', '2026-01-01'),
                                                                                                                                              ('C1-M8', 'col-1', 'SENIOR', TRUE, TRUE, '2026-01-01', '2026-01-01');

-- Collectivité 2
INSERT INTO membership (member_id, collectivity_id, occupation, registration_fee_paid, membership_dues_paid, date_adhesion, payment_date) VALUES
                                                                                                                                              ('C2-M1', 'col-2', 'PRESIDENT', TRUE, TRUE, '2026-01-01', '2026-01-01'),
                                                                                                                                              ('C2-M2', 'col-2', 'VICE_PRESIDENT', TRUE, TRUE, '2026-01-01', '2026-01-01'),
                                                                                                                                              ('C2-M3', 'col-2', 'SECRETARY', TRUE, TRUE, '2026-01-01', '2026-01-01'),
                                                                                                                                              ('C2-M4', 'col-2', 'TREASURER', TRUE, TRUE, '2026-01-01', '2026-01-01'),
                                                                                                                                              ('C2-M5', 'col-2', 'SENIOR', TRUE, TRUE, '2026-01-01', '2026-01-01'),
                                                                                                                                              ('C2-M6', 'col-2', 'SENIOR', TRUE, TRUE, '2026-01-01', '2026-01-01'),
                                                                                                                                              ('C2-M7', 'col-2', 'SENIOR', TRUE, TRUE, '2026-01-01', '2026-01-01'),
                                                                                                                                              ('C2-M8', 'col-2', 'SENIOR', TRUE, TRUE, '2026-01-01', '2026-01-01');

-- Collectivité 3
INSERT INTO membership (member_id, collectivity_id, occupation, registration_fee_paid, membership_dues_paid, date_adhesion, payment_date) VALUES
                                                                                                                                              ('C3-M1', 'col-3', 'PRESIDENT', TRUE, TRUE, '2026-01-01', '2026-01-01'),
                                                                                                                                              ('C3-M2', 'col-3', 'VICE_PRESIDENT', TRUE, TRUE, '2026-01-01', '2026-01-01'),
                                                                                                                                              ('C3-M3', 'col-3', 'SECRETARY', TRUE, TRUE, '2026-01-01', '2026-01-01'),
                                                                                                                                              ('C3-M4', 'col-3', 'TREASURER', TRUE, TRUE, '2026-01-01', '2026-01-01'),
                                                                                                                                              ('C3-M5', 'col-3', 'SENIOR', TRUE, TRUE, '2026-01-01', '2026-01-01'),
                                                                                                                                              ('C3-M6', 'col-3', 'SENIOR', TRUE, TRUE, '2026-01-01', '2026-01-01'),
                                                                                                                                              ('C3-M7', 'col-3', 'JUNIOR', TRUE, TRUE, '2026-01-01', '2026-01-01'),
                                                                                                                                              ('C3-M8', 'col-3', 'JUNIOR', TRUE, TRUE, '2026-01-01', '2026-01-01');

-- =====================================================
-- 8. INSÉRER LES PARRAINAGES
-- =====================================================
INSERT INTO reference (candidate_id, sponsor_id, relation_nature, sponsorship_date) VALUES
                                                                                        ('C1-M3', 'C1-M1', 'FAMILY', '2026-01-01'),
                                                                                        ('C1-M3', 'C1-M2', 'FRIEND', '2026-01-01'),
                                                                                        ('C3-M3', 'C3-M1', 'COLLEAGUE', '2026-01-01'),
                                                                                        ('C3-M3', 'C3-M2', 'REFERRAL', '2026-01-01');

-- =====================================================
-- 9. INSÉRER LES COMPTES FINANCIERS
-- =====================================================

-- Comptes CASH
INSERT INTO financial_account (id, type, amount, collectivity_id) VALUES
                                                                      ('C1-CASH', 'CASH', 0, 'col-1'),
                                                                      ('C2-CASH', 'CASH', 0, 'col-2'),
                                                                      ('C3-CASH', 'CASH', 0, 'col-3');

INSERT INTO cash_account (id) VALUES ('C1-CASH'), ('C2-CASH'), ('C3-CASH');

-- Comptes MOBILE_BANKING
INSERT INTO financial_account (id, type, amount, collectivity_id) VALUES
                                                                      ('C1-MOBILE', 'MOBILE_BANKING', 0, 'col-1'),
                                                                      ('C2-MOBILE', 'MOBILE_BANKING', 0, 'col-2');

INSERT INTO mobile_banking_account (id, holder_name, mobile_service, mobile_number) VALUES
                                                                                        ('C1-MOBILE', 'Mpanorina', 'ORANGE_MONEY', '0370489612'),
                                                                                        ('C2-MOBILE', 'Dobo voalohany', 'ORANGE_MONEY', '0320489612');

-- =====================================================
-- 10. INSÉRER LES COTISATIONS
-- =====================================================
INSERT INTO membership_fee (id, collectivity_id, eligible_from, frequency, amount, label, status) VALUES
                                                                                                      ('FEE-1', 'col-1', '2026-01-01', 'ANNUALLY', 100000, 'Cotisation annuelle', 'ACTIVE'),
                                                                                                      ('FEE-2', 'col-2', '2026-01-01', 'ANNUALLY', 100000, 'Cotisation annuelle', 'ACTIVE'),
                                                                                                      ('FEE-3', 'col-3', '2026-01-01', 'ANNUALLY', 50000, 'Cotisation annuelle', 'ACTIVE');

-- =====================================================
-- 11. INSÉRER LES TRANSACTIONS
-- =====================================================

-- Transactions Collectivité 1
INSERT INTO transaction (id, member_id, collectivity_id, amount, payment_mode, account_credited_id, membership_fee_id, creation_date) VALUES
                                                                                                                                          ('T1-1', 'C1-M1', 'col-1', 100000, 'CASH', 'C1-CASH', 'FEE-1', '2026-01-01'),
                                                                                                                                          ('T1-2', 'C1-M2', 'col-1', 100000, 'CASH', 'C1-CASH', 'FEE-1', '2026-01-01'),
                                                                                                                                          ('T1-3', 'C1-M3', 'col-1', 100000, 'CASH', 'C1-CASH', 'FEE-1', '2026-01-01'),
                                                                                                                                          ('T1-4', 'C1-M4', 'col-1', 100000, 'CASH', 'C1-CASH', 'FEE-1', '2026-01-01'),
                                                                                                                                          ('T1-5', 'C1-M5', 'col-1', 100000, 'CASH', 'C1-CASH', 'FEE-1', '2026-01-01'),
                                                                                                                                          ('T1-6', 'C1-M6', 'col-1', 100000, 'CASH', 'C1-CASH', 'FEE-1', '2026-01-01'),
                                                                                                                                          ('T1-7', 'C1-M7', 'col-1', 60000, 'CASH', 'C1-CASH', 'FEE-1', '2026-01-01'),
                                                                                                                                          ('T1-8', 'C1-M8', 'col-1', 90000, 'CASH', 'C1-CASH', 'FEE-1', '2026-01-01');

-- Mettre à jour le solde
UPDATE financial_account SET amount = 750000 WHERE id = 'C1-CASH';

-- Transactions Collectivité 2
INSERT INTO transaction (id, member_id, collectivity_id, amount, payment_mode, account_credited_id, membership_fee_id, creation_date) VALUES
                                                                                                                                          ('T2-1', 'C2-M1', 'col-2', 100000, 'CASH', 'C2-CASH', 'FEE-2', '2026-01-01'),
                                                                                                                                          ('T2-2', 'C2-M2', 'col-2', 100000, 'CASH', 'C2-CASH', 'FEE-2', '2026-01-01'),
                                                                                                                                          ('T2-3', 'C2-M3', 'col-2', 100000, 'CASH', 'C2-CASH', 'FEE-2', '2026-01-01'),
                                                                                                                                          ('T2-4', 'C2-M4', 'col-2', 100000, 'CASH', 'C2-CASH', 'FEE-2', '2026-01-01'),
                                                                                                                                          ('T2-5', 'C2-M5', 'col-2', 100000, 'CASH', 'C2-CASH', 'FEE-2', '2026-01-01'),
                                                                                                                                          ('T2-6', 'C2-M6', 'col-2', 100000, 'CASH', 'C2-CASH', 'FEE-2', '2026-01-01'),
                                                                                                                                          ('T2-7', 'C2-M7', 'col-2', 60000, 'CASH', 'C2-CASH', 'FEE-2', '2026-01-01'),
                                                                                                                                          ('T2-8', 'C2-M8', 'col-2', 40000, 'MOBILE_BANKING', 'C2-MOBILE', 'FEE-2', '2026-01-01');

-- Mettre à jour les soldes
UPDATE financial_account SET amount = 660000 WHERE id = 'C2-CASH';
UPDATE financial_account SET amount = 40000 WHERE id = 'C2-MOBILE';

-- =====================================================
-- 12. VÉRIFICATION FINALE
-- =====================================================
SELECT 'member' as table_name, COUNT(*) FROM member
UNION ALL
SELECT 'collectivity', COUNT(*) FROM collectivity
UNION ALL
SELECT 'membership', COUNT(*) FROM membership
UNION ALL
SELECT 'reference', COUNT(*) FROM reference
UNION ALL
SELECT 'financial_account', COUNT(*) FROM financial_account
UNION ALL
SELECT 'membership_fee', COUNT(*) FROM membership_fee
UNION ALL
SELECT 'transaction', COUNT(*) FROM transaction;










-- Insertion dans la table parente financial_account
INSERT INTO financial_account (id, type, amount, collectivity_id) VALUES
                                                                      ('C3-A-BANK-1', 'BANK', 0, 'col-3'),
                                                                      ('C3-A-BANK-2', 'BANK', 0, 'col-3'),
                                                                      ('C3-A-MOBILE-1', 'MOBILE_BANKING', 0, 'col-3');

-- Insertion dans la table fille bank_account
INSERT INTO bank_account (id, holder_name, bank_name, bank_code, branch_code, account_number, account_key) VALUES
                                                                                                               ('C3-A-BANK-1', 'Koto', 'BMOI', 00004, 00001, 1234567890, 12),
                                                                                                               ('C3-A-BANK-2', 'Naivo', 'BRED', 00008, 00003, 4567890123, 58);

-- Insertion dans la table fille mobile_banking_account
INSERT INTO mobile_banking_account (id, holder_name, mobile_service, mobile_number) VALUES
    ('C3-A-MOBILE-1', 'Kolo', 'MVOLA', '0341889612');


-- Collectivité 1
INSERT INTO membership_fee (id, collectivity_id, label, status, frequency, eligible_from, amount) VALUES
                                                                                                      ('cot-1', 'col-1', 'Cotisation annuelle', 'ACTIVE', 'ANNUALLY', '2026-01-01', 200000),
                                                                                                      ('cot-2', 'col-1', 'Famangiana', 'ACTIVE', 'PUNCTUALLY', '2026-04-30', 20000);

-- Collectivité 2
INSERT INTO membership_fee (id, collectivity_id, label, status, frequency, eligible_from, amount) VALUES
                                                                                                      ('cot-3', 'col-2', 'Cotisation annuelle', 'ACTIVE', 'ANNUALLY', '2026-01-01', 200000),
                                                                                                      ('cot-4', 'col-2', 'Cotisation 2025', 'INACTIVE', 'ANNUALLY', '2025-01-01', 100000);

-- Collectivité 3
INSERT INTO membership_fee (id, collectivity_id, label, status, frequency, eligible_from, amount) VALUES
    ('cot-5', 'col-3', 'Cotisation mensuelle', 'ACTIVE', 'MONTHLY', '2026-04-01', 25000);

-- Collectivité 1 (4 membres)
INSERT INTO member (id, first_name, last_name, birth_date, gender, email, date_adhesion_federation) VALUES
                                                                                                        ('C1-M9', 'Jean', 'Dupont', '1990-05-15', 'MALE', 'jean.dupont@mail.com', '2026-01-01'),
                                                                                                        ('C1-M10', 'Marie', 'Rasoa', '1992-08-20', 'FEMALE', 'marie.rasoa@mail.com', '2026-01-01'),
                                                                                                        ('C1-M11', 'Luc', 'Rakoto', '1985-03-10', 'MALE', 'luc.rakoto@mail.com', '2026-01-01'),
                                                                                                        ('C1-M12', 'Alice', 'Ranaivo', '1995-12-01', 'FEMALE', 'alice.ranaivo@mail.com', '2026-01-01');

INSERT INTO membership (member_id, collectivity_id, occupation, registration_fee_paid, membership_dues_paid, date_adhesion) VALUES
                                                                                                                                ('C1-M9', 'col-1', 'JUNIOR', true, true, '2026-04-01'),
                                                                                                                                ('C1-M10', 'col-1', 'JUNIOR', true, true, '2026-04-01'),
                                                                                                                                ('C1-M11', 'col-1', 'JUNIOR', true, true, '2026-05-01'),
                                                                                                                                ('C1-M12', 'col-1', 'JUNIOR', true, true, '2026-06-01');

-- Collectivité 2 (3 membres)
INSERT INTO member (id, first_name, last_name, birth_date, gender, email, date_adhesion_federation) VALUES
                                                                                                        ('C2-M9', 'Pierre', 'Randria', '1988-02-14', 'MALE', 'pierre.randria@mail.com', '2026-01-01'),
                                                                                                        ('C2-M10', 'Sitraka', 'Andria', '1993-11-25', 'FEMALE', 'sitraka.andria@mail.com', '2026-01-01'),
                                                                                                        ('C2-M11', 'Theo', 'Zaka', '1980-07-07', 'MALE', 'theo.zaka@mail.com', '2026-01-01');

INSERT INTO membership (member_id, collectivity_id, occupation, registration_fee_paid, membership_dues_paid, date_adhesion) VALUES
                                                                                                                                ('C2-M9', 'col-2', 'JUNIOR', true, true, '2026-03-01'),
                                                                                                                                ('C2-M10', 'col-2', 'JUNIOR', true, true, '2026-03-01'),
                                                                                                                                ('C2-M11', 'col-2', 'JUNIOR', true, true, '2026-03-01');
-- Basé sur le Tableau 15 (Collectivité 1)
INSERT INTO transaction (id, member_id, collectivity_id, amount, payment_mode, account_credited_id, creation_date) VALUES
                                                                                                                       ('TX-001', 'C1-M1', 'col-1', 200000, 'CASH', 'C1-A-CASH', '2026-01-01'),
                                                                                                                       ('TX-002', 'C1-M2', 'col-1', 200000, 'CASH', 'C1-A-CASH', '2026-01-01'),
                                                                                                                       ('TX-003', 'C1-M3', 'col-1', 200000, 'MOBILE_BANKING', 'C1-A-MOBILE-1', '2026-01-01'),
                                                                                                                       ('TX-004', 'C1-M4', 'col-1', 200000, 'MOBILE_BANKING', 'C1-A-MOBILE-1', '2026-01-01'),
                                                                                                                       ('TX-005', 'C1-M5', 'col-1', 150000, 'MOBILE_BANKING', 'C1-A-MOBILE-1', '2026-01-01');

-- Basé sur le Tableau 17 (Collectivité 3 - Bank Transfers)
INSERT INTO transaction (id, member_id, collectivity_id, amount, payment_mode, account_credited_id, creation_date) VALUES
                                                                                                                       ('TX-101', 'C3-M1', 'col-3', 25000, 'BANK_TRANSFER', 'C3-A-BANK-1', '2026-04-01'),
                                                                                                                       ('TX-102', 'C3-M5', 'col-3', 25000, 'BANK_TRANSFER', 'C3-A-BANK-2', '2026-04-01'),
                                                                                                                       ('TX-103', 'C3-M7', 'col-3', 25000, 'CASH', 'C3-A-CASH', '2026-04-01');

