-- Clean up
DROP TABLE IF EXISTS member_referee;
DROP TABLE IF EXISTS member;
DROP TABLE IF EXISTS collectivity;

CREATE TABLE collectivity (
                              id VARCHAR(50) PRIMARY KEY, -- Unique number [cite: 12]
                              name VARCHAR(100) UNIQUE NOT NULL, -- Unique name [cite: 12]
                              location VARCHAR(100) NOT NULL, -- City [cite: 11]
                              specialty VARCHAR(100) NOT NULL, -- Agricultural specialty [cite: 12]
                              creation_date DATE DEFAULT CURRENT_DATE, -- [cite: 13]
                              federation_approval BOOLEAN DEFAULT FALSE -- [cite: 28]
);

CREATE TABLE member (
                        id VARCHAR(50) PRIMARY KEY,
                        first_name VARCHAR(100) NOT NULL, -- [cite: 15]
                        last_name VARCHAR(100) NOT NULL, -- [cite: 15]
                        birth_date DATE, -- [cite: 15]
                        gender VARCHAR(10), -- MALE or FEMALE [cite: 15]
                        address TEXT, -- [cite: 15]
                        profession VARCHAR(100), -- [cite: 15]
                        phone_number VARCHAR(20), -- [cite: 15]
                        email VARCHAR(100) UNIQUE, -- [cite: 15]
                        occupation VARCHAR(50), -- JUNIOR, SENIOR, PRESIDENT, etc. [cite: 16]
                        collectivity_id VARCHAR(50) REFERENCES collectivity(id),
                        registration_fee_paid BOOLEAN DEFAULT FALSE, -- 50,000 MGA [cite: 33, 89]
                        membership_dues_paid BOOLEAN DEFAULT FALSE, -- Annual dues (e.g. 200,000 MGA) [cite: 89, 90]
                        joining_date DATE DEFAULT CURRENT_DATE -- [cite: 15]
);


CREATE TABLE member_referee (
                                candidate_id VARCHAR(50) REFERENCES member(id),
                                referee_id VARCHAR(50) REFERENCES member(id),
                                relationship VARCHAR(50), -- family, friends, colleagues, etc.
                                PRIMARY KEY (candidate_id, referee_id)
);