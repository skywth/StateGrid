package com.example.stategrid.entity;


public class Prearrange {

  private long id;
  private String uuid;
  private java.sql.Date date;
  private long companyId;
  private long quota;
  private java.sql.Timestamp check;
  private long checkId;


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


  public long getCompanyId() {
    return companyId;
  }

  public void setCompanyId(long companyId) {
    this.companyId = companyId;
  }


  public long getQuota() {
    return quota;
  }

  public void setQuota(long quota) {
    this.quota = quota;
  }


  public java.sql.Timestamp getCheck() {
    return check;
  }

  public void setCheck(java.sql.Timestamp check) {
    this.check = check;
  }


  public long getCheckId() {
    return checkId;
  }

  public void setCheckId(long checkId) {
    this.checkId = checkId;
  }

}
