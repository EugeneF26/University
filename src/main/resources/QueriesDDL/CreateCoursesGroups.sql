CREATE TABLE courses_groups
(
course_year INTEGER,
group_name TEXT,
FOREIGN KEY (course_year) REFERENCES courses(year) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (group_name) REFERENCES groups(name) ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT sc_unique_courses_groups UNIQUE (course_year),
CONSTRAINT sc_unique_courses_groups2 UNIQUE (course_year, group_name)
);