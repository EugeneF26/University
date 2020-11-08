package com.project.university.service.impl;

import com.project.university.repository.exception.DaoLayerException;
import com.project.university.repository.exception.DataNotFoundException;

import com.project.university.service.CourseService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
class CourseServiceImplTest {

    @MockBean
    private CourseService courseService;

    @Test
    void test_WhenTheMethodReturnDataIsNotNull() throws DaoLayerException, DataNotFoundException {
        assertThat(courseService.getCourses(), hasItems());
    }
}

