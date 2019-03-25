package com.example.android.learnitalian;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        ArrayList<Word> myPhrases= new ArrayList<>();

        myPhrases.add(new Word("Dove stai andando?", "Where are you going?"));
        myPhrases.add(new Word("Qual è il tuo nome?", "What is your name?"));
        myPhrases.add(new Word("Il mio nome è...", "My name is..."));
        myPhrases.add(new Word("Come ti senti?", "How are you feeling?"));
        myPhrases.add(new Word("Mi sento bene", "I’m feeling good"));
        myPhrases.add(new Word("Andiamo", "Let’s go"));
        myPhrases.add(new Word("Vieni quì", "Come here"));
        myPhrases.add(new Word("Che ore sono?", "What time is it?"));
        myPhrases.add(new Word("Di dove sei?", "Where are you from?"));
        myPhrases.add(new Word("Hai fame?", "Are you hungry?"));
        myPhrases.add(new Word("Hai sete?", "Are you thirsty?"));


        WordAdapter itemsAdapter = new WordAdapter(this, myPhrases);
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);
    }
}
