INSERT INTO students (name, surname, groupId, currentStatus)
VALUES ('Petr', 'Manshikov', 1, 'STUDY'),
('Fedor', 'Gruntovskiy', 2, 'STUDY'),
('Georgiy', 'Murashvily', 3, 'STUDY');
INSERT INTO courses (year)
VALUES (1),
(2),
(4);
INSERT INTO groups (courseId, name)
VALUES (1, 'M2'),
(2, 'G5'),
(4, 'Y5');
INSERT INTO professors (name, surname, patronymic, currentStatus)
VALUES ('Pavel', 'Koronkov', 'Petrovich', 'WORKS'),
('Mark', 'Marsov', 'Vladimirovich', 'WORKS'),
('Petr', 'Fedorenko', 'Konstantinovich', 'WORKS');