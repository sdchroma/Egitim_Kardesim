package com.egitim.kardesligi.Entity;

import java.util.Date;

public class CramSchoolQuota {

    private Long id;
    private Long cramSchoolId;
    private Long classLevelId;
    private String classLevelName;
    private int existingQuota;
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
    public String getClassLevelName() {
        return classLevelName;
    }
    public void setClassLevelName(String classLevelName) {
        this.classLevelName = classLevelName;
    }
    public int getExistingQuota() {
        return existingQuota;
    }
    public void setExistingQuota(int existingQuota) {
        this.existingQuota = existingQuota;
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
