INSERT INTO hospitals(name, address, taluka_id, contact_1,contact_2)
VALUES ('Ashirwad Hospital, Chalisgaon', 'Hanuman Wadi, Chalisgaon, Maharashtra 424101', (SELECT id FROM talukas WHERE name ILIKE 'Chalisgaon'), '9970022653', ''),
       ('Gramin Rugnalaya, Chalisgaon', 'Rahipuri, Chalisgaon, Maharashtra 424101', (SELECT id FROM talukas WHERE name ILIKE 'Chalisgaon'), '9822623524', ''),
       ('Aadhaar Critical Care & Multispeciality Hospital', '402, Swatantrya Chowk - Pande Chowk Rd, Jilha Peth, Prabhat Colony, Jalgaon, Maharashtra 425001', (SELECT id FROM talukas WHERE name ILIKE 'Jalgaon'), '02572220257', ''),
       ('Vijayendra Hospital, Jalgaon', 'Ganesh Colony, Jalgaon, Maharashtra 425001', (SELECT id FROM talukas WHERE name ILIKE 'Jalgaon'), '02572217455', ''),
       ('Shree Datta Hospital And Critical Care Center', 'Suyog Society, Amalner, Maharashtra 425401', (SELECT id FROM talukas WHERE name ILIKE 'Amalner'), '', ''),
       ('Tuljai Hospital, Amalner', 'Dhule Hwy, Near R K Nagar, Kranti Nagar, Amalner, Maharashtra 425401', (SELECT id FROM talukas WHERE name ILIKE 'Amalner'), '02587223131', ''),
       ('Aashray Hospital Faizpur', 'Faizpur', (SELECT id FROM talukas WHERE name ILIKE 'Yawal'), '', ''),
       ('Shree Datta Nursing Home, Jalgaon', 'Pratap Nagar, Jalgaon, Maharashtra 425001', (SELECT id FROM talukas WHERE name ILIKE 'Jalgaon'), '02572220091', ''),
       ('Vighnaharta Hospital Shendurni', 'Shendurni', (SELECT id FROM talukas WHERE name ILIKE 'Jamner'), '', ''),
       ('Sarasvati Hospital Chalisgaon', 'Hira Pur Road, Chalisgaon', (SELECT id FROM talukas WHERE name ILIKE 'Chalisgaon'), '0257222725', ''),
       ('Gajanan Hospital, Chalisgaon', 'Kargaon Rd, Vivekanand Colony, Rahipuri, Chalisgaon', (SELECT id FROM talukas WHERE name ILIKE 'Chalisgaon'), '', ''),
       ('Vyanktesh COVID Care Center Parola', 'Parola', (SELECT id FROM talukas WHERE name ILIKE 'Parola'), '', ''),
       ('Sane Hospital Shendurni', 'Shendurni', (SELECT id FROM talukas WHERE name ILIKE 'Jamner'), '', ''),
       ('Sanjivani Hospital Jamner', '3185, 10, Pachora - Jamner Rd, Jamner, Maharashtra 424206', (SELECT id FROM talukas WHERE name ILIKE 'Jamner'), '07774073011', ''),
       ('Yogeshwar Hospital Amalner', 'Dhule Hwy, Sane Nagar, Mondhale Pr. Amalner, Maharashtra 425113', (SELECT id FROM talukas WHERE name ILIKE 'Amalner'), '02587222206', ''),
       ('Vijayanand Hospital Parola', 'Vijayanand Hospital, 6 , Amalner Road , Parola - 425111', (SELECT id FROM talukas WHERE name ILIKE 'Parola'), '222404', '9421583404'),
       ('SDH Bodwad', 'Bodwad', (SELECT id FROM talukas WHERE name ILIKE 'Bodwad'), '', ''),
       ('Sanjeevani Hospital Faizpur', 'Laxmi Nagar, near Mahalaxmi Madir, Faizpur', (SELECT id FROM talukas WHERE name ILIKE 'Yawal'), '', ''),
       ('Shwas Hospital Bhadgaon', 'Bhadgaon', (SELECT id FROM talukas WHERE name ILIKE 'Bhadgaon'), '', ''),
       ('C. T. Care Hospital Chalisgaon', 'Sardar Vallabhbhai Patel Rd, Laxmi Nagar, Hanuman Wadi, Chalisgaon', (SELECT id FROM talukas WHERE name ILIKE 'Chalisgaon'), '', ''),
       ('Renuka Hospital Jalgaon', 'MIDC to Mehrun Rd, Opp. Water Supply Office, Tambapura, Jalgaon', (SELECT id FROM talukas WHERE name ILIKE 'Jalgaon'), '', ''),
       ('Sai Chhaya Hospital Paldhi', 'Paldhi ', (SELECT id FROM talukas WHERE name ILIKE 'Dharangaon'), '08087961366', ''),
       ('Sai Krishna Hospital, Chalisgaon', 'Hanuman Wadi, Jalgaon, SH-19, Malegaon Chalisgaon Bhadgaon Road, Jalgaon', (SELECT id FROM talukas WHERE name ILIKE 'Chalisgaon'), '7588007116', '');