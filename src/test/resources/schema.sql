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
DROP TABLE IF EXISTS COURSES;
CREATE TABLE courses
(
course_year NUMBER PRIMARY KEY,
);
INSERT INTO courses
VALUES (1),
(2),
(3),
(4);
DROP TABLE IF EXISTS GROUPS;
CREATE TABLE groups
(
group_id NUMBER PRIMARY KEY,
);
INSERT INTO groups
VALUES (1),
(2),
(3),
(4),
(5),
(6);
DROP TABLE IF EXISTS PROFESSORS;
CREATE TABLE professors
(
professor_id SERIAL PRIMARY KEY,
professor_name TEXT,
professor_surname TEXT,
professor_patronymic TEXT
);
INSERT INTO professors (professor_name, professor_surname, professor_patronymic)
VALUES ('Pavel', 'Koronkov', 'Petrovich'),
('Mark', 'Marsov', 'Vladimirovich'),
('Petr', 'Fedorenko', 'Konstantinovich');