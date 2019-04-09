package com.example.android.learnitalian;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class WordAdapter extends ArrayAdapter<Word> {

    public static class ViewHolder {
        public TextView defaultViewHolder, italianViewHolder;
        public ImageView iconViewHolder;
    }

    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context       The current context. Used to inflate the layout file.
     * @param wordArrayList A List of Word objects to display in a list
     */
    public WordAdapter(Context context, ArrayList<Word> wordArrayList) {
        super(context, 0, wordArrayList);
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position    The position in the list of data that should be displayed in the
     *                    list item view.
     * @param convertView The recycled view to populate.
     * @param parent      The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item_layout, parent, false);

            ViewHolder holder = new ViewHolder();
            holder.defaultViewHolder = listItemView.findViewById(R.id.item_default);
            holder.italianViewHolder = listItemView.findViewById(R.id.item_italian);
            holder.iconViewHolder = listItemView.findViewById(R.id.item_icon);
            listItemView.setTag(holder);
        }

        // Get the {@link Word} object located at this position in the list
        Word currentWord = getItem(position);
        ViewHolder holder = (ViewHolder) listItemView.getTag();

        holder.defaultViewHolder.setText(currentWord.getmDefaultTranslation());
        holder.italianViewHolder.setText(currentWord.getmItalianTranslation());
        if (currentWord.hasImage()) {
            holder.iconViewHolder.setVisibility(View.VISIBLE);
            holder.iconViewHolder.setImageResource(currentWord.getmIcon());
            Log.v("PhrasesActivity", "Current word icon " + Integer.toString(currentWord.getmIcon()));
        } else {
            holder.iconViewHolder.setVisibility(View.GONE);
            Log.v("PhrasesActivity", "Current word icon " + Integer.toString(currentWord.getmIcon()));
        }

        return listItemView;
    }
}
