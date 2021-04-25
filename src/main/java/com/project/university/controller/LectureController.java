package com.project.university.controller;

import com.project.university.model.Lecture;
import com.project.university.service.LectureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LectureController {

    private final LectureService lectureService;

    public LectureController(LectureService lectureService) {
        this.lectureService = lectureService;
    }

    @GetMapping("/lectures/list")
    public String list(Model model) throws Exception {
        model.addAttribute("lectures", lectureService.getLectures());
        return "lectures_table/list";
    }

    @GetMapping("/lectures/delete/{id}")
    public String delete(@PathVariable("id") long id) throws Exception {
        lectureService.deleteLecture(id);
        return "redirect:/lectures/list";
    }

    @GetMapping("/lectures/save")
    public String save(Model model) throws Exception {
        Lecture lecture = new Lecture();
        model.addAttribute("lecture", lecture);
        return "lectures_table/save";
    }

    @PostMapping("/lectures/save/form")
    public String createSaveForm(@ModelAttribute("lecture") Lecture lecture) {
        lectureService.addNewLecture(lecture);
        return "redirect:/lectures/list";
    }
}

