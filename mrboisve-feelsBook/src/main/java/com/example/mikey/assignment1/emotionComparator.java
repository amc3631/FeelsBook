package com.example.mikey.assignment1;

import java.util.Comparator;
import java.util.Date;
// comparator class that determines which emotion is greater based on date
// can be used to sort emotion array
/*
Based on code written by Jignesh Ansodariya (user:795554),
https://stackoverflow.com/users/795554/jignesh-ansodariya,
Oct 29 2012,
Viewed on Oct 10 2018
 */
public class emotionComparator implements Comparator<emotion> {
    public int compare(emotion e1, emotion e2){
        Date date1 = e1.getDate();
        Date date2 = e2.getDate();
        if(date1.equals(date2)){
            return 0;
        } else if (date1.after(date2)){
            return -1;
        }
        return 1;
    }
}
