package com.example.user.myquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    DatabaseHelper helper = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText username = (EditText) findViewById(R.id.etUsername);
        final EditText name = (EditText) findViewById(R.id.etName);
        final EditText password = (EditText) findViewById(R.id.etPassword);
        final EditText reenterPassword = (EditText) findViewById(R.id.etRenterPassword);

        final String Usernamestr = username.toString();
        final String Namestr = name.toString();
        final String Passwordstr = password.toString();
        String Renter = reenterPassword.toString();


        Button register = (Button)findViewById(R.id.bRegister);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().toString().length() !=0 || name.getText().toString().length() !=0 ||
                password.getText().toString().length() !=0 || reenterPassword.getText().toString().length() !=0)
                {
                if (password.getText().toString().equals(reenterPassword.getText().toString())){

                    //Code to add to database
                    Player p = new Player();
                    p.setUsername(username.getText().toString());
                    p.setName(name.getText().toString());
                    p.setPassword(password.getText().toString());
                    p.setScore(0);
                    helper.insertPlayer(p);
                    helper.insertPlayerScore(p);

                    Toast confirmDetails = Toast.makeText(getApplicationContext(), "Details Registered",
                            Toast.LENGTH_SHORT );
                    confirmDetails.show();
                    startActivity(new Intent(RegisterActivity.this, LoginScreen.class));
                }
                else {
                    Toast passwordMismatch = Toast.makeText(getApplicationContext(), "Passwords do not match",
                     Toast.LENGTH_SHORT);
                    passwordMismatch.show();
                }}
                else {
                    Toast fill = Toast.makeText(getApplicationContext(), "Please fill out all sections", Toast.LENGTH_SHORT);
                    fill.show();
                }
            }
        });
    }
}
