INSERT INTO hospitals(name, address, taluka_id, contact_1)
VALUES ('GM Health Care - Jamner', '1st floor, BOT Complex, Jamner', (SELECT id FROM talukas WHERE name = 'Jamner'), '02580231301')