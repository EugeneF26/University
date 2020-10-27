package com.project.university.controller;

import com.project.university.service.LectureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LectureController {

    private final LectureService lectureService;

    public LectureController(LectureService lectureService) {
        this.lectureService = lectureService;
    }

    @GetMapping("/lectures")
    public String listLectures(Model model) throws Exception {
        model.addAttribute("lectures", lectureService.getLectures());
        return "lectures";
    }
}
