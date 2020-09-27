CREATE TABLE groups
(
id SERIAL PRIMARY KEY CHECK (id <= 5),
name TEXT,
courseId INTEGER CHECK (courseId <= 5),
CONSTRAINT sc_unique UNIQUE (id, courseId)
);