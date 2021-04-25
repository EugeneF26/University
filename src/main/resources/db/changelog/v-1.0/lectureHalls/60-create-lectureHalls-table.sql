CREATE TABLE LECTURE_HALLS
(
id INT PRIMARY KEY AUTO_INCREMENT,
floor INT NOT NULL CHECK (floor <= 4),
classroom INT NOT NULL,
CONSTRAINT sc_unique_lectureHall UNIQUE (id, floor, classroom),
CONSTRAINT sc_unique_lectureHall2 UNIQUE (floor, classroom),
CHECK (floor = 1 AND classroom BETWEEN 1 AND 50 OR
       floor = 2 AND classroom BETWEEN 51 AND 100 OR
       floor = 3 AND classroom BETWEEN 101 and 150 OR
       floor = 4 AND classroom BETWEEN 151 and 200)
);

GO