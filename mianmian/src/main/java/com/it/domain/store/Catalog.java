package com.it.domain.store;

import java.util.Date;

public class Catalog {
    private String id;
    private String name;
    private String remark;
    private String state;
    private Date createTime;
    private String courseId;
    private Course course;

    public Catalog() {
    }

    public Catalog(String id, String name, String remark, String state, Date createTime, String courseId, Course course) {
        this.id = id;
        this.name = name;
        this.remark = remark;
        this.state = state;
        this.createTime = createTime;
        this.courseId = courseId;
        this.course = course;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                ", state='" + state + '\'' +
                ", createTime=" + createTime +
                ", courseId='" + courseId + '\'' +
                ", course=" + course +
                '}';
    }
}
