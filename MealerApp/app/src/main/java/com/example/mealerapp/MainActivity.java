package com.example.mealerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void loginpage(View view){
        Intent intent = new Intent(getApplicationContext(), WelcomePage.class);
    }

    public void cookpage(View view){
        Intent intent = new Intent(getApplicationContext(), CookRegistration.class);
        startActivity(intent);
    }

    public void clientpage(View view){
        Intent intent = new Intent(getApplicationContext(), ClientRegistration.class);
    }

}