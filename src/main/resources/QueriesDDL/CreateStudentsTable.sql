CREATE TABLE students
(
id SERIAL PRIMARY KEY,
name TEXT NOT NULL,
surname TEXT NOT NULL,
groupId INTEGER NOT NULL CHECK (groupId <= 5),
currentStatus VARCHAR(255) CHECK(currentStatus in ('STUDY','EXPELLED'))
);