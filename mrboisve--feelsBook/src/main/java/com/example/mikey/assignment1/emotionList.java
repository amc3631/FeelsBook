package com.example.mikey.assignment1;

import java.util.ArrayList;
import java.util.Date;

// this class will contain every emotion tha the user adds
public class emotionList implements manageEmotions {

    private ArrayList<emotion> emotionList;

    emotionList(){
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

    private void updateDisplay(){
        // display emotions in list sorted by date

    }

    public ArrayList<emotion> getEmotionList(){
        return this.emotionList;
    }

}
