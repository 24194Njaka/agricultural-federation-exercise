INSERT INTO collectivity (id, number, name, city, specialty, created_at, opening_authorized, current_balance, currency) VALUES
                                                                                                                            (1, 1001, 'Green Harvest', 'Antananarivo', 'Rice', CURRENT_TIMESTAMP, TRUE, 0, 'MGA'),
                                                                                                                            (2, 1002, 'Red Soil Union', 'Toamasina', 'Vanilla', CURRENT_TIMESTAMP, TRUE, 0, 'MGA');

INSERT INTO member (id, collectivity_id, first_name, last_name, birth_date, gender, address, profession, phone, email, joined_at, resigned_at, status) VALUES
                                                                                                                                                           (1, 1, 'Jean', 'Rakoto', '1990-05-10', 'MALE', 'Antananarivo', 'Farmer', '0340000001', 'jean.rakoto@example.com', '2025-01-10', NULL, 'ACTIVE'),
                                                                                                                                                           (2, 1, 'Marie', 'Rasoa', '1992-08-15', 'FEMALE', 'Antananarivo', 'Farmer', '0340000002', 'marie.rasoa@example.com', '2025-02-12', NULL, 'ACTIVE'),
                                                                                                                                                           (3, 2, 'Paul', 'Andry', '1988-11-20', 'MALE', 'Toamasina', 'Agronomist', '0340000003', 'paul.andry@example.com', '2025-03-01', NULL, 'ACTIVE');

INSERT INTO sponsor (candidate_member_id, sponsor_member_id, relationship, sponsored_at) VALUES
                                                                                             (2, 1, 'Colleague', CURRENT_TIMESTAMP),
                                                                                             (3, 1, 'Friend', CURRENT_TIMESTAMP);

INSERT INTO mandate (collectivity_id, role, member_id, start_year, end_year, created_at) VALUES
                                                                                             (1, 'PRESIDENT', 1, 2025, 2025, CURRENT_TIMESTAMP),
                                                                                             (1, 'SECRETARY', 2, 2025, 2025, CURRENT_TIMESTAMP),
                                                                                             (2, 'TREASURER', 3, 2025, 2025, CURRENT_TIMESTAMP);

INSERT INTO contribution (collectivity_id, member_id, contribution_type, amount, payment_method, payment_date, is_periodic) VALUES
                                                                                                                                (1, 1, 'ANNUAL', 200000, 'BANK_TRANSFER', CURRENT_TIMESTAMP, TRUE),
                                                                                                                                (1, 2, 'MONTHLY', 20000, 'MOBILE_MONEY', CURRENT_TIMESTAMP, TRUE),
                                                                                                                                (2, 3, 'ONE_TIME', 50000, 'CASH', CURRENT_TIMESTAMP, FALSE);

INSERT INTO account (owner_type, owner_id, account_type, holder_name, bank_name, rib_number, mobile_money_provider, phone_number, balance, currency, created_at) VALUES
                                                                                                                                                                     ('COLLECTIVITY', 1, 'CASH', 'Green Harvest', NULL, NULL, NULL, NULL, 100000, 'MGA', CURRENT_TIMESTAMP),
                                                                                                                                                                     ('COLLECTIVITY', 1, 'BANK', 'Green Harvest', 'BRED', '123450123450123450123