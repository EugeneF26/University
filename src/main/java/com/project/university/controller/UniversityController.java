package com.project.university.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.university.repository.DataPreparer;
import com.project.university.service.CourseService;
import com.project.university.service.GroupService;
import com.project.university.service.ProfessorService;
import com.project.university.service.StudentService;

@Controller
public class UniversityController {
	
	private StudentService studentService;
	private GroupService groupService;
	private CourseService coursetService;
	private ProfessorService professorSevice;
	private DataPreparer dataPreparer;
	
	@Autowired
	public UniversityController(StudentService studentService, GroupService groupService,
			CourseService coursetService, ProfessorService professorSevice, 
			DataPreparer dataPreparer) {
		this.studentService = studentService;
		this.groupService = groupService;
		this.coursetService = coursetService;
		this.professorSevice = professorSevice;
		this.dataPreparer = dataPreparer;
	}
	
	@GetMapping("/")
	public String createUniversitet(Model model) throws Exception {
		dataPreparer.deleteTables();
		dataPreparer.createTables();
		dataPreparer.prepareData();
		return "welcome";
	}
	
	@GetMapping("/students")
	public String listStudents(Model model) throws Exception {
		model.addAttribute("students", studentService.getStudents());
		return "students";
	}
	
	 @GetMapping("/professors") 
	 public String listProfessors(Model model) throws Exception { 
		model.addAttribute("professors", professorSevice.getProfessors());
		return "professors";
	 } 
	 
	@GetMapping("/courses")
	public String listCourses(Model model) throws Exception {
		model.addAttribute("courses", coursetService.getCourses());
		return "courses";
	}
		
	@GetMapping("/groups") 
	public String listGroups(Model model) throws Exception { 
		model.addAttribute("groups", groupService.getGroups());
		return "groups";
	} 
}

