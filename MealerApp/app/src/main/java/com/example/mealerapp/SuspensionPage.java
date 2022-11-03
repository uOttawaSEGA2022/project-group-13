package com.example.mealerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import android.widget.Button;
import android.widget.EditText;


public class SuspensionPage extends AppCompatActivity implements View.OnClickListener {

    private String cookUID;
    private String date;

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suspension_page);

        Bundle bundle = getIntent().getExtras();
        cookUID = bundle.getString("cookUID");


        EditText suspensionDate = (EditText) findViewById(R.id.lengthOfSuspension);
        date = suspensionDate.getText().toString();

        Button permanent = findViewById(R.id.permanentButton);
        permanent.setOnClickListener(this);
        Button temporary = findViewById(R.id.temporaryButton);
        temporary .setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        UserDatabase dtb = new UserDatabase();
        Intent returnToComplaints = new Intent(getApplicationContext(),ComplaintsPage.class);
        switch (view.getId()){
            case R.id.permanentButton:
                dtb.suspendCook(cookUID,date);
                startActivity(returnToComplaints);
                break;
            case R.id.temporaryButton:
                dtb.suspendCook(cookUID,"Indefinite");
                startActivity(returnToComplaints);
                break;

        }

    }
}