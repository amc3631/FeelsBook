package com.example.mikey.assignment1;

import junit.framework.TestCase;

import java.util.Date;

public class emotionTest extends TestCase {
    public void testEmotion(){
        String emotionType = "Joy";
        String comment = "my first comment";
        // test both constructors
        emotion testEmotion = new emotion(emotionType);
        emotion testEmotion2 = new emotion(emotionType, comment);
        assertTrue("emotion name not saved", emotionType.equals(testEmotion.getEmotionType()));
        assertTrue("comment not saved", comment.equals(testEmotion2.getComment()));
        // test setters
        String comment2 = "my second comment";
        Date date = new Date();
        String emotionType2 = "Fear";
        testEmotion.setDate(date);
        testEmotion.setComment(comment2);
        testEmotion.setEmotionType(emotionType2);
        assertTrue("date edit fail", date.equals(testEmotion.getDate()));
        assertTrue("comment edit fail", comment2.equals(testEmotion.getComment()));
        assertTrue("emotion type edit fail", emotionType2.equals(testEmotion.getEmotionType()));
    }
}
