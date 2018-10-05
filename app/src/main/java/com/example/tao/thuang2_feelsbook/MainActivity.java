package com.example.tao.thuang2_feelsbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public String emotion_now;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button view_history_Button = (Button)findViewById(R.id.history_button);
        view_history_Button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                setResult(RESULT_OK);
                Intent intent = new Intent(v.getContext(), History.class);
                intent.putExtra("Action", "ViewHistory");
                startActivity(intent);

            }
        });
    }

    public void chooseEmotion(View view) {
        switch (view.getId()){
            case R.id.love_button:
                emotion_now = "Love";
                break;
            case R.id.joy_button:
                emotion_now = "Joy";
                break;
            case R.id.surprise_button:
                emotion_now = "Surprise";
                break;
            case R.id.anger_button:
                emotion_now = "Anger";
                break;
            case R.id.sadness_button:
                emotion_now = "Sadness";
                break;
            case R.id.fear_button:
                emotion_now = "Fear";
                break;
        }

        //creates intent to go to Edit page
        Intent intent = new Intent(this, Edit.class);
        intent.putExtra("emotion_now", emotion_now);
        startActivity(intent);
    }
}
