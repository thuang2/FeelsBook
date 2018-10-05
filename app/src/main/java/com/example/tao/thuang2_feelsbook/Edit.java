package com.example.tao.thuang2_feelsbook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class Edit extends AppCompatActivity {

    private static final String FILENAME = "FEELSBOOK.sav";
    ArrayList<Emotion> emotionArray;
    String emotion_now;
    String comment;
    String action;
    int a_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        if (emotionArray == null){
            loadFromFile();
        }


        Intent intent = getIntent();
        emotion_now = (String)intent.getSerializableExtra("emotion_now");
        final Emotion emotion = (Emotion)intent.getSerializableExtra("Emotion");
        a_number = intent.getIntExtra("a_number", -1);

        //Saves the emotion to the array list
        Button saveButton = findViewById(R.id.SaveButtonEditEmotionPage);
        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //setResult(RESULT_OK);
                saveValues(emotion);
            }
        });

        Button deleteButton = findViewById(R.id.DeleteButtonEditEmotionPage);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //setResult(RESULT_OK);
                delete(a_number);

            }
        });

        //Getting the header text
        TextView HeaderText = findViewById(R.id.EmotionHeaderEditEmotionPage);
        HeaderText.setText(emotion_now);

        //Getting the date

        Date date = new Date();
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        String datestring = sdf.format(date);
        EditText DateText = findViewById(R.id.dateedit);
        DateText.setText(datestring);
        //TODO: Throw exceptions when date isn't entered correctly!

        //Getting the comment
        if (emotion == null) {
            comment = null;
        }
        else {
            comment = emotion.getComment();
            //TODO: Throw exceptions when comment is too long.
        }

        if (comment!=null){
            TextView CommentText = (TextView) findViewById(R.id.commentedit);
            CommentText.setText(comment);
        }

        else {
            TextView CommentText = (TextView) findViewById(R.id.commentedit);
            CommentText.setText("");
        }
    }


    //Saving entered comments and date to Emotion object
    private void saveValues(Emotion emotion){

        //if it's a new emotion, make a new Emotion
        if (emotion == null) {
            action = "New";
            emotion  = new Emotion(emotion_now);
        }

        else{
            action = "Old";
        }

        //Save comment
        EditText commentText = findViewById(R.id.commentedit);
        String commentToSave = commentText.getText().toString();

        if (commentToSave == null) {
            commentToSave = "";
        }

        emotion.comment = commentToSave;

        //Save date
        EditText dateText =  findViewById(R.id.dateedit);
        String dateToSave = dateText.getText().toString();

        //formatting date
        // from https://www.mkyong.com/java/how-to-convert-string-to-date-java/
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat("yyyy-MM-dd 'T' HH: mm: ss");

        try {
            // Log.d("Date", dateToSave);
            Date date = sdf.parse(dateToSave);
            emotion.date = date;
        }

        catch (ParseException a){
            a.printStackTrace();
        }

        if (action.equals("New")){
            emotionArray.add(emotion);
        }
        if(action.equals("Old")){
            emotionArray.set(a_number,emotion);
        }

        saveInFile();

        Intent intent = new Intent(this, History.class);
        startActivity(intent);
    }

    void delete (int a_number){
        emotionArray.remove(a_number);
        saveInFile();



        Intent intent = new Intent(this, History.class);
        startActivity(intent);
    }

    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson(); //library to save objects
            Type listType = new TypeToken<ArrayList<Emotion>>() {
            }.getType();
            emotionArray = gson.fromJson(in, listType);
        } catch (FileNotFoundException e) {
            emotionArray = new ArrayList<Emotion>();
        }
    }

    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(emotionArray, out);
            out.flush();
            fos.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
