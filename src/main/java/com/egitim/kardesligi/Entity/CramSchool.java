package com.egitim.kardesligi.Entity;


import java.util.Date;

public class CramSchool {

    private Long id;
    private String cramSchoolName;
    private Long districtId;
    private Long createdBy;
    private Date createdDate;
    private Long modifiedBy;
    private Date modifiedDate;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCramSchoolName() {
        return cramSchoolName;
    }
    public void setCramSchoolName(String cramSchoolName) {
        this.cramSchoolName = cramSchoolName;
    }
    public Long getDistrictId() {
        return districtId;
    }
    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }
    public Long getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }
    public Date getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    public Long getModifiedBy() {
        return modifiedBy;
    }
    public void setModifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
    public Date getModifiedDate() {
        return modifiedDate;
    }
    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
