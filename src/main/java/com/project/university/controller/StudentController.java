package com.project.university.controller;

import com.project.university.model.Student;
import com.project.university.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("university")
public class StudentController {
	
	private final StudentService studentService;

	@Autowired
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}
	
	public String createUniversity() throws Exception {
		return "university";
	}

	@GetMapping("student/list")
	public String list(Model model) throws Exception {
		model.addAttribute("students", studentService.getStudents());
		return "students_table/list";
	}

	@GetMapping("student/delete/{id}")
	public String delete(@PathVariable("id") long id) throws Exception {
		studentService.deleteStudent(id);
		return "redirect:/list";
	}

	@GetMapping("student/expel/{id}")
	public String expel(@PathVariable("id") long id) throws Exception {
		Student student = new Student();
		student.setId(id);
		studentService.expelStudent(student);
		return "redirect:/list";
	}

	@PostMapping("student/update/update_student")
	public String createUpdatePage(@ModelAttribute("student") Student student) {
		studentService.updateStudent(student);
		return "redirect:/list";
	}

	@GetMapping("student/update/{id}")
	public String update(@PathVariable("id") long id, Model model) throws Exception {
		model.addAttribute("student", studentService.getStudent(id));
		return "students_table/update";
	}
}

