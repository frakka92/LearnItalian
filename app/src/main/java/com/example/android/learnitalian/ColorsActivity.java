package com.example.android.learnitalian;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        ArrayList<Word> myColors = new ArrayList<>();

        myColors.add(new Word("rosso", "red"));
        myColors.add(new Word("blu", "blue"));
        myColors.add(new Word("verde", "green"));
        myColors.add(new Word("giallo", "yellow"));
        myColors.add(new Word("arancione", "orange"));
        myColors.add(new Word("marrone", "brown"));
        myColors.add(new Word("grigio", "grey"));
        myColors.add(new Word("viola", "purple"));
        myColors.add(new Word("rosa", "pink"));
        myColors.add(new Word("bianco", "white"));
        myColors.add(new Word("nero", "black"));

        ListView listView = findViewById(R.id.list);
        WordAdapter colorAdapter = new WordAdapter(this,myColors);
        listView.setAdapter(colorAdapter);


    }
}
