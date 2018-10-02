package com.example.mikey.assignment1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static android.app.PendingIntent.getActivity;
import static com.example.mikey.assignment1.emotionListController.getEmotionList;

// serialization will be used to pass an emotion object between activites and save emotion list to shared preferences
public class listEmotionsActivity extends AppCompatActivity implements Serializable {

    String newEmotionData;
    emotionList eList = getEmotionList();
    emotion e;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_emotions);

        // get listView id
        ListView listView = findViewById(R.id.emotionListView);

        // add adapter to listView
        final ArrayAdapter<emotion> adapter = emotionListManager.getAdapter(this);
        listView.setAdapter(adapter);

        adapter.notifyDataSetChanged();

        // create a dialog box that will prompt user to delete or edit emotion entry when an entry is clicked
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                // get the emotion that was clicked
                e = eList.getEmotionList().get(position);

                AlertDialog.Builder adb = new AlertDialog.Builder(listEmotionsActivity.this);
                adb.setMessage("Would you like to edit or delete this emotion?");
                adb.setCancelable(true);

                // delete option deletes the emotion and updates list by notifying adapter
                adb.setPositiveButton("Delete", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        eList.deleteEmotion(e);
                        adapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });

                // edit option opens new activity with edit options
                // should the user save changes to the emotion the data will be returned here and applied
                adb.setNeutralButton("Edit", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(listEmotionsActivity.this, editEntryActivity.class);
                        String entry = e.toString();
                        intent.putExtra("currentEmotion", entry);
                        // onActivityResult will be called after editEntryActivity is complete
                        startActivityForResult(intent, 1);
                        adapter.notifyDataSetChanged();
                        dialog.dismiss();


                    }
                });

                // cancel option simply closes the dialog
                adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                adb.show();

            }
        });
    }

    @Override
    public void onResume(){
        super.onResume();
        emotionListManager.getAdapter(this).notifyDataSetChanged();
    }

    // from: https://developer.android.com/training/basics/intents/result#java
    // override onActivityResult function to control what happens when a result is returned from editEntryActivty
    @Override
    public void onActivityResult (int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Check which request we're responding to
        // This should be the only possible request
        if (requestCode == 1) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                // User saved changes to emotion
                // Store new emotion string
                newEmotionData = data.getStringExtra("editedEmotion");
                try {
                    eList.editEmotion(e, newEmotionData);
                } catch (ParseException x) {
                    // should never reach here as the date was already validated in editEntryActivity
                };
            }
        }
    }
}
