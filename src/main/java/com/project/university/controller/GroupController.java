package com.project.university.controller;

import com.project.university.model.Group;
import com.project.university.service.GroupService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("/groups/list")
    public String list(Model model) throws Exception {
        model.addAttribute("groups", groupService.getGroups());
        return "groups_table/list";
    }

    @GetMapping("/groups/create")
    public String create(Model model) throws Exception {
        Group group = new Group();
        model.addAttribute("group", group);
        return "groups_table/create";
    }

    @PostMapping("/groups/create/form")
    public String createSaveForm(@ModelAttribute("group") Group group) {
        groupService.addNewGroup(group);
        return "redirect:/groups/list";
    }

    @GetMapping("/groups/delete/{id}")
    public String delete(@PathVariable("id") long id) throws Exception {
        groupService.deleteGroup(id);
        return "redirect:/groups/list";
    }

    @PostMapping("/groups/update/form")
    public String createUpdateForm(@ModelAttribute("group") Group selected) {
        groupService.changeNameGroup(selected);
        return "redirect:/groups/list";
    }

    @GetMapping("/groups/update/{id}")
    public String changeNameGroup(@PathVariable("id") long id, Model model) throws Exception {
       model.addAttribute("group", groupService.getGroup(id));
       return "groups_table/form";
    }
}

