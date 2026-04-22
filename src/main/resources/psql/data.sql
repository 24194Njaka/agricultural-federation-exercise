-- Initial data for testing
INSERT INTO collectivity (id, name, location, specialty, federation_approval)
VALUES ('COLL-001', 'Analamanga Agri', 'Antananarivo', 'Rice', TRUE);

-- Inserting a confirmed member to act as a referee
INSERT INTO member (id, first_name, last_name, occupation, collectivity_id, registration_fee_paid)
VALUES ('MEM-001', 'Jean', 'RAKOTO', 'SENIOR', 'COLL-001', TRUE);