package com.project.university.controller;

import com.project.university.model.Student;
import com.project.university.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UniversityController {
	
	private final StudentService studentService;
	private final GroupService groupService;
	private final CourseService courseService;
	private final ProfessorService professorService;
	private final LectureService lectureService;

	@Autowired
	public UniversityController(StudentService studentService, GroupService groupService,
								CourseService courseService, ProfessorService professorService,
								LectureService lectureService) {
		this.studentService = studentService;
		this.groupService = groupService;
		this.courseService = courseService;
		this.professorService = professorService;
		this.lectureService = lectureService;
	}
	
	@GetMapping("/")
	public String createUniversity() throws Exception {
		return "welcome";
	}
	
	@GetMapping("/students")
	public String listStudents(Model model) throws Exception {
		model.addAttribute("students", studentService.getStudents());
		return "students";
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
	public String listLectures(Model model) throws Exception {
		model.addAttribute("lectures", lectureService.getLectures());
		return "lectures";
	} 
}

