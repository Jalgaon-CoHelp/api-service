CREATE TABLE resources
(
    id            SERIAL8 PRIMARY KEY,
    type          VARCHAR(20),
    contact_name  VARCHAR     NOT NULL,
    mobile_number VARCHAR(15) NOT NULL,
    resource_name VARCHAR     NOT NULL,
    address       VARCHAR,
    taluka_id     INT         NOT NULL REFERENCES talukas (id),
    note          VARCHAR,
    created_at    TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
)