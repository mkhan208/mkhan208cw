package com.example.uzairkhan.mkhan208cw;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.icu.text.DateFormat;
import android.icu.util.Calendar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DialogTitle;
import android.support.v7.widget.RecyclerView;
import android.widget.DatePicker;
import android.widget.Toast;

import java.time.Year;
import java.util.Date;

public class PlannedRoadwork extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    private SwipeRefreshLayout swipeContainer;



    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planned_roadwork);

        swipeContainer=(SwipeRefreshLayout)findViewById(R.id.swipeRefreshLayout);
        swipeContainer.setOnRefreshListener((SwipeRefreshLayout.OnRefreshListener) this);
        recyclerView= (RecyclerView) findViewById(R.id.recyclerView);
        ReadRSS1 readRSS1= new ReadRSS1 (this,recyclerView);
        readRSS1.execute();



        //       doTheAutoRefresh();

    }
  /*  @Override
    public Dialog onCreateDialog (Bundle savedInstanceState){
        final Calendar c= Calendar.getInstance();
        int year= c.get(Calendar.YEAR);
        int month= c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(PlannedRoadwork.this,this,year,month,day);


    }
    public void  onDateSet (DatePickerDialog view, int y, int m, int d){

    calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, y);
        calendar.set(Calendar.MONTH, m);
        calendar.set(Calendar.DATE, d);

        weekday= (String) DateFormat.format("EEE",calendar);
        day = (String) DateFormat.format("dd",calendar);
        month= (String) DateFormat.format("MMM",calendar);
        year = (String) DateFormat.format("YYYY",calendar);

        plannedDate= weekday+ ","+ day +","+ month+ ","+ year;

        int toastDuration = Toast.LENGTH_LONG;
        String toastText = "date selected" + plannedDate;

        Toast.makeText(PlannedRoadwork.this, toastText,toastDuration).show()
    }
**/

    public void onRefresh(){
        ReadRSS1 readRSS1= new ReadRSS1(this, recyclerView);
        readRSS1.execute();
        swipeContainer.setRefreshing(false);
    }


}
