package com.example.user.myquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.*;

public class LoginScreen extends AppCompatActivity {
    @Override
    public void onBackPressed() {
    }

    DatabaseHelper helper = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        final EditText username = (EditText) findViewById(R.id.etUsername);
        final EditText password = (EditText) findViewById(R.id.etPassword);
        final String User = username.getText().toString();
        final String pass = password.getText().toString();

        Button login = (Button) findViewById(R.id.bLogin);
        login.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Password = helper.searchPass(username.getText().toString());
                if(Password.equals(password.getText().toString())) {
                    Intent loginIntent = new Intent(LoginScreen.this, MainActivity.class);
                    loginIntent.putExtra("loggedin", username.getText().toString());

                    startActivity(loginIntent);
                }
                else {
                    Toast check = Toast.makeText(getApplicationContext(), "Username or password is incorrect. Please re-enter your details",
                            Toast.LENGTH_SHORT);
                    check.show();
                }
            }


        }));
        Button register = (Button) findViewById(R.id.bRegister);
        register.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(LoginScreen.this, RegisterActivity.class);
                startActivity(loginIntent);


            }


        }));
    }
}