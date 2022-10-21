package com.example.mealerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

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
        startActivity(intent);
    }

    public void cookpage(View view){
        Intent intent = new Intent(getApplicationContext(), CookRegistration.class);
        startActivity(intent);
    }

    public void clientpage(View view){
        Intent intent = new Intent(getApplicationContext(), ClientRegistration.class);
        startActivity(intent);
    }

}