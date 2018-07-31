package com.example.kwq.oneday.db;

public class Plan {
    private String date;
    private String startTime;
    private String endTime;
    private String planType;
    private String plan;

    public Plan(String date, String startTime, String endTime, String planType, String plan) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.planType = planType;
        this.plan = plan;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
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
