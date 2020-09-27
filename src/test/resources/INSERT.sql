INSERT INTO students (name, surname, groupId, currentStatus)
VALUES ('Petr', 'Manshikov', 1, 'STUDY'),
('Fedor', 'Gruntovskiy', 2, 'STUDY'),
('Georgiy', 'Murashvily', 3, 'STUDY');
INSERT INTO courses (year)
VALUES (1),
(2),
(3),
(4);
INSERT INTO groups (courseId, name)
VALUES (1, 'M2'),
(2, 'G5'),
(3, 'G4'),
(4, 'Y5');
INSERT INTO professors (name, patronymic, currentStatus)
VALUES ('Pavel', 'Petrovich', 'WORKS'),
('Mark', 'Vladimirovich', 'WORKS'),
('Petr', 'Konstantinovich', 'WORKS');