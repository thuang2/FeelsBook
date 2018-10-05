package com.example.tao.thuang2_feelsbook;


import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
//import android.util.Log;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

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
import java.util.ArrayList;

public class History extends Activity {
    private static final String FILENAME = "FEELSBOOK.sav";
    private ListView Emotionrecord;
    ArrayList<Emotion> emotionArray;
    ArrayAdapter<Emotion> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        Emotionrecord =  findViewById(R.id.History_listview);

        Button returnButton = findViewById(R.id.back_button);
        returnButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setResult(RESULT_OK);
                saveInFile();
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });



        Button countButton = findViewById(R.id.count_button);
        countButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setResult(RESULT_OK);
                saveInFile();
                Intent intent = new Intent(v.getContext(), Counter.class);
                intent.putExtra("ArrayList", emotionArray);
                startActivity(intent);
            }
        });


        //Takes you to edit when an emotion on list view is clicked
        Emotionrecord.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object object = Emotionrecord.getItemAtPosition(position);
                Emotion emotion = Emotion.class.cast(object);
                String emotion_now = emotion.getEmotionName();
                Intent i = new Intent(view.getContext(), Edit.class);
                i.putExtra("emotion_now", emotion_now);
                i.putExtra("Emotion", emotion);
                i.putExtra("a_number", position);
                Log.d("fuck", String.valueOf(position));
                startActivity(i);
            }
        });
    }

    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        loadFromFile();
        adapter = new ArrayAdapter<Emotion>(this,
                R.layout.list_record, emotionArray);
        adapter.notifyDataSetChanged();
        Emotionrecord.setAdapter(adapter);
    }

    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Emotion>>() {
            }.getType();
            emotionArray = gson.fromJson(in, listType);
        } catch (FileNotFoundException e) {
            emotionArray = new ArrayList<Emotion>();
        }
    }

    //takes a text and date and saves it.
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
