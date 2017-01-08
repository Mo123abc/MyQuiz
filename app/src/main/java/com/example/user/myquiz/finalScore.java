package com.example.user.myquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class finalScore extends AppCompatActivity {
    DatabaseHelper helper = new DatabaseHelper(this);
    @Override
    public void onBackPressed() {

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_score);

       final String name = getIntent().getStringExtra("user");


        String score = getIntent().getStringExtra("score");

        Button back = (Button)findViewById(R.id.bBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(finalScore.this, MainActivity.class);
                back.putExtra("loggedin", name);
                startActivity(back);
            }
        });

        TextView finalScore = (TextView)findViewById(R.id.tscored);
        finalScore.setText(score);

        int myNum=0;
        try {
            myNum = Integer.parseInt(score);
        } catch (NumberFormatException nfe){}

        String username = getIntent().getStringExtra("user");

    }
}
