DROP TABLE IF EXISTS GROUPS;
CREATE TABLE groups
(
group_id NUMBER PRIMARY KEY
);
DROP TABLE IF EXISTS STUDENTS;
CREATE TABLE students
(
student_id SERIAL PRIMARY KEY,
student_name TEXT NOT NULL,
student_surname TEXT NOT NULL,
group_id NUMBER NOT NULL,
FOREIGN KEY (group_id) REFERENCES groups(group_id) ON DELETE CASCADE ON UPDATE CASCADE
);
DROP TABLE IF EXISTS COURSES;
CREATE TABLE courses
(
course_year NUMBER PRIMARY KEY
);
DROP TABLE IF EXISTS PROFESSORS;
CREATE TABLE professors
(
professor_id SERIAL PRIMARY KEY,
professor_name TEXT,
professor_surname TEXT,
professor_patronymic TEXT
);
DROP TABLE IF EXISTS lectures;
CREATE TABLE lectures
(
lecture_title TEXT PRIMARY KEY
);
DROP TABLE IF EXISTS lecturehalls;
CREATE TABLE lecturehalls
(
floor TEXT,
number_room NUMBER PRIMARY KEY
);
DROP TABLE IF EXISTS scheduleItem;
CREATE TABLE scheduleItem
(
study_day TIMESTAMP PRIMARY KEY,
course_year NUMBER,
professor_id NUMBER,
lecture_title TEXT,
number_room NUMBER,
FOREIGN KEY (course_year) REFERENCES courses(course_year) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (professor_id) REFERENCES professors(professor_id) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (lecture_title) REFERENCES lectures(lecture_title) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (number_room) REFERENCES lecturehalls(number_room) ON DELETE CASCADE ON UPDATE CASCADE
);