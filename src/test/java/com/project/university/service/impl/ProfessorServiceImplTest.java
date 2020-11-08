package com.project.university.service.impl;

import com.project.university.model.Professor;
import com.project.university.model.StatusProfessor;
import com.project.university.model.StatusStudent;
import com.project.university.model.Student;
import com.project.university.repository.GroupRepository;
import com.project.university.repository.ProfessorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class ProfessorServiceImplTest {

    @InjectMocks
    private ProfessorServiceImpl professorServiceImpl;

    @Mock
    private ProfessorRepository professorRepository;

    @Test
    void getProfessor() throws Exception {
        professorServiceImpl.getProfessor(anyLong());
        verify(professorRepository, times(1)).getOne(anyLong());
    }

    @Test
    void getProfessors() throws Exception {
        professorServiceImpl.getProfessors();
        verify(professorRepository, times(1)).findAll();
    }

    @Test
    void deleteProfessor() throws Exception {
        professorServiceImpl.deleteProfessor(anyLong());
        verify(professorRepository, times(1)).deleteById(anyLong());
    }
}

