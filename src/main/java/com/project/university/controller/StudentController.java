package com.project.university.controller;

import com.project.university.model.Student;
import com.project.university.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class StudentController {
	
	private final StudentService studentService;

	@Autowired
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}
	
	@GetMapping("/")
	public String createUniversity() throws Exception {
		return "welcome";
	}
	
	@GetMapping("/students")
	public String listAllStudents(Model model) throws Exception {
		model.addAttribute("students", studentService.getStudents());
		return "list_all_students";
	}

	@GetMapping("student/delete/{id}")
	public String deleteStudent(@PathVariable("id") long id) throws Exception {
		studentService.deleteStudent(id);
		return "redirect:/students";
	}

	@GetMapping("student/expel/{id}")
	public String expelStudent(@PathVariable("id") long id) throws Exception {
		Student student = new Student();
		student.setId(id);
		studentService.expelStudent(student);
		return "redirect:/students";
	}

	@PostMapping("student/update/update_student")
	public String createUpdateStudentPage(@ModelAttribute("student") Student selected) {
		studentService.updateStudent(selected);
		return "redirect:/students";
	}

	@GetMapping("student/update/{id}")
	public String updateStudent(@PathVariable("id") long id, Model model) throws Exception {
		model.addAttribute("student", studentService.getStudent(id));
		return "update_student";
	}
}

