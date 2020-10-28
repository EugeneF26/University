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

    @GetMapping("/groups")
    public String listGroups(Model model) throws Exception {
        model.addAttribute("groups", groupService.getGroups());
        return "groups_table/list_all_groups";
    }

    @GetMapping("group/delete/{id}")
    public String deleteGroup(@PathVariable("id") long id) throws Exception {
        groupService.deleteGroup(id);
        return "redirect:/groups";
    }

    @PostMapping("group/updateName/update_group_name")
    public String createUpdateGroupPage(@ModelAttribute("group") Group selected) {
        groupService.changeNameGroup(selected);
        return "redirect:/groups";
    }

    @GetMapping("group/updateName/{id}")
    public String changeNameGroup(@PathVariable("id") long id, Model model) throws Exception {
       model.addAttribute("group", groupService.getGroup(id));
       return "groups_table/update_group_name";
    }
}

