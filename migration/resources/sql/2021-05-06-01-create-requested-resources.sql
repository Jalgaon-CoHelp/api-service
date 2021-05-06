CREATE TABLE requested_resources
(
    id            SERIAL8 PRIMARY KEY,
    patient_name  VARCHAR NOT NULL,
    patient_age   INT     NOT NULL,
    blood_group   VARCHAR(5),
    resource_name VARCHAR,
    address       VARCHAR,
    taluka_id     INT     NOT NULL REFERENCES talukas (id),
    mobile_number VARCHAR(15),
    note          VARCHAR,
    created_at    TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
)