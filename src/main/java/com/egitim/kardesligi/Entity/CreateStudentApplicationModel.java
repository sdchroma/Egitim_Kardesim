package com.egitim.kardesligi.Entity;

import java.util.Date;

public class CreateStudentApplicationModel {
    
    private String identityNumber;
    private String name;
    private String surname;
    private Date birthday;
    private String phoneNumber;
    private Long cramSchoolId;
    private Long classLevelId;

    public String getIdentityNumber() {
        return identityNumber;
    }
    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public Long getCramSchoolId() {
        return cramSchoolId;
    }
    public void setCramSchoolId(Long cramSchoolId) {
        this.cramSchoolId = cramSchoolId;
    }
    public Long getClassLevelId() {
        return classLevelId;
    }
    public void setClassLevelId(Long classLevelId) {
        this.classLevelId = classLevelId;
    }
}
