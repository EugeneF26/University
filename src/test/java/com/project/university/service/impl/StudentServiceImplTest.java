package com.project.university.service.impl;

import com.project.university.model.Group;
import com.project.university.model.StatusStudent;
import com.project.university.model.Student;
import com.project.university.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class StudentServiceImplTest {

    @InjectMocks
    private StudentServiceImpl studentServiceImpl;

    @Mock
    private StudentRepository studentRepository;

    @Test
    void test_WhenServiceWillInvokeItsMethodThenRepoWillChangeStatusForStudentToSTUDYAndInvokeSaveForStudent() throws Exception {
        Student student = spy(Student.class);
        studentServiceImpl.acceptNewStudent(student);

        verify(student).setCurrentStatus(StatusStudent.STUDY);
        verify(studentRepository).save(student);
    }

    @Test
    void test_WhenServiceWillInvokeItsMethodThenRepoWillInvokeFindAllForStudents() throws Exception {
        studentServiceImpl.getStudents();
        verify(studentRepository).findAll();
    }

    @Test
    void test_WhenServiceWillInvokeItsMethodThenRepoWillInvokeDeleteByIdForStudent() {
        studentServiceImpl.deleteStudent(anyLong());
        verify(studentRepository).deleteById(anyLong());
    }

    @Test
    void test_WhenServiceWillInvokeItsMethodThenRepoWillInvokeGetOneForStudent() {
        studentServiceImpl.getStudent(anyLong());
        verify(studentRepository).getOne(anyLong());
    }

    @Test
    void test_WhenServiceWillInvokeItsMethodThenRepoWillChangeGroupForStudentAndInvokeSaveForProfessor() {
        Group group = spy(Group.class);
        group.setId(anyLong());

        Student student = spy(Student.class);
        student.setId(anyLong());
        student.setGroup(group);

        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(student));

        studentServiceImpl.transferStudentToAnotherGroup(student, group);

        verify(student).setGroup(group);
        verify(studentRepository).save(student);
    }

    @Test
    void test_WhenServiceWillInvokeItsMethodThenRepoWillChangeStatusForStudentToEXPELLEDInvokeSaveForStudent() {
        Student student = spy(Student.class);
        student.setId(anyLong());

        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(student));

        studentServiceImpl.expelStudent(student);

        verify(student).setCurrentStatus(StatusStudent.EXPELLED);
        verify(studentRepository).save(student);
    }

    @Test
    void test_WhenServiceWillInvokeItsMethodThenRepoWillChangeNameForStudentAndInvokeSaveForStatus() {
        Student student = spy(Student.class);
        student.setId(anyLong());
        student.setName(anyString());

        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(student));

        studentServiceImpl.updateStudent(student);

        verify(student, times(2)).setName(anyString());
        verify(studentRepository).save(student);
    }
}

