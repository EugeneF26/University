package com.project.university.domain;

import java.sql.ResultSet;

import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.project.university.entity.Group;
import com.project.university.entity.Student;

/**
 * @author Eugene
 *  The class is an implementing @see RowMapper interface
 */
public class StudentMapper implements RowMapper<Student> {

	/**
	 * Perform the actual work of mapping each row to a result object
	 * @param rs
	 * @param rowNum  
	 * @return student
	 */
	@Override
	public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
		Group group = Group
				.builder()
				.groupId(rs.getInt("group_id"))
				.build();
		Student student = Student
				.builder()
				.studentId(rs.getInt("student_id"))
				.studentName(rs.getString("student_name"))
				.studentSurname(rs.getString("student_surname"))
				.groupId(group)
				.build();
        return student;
	}
}

