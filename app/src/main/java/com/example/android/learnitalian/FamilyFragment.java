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
public class FamilyFragment extends Fragment {


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

    public FamilyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        final ArrayList<Word> myFamily = new ArrayList<>();

        myFamily.add(new Word(getString(R.string.mother_mom), "mother, mom", R.drawable.family_mother, R.raw.number_one));
        myFamily.add(new Word(getString(R.string.father_dad), "father, dad", R.drawable.family_father, R.raw.number_one));
        myFamily.add(new Word(getString(R.string.sister), "sister", R.drawable.family_younger_sister, R.raw.number_one));
        myFamily.add(new Word(getString(R.string.brother), "brother", R.drawable.family_older_brother, R.raw.number_one));
        myFamily.add(new Word(getString(R.string.grandmother), "grandmother", R.drawable.family_grandmother, R.raw.number_one));
        myFamily.add(new Word(getString(R.string.grandfather), "grandfather", R.drawable.family_grandfather, R.raw.number_one));
        myFamily.add(new Word(getString(R.string.cousin), "cousin", R.drawable.family_younger_brother, R.raw.number_one));
        myFamily.add(new Word(getString(R.string.son), "son", R.drawable.family_son, R.raw.number_one));
        myFamily.add(new Word(getString(R.string.daughter), "daughter", R.drawable.family_daughter, R.raw.number_one));
        myFamily.add(new Word(getString(R.string.uncle), "uncle", R.drawable.family_younger_sister, R.raw.number_one));
        myFamily.add(new Word(getString(R.string.aunt), "aunt", R.drawable.family_younger_brother, R.raw.number_one));


        WordAdapter itemsAdapter = new WordAdapter(getActivity(), myFamily);
        ListView listView = rootView.findViewById(R.id.list);
        listView.setBackgroundColor(getResources().getColor(R.color.category_family));
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

                    mediaPlayer = MediaPlayer.create(getActivity(), myFamily.get(position).getmAudio());
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
