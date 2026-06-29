CREATE TABLE users (
                       id BIGSERIAL PRIMARY KEY,

                       first_name VARCHAR(255) NOT NULL,
                       last_name VARCHAR(255) NOT NULL,

                       username VARCHAR(64) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,

                       email VARCHAR(255),
                       phone_number VARCHAR(255),

                       created_at TIMESTAMP NOT NULL,
                       updated_at TIMESTAMP,

                       created_by VARCHAR(255),
                       updated_by VARCHAR(255)
);

CREATE TABLE homes (
                       id BIGSERIAL PRIMARY KEY,

                       host_id BIGINT NOT NULL,

                       price_per_night BIGINT NOT NULL,

                       address VARCHAR(1000) NOT NULL,

                       reserve_state VARCHAR(50) NOT NULL DEFAULT 'READY_TO_RESERVED',

                       created_at TIMESTAMP NOT NULL,
                       updated_at TIMESTAMP,

                       created_by VARCHAR(255),
                       updated_by VARCHAR(255),

                       CONSTRAINT fk_homes_host
                           FOREIGN KEY (host_id)
                               REFERENCES users(id)
                               ON DELETE RESTRICT
                               ON UPDATE CASCADE
);

CREATE INDEX idx_homes_host_id ON homes(host_id);
CREATE INDEX idx_users_email ON users(email);