package com.project.university.controller;

import com.project.university.model.Student;
import com.project.university.service.StudentService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = StudentController.class)
class StudentControllerTest {

    @MockBean
    private StudentService studentService;
    
    @Autowired
    private MockMvc mockMvc;

    @Test
    void test_WhenQueryWelcomePage_thenReturn200() throws Exception {
        this.mockMvc.perform(get("/")
                .contentType(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(view().name("university"));
    }

    @Test
    void test_WhenQueryListStudents_thenInvokeGetStudentsOfStudentServiceAndReturn200() throws Exception {
        this.mockMvc.perform(get("/students/list")
                .contentType(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(view().name("students_table/list"));

        verify(studentService).getStudents();
        assertThat(studentService.getStudents(), hasItems());
    }

    @Test
    void test_WhenQueryDeleteStudent_thenInvokeDeleteStudentOfStudentServiceAndReturn302() throws Exception {
        this.mockMvc.perform(get("/students/delete/{id}", 1L)
        .contentType(MediaType.TEXT_HTML))
                .andExpect(status().is(302))
                .andExpect(view().name("redirect:/students/list"));

        verify(studentService).deleteStudent(anyLong());
    }

    @Test
    void test_WhenQueryExpelStudent_thenInvokeExpelStudentOfStudentServiceAndReturn302() throws Exception {
        this.mockMvc.perform(get("/students/expel/{id}", 1L)
                .contentType(MediaType.TEXT_HTML))
                .andExpect(status().is(302))
                .andExpect(view().name("redirect:/students/list"));

        verify(studentService).expelStudent(ArgumentMatchers.any(Student.class));
    }

    @Test
    void test_WhenQueryUpdateStudentPage_thenReturn200() throws Exception {
        this.mockMvc.perform(post("/students/update/form")
                .contentType(MediaType.TEXT_HTML))
                .andExpect(status().is(200));
    }
}

