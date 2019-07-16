package com.ecdevco.springbootrest.beans;

import com.ecdevco.springbootrest.dto.StudentInformation;
import com.ecdevco.springbootrest.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;

@ManagedBean(name = "StudentReportBean")
@RequestScoped
@Controller
public class StudentReportBean {
    @Autowired
    private StudentService studentService;
    private List<StudentInformation> studentInformationList;

    public List<StudentInformation> getStudentInformationList() {
        try {
            studentInformationList = studentService.getAllStudent();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return studentInformationList;
    }
}
