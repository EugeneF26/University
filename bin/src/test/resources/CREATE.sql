DROP TABLE IF EXISTS STUDENTS;
CREATE TABLE students
(
student_id SERIAL PRIMARY KEY,
student_name TEXT NOT NULL,
student_surname TEXT NOT NULL
);
DROP TABLE IF EXISTS COURSES;
CREATE TABLE courses
(
course_year NUMBER PRIMARY KEY,
);
DROP TABLE IF EXISTS GROUPS;
CREATE TABLE groups
(
group_id NUMBER PRIMARY KEY,
);
DROP TABLE IF EXISTS PROFESSORS;
CREATE TABLE professors
(
professor_id SERIAL PRIMARY KEY,
professor_name TEXT,
professor_surname TEXT,
professor_patronymic TEXT
);