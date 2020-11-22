package com.project.university.controller;

import com.project.university.model.Group;
import com.project.university.service.GroupService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = GroupController.class)
class GroupControllerTest {

    @MockBean
    private GroupService groupService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void test_WhenQueryListGroups_thenInvokeGetGroupOfGroupServiceAndReturn200() throws Exception {
        this.mockMvc.perform(get("/groups/list")
                .contentType(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(view().name("groups_table/list"));

        verify(groupService).getGroups();
        assertThat(groupService.getGroups(), hasItems());
    }

    @Test
    void test_WhenQueryDeleteGroup_thenInvokeDeleteGroupOfGroupServiceAndReturn302() throws Exception {
        this.mockMvc.perform(get("/groups/delete/{id}", 1L)
                .contentType(MediaType.TEXT_HTML))
                .andExpect(status().is(302))
                .andExpect(view().name("redirect:/groups/list"));

        verify(groupService).deleteGroup(anyLong());
    }

    @Test
    void test_WhenQueryUpdateGroupPage_thenInvokeGetGroupOfGroupServiceAndReturn302() throws Exception {
        this.mockMvc.perform(post("/groups/update/form")
                .contentType(MediaType.TEXT_HTML))
                .andExpect(status().is(302))
                .andExpect(view().name("redirect:/groups/list"));

        verify(groupService).changeNameGroup(ArgumentMatchers.any(Group.class));
    }

    @Test
    void changeNameGroup() {
    }
}