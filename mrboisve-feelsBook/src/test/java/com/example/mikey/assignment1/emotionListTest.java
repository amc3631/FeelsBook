package com.example.mikey.assignment1;
import junit.framework.TestCase;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 Code taken from student picker tutorial by Abram Hindle,
 https://www.youtube.com/watch?v=k9ZNbsc0Qgo&list=PL240uJOh_Vb4PtMZ0f7N8ACYkCLv0673O&index=3,
 Published Sep 11 2014
 Viewed on Oct 2, 2018
 */
public class emotionListTest extends TestCase{
    public void testEmptyEmotionList() {
        emotionList testList = new emotionList();
        assertTrue("list not empty", testList.getEmotionArray().size() == 0);
    }
    public void testAddEmotion () {
        emotionList testList = new emotionList();
        emotion testEmotion = new emotion("Anger");
        testList.addEmotion(testEmotion);
        assertTrue("list not empty", testList.getEmotionArray().size() == 1);
        assertTrue("emotion not in list", testList.getEmotionArray().contains(testEmotion));
    }
    public void testEditEmotion() {
        emotionList testList = new emotionList();
        emotion testEmotion = new emotion("Anger");
        String emotionString = "Joy 2018-10-02T10:08:22\nsome comment";
        String[] splitEntry = emotionString.split("\\s");
        String emotionType = splitEntry[0];
        Date date = new Date();
        try {
            date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(splitEntry[1]);
        } catch (ParseException x){
            // wrong date format
        }
        int wordsInComment = splitEntry.length - 2;
        StringBuilder comment = new StringBuilder();
        // reconstruct comment in the event there was spaces in it
        if (wordsInComment>0) {
            for (int i=0;i<wordsInComment;i++){
                comment.append(" ").append(splitEntry[i + 2]);
            }
        }
        testList.editEmotion(testEmotion, emotionString);
        assertTrue("date edit fail", date.equals(testEmotion.getDate()));
        assertTrue("comment edit fail", comment.toString().equals(testEmotion.getComment()));
        assertTrue("emotion type edit fail", emotionType.equals(testEmotion.getEmotionType()));
    }
    public void testDeleteEmotion(){
        emotionList testList = new emotionList();
        emotion testEmotion = new emotion("Anger");
        testList.addEmotion(testEmotion);
        testList.deleteEmotion(testEmotion);
        assertTrue("list not empty", testList.getEmotionArray().size() == 0);
        assertFalse("emotion still in list", testList.getEmotionArray().contains(testEmotion));
    }
}

