CREATE TABLE groups
(
group_id NUMBER PRIMARY KEY
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
course_year NUMBER PRIMARY KEY
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
CREATE TABLE lecture_halls
(
floor TEXT,
number_room NUMBER PRIMARY KEY
);
CREATE TABLE schedule_Item
(
study_day TIMESTAMP PRIMARY KEY,
course_year NUMBER,
professor_id NUMBER,
lecture_title TEXT,
number_room NUMBER,
FOREIGN KEY (course_year) REFERENCES courses(course_year) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (professor_id) REFERENCES professors(professor_id) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (lecture_title) REFERENCES lectures(lecture_title) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (number_room) REFERENCES lecture_halls(number_room) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE students_courses
(
student_id NUMBER,
course_year NUMBER,
FOREIGN KEY (student_id) REFERENCES students(student_id) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (course_year) REFERENCES courses(course_year) ON DELETE CASCADE ON UPDATE CASCADE
);