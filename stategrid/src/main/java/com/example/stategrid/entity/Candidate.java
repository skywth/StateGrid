package com.example.stategrid.entity;


public class Candidate {

  private long id;
  private String uuid;
  private java.sql.Date date;
  private long companyId;
  private long quota;


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

}
