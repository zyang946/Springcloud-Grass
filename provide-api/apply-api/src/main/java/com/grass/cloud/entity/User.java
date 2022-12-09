package com.grass.cloud.entity;

import javax.persistence.*;

import lombok.Data;

@Data
public class User {
    @Column
    private Integer user_id;
    @Column
    private String department;
    @Column
    private String password;
    @Column
    private String phone;
    @Column
    private String senior_id;
    @Column
    private String student_id;
    @Column
    private String user_name;
    
    public Integer getUser_id() {
        return user_id;
    }
    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getSenior_id() {
        return senior_id;
    }
    public void setSenior_id(String senior_id) {
        this.senior_id = senior_id;
    }
    public String getStudent_id() {
        return student_id;
    }
    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }
    public String getUser_name() {
        return user_name;
    }
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
}
