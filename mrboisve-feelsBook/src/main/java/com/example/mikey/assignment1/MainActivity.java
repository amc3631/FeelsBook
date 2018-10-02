package com.example.mikey.assignment1;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import static com.example.mikey.assignment1.emotionListController.getEmotionList;

// TODO: cite student picker and lonelytwitter
// serializable from here
// https://stackoverflow.com/questions/12092612/pass-list-of-objects-from-one-activity-to-other-activity-in-android


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

        // comment box id
        final TextView comment = findViewById(R.id.commentBox);

        // create instances of emotionTotalDisplay for each emotion
        joyDisplay = new emotionTotalDisplay((TextView)findViewById(R.id.joyTotal),"joyKey", this);
        fearDisplay = new emotionTotalDisplay((TextView)findViewById(R.id.fearTotal),"fearKey", this);
        sadnessDisplay = new emotionTotalDisplay((TextView)findViewById(R.id.sadnessTotal),"sadnessKey", this);
        loveDisplay = new emotionTotalDisplay((TextView)findViewById(R.id.loveTotal),"loveKey", this);
        surpriseDisplay = new emotionTotalDisplay((TextView)findViewById(R.id.surpriseTotal),"surpriseKey", this);
        angerDisplay = new emotionTotalDisplay((TextView)findViewById(R.id.angerTotal),"angerKey", this);

        // create emotion list controller and get emotion list
        final emotionList eList = getEmotionList(this);

        // set listeners for each button
        // each button will increment total, create a new emotion, and add new emotion to emotion list
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
                    return;
                }
                joyDisplay.incrementTotal();
                emotion e = new emotion("Joy", comment.getText().toString());
                eList.addEmotion(e);


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
                    return;
                }
                fearDisplay.incrementTotal();
                emotion e = new emotion("Fear", comment.getText().toString());
                eList.addEmotion(e);


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
                    return;
                }
                angerDisplay.incrementTotal();
                emotion e = new emotion("Anger", comment.getText().toString());
                eList.addEmotion(e);


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
                    return;
                }
                sadnessDisplay.incrementTotal();
                emotion e = new emotion("Sadness", comment.getText().toString());
                eList.addEmotion(e);


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
                    return;
                }
                surpriseDisplay.incrementTotal();
                emotion e = new emotion("Surprise", comment.getText().toString());
                eList.addEmotion(e);


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
                    return;
                }
                loveDisplay.incrementTotal();
                emotion e = new emotion("Love", comment.getText().toString());
                eList.addEmotion(e);


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
