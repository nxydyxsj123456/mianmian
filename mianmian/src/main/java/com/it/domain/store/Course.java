package com.it.domain.store;

import java.util.Date;

public class Course {
    private String id;
    private String name;
    private String remark;
    private String state;
    private Date createTime;

    public Course() {
    }

    public Course(String id, String name, String remark, String state, Date createTime) {
        this.id = id;
        this.name = name;
        this.remark = remark;
        this.state = state;
        this.createTime = createTime;
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
}
