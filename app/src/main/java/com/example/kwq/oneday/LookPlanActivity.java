package com.example.kwq.oneday;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
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
    private RecyclerView recyclerView;
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

        FloatingActionButton newPlan = findViewById(R.id.lp_newPlan);

        newPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LookPlanActivity.this, NewPlanActivity.class);
                intent.putExtra("date", date);
                intent.putExtra("id", -1);
                startActivity(intent);
            }
        });

        //初始化Plan表
        initPlan();
        if (planList.size() == 0) {
            TextView noPlan = findViewById(R.id.noPlan);
            noPlan.setVisibility(View.VISIBLE);
        }

        else {
            recyclerView = findViewById(R.id.lp_allPlan);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
            PlanAdapter adapter = new PlanAdapter(planList);
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        initPlan();
        PlanAdapter adapter = new PlanAdapter(planList);
        recyclerView.setAdapter(adapter);
    }

    private void initPlan() {
        planList = DataSupport.where("date = ?", date).order("rank").find(Plan.class);
    }

}
