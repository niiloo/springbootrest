package com.ecdevco.springbootrest;

import com.ecdevco.springbootrest.dto.StudentInformation;
import com.ecdevco.springbootrest.entities.Student;
import com.ecdevco.springbootrest.utils.Utils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootRestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringBootRestControllerUnitTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port +"/api/v1";
    }

    @Test
    public void testGetStudentById() {
        StudentInformation studentInformation = testRestTemplate.getForObject(getRootUrl() + "/1", StudentInformation.class);

        Assert.assertFalse(studentInformation.getId() == 0);
        Assert.assertTrue(studentInformation.getId() != 0);
    }

    @Test
    public void testCreateStudent(){
        Student student = Utils.jsonFile2Object("student.txt");
        StudentInformation studentInformation = Utils.map2StudentInfo(student);
        studentInformation.setId(null);
        ResponseEntity<StudentInformation> postResponse = testRestTemplate.postForEntity(getRootUrl()+"/register/student", studentInformation, StudentInformation.class);
        Assert.assertNotNull(postResponse);
        Assert.assertNotNull(postResponse.getBody());
    }
}
