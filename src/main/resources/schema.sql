CREATE TABLE Groups
(
id SERIAL PRIMARY KEY CHECK (id <= 5),
name TEXT,
CONSTRAINT sc_unique_groups UNIQUE (id, name)
);
CREATE TABLE Courses
(
id SERIAL PRIMARY KEY,
year INTEGER CHECK (year <= 5),
CONSTRAINT sc_unique_courses UNIQUE (id, year),
CONSTRAINT sc_unique_courses2 UNIQUE (year)
);
CREATE TABLE Professors
(
id SERIAL PRIMARY KEY,
name TEXT NOT NULL,
patronymic TEXT NOT NULL,
currentStatus VARCHAR(255) CHECK(currentStatus in ('WORKS', 'FIRED'))
);
CREATE TABLE Students
(
id SERIAL PRIMARY KEY,
name TEXT NOT NULL,
surname TEXT NOT NULL,
groupId INTEGER NOT NULL,
currentStatus VARCHAR(255) CHECK(currentStatus in ('STUDY','EXPELLED'))
);
CREATE TABLE Courses_Groups
(
id SERIAL PRIMARY KEY,
courseId INTEGER,
groupId INTEGER,
FOREIGN KEY (courseId) REFERENCES courses(id) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (groupId) REFERENCES groups(id) ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT sc_unique_courses_groups UNIQUE (courseId),
CONSTRAINT sc_unique_courses_groups2 UNIQUE (courseId, groupId)
);
CREATE TABLE Lectures
(
id SERIAL PRIMARY KEY,
title TEXT,
CONSTRAINT sc_unique_lecture UNIQUE (id, title),
CONSTRAINT sc_unique_lecture2 UNIQUE (title)
);
CREATE TABLE LectureHalls
(
id SERIAL PRIMARY KEY,
floor INTEGER CHECK (floor <= 4),
classroom INTEGER NOT NULL,
CONSTRAINT sc_unique_lectureHall UNIQUE (id, floor, classroom),
CONSTRAINT sc_unique_lectureHall2 UNIQUE (floor, classroom),
CONSTRAINT sc_unique_lectureHall3 UNIQUE (floor),
CONSTRAINT sc_unique_lectureHall4 UNIQUE (classroom)
);
CREATE TABLE ScheduleItem
(
id SERIAL PRIMARY KEY,
professorId INTEGER NOT NULL,
courseId INTEGER NOT NULL,
studyDay VARCHAR(255) CHECK(studyDay in ('Monday', 'Tuesday', 'Wednesday', 'Thursday',
'Friday')),
lectureId INTEGER NOT NULL,
floor INTEGER NOT NULL,
classroom INTEGER NOT NULL,
FOREIGN KEY (professorId) REFERENCES Professors(id) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (courseId) REFERENCES Courses(id) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (lectureId) REFERENCES Lectures(id) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (floor) REFERENCES LectureHalls(floor) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (classRoom) REFERENCES LectureHalls(classRoom) ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT sc_unique_schedule UNIQUE (id, professorId, courseId, studyDay, lectureId, floor),
CONSTRAINT sc_unique_schedule2 UNIQUE (professorId, courseId, studyDay, lectureId, floor),
CONSTRAINT sc_unique_schedule3 UNIQUE (professorId, courseId, studyDay, lectureId, floor),
CONSTRAINT sc_unique_schedule4 UNIQUE (courseId, studyDay, lectureId, floor),
CONSTRAINT sc_unique_schedule5 UNIQUE (studyDay, lectureId, floor)
);

Обновление: ограничение на значение больше нуля. CHECK (classroom > 0) а так же при floor=1 
classroom < 101; CONSTRAINT LectureHalls CHECK (floor=1 AND classroom >= 1 AND classroom <= 101 )
NOT NULL поставить на id primary key
CHECK (Ball > 50 OR Form_ob = ‘Дневная’));

How to combine constraint CHECK in SQL?
CREATE TABLE LectureHalls
(
id SERIAL PRIMARY KEY,
floor INTEGER NOT NULL CHECK (floor BETWEEN 1 AND 4),
classroom INTEGER NOT NULL,
UNIQUE KEY (floor, classroom),
CHECK (floor = 1 AND classroom BETWEEN 1 AND 30 OR
       floor = 2 AND classroom BETWEEN 1 AND 15 OR
       floor = 3 AND classroom BETWEEN 1 and 24) 
);

create table lecturehalls (
    id serial primary key,
    floor integer check (floor <= 4),
    classroom integer not null,
    unique (floor, classroom),
    check(floor is distinct from 1 or (classroom > 0 and classroom <= 30)),
    check(floor is distinct from 2 or (classroom > 30 and classroom <= 60)),
    check(floor is distinct from 3 or (classroom > 60 and classroom <= 90)),
    check(floor is distinct from 4 or (classroom > 90 and classroom <= 120))
);

CREATE TABLE LectureHalls (
    id SERIAL PRIMARY KEY,
    floor INTEGER CHECK (floor <= 4),
    classroom INTEGER NOT NULL,
    CHECK (floor is null or floor != 1 or (floor = 1 and classroom > 0 and classroom <= 30))
);
