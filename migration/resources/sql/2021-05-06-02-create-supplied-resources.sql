CREATE TABLE requested_resources
(
    id            SERIAL8 PRIMARY KEY,
    contact_name  VARCHAR NOT NULL,
    resource_name VARCHAR,
    address       VARCHAR,
    taluka_id     INT     NOT NULL REFERENCES talukas (id),
    mobile_number VARCHAR(15),
    note          VARCHAR,
    created_at    TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
)