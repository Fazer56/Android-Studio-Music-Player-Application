package com.example.faoilean.music;

/*
Music app
Student: Faoilean Fortune
Student Number: C15380201
Mobile Software Development*/

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class UserAddSong extends AppCompatActivity {

    private ImageButton adele;
    private ImageButton kendrick;
    private ImageButton kevin;
    private ImageButton gravity;
    private ImageButton asap;
    private ImageButton kendrick2;
    private ImageButton edSheeran;
    private ImageButton eazy;
    private TextView t1;
    private TextView t2;
    private TextView t3;
    private DBHelper myDb;
    private String user;
    private int id;
    private Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_add_song);

        adele = (ImageButton) findViewById(R.id.adele);
        kendrick = (ImageButton) findViewById(R.id.kendrick);
        kendrick2 = (ImageButton) findViewById(R.id.kendrick2);
        kevin = (ImageButton) findViewById(R.id.kevin);
        gravity = (ImageButton) findViewById(R.id.gravity);
        asap = (ImageButton) findViewById(R.id.asap);
        edSheeran = (ImageButton) findViewById(R.id.edSheeran);
        eazy = (ImageButton) findViewById(R.id.boyzin);
        t1 = (TextView) findViewById(R.id.SongInf);
        t2 = (TextView) findViewById(R.id.ArtistInf);
        t3 = (TextView) findViewById(R.id.AlbumInf);
        btn = (Button) findViewById(R.id.finish);

        myDb = new DBHelper(this, null, null, 1);

        Intent getSongListIntent = getIntent();

        user = getSongListIntent.getExtras().getString("Username");

        id = myDb.getId(user);

        //add songs to the user
        adele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addSong("Hello");
            }
        });

        kendrick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addSong("Rigamortus");
            }
        });

        kendrick2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addSong("HiiiPower");
            }
        });

        kevin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addSong("Arm Hammer");
            }
        });

        gravity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addSong("Gravity");
            }
        });

        asap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addSong("Trilla");
            }
        });

        edSheeran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addSong("Thinking Out Loud");
            }
        });

        eazy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addSong("Boyz In The Hood");
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                done();

            }
        });

    }


    public void addSong(String songName)
    {
       myDb.addThisSong(id, myDb.getSongId(songName));
        Toast.makeText(this, "Song " + songName + " added!", Toast.LENGTH_SHORT).show();
        String mySong = myDb.getSongInfo(songName);
        String[] info = mySong.split("\n");
        t1.setText(info[1]);
        t2.setText(info[2]);
        t3.setText(info[3]);

    }

    public void done(){
        //send the result back to the song listed
        Intent songPickedIntent = new Intent();
        songPickedIntent.putExtra("Result", user);
        setResult(Activity.RESULT_OK, songPickedIntent);
        finish();
    }


}
