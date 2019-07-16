package com.ecdevco.springbootrest.utils;

import com.ecdevco.springbootrest.dto.StudentInformation;
import com.ecdevco.springbootrest.entities.Student;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;

import java.io.FileReader;
import java.io.Reader;

public class Utils {

    public static Student jsonFile2Object(String fileName) {
        Gson gson = new Gson();
        Student student = new Student();
        try {
            Reader reader = new FileReader(fileName);
            student = gson.fromJson(reader, Student.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return student;
    }

    public static StudentInformation map2StudentInfo(Student student) {
        StudentInformation studentInformation = null;
        try {
            ModelMapper modelMapper = new ModelMapper();
            studentInformation = modelMapper.map(student, StudentInformation.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return studentInformation != null ? studentInformation : new StudentInformation();
    }

    public static Student map2Student(StudentInformation studentInformation) {
        Student student = new Student();
        try {
            ModelMapper modelMapper = new ModelMapper();
            student = modelMapper.map(studentInformation, Student.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return student != null ? student : new Student();
    }


}
