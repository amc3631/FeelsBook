package com.example.mikey.assignment1;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

// each emotion has a type comment and date
// initializing with comment is optional
// all elements are editable
public class emotion {

    private String emotionType;
    private Date date;
    private String comment;

    private DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.CANADA);


    // constructor with no comment
    emotion(String emotionType){
        this(emotionType, "");
    }

    // constructor with comment
    emotion(String emotionType, String comment){
        this.emotionType = emotionType;
        this.comment = comment;
        TimeZone tz = TimeZone.getTimeZone("Canada/Mountain");
        format.setTimeZone(tz);
        this.date = new Date();
    }

    // edit date
    public void setDate(Date date){
        this.date = date;
    }

    // edit comment
    public void setComment(String comment){
        this.comment = comment;
    }

    // edit emotion type
    public void setEmotionType(String emotionType){
        this.emotionType = emotionType;
    }

    public Date getDate(){
        return  this.date;
    }

    public String getEmotionType(){
        return this.emotionType;
    }

    public String getComment(){
        return this.comment;
    }

    @Override
    public String toString(){
        return emotionType + " " + format.format(date) + "\n" + comment;
    }
}
