package com.example.mealerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class WelcomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        Bundle bundle = getIntent().getExtras();
        String role = bundle.getString("role");


<<<<<<< HEAD
        //EditText userProfile = (EditText) findViewById(R.id.UserProfile);
        //userProfile.setText();
=======



        TextView userProfile = (TextView) findViewById(R.id.UserProfile);

        userProfile.setText(role);
>>>>>>> ec044942af4e6e5b51045f53f85343ac49388e3e
    }

    public void LogInPage(View view){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);


    }



}