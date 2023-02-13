package com.egitim.kardesligi.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.egitim.kardesligi.Entity.CreateStudentApplicationModel;
import com.egitim.kardesligi.Operation.StudentApplicationOperations;

@RestController
@RequestMapping("/egitim")
public class StudentApplicationController {
    @PostMapping("/student/application")
    public void CreateStudentApplication(@RequestBody CreateStudentApplicationModel studentApplications) throws Exception{
        StudentApplicationOperations.CreateStudentApplication(studentApplications);
    }
}
