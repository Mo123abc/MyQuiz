package com.example.user.myquiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by user on 26/12/2016.
 */

public class QuestionBank extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION= 1;
    private static final String DATABASE_NAME= "questionBank.db";
    private static final String TABLE_NAME= "questionBank";
    private static final String COLUMN_ID= "id";
    private static final String COLUMN_QUESTION= "question";
    private static final String COLUMN_ANSWER= "answer";
    SQLiteDatabase db;

    private static final String TABLE_CREATE = "create table questionBank (id integer primary key not null," +
            "question text not null, answer text not null);";

    public QuestionBank(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db = db;
        insertQuestion(db);

    }

    public void insertQuestion(SQLiteDatabase db) {


        ContentValues values = new ContentValues();

        values.put(COLUMN_ID, 1);
        values.put(COLUMN_QUESTION, "What is the name of the world largest ocean?");
        values.put(COLUMN_ANSWER, "pacific");
        db.insert(TABLE_NAME, null, values);

        ContentValues values2 = new ContentValues();

        values2.put(COLUMN_ID, 2);
        values2.put(COLUMN_QUESTION, "Where is the deepest known part of the world located?");
        values2.put(COLUMN_ANSWER, "marianatrench");
        db.insert(TABLE_NAME, null, values2);

        ContentValues values3 = new ContentValues();

        values3.put(COLUMN_ID, 3);
        values3.put(COLUMN_QUESTION, "What is the name of the largest country in the world?");
        values3.put(COLUMN_ANSWER, "russia");
        db.insert(TABLE_NAME, null, values3);


        ContentValues values4 = new ContentValues();

        values4.put(COLUMN_ID, 4);
        values4.put(COLUMN_QUESTION, "Which mega city has the greatest population?");
        values4.put(COLUMN_ANSWER, "tokyo");
        db.insert(TABLE_NAME, null, values4);

        ContentValues values5 = new ContentValues();

        values5.put(COLUMN_ID, 5);
        values5.put(COLUMN_QUESTION, "What is the name of the only mammal with the ability to fly?");
        values5.put(COLUMN_ANSWER, "bat");
        db.insert(TABLE_NAME, null, values5);

       }
    public String selectQuestion (int i) {
        db = this.getReadableDatabase();

        String query = "select " + COLUMN_QUESTION + "," + COLUMN_ANSWER + " from " + TABLE_NAME + " order by " + COLUMN_ID;
        Cursor cursor = db.rawQuery(query, null);


        cursor.moveToPosition(i - 1);
        String question = cursor.getString(cursor.getColumnIndex(COLUMN_QUESTION));

        cursor.close();
        db.close();
        return question;
    }
    public String selectAnswer (int i) {
        db = this.getReadableDatabase();

        String query = "select " + COLUMN_QUESTION + "," + COLUMN_ANSWER + " from " + TABLE_NAME + " order by " + COLUMN_ID;
        Cursor cursor = db.rawQuery(query, null);


        cursor.moveToPosition(i - 1);
        String answer = cursor.getString(cursor.getColumnIndex(COLUMN_ANSWER));

        cursor.close();
        db.close();
        return answer;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query  =" DROP TABLE IF EXISTS"+TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }
}
