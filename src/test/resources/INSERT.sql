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
INSERT INTO courses (year, groupid)
VALUES (1,1),
(2,1),
(3,1),
(4,1);
INSERT INTO professors (name, surname, patronymic, currentStatus)
VALUES ('Pavel', 'Koronkov', 'Petrovich', 'ACCEPTED'),
('Mark', 'Marsov', 'Vladimirovich', 'ACCEPTED'),
('Petr', 'Fedorenko', 'Konstantinovich', 'ACCEPTED');
INSERT INTO students_courses (id, year)
VALUES (1,1),
(2,2),
(3,3),
(4,4);