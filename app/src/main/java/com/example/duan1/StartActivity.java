package com.example.duan1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvQuestion;
    private Button btnCaseA;
    private Button btnCaseB;
    private Button btnCaseC;
    private Button btnCaseD;
    private TextView tvTimedown;
    private TextView tvSoCau;
    private int trueCase;
    private int i;
    private int dem;
    private int wait;
    private boolean check;
    private TextView tvCoin;
    private boolean run;
    private int coin;
    private Dialog dialog;
    private ImageView imHelp_5050;
    private ImageView imHelp_audience;
    private ImageView imHelp_call;
    private ImageView imHelp_stop;
    private MediaPlayer mediaPlayer;
    private ImageView imClock;
    private int timeRunHelp;
    private int idHelp;
    private Animation animationButton;
    private boolean checkPickAnswer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        initView();
    }
    private void initView() {
        i=0;
        dem=30;
        wait=30;
        coin=0;
        timeRunHelp=30;
        imClock= (ImageView) findViewById(R.id.imClock);
        Animation animation= AnimationUtils.loadAnimation(this,R.anim.time_round);
        imClock.startAnimation(animation);
        tvQuestion= (TextView) findViewById(R.id.txt_questions);
        tvTimedown= (TextView) findViewById(R.id.tv_timeDown);
        tvCoin= (TextView) findViewById(R.id.tvCoin);
        tvSoCau= (TextView) findViewById(R.id.tvSoCau);
        btnCaseA= (Button) findViewById(R.id.answer_A);
        btnCaseB= (Button) findViewById(R.id.answer_B);
        btnCaseC= (Button) findViewById(R.id.answer_C);
        btnCaseD= (Button) findViewById(R.id.answer_D);
        imHelp_5050= (ImageView) findViewById(R.id.help_5050);
        imHelp_audience= (ImageView) findViewById(R.id.help_audience);
        imHelp_call= (ImageView) findViewById(R.id.help_call);
        imHelp_stop= (ImageView) findViewById(R.id.help_stop);
        btnCaseA.setOnClickListener(this);
        btnCaseB.setOnClickListener(this);
        btnCaseC.setOnClickListener(this);
        btnCaseD.setOnClickListener(this);
        imHelp_5050.setOnClickListener(this);
        imHelp_audience.setOnClickListener(this);
        imHelp_call.setOnClickListener(this);
        imHelp_stop.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        run=false;
        if (!checkPickAnswer){
            return;
        }
        if (v.getId()==R.id.answer_A ||
                v.getId()==R.id.answer_B||
                v.getId()==R.id.answer_C||
                v.getId()==R.id.answer_D){
            v.setBackgroundResource(R.drawable.answer_choose);
            switch (v.getId()){
                case R.id.answer_A:
                    playSound(R.raw.ans_a);
                    break;
                case R.id.answer_B:
                    playSound(R.raw.ans_b);
                    break;
                case R.id.answer_C:
                    playSound(R.raw.ans_c);
                    break;
                case R.id.answer_D:
                    playSound(R.raw.ans_d);
                    break;
            }
            if (v.getId()==trueCase){
                check=true;
                wait=0;
                coin= coin+200*(i+1);
            }else {
                check=false;
                wait=0;
            }
            checkPickAnswer=false;
        }
        else if (v.getId()==R.id.help_5050){
            checkPickAnswer=false;
            timeRunHelp=0;
            idHelp=R.id.help_5050;
            playSound(R.raw.sound5050);
            imHelp_5050.setEnabled(false);
            imHelp_5050.setImageResource(R.drawable.atp__activity_player_button_image_help_5050_x);
        }else if (v.getId()==R.id.help_audience){
            checkPickAnswer=false;
            timeRunHelp=0;
            idHelp=R.id.help_audience;
            imHelp_audience.setEnabled(false);
            playSound(R.raw.khan_gia);
            imHelp_audience.setImageResource(R.drawable.atp__activity_player_button_image_help_audience_x);
        }else if (v.getId()==R.id.help_call){
            checkPickAnswer=false;
            timeRunHelp=0;
            idHelp=R.id.help_call;
            imHelp_call.setEnabled(false);
            playSound(R.raw.call);
            imHelp_call.setImageResource(R.drawable.atp__activity_player_button_image_help_call_x);
        }else if (v.getId()==R.id.help_stop){
            checkPickAnswer=false;

        }

    }
    public void playSound(int type){
        mediaPlayer= MediaPlayer.create(this,type);
        mediaPlayer.start();
    }
}
