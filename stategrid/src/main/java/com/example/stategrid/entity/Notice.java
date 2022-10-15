package com.example.stategrid.entity;


public class Notice {

  private long id;
  private String uuid;
  private java.sql.Date date;
  private String noticeImgUrl;
  private String title;
  private String description;
  private String detail;
  private long pushObject;


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


  public String getNoticeImgUrl() {
    return noticeImgUrl;
  }

  public void setNoticeImgUrl(String noticeImgUrl) {
    this.noticeImgUrl = noticeImgUrl;
  }


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  public String getDetail() {
    return detail;
  }

  public void setDetail(String detail) {
    this.detail = detail;
  }


  public long getPushObject() {
    return pushObject;
  }

  public void setPushObject(long pushObject) {
    this.pushObject = pushObject;
  }

}
