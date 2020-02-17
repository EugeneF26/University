INSERT INTO groups (group_id)
VALUES (1),
(2),
(3),
(4),
(5);
INSERT INTO students (student_name, student_surname, group_id)
VALUES ('Petr', 'Manshikov', 1),
('Fedor', 'Gruntovskiy', 2),
('Georgiy', 'Murashvily', 3),
('Stepan', 'Parashka', 4),
('Maxim', 'Torkovskiy', 5);
INSERT INTO courses (course_year)
VALUES (1),
(2),
(3),
(4);
INSERT INTO professors (professor_name, professor_surname, professor_patronymic)
VALUES ('Pavel', 'Koronkov', 'Petrovich'),
('Mark', 'Marsov', 'Vladimirovich'),
('Petr', 'Fedorenko', 'Konstantinovich');
INSERT INTO students_courses (student_id, course_year)
VALUES (1,1),
(2,2),
(3,3),
(4,4);