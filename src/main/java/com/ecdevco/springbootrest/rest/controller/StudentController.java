package com.ecdevco.springbootrest.rest.controller;

import com.ecdevco.springbootrest.dto.StudentInformation;
import com.ecdevco.springbootrest.services.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/api/v1")
public class StudentController {

    Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @ResponseBody
    public ResponseEntity<StudentInformation> getStudentById(@Valid @PathVariable(value = "id") long studentId) {
        StudentInformation studentInformation;
        try {
            studentInformation = studentService.getStudentById(studentId);
            logger.info("Get student by id ({}) result value: ({})"
                    , studentId, studentInformation.getFirstName() + " " + studentInformation.getLastName());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();

        }
        return studentInformation.getId() == null ? (ResponseEntity.noContent().build()) : (ResponseEntity.ok().body(studentInformation));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/register/student")
    @ResponseBody
    public ResponseEntity<StudentInformation> createStudent(@Valid @RequestBody StudentInformation studentInformation) {
        logger.info("Register student ({})"
                , studentInformation.getFirstName() + " " + studentInformation.getLastName());
        StudentInformation si =  studentService.createStudent(studentInformation);
        return si.getFirstName() == null ? (ResponseEntity.noContent().build()) : (ResponseEntity.ok().body(si));
    }
}
