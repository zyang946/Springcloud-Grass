package com.grass.cloud.entity;


import javax.persistence.*;

/**
 *
 * Mybatis 需要加上这些注解才可以使用，不然启动都会报错；
 *
 */
@Entity
@Table(name = "apply")
public class Apply {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private Integer id;

  @Column
  private Integer from_id;

  @Column
  private String from_name;

  @Column
  private Integer to_id;

  @Column
  private String to_name;

  @Column
  private String from_time;

  @Column
  private String to_time;

  @Column
  private String start_time;

  @Column
  private String end_time;

  @Column
  private String destination;

  @Column
  private String reason;

  @Column
  private String comment;

  @Column
  private Integer status;

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
}
