package com.example.faoilean.music;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

import static android.R.attr.data;
import static java.lang.String.valueOf;

/*
Music app
Student: Faoilean Fortune
Student Number: C15380201
Mobile Software Development*/

public class SongListed extends AppCompatActivity {

    ListAdapter theAdapter;
    TextView uName;
    ListView list;
    DBHelper dbHelper;
    Button logOut;
    String user;
    Button addSongs;
    String pick;
    String[] splitpick;
    boolean resumeChk;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_listed);

        addSongs = (Button) findViewById(R.id.addSong);
        dbHelper = new DBHelper(this, null, null, 1);
        uName = (TextView) findViewById(R.id.username);
        Intent activityThatCalled = getIntent();
        user = activityThatCalled.getExtras().getString("Username");
        uName.setText(user);

        logOut = (Button) findViewById(R.id.logout);

        pick = dbHelper.getSong(dbHelper.getId(user));
        splitpick = pick.split("\n");

        list = (ListView) findViewById(R.id.theList);
        theAdapter = new MyAdapter(this, splitpick);
        list.setAdapter(theAdapter);
        resumeChk = false;

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String picked = valueOf(parent.getItemAtPosition(position));
                Context context = getApplicationContext();

                Toast.makeText(context, picked, Toast.LENGTH_SHORT).show();
                Intent nowPlayingIntent = new Intent(context, NowPlaying.class);

                nowPlayingIntent.putExtra("Song", picked);
                nowPlayingIntent.putExtra("Username", user);
                nowPlayingIntent.putExtra("SongList", splitpick);
                startActivityForResult(nowPlayingIntent, 1);


            }
        });


        addSongs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                Intent startUserAddIntent = new Intent(context, UserAddSong.class);
                startUserAddIntent.putExtra("Username", user);
                startActivityForResult(startUserAddIntent, 1);
            }
        });

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });


    }

    public void logout() {
        Toast.makeText(this, "Logged Out: " + user, Toast.LENGTH_SHORT).show();
        finish();
    }



    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override

    protected void onRestart() {
        super.onRestart();

        //after user leaves activity call restart to update the list.
            int id = dbHelper.getId(user);
            pick = dbHelper.getSong(id);

            splitpick = pick.split("\n");

            list = (ListView) findViewById(R.id.theList);
            theAdapter = new MyAdapter(this, splitpick);
            list.setAdapter(theAdapter);

            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    String picked = valueOf(parent.getItemAtPosition(position));
                    Context context = getApplicationContext();

                    Toast.makeText(context, picked, Toast.LENGTH_SHORT).show();
                    Intent nowPlayingIntent = new Intent(context, NowPlaying.class);

                    nowPlayingIntent.putExtra("Song", picked);
                    nowPlayingIntent.putExtra("Username", user);
                    nowPlayingIntent.putExtra("SongList", splitpick);
                    startActivity(nowPlayingIntent);
                }
            });

        }

}



