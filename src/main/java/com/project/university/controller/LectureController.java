package com.project.university.controller;

import com.project.university.service.LectureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class LectureController {

    private final LectureService lectureService;

    public LectureController(LectureService lectureService) {
        this.lectureService = lectureService;
    }

    @GetMapping("/lectures")
    public String listLectures(Model model) throws Exception {
        model.addAttribute("lectures", lectureService.getLectures());
        return "lectures_table/list_all_lectures";
    }

//    @GetMapping
//    public String addNewLecture(Model model) throws Exception {
//        return "list_all_lectures";
//    }

    @GetMapping("lecture/delete/{id}")
    public String deleteLecture(@PathVariable("id") long id) throws Exception {
        lectureService.deleteLecture(id);
        return "redirect:/lectures";
    }
}
