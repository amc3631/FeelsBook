package com.example.mikey.assignment1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

// TODO: cite student picker and lonelytwitter


public class MainActivity extends AppCompatActivity {

    protected emotionTotalDisplay joyDisplay;
    protected emotionTotalDisplay fearDisplay;
    protected emotionTotalDisplay angerDisplay;
    protected emotionTotalDisplay sadnessDisplay;
    protected emotionTotalDisplay surpriseDisplay;
    protected emotionTotalDisplay loveDisplay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);

        // get button IDs
        Button joyButton = findViewById(R.id.joyButton);
        Button loveButton = findViewById(R.id.loveButton);
        Button surpriseButton = findViewById(R.id.surpriseButton);
        Button fearButton = findViewById(R.id.fearButton);
        Button sadnessButton = findViewById(R.id.sadnessButton);
        Button angerButton = findViewById(R.id.angerButton);
        Button historyButton = findViewById(R.id.historyButton);

        // create instances of emotionTotalDisplay for each emotion
        joyDisplay = new emotionTotalDisplay((TextView)findViewById(R.id.joyTotal),"joyKey", this);
        fearDisplay = new emotionTotalDisplay((TextView)findViewById(R.id.fearTotal),"fearKey", this);
        sadnessDisplay = new emotionTotalDisplay((TextView)findViewById(R.id.sadnessTotal),"sadnessKey", this);
        loveDisplay = new emotionTotalDisplay((TextView)findViewById(R.id.loveTotal),"loveKey", this);
        surpriseDisplay = new emotionTotalDisplay((TextView)findViewById(R.id.surpriseTotal),"surpriseKey", this);
        angerDisplay = new emotionTotalDisplay((TextView)findViewById(R.id.angerTotal),"angerKey", this);

        // list of displays
        ArrayList<emotionTotalDisplay> displays = new ArrayList<>();
        Collections.addAll(displays, joyDisplay, fearDisplay, sadnessDisplay, loveDisplay, surpriseDisplay, angerDisplay);

        // set listeners for each button such that each button will increment the corresponding total
        joyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                joyDisplay.incrementTotal();
            }
        });

        fearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fearDisplay.incrementTotal();
            }
        });

        angerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                angerDisplay.incrementTotal();
            }
        });

        sadnessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sadnessDisplay.incrementTotal();
            }
        });

        surpriseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                surpriseDisplay.incrementTotal();
            }
        });

        loveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loveDisplay.incrementTotal();
            }
        });

        // history button will take user to list of emotions in a different activity
        historyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, listEmotionsActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // update counters
        updateEveryDisplay();
    }

    protected void updateEveryDisplay(){
        joyDisplay.updateDisplay();
        sadnessDisplay.updateDisplay();
        fearDisplay.updateDisplay();
        angerDisplay.updateDisplay();
        surpriseDisplay.updateDisplay();
        loveDisplay.updateDisplay();
    }

}
