package com.example.stategrid.entity;


public class Gap {

  private long id;
  private String uuid;
  private java.sql.Date date;
  private long planGap;
  private long gap;


  public long getId() {
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


  public long getPlanGap() {
    return planGap;
  }

  public void setPlanGap(long planGap) {
    this.planGap = planGap;
  }


  public long getGap() {
    return gap;
  }

  public void setGap(long gap) {
    this.gap = gap;
  }

}
