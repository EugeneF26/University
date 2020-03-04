package com.project.university.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.university.service.StudentService;

@Controller
@RequestMapping("/")
public class UniversityController {
	
	private StudentService studentService;
	
	@Autowired
	public UniversityController(StudentService studentService) {
		this.studentService = studentService;
	}

	@GetMapping("/index")
	public String index(Model model) {
		return "index";
	}
	
	@GetMapping("/list")
	public String list(Model model) throws Exception {
//		  model.addAttribute("students", studentService.getStudents());
		return "list";
	}
}

