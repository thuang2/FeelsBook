package com.example.tao.thuang2_feelsbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class Counter extends AppCompatActivity {


    ArrayList<Emotion> emotionArray;
    int loveCount = 0;
    int joyCount = 0;
    int surpriseCount = 0;
    int angerCount = 0;
    int sadnessCount = 0;
    int fearCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);

        Intent i = getIntent();
        emotionArray = (ArrayList<Emotion>) i.getSerializableExtra("ArrayList");

        getCount(emotionArray);

        displayCount();



    }

    public void getCount(ArrayList<Emotion> emotionArray){
        for (Emotion each:emotionArray){
            String emotionName = each.getEmotionName();
            if (emotionName.matches("Love")){
                loveCount = loveCount + 1;
            }
            else if (emotionName.matches("Joy")){
                joyCount = joyCount + 1;
            }
            else if (emotionName.matches("Surprise")){
                surpriseCount = surpriseCount + 1;
            }
            else if (emotionName.matches("Anger")){
                angerCount = angerCount + 1;
            }
            else if (emotionName.matches("Sadness")){
                sadnessCount = sadnessCount + 1;
            }
            else if (emotionName.matches("Fear")){
                fearCount = fearCount + 1;
            }
        }
    }

    public void displayCount(){
        TextView loveCountText = findViewById(R.id.love_count);
        loveCountText.setText("Love: " + loveCount);

        TextView joyCountText = findViewById(R.id.joy_count);
        joyCountText.setText("Joy: " + joyCount);


        TextView surpriseCountText = findViewById(R.id.surprise_count);
        surpriseCountText.setText("Surprise: " + surpriseCount);


        TextView angerCountText = findViewById(R.id.anger_count);
        angerCountText.setText("Anger: " + angerCount);


        TextView sadnessCountText =findViewById(R.id.sadness_count);
        sadnessCountText.setText("Sadness: " + sadnessCount);


        TextView fearCountText = findViewById(R.id.fear_count);
        fearCountText.setText("Fear: " + fearCount);
    }
}
