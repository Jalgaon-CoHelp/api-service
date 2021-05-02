CREATE TABLE hospitals
(
    id             SERIAL8 PRIMARY KEY,
    name           VARCHAR   NOT NULL,
    address        VARCHAR,
    taluka_id      INT NOT NULL REFERENCES talukas (id),
    contact_1      VARCHAR(15),
    contact_2      VARCHAR(15),
    bed_general    INT,
    bed_oxygen     INT,
    bed_icu        INT,
    bed_ventilator INT,
    created_at     TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at     TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);