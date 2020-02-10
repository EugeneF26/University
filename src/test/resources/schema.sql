DROP TABLE IF EXISTS STUDENTS;
CREATE TABLE students
(
student_id SERIAL PRIMARY KEY,
student_name TEXT NOT NULL,
student_surname TEXT NOT NULL
);
INSERT INTO students (student_name, student_surname)
VALUES ('Petr', 'Manshikov'),
('Fedor', 'Gruntovskiy'),
('Georgiy', 'Murashvily'),
('Stepan', 'Parashka'),
('Maxim', 'Torkovskiy');