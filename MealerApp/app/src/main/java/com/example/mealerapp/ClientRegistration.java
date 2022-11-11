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


    public void createClient(View view) {
        //setContentView(R.layout.activity_client_registration);
        EditText firstName= (EditText)  findViewById(R.id.firstName);
        EditText lastName= (EditText)  findViewById(R.id.lastName);
        EditText emailAddress= (EditText)  findViewById(R.id.emailAddress);
        EditText accountPassword= (EditText)  findViewById(R.id.accountPassword);
        EditText address= (EditText)  findViewById(R.id.address);
        EditText creditCardInfo= (EditText)  findViewById(R.id.creditCardInfo);

        Button Register= (Button) findViewById(R.id.Register);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstNameOne = firstName.getText().toString();
                String lastNameOne = lastName.getText().toString();
                String emailAddressOne = emailAddress.getText().toString();
                String accountPasswordOne= accountPassword.getText().toString();
                String addressOne = address.getText().toString();
                String creditCardInfoOne = creditCardInfo.getText().toString();

                if(checkFields()) {
                    Client newClient = new Client(firstNameOne, lastNameOne, emailAddressOne, accountPasswordOne, addressOne, creditCardInfoOne);
                    newClient.registerClient();

                    Intent intent = new Intent(getApplicationContext(), WelcomePage.class);
                    intent.putExtra("role", "client");
                    startActivity(intent);
                }
            }
        });





    }

    private boolean checkFields(){
        boolean status = true;

        EditText firstname = (EditText) findViewById(R.id.firstName);
        EditText lastname = (EditText) findViewById(R.id.lastName);
        EditText email = (EditText) findViewById(R.id.emailAddress);
        EditText password = (EditText) findViewById(R.id.accountPassword);
        EditText address = (EditText) findViewById(R.id.address);
        EditText description = (EditText) findViewById(R.id.creditCardInfo);

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