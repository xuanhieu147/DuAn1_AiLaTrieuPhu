package com.example.duan1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnStart;
    private Button btnHelp;
    private Button btnExit;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playSound(R.raw.bg_music);
        initView();
    }

    private void initView() {
        btnStart = (Button) findViewById(R.id.btn_batDau);
        btnHelp = (Button) findViewById(R.id.btn_troGiup);
        btnExit = (Button) findViewById(R.id.btn_thoat);
        btnStart.setOnClickListener(this);
        btnHelp.setOnClickListener(this);
        btnExit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_batDau:
                mediaPlayer.stop();
                playSound(R.raw.batdauchoi);
                Intent intent = new Intent(this, StartActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_troGiup:
                mediaPlayer.stop();
                Intent intent1 = new Intent(this, HelpActivity.class);
                startActivity(intent1);
                break;
            case R.id.btn_thoat:
                System.exit(0);
                break;
        }
    }


    public void playSound(int type) {
        mediaPlayer = MediaPlayer.create(this, type);
        mediaPlayer.start();
    }
}
