package com.project.university.controller;

import com.project.university.model.Professor;
import com.project.university.model.Student;
import com.project.university.service.ProfessorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("professor/delete/{id}")
    public String deleteProfessor(@PathVariable("id") long id) throws Exception {
        professorService.deleteProfessor(id);
        return "redirect:/professors";
    }

    @GetMapping("professor/layOf/{id}")
    public String layOfProfessor(@PathVariable("id") long id) throws Exception {
        Professor professor = new Professor();
        professor.setId(id);
        professorService.layOfProfessor(professor);
        return "redirect:/professors";
    }

    @PostMapping("professor/update/update_professor")
    public String createUpdateProfessorPage(@ModelAttribute("professor") Professor selected) {
        professorService.updateProfessor(selected);
        return "redirect:/professors";
    }

    @GetMapping("professor/update/{id}")
    public String updateProfessor(@PathVariable("id") long id, Model model) throws Exception {
        model.addAttribute("professor", professorService.getProfessor(id));
        return "update_professor";
    }
}
