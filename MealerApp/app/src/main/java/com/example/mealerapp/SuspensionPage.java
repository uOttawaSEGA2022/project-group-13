package com.example.mealerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class SuspensionPage extends AppCompatActivity implements View.OnClickListener {

    private String cookUID;
    private String date;

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suspension_page);

        Bundle bundle = getIntent().getExtras();
        cookUID = bundle.getString("cookUID");

        Boolean isValid = false;
        EditText suspensionDate = (EditText) findViewById(R.id.suspensionDateEditText);

        while(!isValid) {
            date = suspensionDate.getText().toString();

            isValid = verifyDate(date);
            if (!isValid) {
                Toast.makeText(getApplicationContext(), "Invalid date", Toast.LENGTH_SHORT).show();
            }
        }

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

    //This method verfies that the date input is valid but it is incomplete
    //Needs to check the the date makes sense (ex: cannot put febuary 31)
    //Need to check that the date is not in the past
    //Add functionality that checks the current date to make sure the date input is in the future
    private boolean verifyDate(String date){
       Boolean isValid = false;
       int month, day, year;

       if(date.length()<10){
           return isValid;
       }

       try {
           month = Integer.parseInt(date.substring(0, 2));
           day = Integer.parseInt(date.substring(4, 6));
           year = Integer.parseInt(date.substring(6));
       }
       catch(Exception e){
           return isValid;
       }

       if(month < 13 && month> 0){
           if(day < 32 && day > 0){
               if(year >2021){
                   isValid = true;
               }
           }
       }

       return isValid;

    }
}