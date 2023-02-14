package com.egitim.kardesligi.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.egitim.kardesligi.Entity.CreateStudentApplicationModel;
import com.egitim.kardesligi.Operation.StudentApplicationOperations;

@RestController
@RequestMapping("/egitim")
public class StudentApplicationController {
    
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/student/application")
    public ResponseEntity<String> CreateStudentApplication(@RequestBody CreateStudentApplicationModel studentApplications) throws Exception{
        if(StudentApplicationOperations.CreateStudentApplication(studentApplications)){
            return new ResponseEntity<>("Başvurunuz başarılı bir şekilde tamamlanmıştır.",HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Bu TC sistemde mevcuttur.",HttpStatus.CONFLICT);
        }
    }
}
