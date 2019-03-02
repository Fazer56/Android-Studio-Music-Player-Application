package com.example.faoilean.music;

/**
 * Created by Faoilean on 21/11/2017.
 */
//class for song objects

/*
Music app
Student: Faoilean Fortune
Student Number: C15380201
Mobile Software Development*/

public class Song {

    private int song_id;
    private int user_id;
    private String songName;
    private String artist;
    private String album;
    private String songUrl;


    //default constructor
    public Song (String sName, String aName, String albName, String sUrl){
        this.songName = sName;
        this.artist = aName;
        this.album = albName;
        this.songUrl = sUrl;

    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getSongUrl() {
        return songUrl;
    }

    public void setSongUrl(String songUrl) {
        this.songUrl = songUrl;
    }

    public int getSong_id() {
        return song_id;
    }

    public void setSong_id(int song_id) {
        this.song_id = song_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    //getters


    public String getSongName() {
        return songName;
    }



    public void setSongName(String songName) {
        this.songName = songName;
    }

}
