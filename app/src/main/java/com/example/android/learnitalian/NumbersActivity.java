package com.example.android.learnitalian;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        ArrayList<String> myArray = new ArrayList<>();

        myArray.add("zero");
        myArray.add("one");
        myArray.add("two");
        myArray.add("three");
        myArray.add("four");
        myArray.add("five");
        myArray.add("six");
        myArray.add("seven");
        myArray.add("eight");
        myArray.add("ten");

        LinearLayout rootView = findViewById(R.id.linear_root);

        for(int i=0; i < myArray.size();i++) {
            TextView txtNumber = new TextView(this);
            txtNumber.setText(myArray.get(i));
            rootView.addView(txtNumber);
          }
    }
}
