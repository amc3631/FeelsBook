package com.example.mikey.assignment1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class editEntryActivity extends listEmotionsActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_entry);

        // get current emotion data
        // split into date, category, and comment
        final String entry = getIntent().getStringExtra("currentEmotion");
        final String[] splitEntry = entry.split("\\W+");
        final String emotionType = splitEntry[0];
        final String date = splitEntry[1];
        final String comment;
        if (splitEntry.length == 3){
            comment = splitEntry[2];
        } else {
            comment = "";
        }

        // split date into individual parts
        final String[] dateSplit = date.split("[T:\\-]");
        final String year = dateSplit[0];
        final String month = dateSplit[1];
        final String day = dateSplit[2];
        final String hour = dateSplit[3];
        final String minute = dateSplit[4];
        final String second = dateSplit[5]
;
        // populate fields with current data
        final TextView currentEmotion = findViewById(R.id.currentEmotion);
        currentEmotion.setText("Current emotion: " + emotionType);
        final TextView yearText = findViewById(R.id.yearText);
        yearText.setText(year);
        final TextView monthText = findViewById(R.id.monthText);
        monthText.setText(month);
        final TextView dayText = findViewById(R.id.dayText);
        dayText.setText(day);
        final TextView hours = findViewById(R.id.hoursBox);
        hours.setText(hour);
        final TextView minutes = findViewById(R.id.minutesBox);
        minutes.setText(minute);
        final TextView seconds = findViewById(R.id.secondsBox);
        seconds.setText(second);
        final TextView commentBox = findViewById(R.id.commentBox);
        commentBox.setText(comment);

        // get button IDs
        Button joyButton = findViewById(R.id.joyButton);
        Button loveButton = findViewById(R.id.loveButton);
        Button surpriseButton = findViewById(R.id.surpriseButton);
        Button fearButton = findViewById(R.id.fearButton);
        Button sadnessButton = findViewById(R.id.sadnessButton);
        Button angerButton = findViewById(R.id.angerButton);
        Button saveButton = findViewById(R.id.saveButton);

        // button listeners
        joyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentEmotion.setText("Current emotion: Joy");
            }
        });

        fearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentEmotion.setText("Current emotion: Fear");
            }
        });

        angerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentEmotion.setText("Current emotion: Anger");

            }
        });

        sadnessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentEmotion.setText("Current emotion: Sadness");
            }
        });

        surpriseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentEmotion.setText("Current emotion: Surprise");
            }
        });

        loveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentEmotion.setText("Current emotion: Love");

            }
        });

        // save button will validate and record changes to entry
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // compose new strings for date, comment, and category
                // check that they are valid
                // if invalid open dialog box that requests a valid entry

                // parts of date string
                String year = yearText.getText().toString();
                String month = monthText.getText().toString();
                String day = dayText.getText().toString();
                String hour = hours.getText().toString();
                String minute = minutes.getText().toString();
                String second = seconds.getText().toString();

                // make date string and check for validity
                String date = year + "-" + month + "-" + day + "T" + hour + ":" + minute + ":" + second;
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.CANADA);
                format.setLenient(false);
                try {
                    format.parse(date);
                } catch (ParseException e){
                    // date and time invalid
                    AlertDialog.Builder invalid = new AlertDialog.Builder(editEntryActivity.this);
                    invalid.setMessage("Please enter a valid date and time");
                    invalid.setPositiveButton("Okay", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    return;
                }

                String comment = commentBox.getText().toString();
                if(!(comment.length()<=100)){
                    AlertDialog.Builder tooLong = new AlertDialog.Builder(editEntryActivity.this);
                    tooLong.setMessage("Please enter a comment that is no more than 100 characters long");
                    tooLong.setPositiveButton("Okay", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    return;
                }

                String emotionString = currentEmotion.getText().toString();
                String[] emotionSplit = emotionString.split(" ");
                String emotionType = emotionSplit[1];

                String finalEmotion = emotionType + " " + date + "\n" + comment;

                Intent intent = new Intent(editEntryActivity.this, listEmotionsActivity.class);
                intent.putExtra("editedEmotion", finalEmotion);
                editEntryActivity.this.finish();

            }
        });

    }
}
