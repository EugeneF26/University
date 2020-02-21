CREATE TABLE groups
(
id SERIAL PRIMARY KEY CHECK (id <= 5),
courseId NUMBER CHECK (courseId <= 5)
);
CREATE TABLE students
(
id SERIAL PRIMARY KEY,
name TEXT NOT NULL,
surname TEXT NOT NULL,
groupId NUMBER NOT NULL
);
CREATE TABLE courses
(
id SERIAL PRIMARY KEY,
year NUMBER,
groupId NUMBER,
FOREIGN KEY (groupId) REFERENCES groups(id) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE professors
(
id SERIAL PRIMARY KEY,
name TEXT,
surname TEXT,
patronymic TEXT,
currentStatus TEXT
);
CREATE TABLE lectures
(
id SERIAL PRIMARY KEY,
title TEXT 
);
CREATE TABLE lecture_halls
(
id SERIAL PRIMARY KEY,
floor NUMBER,
numberRoom NUMBER
);
CREATE TABLE schedule_Item
(
studyDay TIMESTAMP PRIMARY KEY,
year NUMBER,
id NUMBER,
title TEXT,
numberRoom NUMBER,
FOREIGN KEY (year) REFERENCES courses(year) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (id) REFERENCES professors(id) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (title) REFERENCES lectures(title) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (numberRoom) REFERENCES lecture_halls(numberRoom) ON DELETE CASCADE ON UPDATE CASCADE
);