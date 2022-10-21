package com.example.mealerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;

public class ClientRegistration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_registration);


        //Cook newCook = new Cook(firstname.getText().toString(), lastname.getText().toString(), email.getText().toString(), password.getText().toString(), description.getText().toString());


    }


    protected void createClient(View view) {
        setContentView(R.layout.activity_client_registration);
        EditText firstName= (EditText)  findViewById(R.id.firstName);
        EditText lastName= (EditText)  findViewById(R.id.lastName);
        EditText emailAddress= (EditText)  findViewById(R.id.emailAddress);
        EditText accountPassword= (EditText)  findViewById(R.id.accountPassword);
        EditText address= (EditText)  findViewById(R.id.address);
        EditText creditCardInfo= (EditText)  findViewById(R.id.creditCardInfo);

        Button Register= (Button) findViewById(R.id.Register);

        Register.setOnClickListener(new View.OnClickListener() {

            public void onClick(View A){

                String firstNameOne = firstName.getText().toString();
                String lastNameOne = lastName.getText().toString();
                String emailAddressOne = emailAddress.getText().toString();
                String accountPasswordOne= accountPassword.getText().toString();
                String addressOne = address.getText().toString();
                String creditCardInfoOne = creditCardInfo.getText().toString();
                Client newClient = new Client(firstNameOne,lastNameOne,emailAddressOne, accountPasswordOne,addressOne, creditCardInfoOne);
                newClient.registerClient();
                // Intent intent = new Intent( getApplicationContext(), Welcome.class);
              //  startActivity(intent);
                //add database
            }
        });





    }
}