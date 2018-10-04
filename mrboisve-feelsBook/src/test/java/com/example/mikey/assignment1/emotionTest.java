package com.example.mikey.assignment1;

import junit.framework.TestCase;

import java.util.Date;
/*
 Code taken from student picker tutorial by Abram Hindle,
 https://www.youtube.com/watch?v=k9ZNbsc0Qgo&list=PL240uJOh_Vb4PtMZ0f7N8ACYkCLv0673O&index=3,
 Published Sep 11 2014
 Viewed on Oct 2, 2018
 */
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
