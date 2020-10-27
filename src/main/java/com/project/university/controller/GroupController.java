package com.project.university.controller;

import com.project.university.service.GroupService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("/groups")
    public String listGroups(Model model) throws Exception {
        model.addAttribute("groups", groupService.getGroups());
        return "list_all_groups";
    }
}
