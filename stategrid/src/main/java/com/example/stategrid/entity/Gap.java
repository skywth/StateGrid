package com.example.stategrid.entity;


public class Gap {

  private Long id= Long.valueOf(-1);
  private String uuid;
  private java.sql.Date date;
  private Long plan_gap;
  private Long gap;


  public Long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }


  public java.sql.Date getDate() {
    return date;
  }

  public void setDate(java.sql.Date date) {
    this.date = date;
  }


  public Long getPlan_gap() {
    return plan_gap;
  }

  public void setPlan_gap(long plan_gap) {
    this.plan_gap = plan_gap;
  }


  public Long getGap() {
    return gap;
  }

  public void setGap(long gap) {
    this.gap = gap;
  }

}
