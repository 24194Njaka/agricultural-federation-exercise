INSERT INTO members (first_name, last_name, birth_date, gender, address, profession, phone, email, membership_date, role, collectivity_id) VALUES
                                                                                                                                               ('Prénom membre 1', 'Nom membre 1', '1980-02-01', 'MALE', 'Lot II V M Ambato.', 'Riziculteur', '0341234567', 'member.1@fed-agri.mg', '2026-01-01', 'PRESIDENT', 1),
                                                                                                                                               ('Prénom membre 2', 'Nom membre 2', '1982-03-05', 'MALE', 'Lot II F Ambato.', 'Agriculteur', '0321234567', 'member.2@fed-agri.mg', '2026-01-01', 'VICE_PRESIDENT', 1),
                                                                                                                                               ('Prénom membre 3', 'Nom membre 3', '1992-03-10', 'MALE', 'Lot II J Ambato.', 'Collecteur', '0331234567', 'member.3@fed-agrimg', '2026-01-01', 'SECRETARY', 1),
                                                                                                                                               ('Prénom membre 4', 'Nom membre 4', '1988-05-22', 'FEMALE', 'Lot A K 50 Ambato.', 'Distributeur', '0381234567', 'member.4@fed-agri.mg', '2026-01-01', 'TREASURER', 1),
                                                                                                                                               ('Prénom membre 5', 'Nom membre 5', '1999-08-21', 'MALE', 'Lot UV 80 Ambato.', 'Riziculteur', '0373434567', 'member.5@fed-agri.mg', '2026-01-01', 'CONFIRMED_MEMBER', 1);

INSERT INTO accounts (entity_type, entity_id, account_type, account_name, account_holder_name, mobile_money_service, phone_number, balance) VALUES
                                                                                                                                                ('COLLECTIVITY', 1, 'CASH', 'Caisse Cash C1', 'Mpanorina', NULL, NULL, 0.00),
                                                                                                                                                ('COLLECTIVITY', 1, 'MOBILE_MONEY', 'Orange Money C1', 'Mpanorina', 'ORANGE_MONEY', '0370489612', 0.00),
                                                                                                                                                ('COLLECTIVITY', 2, 'CASH', 'Caisse Cash C2', 'Dobo voalohany', NULL, NULL, 0.00),
                                                                                                                                                ('COLLECTIVITY', 2, 'MOBILE_MONEY', 'Orange Money C2', 'Dobo voalohany', 'ORANGE_MONEY', '0320489612', 0.00);

INSERT INTO membership_fees (collectivity_id, name, amount, frequency, start_date) VALUES
                                                                                       (1, 'Cotisation annuelle', 100000, 'ANNUAL', '2026-01-01'),
                                                                                       (2, 'Cotisation annuelle', 100000, 'ANNUAL', '2026-01-01'),
                                                                                       (3, 'Cotisation annuelle', 50000, 'ANNUAL', '2026-01-01');

INSERT INTO sponsorships (member_id, sponsor_id, relationship, sponsorship_date) VALUES
                                                                                     (3, 1, 'REFERRAL', '2026-01-01'),
                                                                                     (3, 2, 'REFERRAL', '2026-01-01'),
                                                                                     (8, 6, 'REFERRAL', '2026-01-01'),
                                                                                     (8, 7, 'REFERRAL', '2026-01-01');


INSERT INTO collectivities (number, name, location, creation_date, status) VALUES
                                                                               ('1', 'Mpanorina', 'Ambatondrazaka', '2026-01-01', 'ACTIVE'),
                                                                               ('2', 'Dobo voalohany', 'Ambatondrazaka', '2026-01-01', 'ACTIVE'),
                                                                               ('3', 'Tantely mamy', 'Brickaville', '2026-01-01', 'ACTIVE');
