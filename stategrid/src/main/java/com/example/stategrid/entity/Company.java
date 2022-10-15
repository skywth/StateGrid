package com.example.stategrid.entity;

import lombok.ToString;

@ToString
public class Company {

  private long id=-1;
  private String uuid;
  private long accountId;
  private String companyCode;
  private String name;
  private String type;
  private long baseline=-1;
  private long continuity=-1;
  private long deleted;


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


  public long getAccountId() {
    return accountId;
  }

  public void setAccountId(long accountId) {
    this.accountId = accountId;
  }


  public String getCompanyCode() {
    return companyCode;
  }

  public void setCompanyCode(String companyCode) {
    this.companyCode = companyCode;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }


  public long getBaseline() {
    return baseline;
  }

  public void setBaseline(long baseline) {
    this.baseline = baseline;
  }


  public long getContinuity() {
    return continuity;
  }

  public void setContinuity(long continuity) {
    this.continuity = continuity;
  }


  public long getDeleted() {
    return deleted;
  }

  public void setDeleted(long deleted) {
    this.deleted = deleted;
  }

}
