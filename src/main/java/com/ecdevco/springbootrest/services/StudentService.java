package com.ecdevco.springbootrest.services;

import com.ecdevco.springbootrest.dto.StudentInformation;
import com.ecdevco.springbootrest.entities.Student;
import com.ecdevco.springbootrest.repositories.StudentRepository;
import com.ecdevco.springbootrest.utils.Utils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public StudentInformation getStudentById(long studentId) {
        StudentInformation studentInformation = null;
        Optional<Student> st = studentRepository.findById(studentId);
        if (st.isPresent()) {
            Student student = st.get();
            studentInformation = Utils.map2StudentInfo(student);
        }
        return studentInformation != null ? studentInformation : new StudentInformation();
    }

    public List<StudentInformation> getAllStudent() {
        List<StudentInformation> studentInformations = null;
        List<Student> students = studentRepository.findAll();
        if (!students.isEmpty()) {
            for (Student st : students) {
                studentInformations.add(Utils.map2StudentInfo(st));
            }
        }
        return (!studentInformations.isEmpty()) ? studentInformations : new ArrayList<StudentInformation>();
    }

    @Transactional
    public StudentInformation createStudent(StudentInformation studentInformation) {
        Student student = Utils.map2Student(studentInformation);
        if (studentRepository.save(student) != null) {
            return studentInformation;
        } else {
            return new StudentInformation();
        }
    }

}
