/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.learnitalian;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        // Create an array of words
        String[] numbers = {"one","two","three","four","five","six","seven","eight","nine","ten"};


        Log.v("NumbersActivity", numbers[0]);
        ArrayList<String> myArray = new ArrayList<String>();

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

        Log.v("NumbersActivity", myArray.get(6));




    }
}
