package com.example.android.learnitalian;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        ArrayList<Word> myArray = new ArrayList<>();

        myArray.add(new Word("zero", "zero"));
        myArray.add(new Word("uno", "one"));
        myArray.add(new Word("due", "two"));
        myArray.add(new Word("tre", "three"));
        myArray.add(new Word("quattro", "four"));
        myArray.add(new Word("cinque", "five"));
        myArray.add(new Word("sei", "six"));
        myArray.add(new Word("sette", "seven"));
        myArray.add(new Word("otto", "eight"));
        myArray.add(new Word("nove", "nine"));
        myArray.add(new Word("dieci", "ten"));


        WordAdapter itemsAdapter = new WordAdapter(this, myArray);
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);

    }
}
