package com.example.segproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

                Database dbt = new Databse();
                dtb.registerUser(newCook);

                Intent intent = new Intent(getApplicationContext(), Welcome.class);
                startActivity(intent);
            }
        });

        //Cook newCook = new Cook(firstname.getText().toString(), lastname.getText().toString(), email.getText().toString(), password.getText().toString(), description.getText().toString());

    }


}