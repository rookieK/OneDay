package com.example.kwq.oneday;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.kwq.oneday.db.Plan;
import com.example.kwq.oneday.db.PlanAdapter;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;


public class LookPlanActivity extends AppCompatActivity {

    private String date;
    private Toolbar toolbar;
    private List<Plan> planList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_look_plan);

        //取出SelectDate活动传来的date
        Intent intent = getIntent();
        date = intent.getStringExtra("date");

        //将data显示在toolbar上
        toolbar = findViewById(R.id.lp_toolbar);
        toolbar.setTitle("OneDay " + " " + date);

        //初始化Plan表
        initPlan();
        if (planList.size() == 0) {
            TextView noPlan = findViewById(R.id.noPlan);
            noPlan.setVisibility(View.VISIBLE);
        }

        else {
            RecyclerView recyclerView = findViewById(R.id.lp_allPlan);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
            PlanAdapter adapter = new PlanAdapter(planList);
            recyclerView.setAdapter(adapter);
        }
    }
    private void initPlan() {
       planList = DataSupport.where("date = ?", "2018-8-4").order("rank").find(Plan.class);
    }

}
