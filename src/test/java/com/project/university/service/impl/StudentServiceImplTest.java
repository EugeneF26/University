package com.project.university.service.impl;

import com.project.university.model.StatusStudent;
import com.project.university.model.Student;
import com.project.university.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class StudentServiceImplTest {

    @InjectMocks
    private StudentServiceImpl studentServiceImpl;

    @Mock
    private StudentRepository studentRepository;

    @Test
    void acceptNewStudent() throws Exception {
        Student student = spy(Student.class);
        studentServiceImpl.acceptNewStudent(student);

        verify(student).setCurrentStatus(StatusStudent.STUDY);
        verify(studentRepository, times(1)).save(student);
    }

    @Test
    void getStudents() throws Exception {
        studentServiceImpl.getStudents();
        verify(studentRepository, times(1)).findAll();
    }

    @Test
    void deleteStudent() {
        studentServiceImpl.deleteStudent(anyLong());
        verify(studentRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void getStudent() {
        studentServiceImpl.getStudent(anyLong());
        verify(studentRepository, times(1)).getOne(anyLong());
    }
}

