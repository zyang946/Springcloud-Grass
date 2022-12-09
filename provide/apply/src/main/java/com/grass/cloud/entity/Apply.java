package com.grass.cloud.entity;


import javax.persistence.*;

import lombok.Data;

/**
 *
 * Mybatis 需要加上这些注解才可以使用，不然启动都会报错；
 *
 */
@Entity
@Table(name = "apply")
@Data
public class Apply {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private Integer id; // aid

  @Column
  private Integer from_id; // 学生的uid

  @Column
  private String student_id; // 学生的sid

  @Column
  private String from_name; // 学生的name

  @Column
  private String department; // 学生的院系

  @Column
  private String phone; // 学生的联系方式

  @Column
  private Integer to_id; // 辅导员的uid

  @Column
  private String to_name; // 辅导员的name

  @Column
  private String from_time; // 提交申请的时间

  @Column
  private String to_time; // 审批的时间

  @Column
  private String start_time; // 申请出校开始时间

  @Column
  private String end_time; // 申请出校结束时间

  @Column
  private String destination; // 目的地

  @Column
  private String reason; // 理由

  @Column
  private String comment; // 审批意见

  @Column
  private Integer status; // 审批状态

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getFrom_id() {
    return from_id;
  }

  public void setFrom_id(Integer from_id) {
    this.from_id = from_id;
  }

  public Integer getTo_id() {
    return to_id;
  }

  public void setTo_id(Integer to_id) {
    this.to_id = to_id;
  }

  public String getFrom_time() {
    return from_time;
  }

  public void setFrom_time(String from_time) {
    this.from_time = from_time;
  }

  public String getTo_time() {
    return to_time;
  }

  public void setTo_time(String to_time) {
    this.to_time = to_time;
  }

  public String getStart_time() {
    return start_time;
  }

  public void setStart_time(String start_time) {
    this.start_time = start_time;
  }

  public String getEnd_time() {
    return end_time;
  }

  public void setEnd_time(String end_time) {
    this.end_time = end_time;
  }

  public String getDestination() {
    return destination;
  }

  public void setDestination(String destination) {
    this.destination = destination;
  }

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public String getFrom_name() {
    return from_name;
  }

  public void setFrom_name(String from_name) {
    this.from_name = from_name;
  }

  public String getTo_name() {
    return to_name;
  }

  public void setTo_name(String to_name) {
    this.to_name = to_name;
  }


  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getStudent_id() {
    return student_id;
  }

  public void setStudent_id(String student_id) {
    this.student_id = student_id;
  }
  
}
