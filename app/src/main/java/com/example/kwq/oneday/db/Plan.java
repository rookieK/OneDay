package com.example.kwq.oneday.db;

public class Plan {
    private String sTime;
    private String eTime;
    private String planType;
    private String plan;

    public Plan(String sTime, String eTime, String planType, String plan) {
        this.sTime = sTime;
        this.eTime = eTime;
        this.planType = planType;
        this.plan = plan;
    }


    public String getSTime() {
        return sTime;
    }

    public void setSTime(String sTime) {
        this.sTime = sTime;
    }

    public String getETime() {
        return eTime;
    }

    public void setETime(String eTime) {
        this.eTime = eTime;
    }

    public String getPlanType() {
        return planType;
    }

    public void setPlanType(String planType) {
        this.planType = planType;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }
}
