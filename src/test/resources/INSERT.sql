INSERT INTO groups (id, courseId)
VALUES (1, 1),
(2,1),
(3,1);
INSERT INTO students (name, surname, groupId)
VALUES ('Petr', 'Manshikov', 1),
('Fedor', 'Gruntovskiy', 2),
('Georgiy', 'Murashvily', 3);
INSERT INTO courses (year, groupid)
VALUES (1,1),
(2,1),
(3,1);
INSERT INTO professors (name, surname, patronymic, currentStatus)
VALUES ('Pavel', 'Koronkov', 'Petrovich', 'WORKS'),
('Mark', 'Marsov', 'Vladimirovich', 'WORKS'),
('Petr', 'Fedorenko', 'Konstantinovich', 'WORKS');