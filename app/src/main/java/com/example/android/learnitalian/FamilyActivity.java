package com.example.android.learnitalian;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        ArrayList<Word> myFamily= new ArrayList<>();

        myFamily.add(new Word("madre, mamma", "mother, mom"));
        myFamily.add(new Word("padre, papà", "father, dad"));
        myFamily.add(new Word("sorella", "sister"));
        myFamily.add(new Word("fratello", "brother"));
        myFamily.add(new Word("nonna", "grandmother"));
        myFamily.add(new Word("nonno", "grandfather"));
        myFamily.add(new Word("cugino/a", "cousin"));
        myFamily.add(new Word("figlio", "son"));
        myFamily.add(new Word("figlia", "daughter"));
        myFamily.add(new Word("zio", "uncle"));
        myFamily.add(new Word("zia", "aunt"));


        WordAdapter itemsAdapter = new WordAdapter(this, myFamily);
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);
    }
}
