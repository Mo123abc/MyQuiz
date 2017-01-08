package com.example.user.myquiz;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class quizOverView extends AppCompatActivity implements q1f.updateScore, q2f.updateScore2, q3f.updateScore3, q4f.updateScore4, q5f.updateScore5 {
TextView s;
    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_over_view);



        Button back = (Button)findViewById(R.id.bBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Back = new Intent(quizOverView.this, MainActivity.class);
                Back.putExtra("loggedin", getPlayer());
                startActivity(Back);
            }
        });

        Button one = (Button)findViewById(R.id.bOne);
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q1f frag = new q1f();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragmentcontainer, frag, null);
                ft.commit();
            }
        });


        Button two = (Button)findViewById(R.id.bTwo);
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q2f frag2 = new q2f();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragmentcontainer, frag2, null);
                ft.commit();
            }
        });

        Button three = (Button)findViewById(R.id.bThree);
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q3f frag3 = new q3f();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragmentcontainer, frag3, null);
                ft.commit();
            }
        });

        Button four = (Button)findViewById(R.id.bFour);
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q4f frag4 = new q4f();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragmentcontainer, frag4, null);
                ft.commit();
            }
        });
        Button five = (Button)findViewById(R.id.bFive);
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q5f frag5 = new q5f();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragmentcontainer, frag5, null);
                ft.commit();

            }
        });
        Button finish = (Button)findViewById(R.id.bfinish) ;
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent done = new Intent(quizOverView.this, finalScore.class);
                done.putExtra("score", String.valueOf(scoreActivity));
                done.putExtra("user", getPlayer());
                addToLeaderboard(getPlayer(), scoreActivity);
                startActivity(done);
            }
        });

         s = (TextView)findViewById(R.id.tscore);
    }
    int scoreActivity;


public String getPlayer(){

    String loggedin = getIntent().getStringExtra("loggedin");
    return loggedin;
}

    @Override
    public void scoreChanged(int score) {

        scoreActivity = (scoreActivity + score);
        String tally = "Score = " + scoreActivity;

        s.setText(tally);
    }

    public void scoreChanged2 (int score) {

        scoreActivity = (scoreActivity + score);
        String tally = "Score = " + scoreActivity;
        s.setText(tally);
    }
    public void scoreChanged3 (int score) {

        scoreActivity = (scoreActivity + score);
        String tally = "Score = " + scoreActivity;
        s.setText(tally);
    }

    public void scoreChanged4 (int score) {

        scoreActivity = (scoreActivity + score);
        String tally = "Score = " + scoreActivity;
        s.setText(tally);
    }
    public void scoreChanged5 (int score) {

        scoreActivity = (scoreActivity + score);
        String tally = "Score = " + scoreActivity;
        s.setText(tally);
    }
    public void addToLeaderboard(String name, int score){
        Player p = new Player();
        p.setUsername(name);
        p.setScore(score);
        helper.insertPlayerScore(p);
    }


}


