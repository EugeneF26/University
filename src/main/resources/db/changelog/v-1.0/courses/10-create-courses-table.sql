CREATE TABLE UNIVERSITY_COURSES
(
id INT PRIMARY KEY AUTO_INCREMENT,
year INT CHECK (year <= 5),
CONSTRAINT sc_unique_courses UNIQUE (year)
)

GO