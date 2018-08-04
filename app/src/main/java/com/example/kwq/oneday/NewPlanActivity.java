package com.example.kwq.oneday;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import com.example.kwq.oneday.db.Plan;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.List;


public class NewPlanActivity extends AppCompatActivity {

    private EditText npPlanType;
    private EditText npPlan;
    private Toolbar toolbar;
    private TextView npTime;
    private String startTime;
    private String endTime;
    private String hour;
    private String minute;
    private String date;
    private int id;
    private int rank;
    private int rank1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_plan);

        //取出SelectDate活动传来的date
        Intent intent = getIntent();
        date = intent.getStringExtra("date");
        id = intent.getIntExtra("id", 0);

        //将data显示在toolbar上
        toolbar = findViewById(R.id.np_toolbar);
        toolbar.setTitle("OneDay " + " " + date);

        npPlanType = findViewById(R.id.np_planType);
        npPlan = findViewById(R.id.np_plan);

        npTime = findViewById(R.id.np_time);

        FloatingActionButton setStratTime = findViewById(R.id.np_setStartTime);
        FloatingActionButton setEndTime = findViewById(R.id.np_setEndTime);
        FloatingActionButton newPlan = findViewById(R.id.np_newPlan);

        //修改计划的情况
        if(id != -1) {
            String idString = String.valueOf(id);
            List<Plan> plans = DataSupport.where("id = ?", idString).find(Plan.class);
            for (Plan plan: plans) {
                npTime.setText("开始时间:" + plan.getStartTime() + " " + "结束时间:" + plan.getEndTime());
                startTime = plan.getStartTime();
                endTime = plan.getEndTime();
                npPlanType.setText(plan.getPlanType());
                npPlan.setText(plan.getPlan());
                rank = 1;
                rank1 = 2;
            }
        }

        setStratTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new TimePickerDialog(NewPlanActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        rank = i*60 + i1;
                        hour = String.valueOf(i);
                        if (i1 < 10) {
                            minute = "0" + i1;
                        }
                        else {
                            minute = String.valueOf(i1);
                        }
                        startTime = hour + ":" + minute;
                    }
                }, 0, 0, true).show();
            }
        });

        setEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new TimePickerDialog(NewPlanActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        rank1 = i*60 + i1;
                        hour = String.valueOf(i);
                        if (i1 < 10) {
                            minute = "0" + i1;
                        }
                        else {
                            minute = String.valueOf(i1);
                        }
                        endTime = hour + ":" + minute;
                        npTime.setText("开始时间:" + startTime + " " + "结束时间:" + endTime);
                    }
                }, 0, 0, true).show();
            }
        });

        newPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //新增计划的情况
                if (id == -1) {
                    //时间不能为空
                    if (startTime == null || endTime == null) {
                        Toast.makeText(NewPlanActivity.this, "创建计划失败！开始时间或结束时间不能为空", Toast.LENGTH_SHORT).show();
                    }
                    //开始时间不能大于结束时间
                    else if (rank >= rank1) {
                        Toast.makeText(NewPlanActivity.this, "创建计划失败！开始时间不能在结束时间后", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        String planType = npPlanType.getText().toString();
                        String planContent = npPlan.getText().toString();
                        //计划类型和计划内容不能为空
                        if (planType.equals("") || planContent.equals("")) {
                            Toast.makeText(NewPlanActivity.this, "创建计划失败！计划类型或计划内容不能为空", Toast.LENGTH_SHORT).show();
                        } else {
                            LitePal.getDatabase();
                            Plan plan = new Plan();
                            plan.setRank(rank);
                            plan.setDate(date);
                            plan.setStartTime(startTime);
                            plan.setEndTime(endTime);
                            plan.setPlanType(planType);
                            plan.setPlan(planContent);
                            plan.save();
                            Toast.makeText(NewPlanActivity.this, "创建计划成功！", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                }
                //修改计划的情况
                else {
                    //时间不能为空
                    if (startTime == null || endTime == null) {
                        Toast.makeText(NewPlanActivity.this, "修改计划失败！开始时间或结束时间不能为空", Toast.LENGTH_SHORT).show();
                    }
                    //开始时间不能大于结束时间
                    else if (rank >= rank1) {
                        Toast.makeText(NewPlanActivity.this, "修改计划失败！开始时间不能在结束时间后", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        String planType = npPlanType.getText().toString();
                        String planContent = npPlan.getText().toString();
                        //计划类型和计划内容不能为空
                        if (planType.equals("") || planContent.equals("")) {
                            Toast.makeText(NewPlanActivity.this, "修改计划失败！计划类型或计划内容不能为空", Toast.LENGTH_SHORT).show();
                        } else {
                            Plan plan = new Plan();
                            plan.setRank(rank);
                            plan.setDate(date);
                            plan.setStartTime(startTime);
                            plan.setEndTime(endTime);
                            plan.setPlanType(planType);
                            plan.setPlan(planContent);
                            String idString = String.valueOf(id);
                            plan.updateAll("id = ?", idString);
                            Toast.makeText(NewPlanActivity.this, "修改计划成功！", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                }
            }
        });
    }
}
