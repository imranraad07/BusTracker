package com.example.imran.simpleapp;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import java.util.*;
import java.sql.* ;
import android.content.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;
import android.widget.AdapterView.*;
import java.text.*;
import java.text.SimpleDateFormat ;

import  com.example.imran.simpleapp.* ;
import  com.example.imran.simpleapp.allroots.* ;

public class PredictedTimeActivity extends Activity {
    private TimePicker timePicker1;
    private TextView time1 ;
    private String format = "";
    int hour , min ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.predicted_time);

        DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        java.sql.Time timeValue ;
        try {
            timeValue = new java.sql.Time(formatter.parse(MyHabizabiClass.getMyPredictionTime()).getTime());
            timePicker1 = (TimePicker) findViewById(R.id.timePicker1);
            time1 = (TextView) findViewById(R.id.textView1);
            hour = timeValue.getHours() ;
            min = timeValue.getMinutes() ;
            showTime(hour, min);

        }catch (ParseException ex){
            ex.printStackTrace();
        }
    }

    public void setTime(View view) {
        int hour = timePicker1.getCurrentHour();
        int min = timePicker1.getCurrentMinute();
        showTime(hour, min);
    }

    public void showTime(int hour, int min) {
        if (hour == 0) {
            hour += 12;
            format = "AM";
        }
        else if (hour == 12) {
            format = "PM";
        } else if (hour > 12) {
            hour -= 12;
            format = "PM";
        } else {
            format = "AM";
        }
        timePicker1.setCurrentHour(hour) ;
        timePicker1.setCurrentMinute(min) ;
        time1.setText(new StringBuilder().append(hour).append(" : ").append(min)
                .append(" ").append(format));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.prediect_time, menu);
        return true;
    }
}
