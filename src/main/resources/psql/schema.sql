CREATE TABLE IF NOT EXISTS collectivity (
                                            id SERIAL PRIMARY KEY,
                                            number INT UNIQUE,
                                            name VARCHAR(255) UNIQUE,
    city VARCHAR(120) NOT NULL,
    specialty VARCHAR(120) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    opening_authorized BOOLEAN NOT NULL DEFAULT FALSE,
    current_balance NUMERIC(18,2) NOT NULL DEFAULT 0,
    currency VARCHAR(3) NOT NULL DEFAULT 'MGA'
    );

CREATE TABLE IF NOT EXISTS member (
                                      id SERIAL PRIMARY KEY,
                                      collectivity_id INT,
                                      first_name VARCHAR(120) NOT NULL,
    last_name VARCHAR(120) NOT NULL,
    birth_date DATE NOT NULL,
    gender VARCHAR(10) NOT NULL,
    address VARCHAR(255) NOT NULL,
    profession VARCHAR(120) NOT NULL,
    phone VARCHAR(30) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    joined_at DATE NOT NULL DEFAULT CURRENT_DATE,
    resigned_at DATE,
    status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
    CONSTRAINT fk_member_collectivity FOREIGN KEY (collectivity_id) REFERENCES collectivity(id)
    );

CREATE TABLE IF NOT EXISTS sponsor (
                                       id SERIAL PRIMARY KEY,
                                       candidate_member_id INT NOT NULL,
                                       sponsor_member_id INT NOT NULL,
                                       relationship VARCHAR(80) NOT NULL,
    sponsored_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_sponsor_candidate FOREIGN KEY (candidate_member_id) REFERENCES member(id),
    CONSTRAINT fk_sponsor_sponsor FOREIGN KEY (sponsor_member_id) REFERENCES member(id),
    CONSTRAINT uq_candidate_sponsor UNIQUE (candidate_member_id, sponsor_member_id)
    );

CREATE TABLE IF NOT EXISTS mandate (
                                       id SERIAL PRIMARY KEY,
                                       collectivity_id INT,
                                       role VARCHAR(30) NOT NULL,
    member_id INT NOT NULL,
    start_year INT NOT NULL,
    end_year INT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_mandate_collectivity FOREIGN KEY (collectivity_id) REFERENCES collectivity(id),
    CONSTRAINT fk_mandate_member FOREIGN KEY (member_id) REFERENCES member(id)
    );