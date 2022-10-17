package com.example.seg_client;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText First_name= (EditText)  findViewById(R.id.First_name);
        EditText Last_name= (EditText)  findViewById(R.id.Last_Name);
        EditText Email_address= (EditText)  findViewById(R.id.Email_address);
        EditText Account_password= (EditText)  findViewById(R.id.Account_Password);
        EditText Adress= (EditText)  findViewById(R.id.Adress);
        EditText Credit_Card_info= (EditText)  findViewById(R.id.Credit_Card_info);

        Button Register= (Button) findViewById(R.id.Register);

        Register.setOnClickListener(new View.OnClickListener() {

            public void onClick(View A){

                String First_Name_one = First_name.getText().toString();
                String Last_name_one = Last_name.getText().toString();
                String Email_address_one = Email_address.getText().toString();
                String Account_password_one= Account_password.getText().toString();
                String Adress_one = Adress.getText().toString();
                String Credit_Card_info_one = Credit_Card_info.getText().toString();

            }
        });





    }
}