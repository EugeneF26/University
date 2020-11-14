package com.project.university.controller;

import com.project.university.service.CourseService;
import com.project.university.service.ProfessorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CourseController.class)
class CourseControllerTest {

    @MockBean
    private CourseService courseService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void test_WhenQueryListCourses_thenInvokeGetCoursesOfCourseServiceAndReturn200() throws Exception {
        this.mockMvc.perform(get("/courses")
                .contentType(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(view().name("courses_table/list_all_courses"));

        verify(courseService).getCourses();
        assertThat(courseService.getCourses(), hasItems());
    }
}