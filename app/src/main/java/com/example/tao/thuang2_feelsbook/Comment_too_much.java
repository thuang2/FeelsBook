package com.example.tao.thuang2_feelsbook;

public class Comment_too_much extends Exception {

    Comment_too_much(){
        super("This message is too long! Keep your comments to within 100 characters");
    }
}
