DROP TABLE IF EXISTS STUDENTS;
CREATE TABLE students
(
student_id SERIAL PRIMARY KEY,
name TEXT NOT NULL,
surname TEXT NOT NULL
);
INSERT INTO students (name, surname)
VALUES ('Petr', 'Manshikov'),
('Fedor', 'Gruntovskiy'),
('Georgiy', 'Murashvily'),
('Stepan', 'Parashka'),
('Maxim', 'Torkovskiy');