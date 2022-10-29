package com.example.mealerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CookRegistration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_registration);


        //Cook newCook = new Cook(firstname.getText().toString(), lastname.getText().toString(), email.getText().toString(), password.getText().toString(), description.getText().toString());


    }

    public void createAccount(View view){
        EditText firstname = (EditText) findViewById(R.id.firstname);
        EditText lastname = (EditText) findViewById(R.id.lastname);
        EditText email = (EditText) findViewById(R.id.email);
        EditText password = (EditText) findViewById(R.id.password);
        EditText address = (EditText) findViewById(R.id.address);
        EditText description = (EditText) findViewById(R.id.description);
        //EditText p = (EditText) findViewById(R.id.firstname);

        Button createaccount = (Button) findViewById(R.id.createaccount);

        createaccount.setOnClickListener(new View.OnClickListener(){
            public void onClick(View A){
                String firstnameone = firstname.getText().toString();
                String lastnameone = lastname.getText().toString();
                String emailone = email.getText().toString();
                String passwordone = password.getText().toString();
                String addressone = address.getText().toString();
                String descriptionone = description.getText().toString();
                Cook newCook = new Cook(firstnameone, lastnameone, emailone, passwordone, addressone, descriptionone);
                newCook.registerCook();
                //Database dbt = new Databse();
                //dtb.registerUser(newCook);

                // dont forget to change main act to page

                Intent intent = new Intent(getApplicationContext(), WelcomePage.class);

                //Intent intent = new Intent(CookRegistration.this, WelcomePage.class);
                intent.putExtra("role", "cook");

                startActivity(intent);
            }
        });
    }
}