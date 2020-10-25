CREATE TABLE SCHEDULE
(
id INT PRIMARY KEY AUTO_INCREMENT,
professorId INT NOT NULL,
courseId INT NOT NULL,
studyDay VARCHAR(255) CHECK(studyDay in ('Monday', 'Tuesday', 'Wednesday', 'Thursday',
'Friday')),
lectureId INT NOT NULL,
floor INT NOT NULL,
classroom INT NOT NULL,
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

GO