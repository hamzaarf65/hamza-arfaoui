package tn.esprit.studentmanagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import tn.esprit.studentmanagement.entities.Student;
import tn.esprit.studentmanagement.repositories.StudentRepository;
import tn.esprit.studentmanagement.services.StudentService;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @Test
    void getAllStudents_shouldReturnListFromRepository() {
        Student s1 = new Student();
        Student s2 = new Student();

        when(studentRepository.findAll()).thenReturn(List.of(s1, s2));

        List<Student> result = studentService.getAllStudents();

        assertEquals(2, result.size());
        verify(studentRepository).findAll();
    }

    @Test
    void getStudentById_shouldReturnStudent_WhenExists() {
        Student s = new Student();

        when(studentRepository.findById(1L)).thenReturn(Optional.of(s));

        Student result = studentService.getStudentById(1L);

        assertSame(s, result);
        verify(studentRepository).findById(1L);
    }
}
