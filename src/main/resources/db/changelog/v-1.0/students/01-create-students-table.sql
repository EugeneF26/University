CREATE TABLE STUDENTS
(
id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(255) NOT NULL,
surname VARCHAR(255) NOT NULL,
groupId INT NOT NULL CHECK (groupId <= 5),
currentStatus VARCHAR(255) CHECK(currentStatus in ('STUDY','EXPELLED')),
FOREIGN KEY (groupId) REFERENCES UNIVERSITY_GROUPS(id)
)

GO