package com.example.mikey.assignment1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class editEntryActivity extends listEmotionsActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_entry);

        // get current emotion data
        // split into date, category, and comment
        String entry = getIntent().getStringExtra("currentEmotion");
        String[] splitEntry = entry.split("\\W+");
        String emotionType = splitEntry[0];
        String date = splitEntry[1];
        if (splitEntry.length == 3){
            String comment = splitEntry[2];
        } else {
            String comment = "";
        }

        // split date into individual parts
        String[] dateSplit = date.split("[T:\\-]");
        String year = dateSplit[0];
        String month = dateSplit[1];
        String day = dateSplit[2];
        String hour = dateSplit[3];
        String minute = dateSplit[4];
        String second = dateSplit[5]
;
        // populate fields with current data
        TextView currentEmotion = findViewById(R.id.currentEmotion);
        currentEmotion.setText("Current emotion: " + emotionType);
        TextView yearText = findViewById(R.id.yearText);
        TextView monthText = findViewById(R.id.monthText);
        TextView dayText = findViewById(R.id.dayText);

    }
}
