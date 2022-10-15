package com.example.stategrid.entity;


public class Candidate {

  private long id;
  private String uuid;
  private java.sql.Date endDate;
  private java.sql.Date startDate;
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


  public java.sql.Date getEndDate() {
    return endDate;
  }

  public void setEndDate(java.sql.Date endDate) {
    this.endDate = endDate;
  }


  public java.sql.Date getStartDate() {
    return startDate;
  }

  public void setStartDate(java.sql.Date startDate) {
    this.startDate = startDate;
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
