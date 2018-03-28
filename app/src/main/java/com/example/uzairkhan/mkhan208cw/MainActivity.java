package com.example.uzairkhan.mkhan208cw;

import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button currentincidents_btn;
    private Button planned_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentincidents_btn =(Button) findViewById(R.id.currentincidents_btn);
        planned_btn =(Button) findViewById(R.id.planned_btn);
        currentincidents_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openCurrentIncidents();

            }
        });

        planned_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                openPlannedRoadwork();

            }

        });

    }


    public void clickexit (View v){
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Do you want to quit ?");
        builder.setCancelable(true);
        builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                finish();
            }
        });
        builder.setPositiveButton("Cancel!",new DialogInterface.OnClickListener() {
            @Override
            public void onClick (DialogInterface dialogInterface, int i){
                dialogInterface.cancel();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        /**moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);*/
    }


    public void openCurrentIncidents(){
        Intent intent = new Intent(this,CurrentIncidents.class);
        startActivity(intent);
    }

    public void openPlannedRoadwork(){
        Intent intent = new Intent(this,PlannedRoadwork.class);
        startActivity(intent);
    }
}
