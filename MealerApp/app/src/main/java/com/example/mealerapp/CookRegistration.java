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

                if(checkFields()) {
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
            }
        });



    }

    private boolean checkFields(){
        boolean status = true;

        EditText firstname = (EditText) findViewById(R.id.firstname);
        EditText lastname = (EditText) findViewById(R.id.lastname);
        EditText email = (EditText) findViewById(R.id.email);
        EditText password = (EditText) findViewById(R.id.password);
        EditText address = (EditText) findViewById(R.id.address);
        EditText description = (EditText) findViewById(R.id.description);

        if(firstname.length() == 0){
            firstname.setError("This field is required");
            status = false;
        }
        if(lastname.length() == 0){
            lastname.setError("This field is required");
            status = false;
        }
        if(email.length() == 0 || !email.getText().toString().contains("@")){
            email.setError("This field is required and required a valid email with an @ symbol");
            status = false;
        }
        if(password.length() == 0){
            password.setError("This field is required");
            status = false;
        }
        if(address.length() == 0){
            address.setError("This field is required");
            status = false;
        }
        if(description.length() == 0){
            description.setError("This field is required");
            status = false;
        }


        return status;
    }


}