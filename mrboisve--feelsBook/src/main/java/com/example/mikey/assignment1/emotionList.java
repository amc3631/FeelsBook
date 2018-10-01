package com.example.mikey.assignment1;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

// this class will contain every emotion tha the user adds
// implements functions necessary to manage emotion list
// list stored in shared preferences
public class emotionList implements manageEmotions {

    private ArrayList<emotion> emotionList;
    private SharedPreferences myPrefs;
    private Context activity;
    private String key = "list key";

    emotionList(Context acitvity){
        //emotionList = myPrefs.getClass();
        this.activity = acitvity;
        myPrefs = activity.getSharedPreferences("prefs", Context.MODE_PRIVATE);
        emotionList = new ArrayList<>();
    }

    public void addEmotion(emotion e){
        emotionList.add(e);
        updateDisplay();
    }

    public void editEmotion(emotion e, Date date, String comment, String emotionType){
        e.setComment(comment);
        e.setDate(date);
        e.setEmotionType(emotionType);
        updateDisplay();
    }

    public void deleteEmotion(emotion e){
        emotionList.remove(e);
        updateDisplay();
    }

    // display emotions in list sorted by date
    private void updateDisplay(){


    }

    public ArrayList<emotion> getEmotionList(){
        return this.emotionList;
    }

    // code from https://freakycoder.com/android-notes-40-how-to-save-and-get-arraylist-into-sharedpreference-7d1f044bc79a
    public void saveArrayList(){
        SharedPreferences.Editor editor = myPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(emotionList);
        editor.putString(key, json);
        editor.apply();
    }

    public ArrayList<emotion> retrieveArrayList(){
        Gson gson = new Gson();
        String json = myPrefs.getString(key, null);
        Type type = new TypeToken<ArrayList<emotion>>() {}.getType();
        return gson.fromJson(json, type);
    }
}
