CREATE TABLE groups
(
groupId NUMBER PRIMARY KEY
);
CREATE TABLE students
(
studentId SERIAL PRIMARY KEY,
studentName TEXT NOT NULL,
studentSurname TEXT NOT NULL,
groupId NUMBER NOT NULL
);
CREATE TABLE courses
(
courseYear NUMBER PRIMARY KEY
);
CREATE TABLE professors
(
professorId SERIAL PRIMARY KEY,
professorName TEXT,
professorSurname TEXT,
professorPatronymic TEXT
);
CREATE TABLE lectures
(
lectureTitle TEXT PRIMARY KEY
);
CREATE TABLE lecture_halls
(
floor TEXT,
numberRoom NUMBER PRIMARY KEY
);
CREATE TABLE schedule_Item
(
studyDay TIMESTAMP PRIMARY KEY,
courseYear NUMBER,
professorId NUMBER,
lectureTitle TEXT,
numberRoom NUMBER,
FOREIGN KEY (courseYear) REFERENCES courses(courseYear) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (professorId) REFERENCES professors(professorId) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (lectureTitle) REFERENCES lectures(lectureTitle) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (numberRoom) REFERENCES lecture_halls(numberRoom) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE students_courses
(
studentId NUMBER,
courseYear NUMBER,
FOREIGN KEY (studentId) REFERENCES students(studentId) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (courseYear) REFERENCES courses(courseYear) ON DELETE CASCADE ON UPDATE CASCADE
);