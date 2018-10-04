/*
Copyright 2018 Michael Boisvert

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
associated documentation files (the "Software"), to deal in the Software without restriction,
including without limitation the rights to use, copy, modify, merge, publish, distribute,
sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or
substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING
BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

The test classes as well as the load emotion list and save emotion list methods in the emotion list
manager class are taken from the student picker series by Abram Hindle.

Code for creating button listeners taken from the CMPUT 301 lab LonelyTwitter android project.

Examples from https://developer.android.com/training/basics/intents/result#java used in writing the
code for moving data between activites.

Code for opening an activity for a specific result based on code by Kanth (user:1592160)
found at https://stackoverflow.com/questions/14785806/android-how-to-make-an-activity-return-results-to-the-activity-which-calls-it



 */




package com.example.mikey.assignment1;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    TextView joyTotal;
    TextView fearTotal;
    TextView sadnessTotal;
    TextView angerTotal;
    TextView surpriseTotal;
    TextView loveTotal;

    emotionListManager manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);

        // initialize emotionList manager for saving and loading
        manager = new emotionListManager(getApplicationContext().getApplicationContext());

        final emotionList eList = manager.loadEmotionList();

        // get button IDs
        Button joyButton = findViewById(R.id.joyButton);
        Button loveButton = findViewById(R.id.loveButton);
        Button surpriseButton = findViewById(R.id.surpriseButton);
        Button fearButton = findViewById(R.id.fearButton);
        Button sadnessButton = findViewById(R.id.sadnessButton);
        Button angerButton = findViewById(R.id.angerButton);
        Button historyButton = findViewById(R.id.historyButton);

        // get emotion total display ids
        joyTotal = findViewById(R.id.joyTotal);
        fearTotal = findViewById(R.id.fearTotal);
        sadnessTotal = findViewById(R.id.sadnessTotal);
        angerTotal = findViewById(R.id.angerTotal);
        surpriseTotal = findViewById(R.id.surpriseTotal);
        loveTotal = findViewById(R.id.loveTotal);


        // comment box id
        final TextView comment = findViewById(R.id.commentBox);

        // set listeners for each button
        // each button will add a new emotion to emotion list and initiate a count of the total of that emotion entered
        // each button checks that the length of the comment is no more than 100 characters before adding the emotion
        /*
        Code for creating onClickListeners written by Rosevear,
        https://github.com/Rosevear/lonelyTwitter,
        Last update Sept 23 2018,
        Viewed on Oct 5 2018
         */
        joyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!(comment.length()<=100)){
                    AlertDialog.Builder tooLong = new AlertDialog.Builder(MainActivity.this);
                    tooLong.setMessage("Please enter a comment that is no more than 100 characters long");
                    tooLong.setPositiveButton("Okay", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    tooLong.show();
                    return;
                }
                emotion e = new emotion("Joy", comment.getText().toString());
                eList.addEmotion(e);
                joyTotal.setText("Total: " + eList.countEmotion("Joy"));
                manager.saveEmotionList(eList);
            }
        });
        fearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!(comment.length()<=100)){
                    AlertDialog.Builder tooLong = new AlertDialog.Builder(MainActivity.this);
                    tooLong.setMessage("Please enter a comment that is no more than 100 characters long");
                    tooLong.setPositiveButton("Okay", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    tooLong.show();
                    return;
                }
                emotion e = new emotion("Fear", comment.getText().toString());
                eList.addEmotion(e);
                fearTotal.setText("Total: " + eList.countEmotion("Fear"));
                manager.saveEmotionList(eList);
            }
        });
        angerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!(comment.length()<=100)){
                    AlertDialog.Builder tooLong = new AlertDialog.Builder(MainActivity.this);
                    tooLong.setMessage("Please enter a comment that is no more than 100 characters long");
                    tooLong.setPositiveButton("Okay", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    tooLong.show();
                    return;
                }
                emotion e = new emotion("Anger", comment.getText().toString());
                eList.addEmotion(e);
                angerTotal.setText("Total: " + eList.countEmotion("Anger"));
                manager.saveEmotionList(eList);
            }
        });
        sadnessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!(comment.length()<=100)){
                    AlertDialog.Builder tooLong = new AlertDialog.Builder(MainActivity.this);
                    tooLong.setMessage("Please enter a comment that is no more than 100 characters long");
                    tooLong.setPositiveButton("Okay", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    tooLong.show();
                    return;
                }
                emotion e = new emotion("Sadness", comment.getText().toString());
                eList.addEmotion(e);
                sadnessTotal.setText("Total: " + eList.countEmotion("Sadness"));
                manager.saveEmotionList(eList);
            }
        });
        surpriseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!(comment.length()<=100)){
                    AlertDialog.Builder tooLong = new AlertDialog.Builder(MainActivity.this);
                    tooLong.setMessage("Please enter a comment that is no more than 100 characters long");
                    tooLong.setPositiveButton("Okay", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    tooLong.show();
                    return;
                }
                emotion e = new emotion("Surprise", comment.getText().toString());
                eList.addEmotion(e);
                surpriseTotal.setText("Total: " + eList.countEmotion("Surprise"));
                manager.saveEmotionList(eList);
            }
        });
        loveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!(comment.length()<=100)){
                    AlertDialog.Builder tooLong = new AlertDialog.Builder(MainActivity.this);
                    tooLong.setMessage("Please enter a comment that is no more than 100 characters long");
                    tooLong.setPositiveButton("Okay", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    tooLong.show();
                    return;
                }
                emotion e = new emotion("Love", comment.getText().toString());
                eList.addEmotion(e);
                loveTotal.setText("Total: " + eList.countEmotion("Love"));
                manager.saveEmotionList(eList);
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

    public void updateEveryDisplay(){
        emotionList eList = manager.loadEmotionList();
        loveTotal.setText("Total: " + eList.countEmotion("Love"));
        surpriseTotal.setText("Total: " + eList.countEmotion("Surprise"));
        sadnessTotal.setText("Total: " + eList.countEmotion("Sadness"));
        angerTotal.setText("Total: " + eList.countEmotion("Anger"));
        joyTotal.setText("Total: " + eList.countEmotion("Joy"));
        fearTotal.setText("Total: " + eList.countEmotion("Fear"));
    }

}
