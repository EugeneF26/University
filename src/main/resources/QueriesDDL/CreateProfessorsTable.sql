CREATE TABLE professors
(
id SERIAL PRIMARY KEY,
name TEXT NOT NULL,
patronymic TEXT NOT NULL,
currentStatus VARCHAR(255) CHECK(currentStatus in ('WORKS', 'FIRED'))
);