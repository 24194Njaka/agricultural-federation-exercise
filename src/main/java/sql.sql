-- =====================================================
-- 1. SUPPRIMER LES ANCIENNES DONNÉES
-- =====================================================
DROP TABLE IF EXISTS transaction CASCADE;
DROP TABLE IF EXISTS sponsorship CASCADE;
DROP TABLE IF EXISTS membership_fee CASCADE;
DROP TABLE IF EXISTS account CASCADE;
DROP TABLE IF EXISTS member CASCADE;
DROP TABLE IF EXISTS collectivity CASCADE;

DROP TYPE IF EXISTS member_role CASCADE;
DROP TYPE IF EXISTS type_gender CASCADE;
DROP TYPE IF EXISTS type_status CASCADE;
DROP TYPE IF EXISTS frequency CASCADE;
DROP TYPE IF EXISTS payment_method CASCADE;

-- =====================================================
-- 2. CRÉER LES TYPES ENUM
-- =====================================================
CREATE TYPE member_role AS ENUM ('PRESIDENT', 'VICE_PRESIDENT', 'TREASURER', 'SECRETARY', 'CONFIRMED_MEMBER', 'JUNIOR_MEMBER');
CREATE TYPE type_gender AS ENUM ('MALE', 'FEMALE');
CREATE TYPE type_status AS ENUM ('PENDING', 'APPROVED', 'REJECTED', 'ACTIVE', 'INACTIVE');
CREATE TYPE frequency AS ENUM ('WEEKLY', 'MONTHLY', 'ANNUALLY', 'PUNCTUALLY');
CREATE TYPE payment_method AS ENUM ('CASH', 'MOBILE_MONEY', 'BANK_TRANSFER');

-- =====================================================
-- 3. CRÉER LES TABLES
-- =====================================================

