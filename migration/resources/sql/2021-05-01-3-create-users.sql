CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE users
(
    id         BIGSERIAL PRIMARY KEY,
    name       VARCHAR        NOT NULL,
    email      VARCHAR UNIQUE NOT NULL,
    phone      VARCHAR(15)    NOT NULL,
    password   VARCHAR        NOT NULL,
    taluka_id  INT            NOT NULL REFERENCES talukas (id),
    created_at TIMESTAMPTZ    NOT NULL DEFAULT CURRENT_TIMESTAMP
);