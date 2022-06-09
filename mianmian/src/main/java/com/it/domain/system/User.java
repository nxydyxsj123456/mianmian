package com.it.domain.system;

import java.util.Date;

public class User {
    private String id;
    private String email;
    private String userName;
    private String password;
    private Long state;
    private String gender;
    private String telephone;
    private Date birthday;
    private Date joinDate;
    private String deptId;
    private Dept dept;

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public User() {
    }

    public User(String id, String email, String userName, String password, Long state, String gender, String telephone, Date birthday, Date joinDate, String deptId) {
        this.id = id;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.state = state;
        this.gender = gender;
        this.telephone = telephone;
        this.birthday = birthday;
        this.joinDate = joinDate;
        this.deptId = deptId;

    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", state=" + state +
                ", gender='" + gender + '\'' +
                ", telephone='" + telephone + '\'' +
                ", birthday=" + birthday +
                ", joinDate=" + joinDate +
                ", deptId='" + deptId + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getState() {
        return state;
    }

    public void setState(Long state) {
        this.state = state;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }
}
