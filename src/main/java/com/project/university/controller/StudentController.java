package com.project.university.controller;

import com.project.university.model.StatusStudent;
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
		return "university";
	}

	@GetMapping("/students/list")
	public String list(Model model) throws Exception {
		model.addAttribute("students", studentService.getStudents());
		return "students_table/list";
	}

	@GetMapping("/students/delete/{id}")
	public String delete(@PathVariable("id") long id) throws Exception {
		studentService.deleteStudent(id);
		return "redirect:/students/list";
	}

	@GetMapping("/students/expel/{id}")
	public String expel(@PathVariable("id") long id) throws Exception {
		Student student = new Student();
		student.setId(id);
		studentService.expelStudent(student);
		return "redirect:/students/list";
	}

	@PostMapping("/students/update/form")
	public String createUpdateForm(@ModelAttribute("student") Student student) {
		try {
			studentService.changeGroupForStudent(student.getGroup().getId(), student.getId());
			studentService.updateStudent(student);
		} catch (Exception ex) {
			return "redirect:/";
		}
		return "redirect:/students/list";
	}

	@GetMapping("/students/update/{id}")
	public String update(@PathVariable("id") long id, Model model) throws Exception {
		model.addAttribute("student", studentService.getStudent(id));
		return "students_table/form";
	}

	@PostMapping("/students/save/form")
	public String createSaveForm(@ModelAttribute("student") Student student) {
		try {
			student.setCurrentStatus(StatusStudent.STUDY);
			studentService.addStudent(student);
		} catch (Exception ex) {
			return "redirect:/";
		}
		return "redirect:/students/list";
	}

	@GetMapping("/students/save")
	public String save(Model model) throws Exception {
		Student student = new Student();
		model.addAttribute("student", student);
		return "students_table/save";
	}
}

