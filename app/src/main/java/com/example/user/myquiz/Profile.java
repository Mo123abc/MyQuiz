package com.example.user.myquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Profile extends AppCompatActivity {
DatabaseHelper helper = new DatabaseHelper(this);
    String scores[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        final String name = getIntent().getStringExtra("loggedin");
        scores = helper.recentScore(name);

        Button back = (Button)findViewById(R.id.bbackp);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Back = new Intent(Profile.this, MainActivity.class);
                Back.putExtra("loggedin", name );
                startActivity(Back);
            }
        });
        TextView title = (TextView)findViewById(R.id.tttt);
        title.setText("Top scores for player: " + name);

        TextView first = (TextView)findViewById(R.id.tfirst);
        TextView second = (TextView)findViewById(R.id.tsecond);
        TextView third = (TextView)findViewById(R.id.tthird);

        first.setText("Best:  " + String.valueOf(scores[0]));
        second.setText( "2nd:  " + String.valueOf(scores[1]));
        third.setText("3rd: " + String.valueOf(scores[2]));
    }
}
