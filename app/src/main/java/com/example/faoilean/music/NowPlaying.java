package com.example.faoilean.music;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Message;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.os.Message;
import android.os.Handler;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


/*
Music app
Student: Faoilean Fortune
Student Number: C15380201
Mobile Software Development*/

public class NowPlaying extends AppCompatActivity {

    private SeekBar seekB;
    private TextView elapsedTime;
    private TextView totalTime;
    private TextView songName;
    private TextView album;
    private TextView artist;
    private ImageView img;
    private TextView user;
    private MediaPlayer myPlayer;
    private ImageButton pause;
    private ImageButton play;
    private double current;
    private double finish;
    private Handler myHandler;
    private DBHelper myDb;
    private String usern;
    private String song;
    private int update;
    private Button finishBtn;
    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_playing);

        myDb = new DBHelper(this, null, null, 1);
        Intent songListIntent = getIntent();
        song = songListIntent.getExtras().getString("Song");
        usern = songListIntent.getExtras().getString("Username");

        String songInfo = myDb.getSongInfo(song);
        String[] songList = songInfo.split("\n");

        img = (ImageView) findViewById(R.id.vImage);
        user = (TextView) findViewById(R.id.userName);
        seekB = (SeekBar) findViewById(R.id.seekBar);
        elapsedTime = (TextView) findViewById(R.id.timePassed);
        totalTime = (TextView) findViewById(R.id.timeLeft);
        songName = (TextView) findViewById(R.id.songName);
        artist = (TextView) findViewById(R.id.artist);
        album = (TextView) findViewById(R.id.album);
        play = (ImageButton) findViewById(R.id.playBtn);
        pause = (ImageButton) findViewById(R.id.pauseBtn);
        finishBtn = (Button) findViewById(R.id.finish) ;
        seekB.setClickable(false);
        pause.setEnabled(false);
        Context context = getApplicationContext();

        songName.setText(songList[1]);
        artist.setText(songList[2]);
        album.setText(songList[3]);
        user.setText(usern);

        String songN = songList[1];

        //Switch for songchoice to play
        switch(songN) {
            case "HiiiPower":
                img.setImageResource(R.drawable.kendrick);
                myPlayer = MediaPlayer.create(context, R.raw.hiiipower);
                break;
            case "Rigamortus":
                img.setImageResource(R.drawable.kendrick);
                myPlayer = MediaPlayer.create(context, R.raw.hiiipower);
                break;
            case "Gravity":
                myPlayer = MediaPlayer.create(context, R.raw.gravity);
                img.setImageResource(R.drawable.coldplay_talk);
                break;
            case "Trilla":
                myPlayer = MediaPlayer.create(context, R.raw.trilla);
                img.setImageResource(R.drawable.asap);
                break;
            case "Boyz In The Hood":
                myPlayer = MediaPlayer.create(context, R.raw.boyzin);
                img.setImageResource(R.drawable.boyzin);
                break;
            case "Thinking Out Loud":
                img.setImageResource(R.drawable.edsheeran);
                myPlayer = MediaPlayer.create(context, R.raw.thinking_out_loud);
                break;
            case "Hello":
                img.setImageResource(R.drawable.adele);
                myPlayer = MediaPlayer.create(context, R.raw.hello);
                break;
            case "Arm Hammer":
                img.setImageResource(R.drawable.kevin);
                myPlayer = MediaPlayer.create(context, R.raw.armhammer);
                break;
        }



        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                playSong();


            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseSong();
            }
        });

        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                done();
            }
        });

        //handler to update progressBar while song is playing.
        myHandler = new Handler();
        //update once every second
        update = 1000;


    }

    //play the song from the switch statement
    public void playSong() {

        play.setEnabled(false);
        pause.setEnabled(true);

        myPlayer.start();

        //
        current = myPlayer.getCurrentPosition();
        finish = myPlayer.getDuration();
        seekB.setMax((int) finish);
        seekB.setProgress((int) current);

        //handler is called every second using postdelayed the time and progress bar are updated using current position of the mediaplayer buffer
        myHandler.postDelayed(new Runnable() {
            public void run() {

                current = myPlayer.getCurrentPosition();
                finish = myPlayer.getDuration();
                seekB.setProgress((int) current);
                totalTime.setText(String.format("%d.%d", TimeUnit.MILLISECONDS.toMinutes((long) finish),
                        TimeUnit.MILLISECONDS.toSeconds((long) finish) - TimeUnit.MILLISECONDS.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) finish))));


                elapsedTime.setText(String.format("%d.%d", TimeUnit.MILLISECONDS.toMinutes((long) current), TimeUnit.MILLISECONDS.toSeconds((long) current)
                        - TimeUnit.MILLISECONDS.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) current))));

                myHandler.postDelayed(this, update);
            }
        }, update);

    }
    //pause song
    public void pauseSong(){
        play.setEnabled(true);
        pause.setEnabled(false);

        myPlayer.pause();
    }

    //finished with song
    public void done() {

        myPlayer.stop();
        finish();
    }

}
