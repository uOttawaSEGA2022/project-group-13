package com.example.mealerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements Database.retrieveListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void loginpage(View view){
        EditText emailEditText = (EditText)findViewById(R.id.username2);
        EditText passwordEditText = (EditText)findViewById(R.id.password2);

        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        Database dtb = new Database();
        dtb.login(email,password);
        Intent intent = new Intent(getApplicationContext(), WelcomePage.class);

        Database.retrieveListener roleListener = new Database.retrieveListener() {
            @Override
            public void onDataReceived(String data) {
                intent.putExtra("role",data);
                startActivity(intent);

            }

            @Override
            public void onError() {
                intent.putExtra("role", "user");
                startActivity(intent);

            }
        };

        dtb.retrieveInfo(Database.dataField.ROLE,roleListener);

    }

    public void cookpage(View view){
        Intent intent = new Intent(getApplicationContext(), CookRegistration.class);
        startActivity(intent);
    }

    public void clientpage(View view){
        Intent intent = new Intent(getApplicationContext(), ClientRegistration.class);
        startActivity(intent);
    }

    @Override
    public void onDataReceived(String data) {

    }

    @Override
    public void onError() {

    }
}