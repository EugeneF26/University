package com.project.university.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void test_() {
//        Student student = Student.builder().name("").surname("").build();

//        Long savedInDb = entityManager.persist(student).getId();
//        Long getFromDb = studentRepository.findById(1L);

//        assertThat(getFromDb).isEqualTo(savedInDb);
    }
}

