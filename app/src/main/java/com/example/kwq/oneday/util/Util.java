package com.example.kwq.oneday.util;

import android.support.v7.widget.RecyclerView;

import com.example.kwq.oneday.db.Plan;
import com.example.kwq.oneday.db.PlanAdapter;

import org.litepal.crud.DataSupport;

import java.util.Calendar;
import java.util.List;

public class Util {
    public void initPlan(List<Plan> planList) {
        Calendar calendar = Calendar.getInstance();
        String dateString = calendar.get(Calendar.YEAR) + "-" + ((calendar.get(Calendar.MONTH)+1)<10? "0" + (calendar.get(Calendar.MONTH)+1) : (calendar.get(Calendar.MONTH)+1)) + "-" + (calendar.get(Calendar.DAY_OF_MONTH)<10? "0" + calendar.get(Calendar.DAY_OF_MONTH):calendar.get(Calendar.DAY_OF_MONTH));
        planList = DataSupport.where("date = ?", dateString).order("rank").find(Plan.class);
    }

    public void setAdapter(List<Plan> planList, RecyclerView recyclerView) {
        PlanAdapter adapter = new PlanAdapter(planList);
        recyclerView.setAdapter(adapter);
    }
}
