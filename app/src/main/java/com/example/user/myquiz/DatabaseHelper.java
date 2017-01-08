package com.example.user.myquiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by user on 20/12/2016.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION= 1;
    private static final String DATABASE_NAME= "player.db";
    private static final String TABLE_NAME= "player";
    private static final String COLUMN_ID= "id";
    private static final String COLUMN_USERNAME= "Username";
    private static final String COLUMN_NAME= "name";
    private static final String COLUMN_PASSWORD= "password";
    private static final String TABLE2_NAME= "leaderboard";
    private static final String COLUMN2_ID= "id";
    private static final String COLUMN2_USERNAME= "Username";
    private static final String COLUMN2_SCORE= "Score";
    SQLiteDatabase db;



    private static final String TABLE_CREATE = "create table player (id integer primary key not null," +
            "Username text not null, name text not null , password text not null);";
    private static final String TABLE2_CREATE = "create table leaderboard (id integer primary key not null," +
            "Username text not null, Score text not null);";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        db.execSQL(TABLE2_CREATE);
        this.db = db;


    }


    public void insertPlayer(Player p){
        db = this.getWritableDatabase();

        ContentValues Values = new ContentValues();

        String query = "select * from Player";
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();

        Values.put(COLUMN_ID, count);
        Values.put(COLUMN_USERNAME, p.getUsername());
        Values.put(COLUMN_NAME, p.getName());
        Values.put(COLUMN_PASSWORD, p.getPassword());

        db.insert(TABLE_NAME, null, Values);
        db.close();
    }

    public void insertPlayerScore(Player p){
        db = this.getWritableDatabase();

        ContentValues Values = new ContentValues();

        String query = "select * from Leaderboard";
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();

        Values.put(COLUMN2_ID, count);
        Values.put(COLUMN2_USERNAME, p.getUsername());
        Values.put(COLUMN2_SCORE, p.getScore());

        db.insert(TABLE2_NAME, null, Values);
        cursor.close();
        db.close();
    }

    String [] recentScore (String a){
        db = this.getReadableDatabase();
        String query = "select * from " + TABLE2_NAME + " order by " + COLUMN2_USERNAME + " asc, " + COLUMN2_SCORE + " desc";
        Cursor cursor = db.rawQuery(query, null);
        String [] scores = new String[10];
        int i =0;
        if (cursor.moveToFirst()){
            do{
                if (a.equals(cursor.getString(cursor.getColumnIndex(COLUMN2_USERNAME)))){
                    scores[i] = cursor.getString(cursor.getColumnIndex(COLUMN2_SCORE));
                    i++;
                    if(i==3){
                        break;
                    }
                }

            }
            while (cursor.moveToNext());
        }
        return scores;
    }

    public String searchPass(String username) {
        String a, b = "not found";
        db = this.getReadableDatabase();

        String query = "select " + COLUMN_USERNAME +"," + COLUMN_PASSWORD + " from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);


        if (cursor.moveToFirst()) {
            do {
                a = cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME));

                if (a.equals(username)) {
                    b = cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD));
                    db.close();
                    break;
                }
            }
                while (cursor.moveToNext()) ;

        }
        cursor.close();
        return b;

    }
    Player getScore(int i) {
        Player player = new Player();
        //String b = "not found";
        db = this.getReadableDatabase();

        String query = "select * from " + TABLE2_NAME + " order by " + COLUMN2_SCORE + " desc, " + COLUMN2_USERNAME;
        Cursor cursor = db.rawQuery(query, null);


        cursor.moveToPosition(i-1);
        player.setUsername(cursor.getString(cursor.getColumnIndex(COLUMN2_USERNAME)));
        player.setScore(cursor.getInt( cursor.getColumnIndex(COLUMN2_SCORE)));

        cursor.close();
        db.close();
        return player;

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    String query  =" DROP TABLE IF EXISTS"+TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }
}
