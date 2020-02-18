INSERT INTO groups (groupId)
VALUES (1),
(2),
(3),
(4),
(5);
INSERT INTO students (studentName, studentSurname, groupId)
VALUES ('Petr', 'Manshikov', 1),
('Fedor', 'Gruntovskiy', 2),
('Georgiy', 'Murashvily', 3),
('Stepan', 'Parashka', 4),
('Maxim', 'Torkovskiy', 5);
INSERT INTO courses (courseYear)
VALUES (1),
(2),
(3),
(4);
INSERT INTO professors (professorName, professorSurname, professorPatronymic)
VALUES ('Pavel', 'Koronkov', 'Petrovich'),
('Mark', 'Marsov', 'Vladimirovich'),
('Petr', 'Fedorenko', 'Konstantinovich');
INSERT INTO students_courses (studentId, courseYear)
VALUES (1,1),
(2,2),
(3,3),
(4,4);