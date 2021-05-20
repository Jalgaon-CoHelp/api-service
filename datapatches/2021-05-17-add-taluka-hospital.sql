INSERT INTO hospitals(name, address, taluka_id, contact_1,contact_2)
VALUES ('Maher Hospital, Parola', 'Mahavir Nagar, Parola', (SELECT id FROM talukas WHERE name ILIKE 'Parola'), '9730849062', '');