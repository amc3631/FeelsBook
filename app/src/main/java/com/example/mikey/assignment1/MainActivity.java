package com.example.mikey.assignment1;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {


    // Initialize shared preferences
    //SharedPreferences records = PreferenceManager.getDefaultSharedPreferences(this);
    SharedPreferences records = getSharedPreferences("Settings", Context.MODE_PRIVATE);

    //SharedPreferences.Editor editor = records.edit();

    // Retrieve emotion records
    //int counter = records.getInt("counter", 0);
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




    }

    public void onClick(View v) {
        counter++;
        TextView display = (TextView)findViewById(R.id.textView2);
        display.setText("Counter: " + counter);
        //editor.putInt("counter", counter);
        //editor.commit();
    }
}
