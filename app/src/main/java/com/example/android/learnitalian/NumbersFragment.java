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
public class NumbersFragment extends Fragment {


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

    public NumbersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        final ArrayList<Word> myNumbers = new ArrayList<>();

        //myNumbers.add(new Word(getString(R.string.zero), "zero"));
        myNumbers.add(new Word(getString(R.string.one), "one", R.drawable.number_one, R.raw.number_one));
        myNumbers.add(new Word(getString(R.string.two), "two", R.drawable.number_two, R.raw.number_two));
        myNumbers.add(new Word(getString(R.string.three), "three", R.drawable.number_three, R.raw.number_three));
        myNumbers.add(new Word(getString(R.string.four), "four", R.drawable.number_four, R.raw.number_four));
        myNumbers.add(new Word(getString(R.string.five), "five", R.drawable.number_five, R.raw.number_five));
        myNumbers.add(new Word(getString(R.string.six), "six", R.drawable.number_six, R.raw.number_six));
        myNumbers.add(new Word(getString(R.string.seven), "seven", R.drawable.number_seven, R.raw.number_seven));
        myNumbers.add(new Word(getString(R.string.eight), "eight", R.drawable.number_eight, R.raw.number_eight));
        myNumbers.add(new Word(getString(R.string.nine), "nine", R.drawable.number_nine, R.raw.number_nine));
        myNumbers.add(new Word(getString(R.string.ten), "ten", R.drawable.number_ten, R.raw.number_ten));


        WordAdapter itemsAdapter = new WordAdapter(getActivity(), myNumbers);
        ListView listView = rootView.findViewById(R.id.list);
        listView.setBackgroundColor(getResources().getColor(R.color.category_numbers));
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

                    mediaPlayer = MediaPlayer.create(getActivity(), myNumbers.get(position).getmAudio());
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
