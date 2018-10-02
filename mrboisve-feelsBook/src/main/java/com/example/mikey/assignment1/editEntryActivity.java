package com.example.mikey.assignment1;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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
        // separate date, category, and comment
        String entry = getIntent().getStringExtra("currentEmotion");
        String[] splitEntry = entry.split("\\s");
        String emotionType = splitEntry[0];
        String date = splitEntry[1];
        int wordsInComment = splitEntry.length - 2;
        String comment = "";
        // reconstruct comment in the event there was spaces in it
        if (wordsInComment>0) {
            for (int i=0;i<wordsInComment;i++){
                comment = comment + " " + splitEntry[i+2];
            }
        }

        // split date into individual parts
        String[] dateSplit = date.split("[T:\\-]");
        String year = dateSplit[0];
        String month = dateSplit[1];
        String day = dateSplit[2];
        String hour = dateSplit[3];
        String minute = dateSplit[4];
        String second = dateSplit[5];

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

        // button listeners for each type of emotion
        // clicking one changes the category of the emotion
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
                String emotionType = emotionSplit[2];
                String finalEmotion = emotionType + " " + date + "\n" + comment;

                // finish activity and return resulting emotion string
                // from: https://stackoverflow.com/questions/14785806/android-how-to-make-an-activity-return-results-to-the-activity-which-calls-it
                Intent intent = getIntent();
                intent.putExtra("editedEmotion", finalEmotion);
                setResult(RESULT_OK, intent);
                finish();

            }
        });
    }
}
