package com.example.kwq.oneday;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CalendarView;

import java.util.Calendar;


public class SetPlan extends AppCompatActivity {

    CalendarView mCalendarView;
    Button spButton;
    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_plan);

        //启用HomeAsUp按钮
        Toolbar toolbar = findViewById(R.id.sp_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        mCalendarView = findViewById(R.id.sp_calendarView);
        spButton = findViewById(R.id.sp_Button);

        Calendar calender = Calendar.getInstance();
        date = calender.get(Calendar.YEAR) + "-" + ((calender.get(Calendar.MONTH)+1)<10? "0" + (calender.get(Calendar.MONTH)+1) : (calender.get(Calendar.MONTH)+1)) + "-" + (calender.get(Calendar.DAY_OF_MONTH)<10? "0" + calender.get(Calendar.DAY_OF_MONTH):calender.get(Calendar.DAY_OF_MONTH));
        spButton.setText("编辑" + date + "的计划");


        chooseCalender();
    }

    //处理HomeAsUp点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void chooseCalender() {
        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                date = year + "-" + ((month + 1) < 10 ? "0" + (month + 1) : (month + 1)) + "-" + (dayOfMonth < 10 ? "0" + dayOfMonth : dayOfMonth);
                spButton.setText("编辑" + date + "的计划");

            }
        });
    }

}
