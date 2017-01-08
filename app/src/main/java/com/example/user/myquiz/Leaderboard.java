package com.example.user.myquiz;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Leaderboard extends AppCompatActivity  {
    @Override
    public void onBackPressed() {

    }

    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);


       final String name = getIntent().getStringExtra("loggedin");

       TextView Score1 = (TextView)findViewById(R.id.tScore);
        Score1.setText(String.valueOf(helper.getScore(1).getUsername()+ ": "
                + helper.getScore(1).getScore()));

        TextView Score2 = (TextView)findViewById(R.id.tScore2);
        Score2.setText(String.valueOf(helper.getScore(2).getUsername()+ ": "
                + helper.getScore(2).getScore()));

        TextView Score3 = (TextView)findViewById(R.id.tScore3);
        Score3.setText(String.valueOf(helper.getScore(3).getUsername()+ ": "
                + helper.getScore(3).getScore()));

        TextView Score4 = (TextView)findViewById(R.id.tScore4);
        Score4.setText(String.valueOf(helper.getScore(4).getUsername()+ ": "
                + helper.getScore(4).getScore()));

        Button back = (Button)findViewById(R.id.bback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Back = new Intent(Leaderboard.this, MainActivity.class);
                Back.putExtra("loggedin", name );
                startActivity(Back);
            }
        });




    }
}
