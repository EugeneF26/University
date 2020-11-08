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

import java.util.Optional;

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
    void acceptNewProfessor() throws Exception {
        Professor professor = mock(Professor.class);

        professorServiceImpl.acceptNewProfessor(professor);

        verify(professor).setCurrentStatus(StatusProfessor.WORKS);
        verify(professorRepository).save(professor);
    }

    @Test
    void getProfessor() {
        professorServiceImpl.getProfessor(anyLong());
        verify(professorRepository).getOne(anyLong());
    }

    @Test
    void getProfessors() {
        professorServiceImpl.getProfessors();
        verify(professorRepository).findAll();
    }

    @Test
    void deleteProfessor() {
        professorServiceImpl.deleteProfessor(anyLong());
        verify(professorRepository).deleteById(anyLong());
    }

    @Test
    void layOfProfessor(){
        Professor professor = spy(Professor.class);
        professor.setId(anyLong());

        when(professorRepository.findById(anyLong())).thenReturn(Optional.of(professor));

        professorServiceImpl.layOfProfessor(professor);

        verify(professor).setCurrentStatus(StatusProfessor.FIRED);
        verify(professorRepository).save(professor);
    }

    @Test
    void updateProfessor(){
        Professor professor = spy(Professor.class);
        professor.setId(anyLong());
        professor.setName(anyString());
        professor.setPatronymic(anyString());

        when(professorRepository.findById(anyLong())).thenReturn(Optional.of(professor));

        professorServiceImpl.updateProfessor(professor);

        verify(professor, times(2)).setName(anyString());
        verify(professor, times(2)).setPatronymic(anyString());
        verify(professorRepository).save(professor);
    }
}

