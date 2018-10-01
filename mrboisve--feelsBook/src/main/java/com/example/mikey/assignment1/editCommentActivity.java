package com.example.mikey.assignment1;

import android.os.Bundle;
import android.app.Activity;
import android.util.DisplayMetrics;

public class editCommentActivity extends listEmotionsActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_comment);

        // display as pop up windows
        // code from https://www.youtube.com/watch?v=fn5OlqQuOCk
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*.8), (int)(height*.6));

    }

}
