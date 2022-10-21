package com.example.mealerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class WelcomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);



        EditText userProfile = (EditText) findViewById(R.id.UserProfile);
        userProfile.setText();
    }

    public void LogInPage(View view){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);


    }



}