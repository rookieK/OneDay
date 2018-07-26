package com.example.kwq.oneday;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.kwq.oneday.db.Plan;
import com.example.kwq.oneday.db.PlanAdapter;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private List<Plan> planList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        initPlan();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.all_plan);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        PlanAdapter adapter = new PlanAdapter(planList);
        recyclerView.setAdapter(adapter);
    }

    private void initPlan() {
        String sTime = "11:00";
        String eTime = "11:30";
        String planType = "工作";
        String plan = "酒店客房就开始的房价快速反击肯定是九分";
        for (int i = 0; i < 10; i++){
            Plan p = new Plan(sTime, eTime, planType, plan);
            planList.add(p);
        }
    }

}
