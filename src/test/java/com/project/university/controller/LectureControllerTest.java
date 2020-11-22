package com.project.university.controller;

import com.project.university.service.LectureService;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = LectureController.class)
class LectureControllerTest {

    @MockBean
    private LectureService lectureService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void test_WhenQueryListLectures_thenInvokeGetLecturesOfLectureServiceAndReturn200() throws Exception {
        this.mockMvc.perform(get("/lectures/list")
                .contentType(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(view().name("lectures_table/list"));

        verify(lectureService).getLectures();
        assertThat(lectureService.getLectures(), hasItems());
    }

    @Test
    void test_WhenQueryDeleteLecture_thenInvokeDeleteLectureOfLectureServiceAndReturn302() throws Exception {
        this.mockMvc.perform(get("/lectures/delete/{id}", 1L)
                .contentType(MediaType.TEXT_HTML))
                .andExpect(status().is(302))
                .andExpect(view().name("redirect:/lectures/list"));

        verify(lectureService).deleteLecture(anyLong());
    }
}