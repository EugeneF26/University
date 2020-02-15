CREATE TABLE groups
(
group_id NUMBER PRIMARY KEY,
student_id NUMBER
);
CREATE TABLE students
(
student_id SERIAL PRIMARY KEY,
student_name TEXT NOT NULL,
student_surname TEXT NOT NULL,
group_id NUMBER NOT NULL
);
CREATE TABLE courses
(
course_year NUMBER PRIMARY KEY,
group_id NUMBER
);
CREATE TABLE professors
(
professor_id SERIAL PRIMARY KEY,
professor_name TEXT,
professor_surname TEXT,
professor_patronymic TEXT
);
CREATE TABLE lectures
(
lecture_title TEXT PRIMARY KEY
);
CREATE TABLE lecturehalls
(
floor TEXT,
number_room NUMBER PRIMARY KEY
);
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