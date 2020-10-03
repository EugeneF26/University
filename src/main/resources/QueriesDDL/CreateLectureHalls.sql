CREATE TABLE LectureHalls
(
id SERIAL PRIMARY KEY,
floor INTEGER CHECK (floor <= 4),
classroom INTEGER,
CONSTRAINT sc_unique_lectureHall UNIQUE (id, floor, classroom),
CONSTRAINT sc_unique_lectureHall2 UNIQUE (floor, classroom),
CONSTRAINT sc_unique_lectureHall3 UNIQUE (floor),
CONSTRAINT sc_unique_lectureHall4 UNIQUE (classroom)
);