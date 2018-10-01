package com.example.mikey.assignment1;

import java.util.Date;

public interface manageEmotions {
    void addEmotion(emotion e);
    void deleteEmotion(emotion e);
    void editEmotion(emotion e, Date date, String comment, String emotionType);
}