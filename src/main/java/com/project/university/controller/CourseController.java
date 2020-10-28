package com.project.university.controller;

import com.project.university.service.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/courses")
    public String listCourses(Model model) throws Exception {
        model.addAttribute("courses", courseService.getCourses());
        return "courses_table/list_all_courses";
    }
}
