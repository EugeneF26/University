package com.project.university.service.impl;

import com.project.university.model.Lecture;
import com.project.university.repository.LectureRepository;
import com.project.university.repository.ProfessorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class LectureServiceImplTest {

    @InjectMocks
    private LectureServiceImpl lectureServiceImpl;

    @Mock
    private LectureRepository lectureRepository;

    @Test
    void removeLecture() {
        lectureServiceImpl.removeLecture(anyLong());
        verify(lectureRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void addNewLecture() {
        Lecture lecture = mock(Lecture.class);

        lectureServiceImpl.addNewLecture(lecture);
        verify(lectureRepository, times(1)).save(lecture);
    }

    @Test
    void updateLecture() {
        Lecture lecture = spy(Lecture.class);
        lecture.setId(anyLong());
        lecture.setTitle(anyString());

        when(lectureRepository.getOne(lecture.getId())).thenReturn(lecture);

        lectureServiceImpl.updateLecture(lecture);

        verify(lecture, times(2)).setTitle(anyString());
        verify(lectureRepository, times(1)).save(any(Lecture.class));
    }

    @Test
    void getLectures() {
        lectureServiceImpl.getLectures();
        verify(lectureRepository, times(1)).findAll();
    }

    @Test
    void deleteLecture() {
        lectureServiceImpl.deleteLecture(anyLong());
        verify(lectureRepository, times(1)).deleteById(anyLong());
    }
}

