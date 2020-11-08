package com.project.university.service.impl;

import com.project.university.model.Lecture;
import com.project.university.repository.LectureRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

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
        verify(lectureRepository).deleteById(anyLong());
    }

    @Test
    void addNewLecture() {
        Lecture lecture = mock(Lecture.class);

        lectureServiceImpl.addNewLecture(lecture);
        verify(lectureRepository).save(lecture);
    }

    @Test
    void updateLecture() {
        Lecture lecture = spy(Lecture.class);
        lecture.setId(anyLong());
        lecture.setTitle(anyString());

        when(lectureRepository.getOne(lecture.getId())).thenReturn(lecture);

        lectureServiceImpl.updateLecture(lecture);

        verify(lecture, times(2)).setTitle(anyString());
        verify(lectureRepository).save(any(Lecture.class));
    }

    @Test
    void getLectures() {
        lectureServiceImpl.getLectures();
        verify(lectureRepository).findAll();
    }

    @Test
    void deleteLecture() {
        lectureServiceImpl.deleteLecture(anyLong());
        verify(lectureRepository).deleteById(anyLong());
    }
}

