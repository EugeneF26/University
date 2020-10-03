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
classroom < 101