package com.example.kwq.oneday;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

import java.util.Calendar;


public class SelectDateActivity extends AppCompatActivity {

    private CalendarView mCalendarView;
    private Button sdButton;
    private String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_date);

        //启用HomeAsUp按钮
        Toolbar toolbar = findViewById(R.id.sd_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        mCalendarView = findViewById(R.id.sd_calendarView);
        sdButton = findViewById(R.id.sd_Button);

        //获取当前日期显示在Button上
        Calendar calendar = Calendar.getInstance();
        date = calendar.get(Calendar.YEAR) + "-" + ((calendar.get(Calendar.MONTH)+1)<10? "0" + (calendar.get(Calendar.MONTH)+1) : (calendar.get(Calendar.MONTH)+1)) + "-" + (calendar.get(Calendar.DAY_OF_MONTH)<10? "0" + calendar.get(Calendar.DAY_OF_MONTH):calendar.get(Calendar.DAY_OF_MONTH));
        sdButton.setText("查看/编辑" + date + "的计划");
        chooseCalender();

        //设置sdButton点击事件，启动LookPlan活动
        sdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectDateActivity.this, LookPlanActivity.class);
                intent.putExtra("date", date);
                startActivity(intent);
                finish();
            }
        });
    }

    //处理HomeAsUp点击事件，点击返回上个活动
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //获取所选日期显示在Button上
    private void chooseCalender() {
        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                date = year + "-" + ((month + 1) < 10 ? "0" + (month + 1) : (month + 1)) + "-" + (dayOfMonth < 10 ? "0" + dayOfMonth : dayOfMonth);
                sdButton.setText("编辑" + date + "的计划"); }
        });
    }

}
