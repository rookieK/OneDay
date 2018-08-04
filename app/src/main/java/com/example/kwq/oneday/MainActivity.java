package com.example.kwq.oneday;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;



public class MainActivity extends AppCompatActivity {

    private List<Plan> planList = new ArrayList<>();
    private TextView time;
    private TextView date;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {

        public void handleMessage(Message msg) {
            switch (msg.what) {
                //接收子线程信号更新时间和日期
                case 1:
                    Calendar calendar = Calendar.getInstance();
                    time.setText(calendar.get(Calendar.HOUR_OF_DAY) + ":" + (calendar.get(Calendar.MINUTE)<10?  "0" + calendar.get(Calendar.MINUTE) : calendar.get(Calendar.MINUTE)));
                    String week = String.valueOf(calendar.get(Calendar.DAY_OF_WEEK));
                    if("1".equals(week)){
                        week ="日";
                    }else if("2".equals(week)){
                        week ="一";
                    }else if("3".equals(week)){
                        week ="二";
                    }else if("4".equals(week)){
                        week ="三";
                    }else if("5".equals(week)){
                        week ="四";
                    }else if("6".equals(week)){
                        week ="五";
                    }else if("7".equals(week)) {
                        week = "六";
                    }

                    String dateString = (calendar.get(Calendar.MONTH)+1) + "月" + calendar.get(Calendar.DAY_OF_MONTH) + "日" + " " + "星期" + week;
                    date.setText(dateString);
                    break;
                default:
                    break;
            }
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        time = findViewById(R.id.toolbar_time);
        date = findViewById(R.id.toolbar_date);
        //一秒获取一次系统时间
        new Thread(new Runnable() {
            @Override
            public void run() {
                do {
                    try {
                        Thread.sleep(1000);
                        Message message = new Message();
                        message.what = 1;
                        handler.sendMessage(message); // 将Message对象发送出去
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } while (true);
            }
        }).start();

        //初始化Plan表
        initPlan();
        if (planList.size() == 0) {
            TextView noPlan = findViewById(R.id.noPlan);
            noPlan.setVisibility(View.VISIBLE);
        }

        else {
            RecyclerView recyclerView = findViewById(R.id.allPlan);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
            PlanAdapter adapter = new PlanAdapter(planList);
            recyclerView.setAdapter(adapter);
        }

        //Intent SelectDate活动
        FloatingActionButton setPlan = findViewById(R.id.setPlan);
        setPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SelectDateActivity.class);
                startActivity(intent);
            }
        });
    }


    private void initPlan() {
        String sTime = "11:00";
        String eTime = "11:30";
        String planType = "工作";
        String plan = "酒店客房就开始的房价快速反击肯定是九分";
        for (int i = 0; i < 10; i++){
            Plan p = new Plan("0", sTime, eTime, planType, plan);
            planList.add(p);
        }
    }

}
