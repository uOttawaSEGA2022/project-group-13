package com.example.mealerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class WelcomePage extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        Bundle bundle = getIntent().getExtras();
        String role = bundle.getString("role");


        TextView userProfile = (TextView) findViewById(R.id.UserProfile);
        userProfile.setText(role);


        Button logoff = (Button) findViewById(R.id.LogOff);
        logoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogInPage(v);
            }
        });

        Button viewcomplaints = (Button) findViewById(R.id.viewcomplaints);
        viewcomplaints.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                ComplaintsPage(v);
            }
        });

    }

    public void LogInPage(View view){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);


    }

    public void ComplaintsPage(View view){
        Intent intent = new Intent(getApplication(), ComplaintsPage.class);
        startActivity(intent);
    }


}