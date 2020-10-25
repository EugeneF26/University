CREATE TABLE LECTURE_HALLS
(
id INT PRIMARY KEY AUTO_INCREMENT,
floor INT NOT NULL CHECK (floor <= 4),
classroom INT NOT NULL,
CONSTRAINT sc_unique_lectureHall UNIQUE (id, floor, classroom),
CONSTRAINT sc_unique_lectureHall2 UNIQUE (floor, classroom),
CONSTRAINT sc_unique_lectureHall3 UNIQUE (floor),
CONSTRAINT sc_unique_lectureHall4 UNIQUE (classroom)
);

GO