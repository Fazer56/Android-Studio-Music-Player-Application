package com.example.faoilean.music;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.util.Objects;

/*
Music app
Student: Faoilean Fortune
Student Number: C15380201
Mobile Software Development*/
/**
 * Created by Faoilean on 21/11/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    //Database
    public static final String DATABASE_NAME = "Music.db";
    private static final int DATABASE_VERSION = 1;

    //tables
    public static final String USER_TABLE = "user";
    public static final String SONG_TABLE = "song";
    public static final String PLAYLIST_TABLE = "playlist";

    //user table
    public static final String USERID_COLUMN = "user_id";
    public static final String USERNAME_COLUMN = "username";
    public static final String PASSWORD_COLUMN = "password";
    public static final String FIRSTNAME_COLUMN = "firstname";
    public static final String LASTNAME_COLUMN = "lastname";

    //Song Table
    public static final String SONG_ID_COLUMN = "song_id";
    public static final String UID_COLUMN = "user_id";
    public static final String SONG_NAME = "songName";
    public static final String ARTIST_NAME_COLUMN = "artist";
    public static final String SONG_URL = "songUrl";
    public static final String SONG_PIC = "songPic";
    public static final String ALBUM_NAME_COLUMN = "album";


    //Playlist Table
    public static final String PLAYLIST_ID_COLUMN = "pList_id";
    public static final String PLUSER_ID_COLUMN = "user_id";
    public static final String SONGID_COLUMN = "song_id";
    public static final String PLAYLIST_NAME_COLUMN = "pListName";

    //create the tables
    public static final String SQL_CREATE_TABLE_USER = "CREATE TABLE IF NOT EXISTS " + USER_TABLE + "(" + USERID_COLUMN +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " + USERNAME_COLUMN +
            " TEXT, " + PASSWORD_COLUMN + " TEXT, " + FIRSTNAME_COLUMN + " TEXT, " +
            LASTNAME_COLUMN + " TEXT, " + " CONSTRAINT username_unique UNIQUE (" + USERNAME_COLUMN + ") " + ");";

    public static final String SQL_CREATE_TABLE_SONG = "CREATE TABLE IF NOT EXISTS " + SONG_TABLE + "(" + SONG_ID_COLUMN +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " + SONG_NAME + " TEXT, " + ARTIST_NAME_COLUMN + " TEXT, " +
            ALBUM_NAME_COLUMN + " TEXT, " + UID_COLUMN + " INT, " + SONG_PIC + " TEXT, " + SONG_URL + " TEXT UNIQUE, " +
            " FOREIGN KEY(" + UID_COLUMN + ") REFERENCES " + USER_TABLE + "(user_id) " + ");";

    public static final String SQL_CREATE_TABLE_PLAYLIST = "CREATE TABLE IF NOT EXISTS " + PLAYLIST_TABLE + "(" + PLAYLIST_ID_COLUMN +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " + PLAYLIST_NAME_COLUMN + " TEXT, " + PLUSER_ID_COLUMN + " INT, " +
            SONGID_COLUMN + " INT, " +
            " FOREIGN KEY(" + PLUSER_ID_COLUMN + ") REFERENCES " + USER_TABLE + "(user_id), " +
            " FOREIGN KEY(" + SONGID_COLUMN + ") REFERENCES " + SONG_TABLE + "(song_id) " + ");";

//inserts for song table
    public static final String INSERT_SONG_TABlE1 = "INSERT INTO " + SONG_TABLE + " VALUES(1,'HiiiPower', 'Kendrick Lamar', " +
    " 'Section 80', 1, 'kendrick', 'https://archive.org/details/MegaDrive198XADFullAlbum/16-Hiiipower.mp3');";
    public static final String INSERT_SONG_TABlE2 = " INSERT INTO " + SONG_TABLE + " VALUES(2, 'Rigamortus', 'Kendrick Lamar', " +
            " 'Section 80', 1, 'kendrick', 'https://ia601207.us.archive.org/28/items/MegaDrive198XADFullAlbum/12-Rigamortus.mp3');";
    public static final String INSERT_SONG_TABlE3 = " INSERT INTO " + SONG_TABLE + " VALUES(3,'Boyz In The Hood', 'Eazy-E', " +
            " 'Straight Outta Compton', 1, 'boyzin', 'https://ia600202.us.archive.org/33/items/EazyECruisinInMy64Lyrics/Eazy%20E%20-%20Cruisin%20in%20my%2064%27%20lyrics.mp3');" ;
    public static final String INSERT_SONG_TABlE4 = " INSERT INTO " + SONG_TABLE + " VALUES(4,'Arm Hammer', 'Kevin Gates', " +
            " 'Arm and Hammer', 1, 'kevin', 'https://ia801203.us.archive.org/11/items/Kevin_Gates_-_Arm_and_Hammer-2016/01%20-%20Kevin%20Gates%20-%20Arm%20%20Hammer.mp3');";
    public static final String INSERT_SONG_TABlE5 = " INSERT INTO " + SONG_TABLE + " VALUES(5,'Trilla', 'Asap-Rocky', " +
            " 'Long Live ASAP', 1, 'asap', 'https://ia601306.us.archive.org/26/items/AsAP_Rocky_-_LiveLoveAsAP-2011/08%20Asap%20Rocky%20-%20Trilla%20%28Feat.%20Asap%20Twelvy%20%26%20Asap%20Nast%29%20%5BProd.%20By%20Beautiful%20Lou%5D.mp3');" ;
    public static final String INSERT_SONG_TABlE6 = " INSERT INTO " + SONG_TABLE + " VALUES(6,'Gravity', 'Coldplay', " +
                    " 'Talk', 1, 'coldplay_talk', 'https://ia800806.us.archive.org/31/items/Gravity-Coldplay/ColdplayGravity1.mp3');" ;
    public static final String INSERT_SONG_TABlE7 = "INSERT INTO " + SONG_TABLE + " VALUES(7,'Hello', 'Adele', " +
                    " '25', 1, 'adele', 'https://ia800604.us.archive.org/5/items/AdeleHello_201706/Adele%20-%20Hello.mp3');" ;
    public static final String INSERT_SONG_TABlE8 = " INSERT INTO " + SONG_TABLE + " VALUES(8,'Thinking Out Loud', 'Ed Sheeran', " +
            " 'x', 1, 'edsheeran', 'https://ia600604.us.archive.org/5/items/AdeleHello_201706/Ed%20Sheeran%20-%20Thinking%20Out%20Loud%20%5BOfficial%20Video%5D.mp3');";

    public static final String INSERT_PLAYLIST_TABlE1 = "INSERT INTO " + PLAYLIST_TABLE + " VALUES(9, 'HiiiPower', 1, 1);";
    public static final String INSERT_PLAYLIST_TABlE2 = " INSERT INTO " + PLAYLIST_TABLE + " VALUES(10, 'Rigamortus', 1, 2);";
    public static final String INSERT_PLAYLIST_TABlE3 = " INSERT INTO " + PLAYLIST_TABLE + " VALUES(11 ,'Boyz In The Hood',1, 3);" ;
    public static final String INSERT_PLAYLIST_TABlE4 = " INSERT INTO " + PLAYLIST_TABLE + " VALUES(12,'Arm Hammer', 1, 4);";
    public static final String INSERT_PLAYLIST_TABlE5 = " INSERT INTO " + PLAYLIST_TABLE + " VALUES(13,'Trilla', 1, 5);";
    public static final String INSERT_PLAYLIST_TABlE6 = " INSERT INTO " + PLAYLIST_TABLE + " VALUES(14,'Gravity',1 ,6);" ;
    public static final String INSERT_PLAYLIST_TABlE7 = "INSERT INTO " + PLAYLIST_TABLE + " VALUES(15,'Hello', 1, 7);" ;
    public static final String INSERT_PLAYLIST_TABlE8 = " INSERT INTO " + PLAYLIST_TABLE + " VALUES(16,'Thinking Out Loud', 1, 8);";

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SQL_CREATE_TABLE_USER);
        db.execSQL(SQL_CREATE_TABLE_SONG);
        db.execSQL(SQL_CREATE_TABLE_PLAYLIST);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
       // db.execSQL("DROP TABLE IF EXISTS " + SONG_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + PLAYLIST_TABLE);

        onCreate(db);

    }

    //add the user to the song
    public void addThisSong(int userId, int songId){

        ContentValues values = new ContentValues();
        values.put("user_id", userId);


        SQLiteDatabase db = getWritableDatabase();
        db.update(SONG_TABLE, values, " song_id="+songId , null);

        db.close();

    }
    //get the id of the song
    public int getSongId(String songName){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        int id;
        //1 is every condition is met
        String query = "SELECT song_id FROM " + SONG_TABLE + " WHERE " + SONG_NAME + " =\"" + songName + "\";";

        //cursor point to location in your results
        Cursor c = db.rawQuery(query, null);
        //move to the first row in your results


        if(c.getCount() > 0)
        {
            for(int i = 0; i < c.getCount(); i++){
                c.moveToNext();
                dbString = c.getString(0);
            }

        }
        else{
            id = 99;
        }

        c.close();
        db.close();
        id = Integer.parseInt(dbString);
        return id;
    }

    public void addUser(User user) {

        //content valuse allows to set values for different columns at the same time
        ContentValues values = new ContentValues();
        values.put(USERNAME_COLUMN, user.getUsername());
        values.put(PASSWORD_COLUMN, user.getPassword());
        values.put(FIRSTNAME_COLUMN, user.getFirstName());
        values.put(LASTNAME_COLUMN, user.getLastName());

        //'db' is the database to be written to
        SQLiteDatabase db = getWritableDatabase();
       //INSERTS FOR SONG TABLE
        db.execSQL("DROP TABLE IF EXISTS " + SONG_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + PLAYLIST_TABLE);
        onCreate(db);
        db.execSQL(INSERT_SONG_TABlE1);
        db.execSQL(INSERT_SONG_TABlE2);
        db.execSQL(INSERT_SONG_TABlE3);
        db.execSQL(INSERT_SONG_TABlE4);
        db.execSQL(INSERT_SONG_TABlE5);
        db.execSQL(INSERT_SONG_TABlE6);
        db.execSQL(INSERT_SONG_TABlE7);
        db.execSQL(INSERT_SONG_TABlE8);
        db.execSQL(INSERT_PLAYLIST_TABlE1);
        db.execSQL(INSERT_PLAYLIST_TABlE2);
        db.execSQL(INSERT_PLAYLIST_TABlE3);
        db.execSQL(INSERT_PLAYLIST_TABlE4);
        db.execSQL(INSERT_PLAYLIST_TABlE5);
        db.execSQL(INSERT_PLAYLIST_TABlE6);
        db.execSQL(INSERT_PLAYLIST_TABlE7);
        db.execSQL(INSERT_PLAYLIST_TABlE8);
        //values the values to be inserted
        db.insert(USER_TABLE, null, values);
        db.close();
    }

    public void deleteUser(String userName){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + USER_TABLE + " WHERE " + USERNAME_COLUMN + " =\"" + userName + "\";" );

    }

    //print out database
    public String databaseToString(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();

        //1 is every condition is met
        String query = "SELECT * FROM " + USER_TABLE + " WHERE 1";

        //cursor point to location in your results
        Cursor c = db.rawQuery(query, null);
        //move to the first row in your results
        c.moveToFirst();

        while(!c.isAfterLast()){
            if(c.getString(c.getColumnIndex("username")) != null){
                dbString += c.getString(c.getColumnIndex("username"));
                dbString +="\n";
            }
            c.moveToNext();
        }

        c.close();
        db.close();
        return dbString;

    }

    //check that the password matches the username
    public String pWordSearch(String userName) {

        String dbString = "";
        String query = "SELECT password FROM " + USER_TABLE + " WHERE " + USERNAME_COLUMN + " =\"" + userName + "\";";
        SQLiteDatabase db = getWritableDatabase();

        Cursor c = db.rawQuery(query, null);
        if (c.getCount() > 0){
            c.moveToFirst();
            while (!c.isAfterLast()) {
                if (c.getString(c.getColumnIndex("password")) != null) {
                    dbString += c.getString(c.getColumnIndex("password"));
                }
                c.moveToNext();
            }
        }
        else{
            dbString = "Empty";
        }

        c.close();
        db.close();
        return dbString;

    }

    //get songs that the user has
    public String getSong(int id){

        String dbString="";
        String query = "SELECT songName FROM " + SONG_TABLE + " WHERE " + UID_COLUMN + " =\"" + id + "\" order by songName asc;";
        SQLiteDatabase db = getWritableDatabase();

        Cursor c = db.rawQuery(query, null);
        if(c.getCount() >0) {
            c.moveToFirst();
            while (!c.isAfterLast()) {
                if (c.getString(c.getColumnIndex("songName")) != null) {
                    dbString += c.getString(c.getColumnIndex("songName"));
                    dbString += "\n";
                }
                c.moveToNext();
            }
        }
        else{
            dbString = "Empty";
        }
        c.close();
        db.close();
        return dbString;
    }

    //get song info about a certain song
    public String getSongInfo(String song){

        String dbString="";
        String query = "SELECT * FROM " + SONG_TABLE + " WHERE " + SONG_NAME + "=\"" + song + "\";";
        SQLiteDatabase db = getWritableDatabase();

        Cursor c = db.rawQuery(query, null);

        if(c.getCount() > 0){
            for(int i =0; i < c.getCount(); i++)
            {
                c.moveToNext();
                dbString +=c.getString(0);
                dbString +="\n";
                dbString +=c.getString(1);
                dbString +="\n";
                dbString +=c.getString(2);
                dbString +="\n";
                dbString +=c.getString(3);
                dbString +="\n";
                dbString +=c.getString(4);
                dbString +="\n";
                dbString +=c.getString(5);
                dbString +="\n";
                dbString +=c.getString(6);
                dbString +="\n";


            }
        }

        c.close();
        db.close();
        return dbString;
    }


    //get song info about all songs
    public String getSongInfo() {

        String dbString = "";
        String query = "SELECT * FROM " + SONG_TABLE + " WHERE 1;";
        SQLiteDatabase db = getWritableDatabase();

        Cursor c = db.rawQuery(query, null);

        if (c.getCount() > 0) {
            for (int i = 0; i < c.getCount(); i++) {
                c.moveToNext();
                dbString += c.getString(1);
                dbString += "\n";
                dbString += c.getString(2);
                dbString += "\n";
                dbString += c.getString(3);
                dbString += "\n";
                dbString += c.getString(4);
                dbString += "\n";
                dbString += c.getString(5);
                dbString += "\n";


            }
        }

        return dbString;
    }

    //get the users id
    public int getId(String userName){
        int userId = 0;
        String id ="";
        String query = "SELECT user_id FROM " + USER_TABLE + " WHERE username " + " =\"" + userName + "\";";
        SQLiteDatabase db = getWritableDatabase();

        Cursor c = db.rawQuery(query, null);

        //check cursor for input
        if(c.getCount() > 0)
        {
            for(int i = 0; i < c.getCount(); i++){
                c.moveToNext();
                id = c.getString(0);

            }

        }
        else{
            userId = 0;
        }

        c.close();
        db.close();

        userId = Integer.parseInt(id);

        return userId;
    }


}
