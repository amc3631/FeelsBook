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

// continuously updates a ListView with the contents of an arrayList of emotions
// offers user interface options to edit or delete emotions
public class listEmotionsActivity extends AppCompatActivity implements Serializable {

    emotionList eList;
    ArrayAdapter<emotion> adapter;
    emotionListManager manager;
    emotion selectedEmotion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_emotions);

        // get listView id
        ListView listView = findViewById(R.id.emotionListView);

        // initialize emotionList manager for saving and loading
        manager = new emotionListManager(this.getApplicationContext());

        // get the emotion list
        eList = manager.loadEmotionList();

        // add adapter to listView
        adapter = new ArrayAdapter<>(this.getApplicationContext(), android.R.layout.simple_list_item_1, eList.getEmotionArray());
        listView.setAdapter(adapter);



        // create a dialog box that will prompt user to delete or edit emotion entry when an entry is clicked
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                // get the emotion that was clicked
                selectedEmotion = eList.getEmotionArray().get(position);

                // ask user for input
                AlertDialog.Builder adb = new AlertDialog.Builder(listEmotionsActivity.this);
                adb.setMessage("Would you like to edit or delete this emotion?");
                adb.setCancelable(true);

                // delete option deletes the emotion and updates list by notifying adapter
                adb.setPositiveButton("Delete", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        eList.deleteEmotion(selectedEmotion);
                        adapter.notifyDataSetChanged();
                        manager.saveEmotionList(eList);
                        dialog.dismiss();
                    }
                });

                // edit option opens new activity with edit options
                // should the user save changes to the emotion the data will be returned here and applied
                adb.setNeutralButton("Edit", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(listEmotionsActivity.this, editEntryActivity.class);
                        String entry = selectedEmotion.toString();
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

    // whenever this activity resumes update the ListView
    @Override
    public void onResume(){
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    // override onActivityResult function to control what happens when a result is returned from editEntryActivty
    /*
    Based on example code in android developers documentation,
    https://developer.android.com/training/basics/intents/result#java,
    Last updated Sep 20 2018,
    Viewed on Oct 2 2018
    */
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
                String newEmotionData = data.getStringExtra("editedEmotion");
                eList.editEmotion(selectedEmotion, newEmotionData);
                manager.saveEmotionList(eList);

            }
        }
    }
}
