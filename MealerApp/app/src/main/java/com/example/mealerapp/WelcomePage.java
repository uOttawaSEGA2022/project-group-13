package com.example.mealerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class WelcomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        Bundle bundle = getIntent().getExtras();
        String role = bundle.getString("role");


        //EditText userProfile = (EditText) findViewById(R.id.UserProfile);
        //userProfile.setText();
//=======



//>>>>>>> f763cf6666e5d8b36ce2fba4d1b804b5a1ce62bb
        TextView userProfile = (TextView) findViewById(R.id.UserProfile);
        userProfile.setText(role);
//<<<<<<< HEAD

        Button logoff = (Button) findViewById(R.id.LogOff);
        logoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogInPage(v);
            }
        });

    }

    public void LogInPage(View view){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);


    }



}