package com.example.user.myquiz;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String LoggedIn = getIntent().getStringExtra("loggedin");
        TextView LoggedInAs = (TextView) findViewById(R.id.tLoggedInAs);
        LoggedInAs.setText("Logged in as: " + LoggedIn);



        Button logout = (Button) findViewById(R.id.blogout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            AlertDialog.Builder confirm = new AlertDialog.Builder(MainActivity.this);
                confirm.setMessage("Are you sure you want to log out?").setCancelable(false)

                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Log out confirm
                        startActivity(new Intent(MainActivity.this, LoginScreen.class));
                        dialog.dismiss();
                    }
                })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                                dialog.dismiss();
                            }
                        });
                AlertDialog alert = confirm.create();
                alert.setTitle("Confirm");
                alert.show();
            }
        });

        Button start = (Button) findViewById(R.id.bStartQuiz);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewQuiz = new Intent(MainActivity.this, quizOverView.class);
                viewQuiz.putExtra("loggedin", LoggedIn);
                startActivity(viewQuiz);
            }
        });
        Button leader =(Button)findViewById(R.id.bLeaderboard);
        leader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent leaderboard = new Intent(MainActivity.this, Leaderboard.class);
                leaderboard.putExtra("loggedin", LoggedIn);
                startActivity(leaderboard);
            }
        });
        Button profile = (Button)findViewById(R.id.bprofile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profile = new Intent(MainActivity.this, Profile.class);
                profile.putExtra("loggedin", LoggedIn);
                startActivity(profile);
            }
        });
    }

    @Override
    public void onBackPressed() {
    }
}
