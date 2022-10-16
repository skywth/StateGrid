package com.example.stategrid.entity;

public class CompanyAllInfo_entity {
    private long id;
    private String name;
    private String type;
    private long baseline;
    private String check;
    private long quotaValue;
    private Integer quotaPercentage;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    public long getQuotaValue() {
        return quotaValue;
    }

    public void setQuotaValue(long quotaValue) {
        this.quotaValue = quotaValue;
    }

    public Integer getQuotaPercentage() {
        return quotaPercentage;
    }

    public void setQuotaPercentage(Integer quotaPercentage) {
        this.quotaPercentage = quotaPercentage;
    }
}

