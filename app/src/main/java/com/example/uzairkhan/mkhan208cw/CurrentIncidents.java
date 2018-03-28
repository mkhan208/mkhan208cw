package com.example.uzairkhan.mkhan208cw;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.os.Handler;

public class CurrentIncidents extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

/*private final Handler handler= new Handler();


    private void doTheAutoRefresh(){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                doTheAutoRefresh();
            }
        },5000);
    }
*/
private SwipeRefreshLayout swipeContainer;

//private Button returnButton, searchTitle,button1;


RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_incidents);

        swipeContainer=(SwipeRefreshLayout)findViewById(R.id.swipeRefreshLayout);
        swipeContainer.setOnRefreshListener((SwipeRefreshLayout.OnRefreshListener) this);
        recyclerView= (RecyclerView) findViewById(R.id.recyclerView);
        ReadRSS readRSS= new ReadRSS (this,recyclerView);
        readRSS.execute();
        //searchTitle= (Button)findViewById (R.id.searchTitle);
 //       doTheAutoRefresh();

    }
    public void onRefresh(){
        ReadRSS readRSS= new ReadRSS(this, recyclerView);
        readRSS.execute();
        swipeContainer.setRefreshing(false);
    }



}
