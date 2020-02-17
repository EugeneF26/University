package com.project.university.crud;

import com.project.university.entity.Course;
import com.project.university.entity.Student;

public interface CrudCourseService {
	int acceptNewStudentToCourse(Student student, Course course);
	int truncateCoursesTable();
}

