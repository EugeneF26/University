INSERT INTO groups (id)
VALUES (1),
(2),
(3),
(4),
(5);
INSERT INTO students (name, surname, groupId)
VALUES ('Petr', 'Manshikov', 1),
('Fedor', 'Gruntovskiy', 2),
('Georgiy', 'Murashvily', 3),
('Stepan', 'Parashka', 4),
('Maxim', 'Torkovskiy', 5);
INSERT INTO courses (year)
VALUES (1),
(2),
(3),
(4);
INSERT INTO professors (name, surname, patronymic)
VALUES ('Pavel', 'Koronkov', 'Petrovich'),
('Mark', 'Marsov', 'Vladimirovich'),
('Petr', 'Fedorenko', 'Konstantinovich');
INSERT INTO students_courses (id, year)
VALUES (1,1),
(2,2),
(3,3),
(4,4);