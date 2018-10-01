package com.example.mikey.assignment1;

import android.content.Context;

// used to get emotionList from any activity
public abstract class emotionListController {
    private static emotionList eList = null;


    public static emotionList getEmotionList(Context activity) {
        if (eList == null){
            eList = new emotionList(activity);
        }
        return eList;
    }


}
