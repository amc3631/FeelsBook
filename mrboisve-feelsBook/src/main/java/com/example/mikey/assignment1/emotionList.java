package com.example.mikey.assignment1;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;

// this class will contain every emotion that the user adds
// implements functions necessary to manage emotion list
public class emotionList implements Serializable {

    private ArrayList<emotion> emotionList;

    emotionList(){
        emotionList = new ArrayList<>();
    }

    // add an emotion to list then sort
    public void addEmotion(emotion e){
        emotionList.add(e);
        sortList();
    }

    // change an emotion in the list by changing each individual element of emotion then sort list
    public void editEmotion(emotion e, String emotionData) {
        // obtain emotion type, date, and comment from single data string
        String[] splitData = emotionData.split("\\s");
        e.setEmotionType(splitData[0]);
        // parse date part of string into a Date object
        try{
            Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.CANADA).parse(splitData[1]);
            e.setDate(date);
        } catch (ParseException x){
            // should never reach here as emotion string would already have been validated in editEntryActivity
            System.out.println("invalid emotion string");
        }
        // reconstruct comment in the event there was spaces in it
        int wordsInComment = splitData.length - 2;
        StringBuilder comment = new StringBuilder();
        if (wordsInComment>0) {
            for (int i=0;i<wordsInComment;i++){
                comment.append(" ").append(splitData[i + 2]);
            }
        }
        e.setComment(comment.toString());
        sortList();
    }

    // delete emotion then sort
    public void deleteEmotion(emotion e){
        emotionList.remove(e);
        sortList();
    }

    // find the total amount of a specific type of emotion in the emotion list
    public int countEmotion(String emotion){
        int count = 0;
        for (emotion e : this.emotionList){
            if (e.getEmotionType().equals(emotion)){
                count++;
            }
        }
        return count;
    }

    // sort emotions by date using custom comparator class
    // they are sorted from latest to earliest so that the newest emotions added
    // will appear on top of the ListView
    private void sortList(){
        Collections.sort(emotionList, new emotionComparator());
    }

    // get the actual ArrayList of emotions
    public ArrayList<emotion> getEmotionArray(){
        return this.emotionList;
    }


}
