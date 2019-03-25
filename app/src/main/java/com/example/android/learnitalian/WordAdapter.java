package com.example.android.learnitalian;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;


public class WordAdapter extends ArrayAdapter<Word> {

    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context        The current context. Used to inflate the layout file.
     * @param wordArrayList A List of Word objects to display in a list
     */
    public WordAdapter(Context context, ArrayList<Word> wordArrayList) {
        super(context, 0, wordArrayList);
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position The position in the list of data that should be displayed in the
     *                 list item view.
     * @param convertView The recycled view to populate.
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item_layout, parent, false);
        }

        // Get the {@link Word} object located at this position in the list
        Word currentWord = getItem(position);

        // Find the TextView in the list_item_layout.xml layout with the ID item_italian
        TextView italianTextView = listItemView.findViewById(R.id.item_italian);
        // Get the Italian translation from the current Word object and
        // set this text on the number TextView
        italianTextView.setText(currentWord.getmItalianWord());

        // Find the TextView in the list_item_layout.xml layout with the ID item_default
        TextView defaultTextView = listItemView.findViewById(R.id.item_default);
        // Get the default translation from the current Word object and
        // set this text on the name TextView
        defaultTextView.setText(currentWord.getmDefaultTranslation());

        // Return the whole list item layout (containing 2 TextViews)
        // so that it can be shown in the ListView
        return listItemView;
    }
}
