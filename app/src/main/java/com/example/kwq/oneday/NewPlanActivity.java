package com.example.kwq.oneday;

import android.app.TimePickerDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.kwq.oneday.db.Plan;

import org.litepal.LitePal;


public class NewPlanActivity extends AppCompatActivity {

    private EditText npPlanType;
    private EditText npPlan;
    private TextView npTime;
    private String startTime;
    private String endTime;
    private String hour;
    private String minute;
    private int rank;
    private int rank1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_plan);

        npPlanType = findViewById(R.id.np_planType);
        npPlan = findViewById(R.id.np_plan);

        npTime = findViewById(R.id.np_time);

        FloatingActionButton setStratTime = findViewById(R.id.np_setStartTime);
        FloatingActionButton setEndTime = findViewById(R.id.np_setEndTime);
        FloatingActionButton newPlan = findViewById(R.id.np_newPlan);


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
                        npTime.setText("S:" + startTime + " " + "E:" + endTime);
                    }
                }, 0, 0, true).show();
            }
        });

        newPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (startTime == null || endTime == null) {
                    Toast.makeText(NewPlanActivity.this, "创建计划失败！开始时间或结束时间不能为空", Toast.LENGTH_SHORT).show();
                }
                else if (rank >= rank1) {
                    Toast.makeText(NewPlanActivity.this, "创建计划失败！开始时间不能小于等于结束时间", Toast.LENGTH_SHORT).show();
                }
                else {
                    String planType = npPlanType.getText().toString();
                    String planContent = npPlan.getText().toString();
                    if (planType.equals("")|| planContent.equals(""))
                    {
                        Toast.makeText(NewPlanActivity.this, "创建计划失败！计划类型或计划内容不能为空", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        LitePal.getDatabase();
                        Plan plan = new Plan();
                        plan.setRank(rank);
                        plan.setDate("2018-8-4");
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
        });
    }
}
