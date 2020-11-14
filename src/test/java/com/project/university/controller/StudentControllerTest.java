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
                .andExpect(view().name("welcome"));
    }

    @Test
    void test_WhenQueryListStudents_thenInvokeGetStudentsOfStudentServiceAndReturn200() throws Exception {
        this.mockMvc.perform(get("/students")
                .contentType(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(view().name("students_table/list_all_students"));

        verify(studentService).getStudents();
        assertThat(studentService.getStudents(), hasItems());
    }

    @Test
    void test_WhenQueryDeleteStudent_thenInvokeDeleteStudentOfStudentServiceAndReturn302() throws Exception {
        this.mockMvc.perform(get("/student/delete/{id}", 1L)
        .contentType(MediaType.TEXT_HTML))
                .andExpect(status().is(302))
                .andExpect(view().name("redirect:/students"));

        verify(studentService).deleteStudent(anyLong());
    }

    @Test
    void test_WhenQueryExpelStudent_thenInvokeExpelStudentOfStudentServiceAndReturn302() throws Exception {
        this.mockMvc.perform(get("/student/expel/{id}", 1L)
                .contentType(MediaType.TEXT_HTML))
                .andExpect(status().is(302))
                .andExpect(view().name("redirect:/students"));

        verify(studentService).expelStudent(ArgumentMatchers.any(Student.class));
    }

    @Test
    void test_WhenQueryUpdateStudentPage_thenInvokeUpdateStudentOfStudentServiceAndReturn302() throws Exception {
        this.mockMvc.perform(post("/student/update/update_student")
                .contentType(MediaType.TEXT_HTML))
                .andExpect(status().is(302))
                .andExpect(view().name("redirect:/students"));

        verify(studentService).updateStudent(ArgumentMatchers.any(Student.class));
    }

    @Test
    void test_WhenQueryUpdateStudent_thenReturn200() {

    }
}

