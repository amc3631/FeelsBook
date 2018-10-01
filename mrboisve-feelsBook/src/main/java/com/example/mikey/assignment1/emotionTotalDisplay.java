package com.example.mikey.assignment1;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import static android.app.PendingIntent.getActivity;

// each emotion display has an emotion total and a key needed to retrieve the emotion total from shared preferences
// each display has a textview on main activity which shows current emotion total
// emotion totals are kept persistent by storing them in shared preferences
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

    // set view to show current emotion total
    public void updateDisplay(){
        emotionTotal = myPrefs.getInt(emotionKey,0);
        display.setText("Total: " + emotionTotal);
    }

    // increase total emotion count by 1
    // should be used after adding emotion to emotion list
    public void incrementTotal(){
        emotionTotal ++;
        SharedPreferences.Editor editor = myPrefs.edit();
        editor.putInt(emotionKey, emotionTotal);
        editor.apply();
        this.updateDisplay();
    }

    // decrease total emotion count by 1
    // should be used after deleting emotion from emotion list
    public void decrementTotal(){
        emotionTotal --;
        SharedPreferences.Editor editor = myPrefs.edit();
        editor.putInt(emotionKey, emotionTotal);
        editor.apply();
        this.updateDisplay();
    }

}
