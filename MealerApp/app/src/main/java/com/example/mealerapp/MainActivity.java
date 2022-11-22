package com.example.mealerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import java.util.Date;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class MainActivity extends AppCompatActivity implements Database.retrieveListener{
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    Date today = new Date();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button login = (Button) findViewById(R.id.login2);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginpage(v);
            }
        });

    }

    public void loginpage(View view){

        EditText emailEditText = (EditText)findViewById(R.id.username2);
        EditText passwordEditText = (EditText)findViewById(R.id.password2);


        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        UserDatabase dtb = new UserDatabase();
        dtb.login(email,password);
        Intent intent = new Intent(getApplicationContext(), WelcomePage.class);

        Database.retrieveListener roleListener = new Database.retrieveListener() {
            @Override
            public void onDataReceived(Object data) {
                String dataString = data.toString();
                if(dataString.equals("COOK")){
                    Database.retrieveListener suspendedListener = new Database.retrieveListener() {
                        @Override
                        public void onDataReceived(Object data) {
                            Boolean isSuspended = Boolean.valueOf(data.toString());
                            if(isSuspended){
                                Database.retrieveListener dateListener = new Database.retrieveListener() {
                                    @Override
                                    public void onDataReceived(Object data) {
                                        if(isDatePassed(data.toString())){
                                            dtb.liftSuspension();
                                            Intent intent = new Intent(getApplicationContext(), WelcomePage.class);
                                            intent.putExtra("role",dataString);
                                            startActivity(intent);
                                        }
                                        else {
                                            Intent intent = new Intent(getApplicationContext(), SuspendedCookPage.class);
                                            intent.putExtra("date", data.toString());
                                            startActivity(intent);
                                        }
                                    }

                                    @Override
                                    public void onError() {

                                    }
                                };
                                dtb.retrieveInfo(UserDatabase.dataField.SUSPENSIONDATE, dateListener);
                            }
                        }

                        @Override
                        public void onError() {

                        }
                    };
                    dtb.retrieveInfo(UserDatabase.dataField.ISSUSPENDED,suspendedListener);
                }
                Log.d("Here","Here");

                intent.putExtra("role", dataString);
                startActivity(intent);
            }

            @Override
            public void onError() {
                //Toast.makeText(getApplicationContext(), "Invalid Login Credentials", Toast.LENGTH_SHORT);

            }
        };


        dtb.retrieveInfo(UserDatabase.dataField.ROLE,roleListener);

    }

    /**
     * Helper function that compares dates
     * @param date
     * @return true is the parameter date is before or equal to today and false otherwise
     */
    private boolean isDatePassed(String date){

       Date suspension;

        try{
            suspension = formatter.parse(date);
            if(suspension.compareTo(today)<=0){
                return true;
            }
        }
        catch(Exception e){

        }
        return false;
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
    public void onDataReceived(Object data) {
    }

    @Override
    public void onError() {

    }
}