-- Table collectivity
CREATE TABLE collectivity (
                              id VARCHAR(50) PRIMARY KEY,
                              number VARCHAR(50) UNIQUE,
                              name VARCHAR(255) UNIQUE,
                              location VARCHAR(255),
                              specialisation VARCHAR(100),
                              creation_date DATE,
                              status type_status DEFAULT 'PENDING',
                              authorization_date DATE,
                              created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                              updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Table member
CREATE TABLE member (
                        id VARCHAR(50) PRIMARY KEY,
                        first_name VARCHAR(255),
                        last_name VARCHAR(255),
                        birth_date DATE,
                        gender type_gender,
                        address TEXT,
                        profession VARCHAR(100),
                        phone_number VARCHAR(20),
                        email VARCHAR(255) UNIQUE,
                        membership_date DATE,
                        role member_role DEFAULT 'JUNIOR_MEMBER',
                        collectivity_id VARCHAR(50) REFERENCES collectivity(id) ON DELETE SET NULL,
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Table sponsorship
CREATE TABLE sponsorship (
                             id VARCHAR(50) PRIMARY KEY,
                             member_id VARCHAR(50) REFERENCES member(id) ON DELETE CASCADE,
                             sponsor_id VARCHAR(50) REFERENCES member(id) ON DELETE CASCADE,
                             relationship VARCHAR(100),
                             sponsorship_date DATE,
                             created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Table account
CREATE TABLE account (
                         id VARCHAR(50) PRIMARY KEY,
                         entity_id VARCHAR(50),
                         entity_type VARCHAR(20),
                         account_type VARCHAR(50),
                         account_name VARCHAR(255),
                         account_holder_name VARCHAR(255),
                         bank_name VARCHAR(100),
                         mobile_money_service VARCHAR(100),
                         phone_number VARCHAR(20),
                         balance DOUBLE PRECISION DEFAULT 0,
                         currency VARCHAR(10) DEFAULT 'MGA',
                         created_at DATE,
                         updated_at DATE
);

-- Table membership_fee
CREATE TABLE membership_fee (
                                id VARCHAR(50) PRIMARY KEY,
                                collectivity_id VARCHAR(50) REFERENCES collectivity(id) ON DELETE CASCADE,
                                label VARCHAR(255) NOT NULL,
                                amount DOUBLE PRECISION NOT NULL,
                                frequency frequency NOT NULL,
                                status VARCHAR(20) DEFAULT 'ACTIVE',
                                start_date DATE,
                                end_date DATE,
                                description TEXT,
                                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Table transaction
CREATE TABLE transaction (
                             id VARCHAR(50) PRIMARY KEY,
                             account_id VARCHAR(50) REFERENCES account(id) ON DELETE CASCADE,
                             member_id VARCHAR(50) REFERENCES member(id) ON DELETE CASCADE,
                             transaction_type VARCHAR(20),
                             amount DOUBLE PRECISION,
                             payment_method payment_method,
                             transaction_date DATE,
                             label VARCHAR(255),
                             created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- =====================================================
-- 4. INSÉRER LES COLLECTIVITÉS
-- =====================================================
INSERT INTO collectivity (id, number, name, location, specialisation, creation_date, status) VALUES
                                                                                                 ('col-1', '1', 'Mpanorina', 'Ambatondrazaka', 'Riziculture', '2026-01-01', 'APPROVED'),
                                                                                                 ('col-2', '2', 'Dobo voalohany', 'Ambatondrazaka', 'Pisciculture', '2026-01-01', 'APPROVED'),
                                                                                                 ('col-3', '3', 'Tantely mamy', 'Brickaville', 'Apiculture', '2026-01-01', 'APPROVED');

-- =====================================================
-- 5. INSÉRER LES MEMBRES
-- =====================================================

-- Membres Collectivité 1 (8 membres)
INSERT INTO member (id, first_name, last_name, birth_date, gender, address, profession, phone_number, email, role, collectivity_id, membership_date) VALUES
                                                                                                                                                         ('C1-M1', 'Jean', 'Rakoto', '1980-02-01', 'MALE', 'Lot II V M Ambato.', 'Riziculteur', '0341234567', 'member.1@fed-agri.mg', 'PRESIDENT', 'col-1', '2025-10-01'),
                                                                                                                                                         ('C1-M2', 'Marie', 'Rasoa', '1982-03-05', 'FEMALE', 'Lot II F Ambato.', 'Agriculteur', '0321234567', 'member.2@fed-agri.mg', 'VICE_PRESIDENT', 'col-1', '2025-10-01'),
                                                                                                                                                         ('C1-M3', 'Pierre', 'Andrian', '1992-03-10', 'MALE', 'Lot II J Ambato.', 'Collecteur', '0331234567', 'member.3@fed-agri.mg', 'SECRETARY', 'col-1', '2025-10-01'),
                                                                                                                                                         ('C1-M4', 'Sophie', 'Rabe', '1988-05-22', 'FEMALE', 'Lot A K 50 Ambato.', 'Distributeur', '0381234567', 'member.4@fed-agri.mg', 'TREASURER', 'col-1', '2025-10-01'),
                                                                                                                                                         ('C1-M5', 'Paul', 'Ranaivo', '1999-08-21', 'MALE', 'Lot UV 80 Ambato.', 'Riziculteur', '0373434567', 'member.5@fed-agri.mg', 'CONFIRMED_MEMBER', 'col-1', '2025-10-01'),
                                                                                                                                                         ('C1-M6', 'Claire', 'Razafy', '1998-08-22', 'FEMALE', 'Lot UV 6 Ambato.', 'Riziculteur', '0372234567', 'member.6@fed-agri.mg', 'CONFIRMED_MEMBER', 'col-1', '2025-10-01'),
                                                                                                                                                         ('C1-M7', 'Thomas', 'Rakotomalala', '1998-01-31', 'MALE', 'Lot UV 7 Ambato.', 'Riziculteur', '0374234567', 'member.7@fed-agri.mg', 'CONFIRMED_MEMBER', 'col-1', '2025-10-01'),
                                                                                                                                                         ('C1-M8', 'Lala', 'Ravoson', '1975-08-20', 'FEMALE', 'Lot UV 8 Ambato.', 'Riziculteur', '0370234567', 'member.8@fed-agri.mg', 'CONFIRMED_MEMBER', 'col-1', '2025-10-01');

-- Membres Collectivité 2 (4 membres)
INSERT INTO member (id, first_name, last_name, birth_date, gender, address, profession, phone_number, email, role, collectivity_id, membership_date) VALUES
                                                                                                                                                         ('C2-M1', 'Mampionona', 'Rakoto', '1985-01-15', 'MALE', 'Lot 10 Ambatondrazaka', 'Pisciculteur', '0341234501', 'member.col2.1@fed-agri.mg', 'PRESIDENT', 'col-2', '2025-10-01'),
                                                                                                                                                         ('C2-M2', 'Hanta', 'Rasoa', '1987-02-20', 'FEMALE', 'Lot 11 Ambatondrazaka', 'Pisciculteur', '0341234502', 'member.col2.2@fed-agri.mg', 'VICE_PRESIDENT', 'col-2', '2025-10-01'),
                                                                                                                                                         ('C2-M3', 'Rado', 'Andrian', '1990-06-15', 'MALE', 'Lot 12 Ambatondrazaka', 'Pisciculteur', '0341234503', 'member.col2.3@fed-agri.mg', 'SECRETARY', 'col-2', '2025-10-01'),
                                                                                                                                                         ('C2-M4', 'Tiana', 'Rabe', '1988-07-20', 'FEMALE', 'Lot 13 Ambatondrazaka', 'Pisciculteur', '0341234504', 'member.col2.4@fed-agri.mg', 'TREASURER', 'col-2', '2025-10-01'),
                                                                                                                                                         ('C2-M5', 'Faneva', 'Ranaivo', '1992-08-15', 'MALE', 'Lot 14 Ambatondrazaka', 'Pisciculteur', '0341234505', 'member.col2.5@fed-agri.mg', 'CONFIRMED_MEMBER', 'col-2', '2025-10-01'),
                                                                                                                                                         ('C2-M6', 'Voahangy', 'Razafy', '1995-09-20', 'FEMALE', 'Lot 15 Ambatondrazaka', 'Pisciculteur', '0341234506', 'member.col2.6@fed-agri.mg', 'CONFIRMED_MEMBER', 'col-2', '2025-10-01'),
                                                                                                                                                         ('C2-M7', 'Andry', 'Rakotomalala', '1990-08-15', 'MALE', 'Lot 16 Ambatondrazaka', 'Pisciculteur', '0341234507', 'member.col2.7@fed-agri.mg', 'CONFIRMED_MEMBER', 'col-2', '2025-10-01'),
                                                                                                                                                         ('C2-M8', 'Vololona', 'Razafy', '1992-09-20', 'FEMALE', 'Lot 17 Ambatondrazaka', 'Pisciculteur', '0341234508', 'member.col2.8@fed-agri.mg', 'CONFIRMED_MEMBER', 'col-2', '2025-10-01');

-- Membres Collectivité 3 (8 membres)
INSERT INTO member (id, first_name, last_name, birth_date, gender, address, profession, phone_number, email, role, collectivity_id, membership_date) VALUES
                                                                                                                                                         ('C3-M1', 'Hery', 'Rakotovao', '1988-01-02', 'MALE', 'Lot 33 J Antsirabe', 'Apiculteur', '0340345678', 'member.9@fed-agri.mg', 'PRESIDENT', 'col-3', '2025-10-01'),
                                                                                                                                                         ('C3-M2', 'Mamy', 'Randria', '1982-05-03', 'FEMALE', 'Lot 2 J Antsirabe', 'Agriculteur', '0338634567', 'member.10@fed-agri.mg', 'VICE_PRESIDENT', 'col-3', '2025-10-01'),
                                                                                                                                                         ('C3-M3', 'Tovo', 'Rakoto', '1990-06-15', 'MALE', 'Lot 45 J Antsirabe', 'Apiculteur', '0341234568', 'member.11@fed-agri.mg', 'SECRETARY', 'col-3', '2025-10-01'),
                                                                                                                                                         ('C3-M4', 'Nomena', 'Rasoa', '1985-07-20', 'FEMALE', 'Lot 67 J Antsirabe', 'Comptable', '0337654322', 'member.12@fed-agri.mg', 'TREASURER', 'col-3', '2025-10-01'),
                                                                                                                                                         ('C3-M5', 'Faneva', 'Andrian', '1992-09-10', 'MALE', 'Lot 89 J Antsirabe', 'Apiculteur', '0341122335', 'member.13@fed-agri.mg', 'CONFIRMED_MEMBER', 'col-3', '2025-10-01'),
                                                                                                                                                         ('C3-M6', 'Voahangy', 'Rabe', '1988-11-25', 'FEMALE', 'Lot 12 J Antsirabe', 'Distributeur', '0388776656', 'member.14@fed-agri.mg', 'CONFIRMED_MEMBER', 'col-3', '2025-10-01'),
                                                                                                                                                         ('C3-M7', 'Tiana', 'Ranaivo', '1995-02-18', 'MALE', 'Lot 34 J Antsirabe', 'Apiculteur', '0345566779', 'member.15@fed-agri.mg', 'JUNIOR_MEMBER', 'col-3', '2025-10-01'),
                                                                                                                                                         ('C3-M8', 'Miora', 'Razafy', '1996-12-03', 'FEMALE', 'Lot 56 J Antsirabe', 'Apiculteur', '0388990012', 'member.16@fed-agri.mg', 'JUNIOR_MEMBER', 'col-3', '2025-10-01');

-- =====================================================
-- 6. INSÉRER LES PARRAINAGES
-- =====================================================
INSERT INTO sponsorship (id, member_id, sponsor_id, relationship, sponsorship_date) VALUES
                                                                                        ('SP-1', 'C1-M3', 'C1-M1', 'FAMILY', '2026-01-01'),
                                                                                        ('SP-2', 'C1-M3', 'C1-M2', 'FRIEND', '2026-01-01'),
                                                                                        ('SP-3', 'C3-M3', 'C3-M1', 'COLLEAGUE', '2026-01-01'),
                                                                                        ('SP-4', 'C3-M3', 'C3-M2', 'REFERRAL', '2026-01-01');

-- =====================================================
-- 7. INSÉRER LES COMPTES
-- =====================================================
INSERT INTO account (id, entity_id, entity_type, account_type, account_name, account_holder_name, phone_number, balance, created_at) VALUES
                                                                                                                                         ('C1-A-CASH', 'col-1', 'COLLECTIVITY', 'CASH', 'Caisse Mpanorina', NULL, NULL, 0, '2026-01-01'),
                                                                                                                                         ('C1-A-MOBILE-1', 'col-1', 'COLLECTIVITY', 'MOBILE_BANKING', 'Orange Money Mpanorina', 'Mpanorina', '0370489612', 0, '2026-01-01'),
                                                                                                                                         ('C2-A-CASH', 'col-2', 'COLLECTIVITY', 'CASH', 'Caisse Dobo voalohany', NULL, NULL, 0, '2026-01-01'),
                                                                                                                                         ('C2-A-MOBILE-1', 'col-2', 'COLLECTIVITY', 'MOBILE_BANKING', 'Orange Money Dobo', 'Dobo voalohany', '0320489612', 0, '2026-01-01'),
                                                                                                                                         ('C3-A-CASH', 'col-3', 'COLLECTIVITY', 'CASH', 'Caisse Tantely mamy', NULL, NULL, 0, '2026-01-01');

-- =====================================================
-- 8. INSÉRER LES COTISATIONS
-- =====================================================
INSERT INTO membership_fee (id, collectivity_id, label, amount, frequency, status, start_date, end_date) VALUES
                                                                                                             ('cot-1', 'col-1', 'Cotisation annuelle', 100000, 'ANNUALLY', 'ACTIVE', '2026-01-01', '2026-12-31'),
                                                                                                             ('cot-2', 'col-2', 'Cotisation annuelle', 100000, 'ANNUALLY', 'ACTIVE', '2026-01-01', '2026-12-31'),
                                                                                                             ('cot-3', 'col-3', 'Cotisation annuelle', 50000, 'ANNUALLY', 'ACTIVE', '2026-01-01', '2026-12-31');

-- =====================================================
-- 9. INSÉRER LES TRANSACTIONS
-- =====================================================

-- Transactions pour Collectivité 1
INSERT INTO transaction (id, account_id, member_id, transaction_type, amount, payment_method, transaction_date, label) VALUES
                                                                                                                           ('T1-1', 'C1-A-CASH', 'C1-M1', 'CONTRIBUTION', 100000, 'CASH', '2026-01-01', 'Cotisation annuelle'),
                                                                                                                           ('T1-2', 'C1-A-CASH', 'C1-M2', 'CONTRIBUTION', 100000, 'CASH', '2026-01-01', 'Cotisation annuelle'),
                                                                                                                           ('T1-3', 'C1-A-CASH', 'C1-M3', 'CONTRIBUTION', 100000, 'CASH', '2026-01-01', 'Cotisation annuelle'),
                                                                                                                           ('T1-4', 'C1-A-CASH', 'C1-M4', 'CONTRIBUTION', 100000, 'CASH', '2026-01-01', 'Cotisation annuelle'),
                                                                                                                           ('T1-5', 'C1-A-CASH', 'C1-M5', 'CONTRIBUTION', 100000, 'CASH', '2026-01-01', 'Cotisation annuelle'),
                                                                                                                           ('T1-6', 'C1-A-CASH', 'C1-M6', 'CONTRIBUTION', 100000, 'CASH', '2026-01-01', 'Cotisation annuelle'),
                                                                                                                           ('T1-7', 'C1-A-CASH', 'C1-M7', 'CONTRIBUTION', 60000, 'CASH', '2026-01-01', 'Cotisation partielle'),
                                                                                                                           ('T1-8', 'C1-A-CASH', 'C1-M8', 'CONTRIBUTION', 90000, 'CASH', '2026-01-01', 'Cotisation partielle');

-- Transactions pour Collectivité 2
INSERT INTO transaction (id, account_id, member_id, transaction_type, amount, payment_method, transaction_date, label) VALUES
                                                                                                                           ('T2-1', 'C2-A-CASH', 'C2-M1', 'CONTRIBUTION', 60000, 'CASH', '2026-01-01', 'Cotisation partielle'),
                                                                                                                           ('T2-2', 'C2-A-CASH', 'C2-M2', 'CONTRIBUTION', 90000, 'CASH', '2026-01-01', 'Cotisation'),
                                                                                                                           ('T2-3', 'C2-A-CASH', 'C2-M3', 'CONTRIBUTION', 100000, 'CASH', '2026-01-01', 'Cotisation'),
                                                                                                                           ('T2-4', 'C2-A-CASH', 'C2-M4', 'CONTRIBUTION', 100000, 'CASH', '2026-01-01', 'Cotisation'),
                                                                                                                           ('T2-5', 'C2-A-CASH', 'C2-M5', 'CONTRIBUTION', 100000, 'CASH', '2026-01-01', 'Cotisation'),
                                                                                                                           ('T2-6', 'C2-A-CASH', 'C2-M6', 'CONTRIBUTION', 100000, 'CASH', '2026-01-01', 'Cotisation'),
                                                                                                                           ('T2-7', 'C2-A-MOBILE-1', 'C2-M7', 'CONTRIBUTION', 40000, 'MOBILE_MONEY', '2026-01-01', 'Cotisation partielle'),
                                                                                                                           ('T2-8', 'C2-A-MOBILE-1', 'C2-M8', 'CONTRIBUTION', 60000, 'MOBILE_MONEY', '2026-01-01', 'Cotisation partielle');

-- =====================================================
-- 10. METTRE À JOUR LES SOLDES DES COMPTES
-- =====================================================
UPDATE account SET balance = (
    SELECT COALESCE(SUM(CASE WHEN t.transaction_type = 'CONTRIBUTION' THEN t.amount ELSE -t.amount END), 0)
    FROM transaction t
    WHERE t.account_id = account.id
);

-- =====================================================
-- 11. VÉRIFICATION FINALE
-- =====================================================
SELECT 'collectivity' as table_name, COUNT(*) FROM collectivity
UNION ALL
SELECT 'member', COUNT(*) FROM member
UNION ALL
SELECT 'sponsorship', COUNT(*) FROM sponsorship
UNION ALL
SELECT 'account', COUNT(*) FROM account
UNION ALL
SELECT 'membership_fee', COUNT(*) FROM membership_fee
UNION ALL
SELECT 'transaction', COUNT(*) FROM transaction;