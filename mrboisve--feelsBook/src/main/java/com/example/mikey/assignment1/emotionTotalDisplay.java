package com.example.mikey.assignment1;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import static android.app.PendingIntent.getActivity;

public class emotionTotalDisplay {

    private Context mainActivity;
    private TextView display;
    private String emotionKey;
    private int emotionTotal;
    private SharedPreferences myPrefs;

    emotionTotalDisplay(TextView display, String emotionKey, Context mainActivity){
        this.display = display;
        this.emotionKey = emotionKey;
        this.mainActivity = mainActivity;
        emotionTotal = 0;
        myPrefs = mainActivity.getSharedPreferences("prefs", Context.MODE_PRIVATE);
    }

    public void updateDisplay(){
        emotionTotal = myPrefs.getInt(emotionKey,0);
        display.setText("Total: " + emotionTotal);
    }

    public void incrementTotal(){
        emotionTotal ++;
        SharedPreferences.Editor editor = myPrefs.edit();
        editor.putInt(emotionKey, emotionTotal);
        editor.apply();
        this.updateDisplay();
    }

    public void decrementTotal(){
        emotionTotal --;
        SharedPreferences.Editor editor = myPrefs.edit();
        editor.putInt(emotionKey, emotionTotal);
        editor.apply();
        this.updateDisplay();
    }

}
