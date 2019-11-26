package com.example.duan1;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HelpActivity extends AppCompatActivity {
    private Button btnReady;
    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        mediaPlayer= MediaPlayer.create(this,R.raw.luatchoi);
        mediaPlayer.start();
        initView();
    }


    private void initView() {
        btnReady = (Button) findViewById(R.id.btnSanSang);
        btnReady.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                onBackPressed();
                playSound(R.raw.bg_music);
            }
        });
    }

    public void playSound(int type){
        mediaPlayer = MediaPlayer.create(this, type);
        mediaPlayer.start();
    }
}
