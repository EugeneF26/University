package com.project.university.controller;

import com.project.university.SessionManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.university.repository.DataPreparer;
import com.project.university.service.CourseService;
import com.project.university.service.GroupService;
import com.project.university.service.LectureService;
import com.project.university.service.ProfessorService;
import com.project.university.service.StudentService;

@Controller
public class UniversityController {
	
	private final StudentService studentService;
	private final GroupService groupService;
	private final CourseService courseService;
	private final ProfessorService professorService;
	private final LectureService lectureService;
	private final DataPreparer dataPreparer;
	private SessionManager sessionManager;
	
	@Autowired
	public UniversityController(StudentService studentService, GroupService groupService,
								CourseService courseService, ProfessorService professorService,
								LectureService lectureService, DataPreparer dataPreparer,
								SessionManager sessionManager) {
		this.studentService = studentService;
		this.groupService = groupService;
		this.courseService = courseService;
		this.professorService = professorService;
		this.lectureService = lectureService;
		this.dataPreparer = dataPreparer;
		this.sessionManager = sessionManager;
	}
	
	@GetMapping("/")
	public String createUniversity() throws Exception {
		Session session = sessionManager.getConfiguration().openSession();
		dataPreparer.createCourses();
		dataPreparer.createGroups();
		dataPreparer.createStudents();
		dataPreparer.createProfessors();
		session.close();
		return "welcome";
	}
	
	@GetMapping("/students")
	public String listStudents(Model model) throws Exception {
		model.addAttribute("students", studentService.getStudents());
		return "students";
	}
	
	 @GetMapping("/professors") 
	 public String listProfessors(Model model) throws Exception { 
		model.addAttribute("professors", professorService.getProfessors());
		return "professors";
	 } 
	 
	@GetMapping("/courses")
	public String listCourses(Model model) throws Exception {
		model.addAttribute("courses", courseService.getCourses());
		return "courses";
	}
		
	@GetMapping("/groups") 
	public String listGroups(Model model) throws Exception { 
		model.addAttribute("groups", groupService.getGroups());
		return "groups";
	} 
	
	@GetMapping("/lectures") 
	public String listLectures(Model model) {
		model.addAttribute("lectures", lectureService.getLectures());
		return "lectures";
	} 
}

