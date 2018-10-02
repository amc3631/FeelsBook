package com.example.mikey.assignment1;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class emotionListManager {
    Context context;
    private static ArrayAdapter<emotion> adapter = null;
    ListView listView;

    emotionListManager(Context context, ListView listView){
        this.context = context;
        this.listView = listView;
    }

    public static ArrayAdapter<emotion> getAdapter(Context context){
        if (adapter == null){
            ArrayList<emotion> eList = emotionListController.getEmotionList().getEmotionList();
            adapter = new ArrayAdapter<emotion>(context, android.R.layout.simple_list_item_1, eList);
        }
        return adapter;

    }

    public void saveEmotionList(){

    }

    public void loadEmotionList(){

    }
}


