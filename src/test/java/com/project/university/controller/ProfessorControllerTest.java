package com.project.university.controller;

import com.project.university.model.Professor;
import com.project.university.service.ProfessorService;
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
@WebMvcTest(controllers = ProfessorController.class)
class ProfessorControllerTest {

    @MockBean
    private ProfessorService professorService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void test_WhenQueryListProfessors_thenInvokeGetProfessorsOfProfessorServiceAndReturn200() throws Exception {
        this.mockMvc.perform(get("/professors")
                .contentType(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(view().name("professors_table/list_all_professors"));

        verify(professorService).getProfessors();
        assertThat(professorService.getProfessors(), hasItems());
    }

    @Test
    void test_WhenQueryDeleteProfessor_thenInvokeDeleteProfessorOfProfessorServiceAndReturn302() throws Exception {
        this.mockMvc.perform(get("/professor/delete/{id}", 1L)
                .contentType(MediaType.TEXT_HTML))
                .andExpect(status().is(302))
                .andExpect(view().name("redirect:/professors"));

        verify(professorService).deleteProfessor(anyLong());
    }

    @Test
    void test_WhenQueryLayOfProfessor_thenInvokeLayOfProfessorOfProfessorServiceAndReturn302() throws Exception {
        this.mockMvc.perform(get("/professor/layOf/{id}", 1L)
                .contentType(MediaType.TEXT_HTML))
                .andExpect(status().is(302))
                .andExpect(view().name("redirect:/professors"));

        verify(professorService).layOfProfessor(ArgumentMatchers.any(Professor.class));
    }

    @Test
    void test_WhenQueryUpdateProfessorPage_thenInvokeUpdateProfessorOfProfessorServiceAndReturn302() throws Exception {
        this.mockMvc.perform(post("/professor/update/update_professor")
                .contentType(MediaType.TEXT_HTML))
                .andExpect(status().is(302))
                .andExpect(view().name("redirect:/professors"));

        verify(professorService).updateProfessor(ArgumentMatchers.any(Professor.class));
    }

    @Test
    void updateProfessor() {
    }
}