CREATE TABLE SCHEDULE
(
id INT PRIMARY KEY AUTO_INCREMENT,
professorId INT NOT NULL,
courseId INT NOT NULL,
studyDay VARCHAR(255) CHECK(studyDay in ('Monday', 'Tuesday', 'Wednesday', 'Thursday',
'Friday')),
lectureId INT NOT NULL,
lectureHallId INT NOT NULL,
FOREIGN KEY (professorId) REFERENCES PROFESSORS(id) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (courseId) REFERENCES UNIVERSITY_COURSES(id) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (lectureId) REFERENCES UNIVERSITY_LECTURES(id) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (lectureHallId) REFERENCES LECTURE_HALLS(id) ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT sc_unique_schedule UNIQUE (id, professorId, courseId, studyDay, lectureId),
CONSTRAINT sc_unique_schedule2 UNIQUE (professorId, courseId, studyDay, lectureId),
CONSTRAINT sc_unique_schedule3 UNIQUE (professorId, courseId, studyDay, lectureId),
CONSTRAINT sc_unique_schedule4 UNIQUE (courseId, studyDay, lectureId),
CONSTRAINT sc_unique_schedule5 UNIQUE (studyDay, lectureId)
);

GO