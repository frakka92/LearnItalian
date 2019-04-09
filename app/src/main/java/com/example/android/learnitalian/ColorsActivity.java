package com.example.android.learnitalian;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        final ArrayList<Word> myColors = new ArrayList<>();

        myColors.add(new Word(getString(R.string.red), "red", R.drawable.color_red, R.raw.number_one));
        myColors.add(new Word(getString(R.string.blue), "blue", R.drawable.color_gray, R.raw.number_one));
        myColors.add(new Word(getString(R.string.green), "green", R.drawable.color_green, R.raw.number_one));
        myColors.add(new Word(getString(R.string.yellow), "yellow", R.drawable.color_dusty_yellow, R.raw.number_one));
        myColors.add(new Word(getString(R.string.orange), "orange", R.drawable.color_mustard_yellow, R.raw.number_one));
        myColors.add(new Word(getString(R.string.brown), "brown", R.drawable.color_brown, R.raw.number_one));
        myColors.add(new Word(getString(R.string.gray), "grey", R.drawable.color_gray, R.raw.number_one));
        myColors.add(new Word(getString(R.string.purple), "purple", R.drawable.color_gray, R.raw.number_one));
        myColors.add(new Word(getString(R.string.pink), "pink", R.drawable.color_red, R.raw.number_one));
        myColors.add(new Word(getString(R.string.white), "white", R.drawable.color_white, R.raw.number_one));
        myColors.add(new Word(getString(R.string.black), "black", R.drawable.color_black, R.raw.number_one));

        ListView listView = findViewById(R.id.list);
        WordAdapter colorAdapter = new WordAdapter(this, myColors);
        listView.setBackgroundColor(getResources().getColor(R.color.category_colors));
        listView.setAdapter(colorAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releaseMediaPlayer();

                //initialize audioManager
                audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

                //This method was deprecated in API level 26. use requestAudioFocus(android.media.AudioFocusRequest)
                int audioFocusReturn = audioManager.requestAudioFocus(onAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (audioFocusReturn == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    mediaPlayer = MediaPlayer.create(ColorsActivity.this, myColors.get(position).getmAudio());
                    mediaPlayer.start();

                    mediaPlayer.setOnCompletionListener(mCompletionListener);
                }

            }
        });
    }

    @Override
    protected void onStop() {
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
