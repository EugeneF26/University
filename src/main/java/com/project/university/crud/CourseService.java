package com.project.university.crud;

import com.project.university.entity.Course;
import com.project.university.entity.Student;

public interface CourseService {
	int acceptNewStudentToCourse(Student student, Course course);
	int truncateCoursesTable();
}

