package com.example.mikey.assignment1;

import junit.framework.TestCase;

import java.util.Date;

public class emotionListTest extends TestCase{
    public void testEmotionList() {
        emotionList testList = new emotionList();
        assertTrue("list not empty", testList.getEmotionList().size() == 0);
        emotion testEmotion = new emotion("Anger");
        // test add method
        testList.addEmotion(testEmotion);
        assertTrue("list not empty", testList.getEmotionList().size() == 1);
        assertTrue("emotion not in list", testList.getEmotionList().contains(testEmotion));
        // test edit method
        String comment = "some comment";
        Date date = new Date();
        String emotionType = "Fear";
        testList.editEmotion(testEmotion, date, comment, emotionType);
        assertTrue("date edit fail", date.equals(testEmotion.getDate()));
        assertTrue("comment edit fail", comment.equals(testEmotion.getComment()));
        assertTrue("emotion type edit fail", emotionType.equals(testEmotion.getEmotionType()));
        // test delete method
        testList.deleteEmotion(testEmotion);
        assertTrue("list not empty", testList.getEmotionList().size() == 0);
        assertFalse("emotion still in list", testList.getEmotionList().contains(testEmotion));

    }
}

