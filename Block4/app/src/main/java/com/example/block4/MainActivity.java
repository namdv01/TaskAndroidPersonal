package com.example.block4;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player = MediaPlayer.create(this,R.raw.music);
        Switch isLooping = (Switch) findViewById(R.id.isLooping);
        isLooping.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                player.setLooping(b);
            }
        });
    }

    public void play(View v) {
        player.start();
    }

    public void pause(View v) {
        if(player.isPlaying()) {
            player.pause();
        }
    }
}