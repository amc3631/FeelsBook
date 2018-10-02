package com.example.mikey.assignment1;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.lang.reflect.Type;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

// this class will contain every emotion tha the user adds
// implements functions necessary to manage emotion list
// list stored in shared preferences
public class emotionList implements manageEmotions {

    private ArrayList<emotion> emotionList;

    emotionList(){
        emotionList = new ArrayList<>();
    }

    public void addEmotion(emotion e){
        emotionList.add(e);
        sortList();
    }

    public void editEmotion(emotion e, String emotionData) throws ParseException {
        String[] splitData = emotionData.split("\\s");
        e.setEmotionType(splitData[0]);
        Date date = new Date();
        date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(splitData[1]);
        e.setDate(date);
        // reconstruct comment in the event there was spaces in it
        int wordsInComment = splitData.length - 2;
        String comment = "";
        if (wordsInComment>0) {
            for (int i=0;i<wordsInComment;i++){
                comment = comment + " " + splitData[i+2];
            }
        }
        e.setComment(comment);
        sortList();
    }

    public void deleteEmotion(emotion e){
        emotionList.remove(e);
        sortList();
    }

    // sort emotions by date using custom comparator class
    // they are sorted from latest to earliest so that the newest emotions added
    // will appear on top of the ListView
    private void sortList(){
        Collections.sort(emotionList, new emotionComparator());
    }

    public ArrayList<emotion> getEmotionList(){
        return this.emotionList;
    }


}
