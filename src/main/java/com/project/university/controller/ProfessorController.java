package com.project.university.controller;

import com.project.university.model.Professor;
import com.project.university.model.StatusProfessor;
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

    @GetMapping("/professors/list")
    public String list(Model model) throws Exception {
        model.addAttribute("professors", professorService.getProfessors());
        return "professors_table/list";
    }

    @GetMapping("/professors/delete/{id}")
    public String delete(@PathVariable("id") long id) throws Exception {
        professorService.deleteProfessor(id);
        return "redirect:/professors/list";
    }

    @GetMapping("/professors/layOf/{id}")
    public String layOf(@PathVariable("id") long id) throws Exception {
        Professor professor = new Professor();
        professor.setId(id);
        professorService.layOfProfessor(professor);
        return "redirect:/professors/list";
    }

    @PostMapping("/professors/update/form")
    public String createUpdateForm(@ModelAttribute("professor") Professor selected) {
        professorService.updateProfessor(selected);
        return "redirect:/professors/list";
    }

    @GetMapping("/professors/update/{id}")
    public String update(@PathVariable("id") long id, Model model) throws Exception {
        model.addAttribute("professor", professorService.getProfessor(id));
        return "professors_table/form";
    }

    @PostMapping("/professors/save/form")
    public String createSaveForm(@ModelAttribute("professor") Professor professor) {
        professor.setCurrentStatus(StatusProfessor.WORKS);
        professorService.addProfessor(professor);
        return "redirect:/professors/list";
    }

    @GetMapping("/professors/save")
    public String save(Model model) throws Exception {
        Professor professor = new Professor();
        model.addAttribute("professor", professor);
        return "professors_table/save";
    }
}

