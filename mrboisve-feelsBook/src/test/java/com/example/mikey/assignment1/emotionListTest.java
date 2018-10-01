package com.example.mikey.assignment1;

import junit.framework.TestCase;

import java.util.Date;

import static android.app.PendingIntent.getActivity;

public class emotionListTest extends TestCase{
    public void testEmptyEmotionList() {
        MainActivity activity = new MainActivity();
        emotionList testList = new emotionList(activity);
        assertTrue("list not empty", testList.getEmotionList().size() == 0);
    }
    public void testAddEmotion () {
        MainActivity activity = new MainActivity();
        emotionList testList = new emotionList(activity);
        emotion testEmotion = new emotion("Anger");
        testList.addEmotion(testEmotion);
        assertTrue("list not empty", testList.getEmotionList().size() == 1);
        assertTrue("emotion not in list", testList.getEmotionList().contains(testEmotion));
    }
    public void testEditEmotion() {
        MainActivity activity = new MainActivity();
        emotionList testList = new emotionList(activity);
        emotion testEmotion = new emotion("Anger");
        String comment = "some comment";
        Date date = new Date();
        String emotionType = "Fear";
        testList.editEmotion(testEmotion, date, comment, emotionType);
        assertTrue("date edit fail", date.equals(testEmotion.getDate()));
        assertTrue("comment edit fail", comment.equals(testEmotion.getComment()));
        assertTrue("emotion type edit fail", emotionType.equals(testEmotion.getEmotionType()));
    }
    public void testDeleteEmotion(){
        MainActivity activity = new MainActivity();
        emotionList testList = new emotionList(activity);
        emotion testEmotion = new emotion("Anger");
        testList.deleteEmotion(testEmotion);
        assertTrue("list not empty", testList.getEmotionList().size() == 0);
        assertFalse("emotion still in list", testList.getEmotionList().contains(testEmotion));
    }
    public void testSaveRetrieveList(){
        MainActivity activity = new MainActivity();
        emotionList testList = new emotionList(activity);
        emotion testEmotion = new emotion("Anger");
        testList.addEmotion(testEmotion);
        testList.saveArrayList();
        testList.retrieveArrayList();
        assertTrue("save and retrieve fail", testList.getEmotionList().contains(testEmotion));
    }
}

