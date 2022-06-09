package com.it.domain.store;

import java.util.Date;

public class Question {
    private String id;

    private String companyId;
    private String catalogId;

    private String remark;

    private String subject;

    private String analysis;
    private String picture;

    private String type;
    private String difficulty;

    private String isClassic;
    private String state;

    private String reviewStatus;
    private Date createTime;

    private Company company;
    private Catalog catalog;

    public Question() {
    }

    public String getId() {
        return id;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id='" + id + '\'' +
                ", companyId='" + companyId + '\'' +
                ", catalogId='" + catalogId + '\'' +
                ", remark='" + remark + '\'' +
                ", subject='" + subject + '\'' +
                ", analysis='" + analysis + '\'' +
                ", type='" + type + '\'' +
                ", difficulty='" + difficulty + '\'' +
                ", isClassic='" + isClassic + '\'' +
                ", state='" + state + '\'' +
                ", reviewStatus='" + reviewStatus + '\'' +
                ", createTime=" + createTime +
                ", company=" + company +
                ", catalog=" + catalog +
                '}';
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(String catalogId) {
        this.catalogId = catalogId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getIsClassic() {
        return isClassic;
    }

    public void setIsClassic(String isClassic) {
        this.isClassic = isClassic;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(String reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public Question(String id, String companyId, String catalogId, String remark, String subject, String analysis, String picture, String type, String difficulty, String isClassic, String state, String reviewStatus, Date createTime, Company company, Catalog catalog) {
        this.id = id;
        this.companyId = companyId;
        this.catalogId = catalogId;
        this.remark = remark;
        this.subject = subject;
        this.analysis = analysis;
        this.picture = picture;
        this.type = type;
        this.difficulty = difficulty;
        this.isClassic = isClassic;
        this.state = state;
        this.reviewStatus = reviewStatus;
        this.createTime = createTime;
        this.company = company;
        this.catalog = catalog;
    }
}
