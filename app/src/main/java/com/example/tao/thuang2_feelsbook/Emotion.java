package com.example.tao.thuang2_feelsbook;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Emotion implements Serializable {

    private String emotion;
    public Date date;
    public String comment;


    public Emotion(String emotion) {
        this.date = new Date();
        this.comment = null;
        this.emotion = emotion;
    }


    public String toString() {
        return emotion + "\n" + date + "\n" + "Comment:" + getComment();
    }

    public String getEmotionName() {
        return this.emotion;
    }

    public Date getDate() {
        return this.date;
    }

    public String getComment() {
        return this.comment;
    }
}