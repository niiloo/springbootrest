package com.ecdevco.springbootrest;


import com.ecdevco.springbootrest.dto.StudentInformation;
import com.ecdevco.springbootrest.entities.Student;
import com.ecdevco.springbootrest.repositories.StudentRepository;
import com.ecdevco.springbootrest.services.StudentService;
import com.ecdevco.springbootrest.utils.Utils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootRestServiceTests {


    @MockBean
    private StudentRepository studentRepositoryMock;

    @Autowired
    private StudentService studentService;

    @Test
    public void testGetStudentById() {
        Student student = Utils.jsonFile2Object("student.txt");
        when(studentRepositoryMock.findById(1L)).thenReturn(Optional.of(student));

        StudentInformation si = studentService.getStudentById(1);

        Assert.assertFalse(si.getId() == 0);

        Assert.assertTrue(si.getId() == student.getId());
        Assert.assertTrue(si.getFirstName() == student.getFirstName());
        Assert.assertTrue(si.getLastName() == student.getLastName());
        Assert.assertTrue(si.getEmail() == student.getEmail());
        Assert.assertTrue(si.getCreatedBy() == student.getCreatedBy());
        Assert.assertTrue(si.getCreatedAt() == student.getCreatedAt());

    }

    @Test
    public void tesCreateStudent() {
        Student student = new Student();
        when(studentRepositoryMock.save(any(Student.class))).thenReturn(student);
        StudentInformation si = Utils.map2StudentInfo(student);
        Assert.assertNotNull(studentService.createStudent(si));
    }
}
