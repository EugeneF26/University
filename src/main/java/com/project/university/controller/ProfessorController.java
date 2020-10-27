package com.project.university.controller;

import com.project.university.service.ProfessorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfessorController {

    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @GetMapping("/professors")
    public String listProfessors(Model model) throws Exception {
        model.addAttribute("professors", professorService.getProfessors());
        return "list_all_professors";
    }
}
