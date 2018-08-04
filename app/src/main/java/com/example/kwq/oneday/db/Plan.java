package com.example.kwq.oneday.db;

import org.litepal.crud.DataSupport;

public class Plan extends DataSupport {
    private int id;
    private int rank;
    private String date;
    private String startTime;
    private String endTime;
    private String planType;
    private String plan;

    public Plan() {}

    public Plan(String date, String startTime, String endTime, String planType, String plan) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.planType = planType;
        this.plan = plan;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
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
