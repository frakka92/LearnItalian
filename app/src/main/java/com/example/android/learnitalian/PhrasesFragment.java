package com.example.android.learnitalian;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhrasesFragment extends Fragment {


    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    private AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                //Gain audio focus
                mediaPlayer.start();

            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                //Permanent loss of the audio focus
                releaseMediaPlayer();

            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                //Temporary loss of the audio focus, we should start the audio from the beginning since we want to hear the whole pronunciation
                mediaPlayer.pause();
                mediaPlayer.seekTo(0);
            }
        }
    };

    public PhrasesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        final ArrayList<Word> myPhrases = new ArrayList<>();

        myPhrases.add(new Word(getString(R.string.where_going), "Where are you going?", R.raw.number_one));
        myPhrases.add(new Word(getString(R.string.what_name), "What is your name?", R.raw.number_one));
        myPhrases.add(new Word(getString(R.string.my_name_is), "My name is...", R.raw.number_one));
        myPhrases.add(new Word(getString(R.string.how_feel), "How are you feeling?", R.raw.number_one));
        myPhrases.add(new Word(getString(R.string.feel_good), "I’m feeling good", R.raw.number_one));
        myPhrases.add(new Word(getString(R.string.lets_go), "Let’s go", R.raw.number_one));
        myPhrases.add(new Word(getString(R.string.come_here), "Come here", R.raw.number_one));
        myPhrases.add(new Word(getString(R.string.what_time), "What time is it?", R.raw.number_one));
        myPhrases.add(new Word(getString(R.string.where_form), "Where are you from?", R.raw.number_one));
        myPhrases.add(new Word(getString(R.string.hungry), "Are you hungry?", R.raw.number_one));
        myPhrases.add(new Word(getString(R.string.thirsty), "Are you thirsty?", R.raw.number_one));


        WordAdapter itemsAdapter = new WordAdapter(getActivity(), myPhrases);
        ListView listView = rootView.findViewById(R.id.list);
        listView.setBackgroundColor(getResources().getColor(R.color.category_phrases));
        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releaseMediaPlayer();

                //initialize audioManager
                audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

                //This method was deprecated in API level 26. use requestAudioFocus(android.media.AudioFocusRequest)
                int audioFocusReturn = audioManager.requestAudioFocus(onAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (audioFocusReturn == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    mediaPlayer = MediaPlayer.create(getActivity(), myPhrases.get(position).getmAudio());
                    mediaPlayer.start();

                    mediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });

        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;

            audioManager.abandonAudioFocus(onAudioFocusChangeListener);
        }
    }
}
