package com.example.mikey.assignment1;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

// takes care of serializing, saving, and loading of emotion list
/*
 code by Abram Hindle, (CMPUT 301) in Student Picker tutorial,
 https://www.youtube.com/watch?v=uat-8Z6U_Co&index=8&list=PL240uJOh_Vb4PtMZ0f7N8ACYkCLv0673O
 Sep 14 2014
 Viewed Oct  2018
 */
public class emotionListManager implements Serializable {
    private Context context;
    final private static String eListKey = "Emotion List";

    emotionListManager(Context context){
        this.context = context;
    }

    // save serialized string of emotion list to shared preferences
    // call method to convert emotionList object  to serialized string
    public void saveEmotionList(emotionList eList) {
        SharedPreferences myPrefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = myPrefs.edit();
        editor.putString(eListKey, emotionListToString(eList));
        editor.apply();

    }

    // load serialized string of emotion list from shared preferences
    // call method to convert serialized string back to emotionList object
    public emotionList loadEmotionList()  {
        SharedPreferences myPrefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
        String emotionListString = myPrefs.getString(eListKey, "");
        emotionList list = emotionListFromString(emotionListString);
        if(list == null){
            return new emotionList();
        }
        return list;

    }

    // convert emotion list to serialized string
    private String emotionListToString(emotionList eList) {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oo = new ObjectOutputStream(bo);
            oo.writeObject(eList);
            oo.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        byte[] bytes = bo.toByteArray();
        return Base64.encodeToString(bytes, Base64.DEFAULT);

    }

    // convert serialized string to emotion list
    private emotionList emotionListFromString(String emotionData) {
        ByteArrayInputStream bi = new ByteArrayInputStream(Base64.decode(emotionData, Base64.DEFAULT));
        emotionList eList = null;
        try {
            ObjectInputStream oi = new ObjectInputStream(bi);
            eList = (emotionList)oi.readObject();
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
        return eList;
    }
}


