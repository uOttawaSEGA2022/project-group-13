package com.example.mealerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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

        ClientDatabase dtb = new ClientDatabase();
        dtb.login(email,password);
        Intent intent = new Intent(getApplicationContext(), WelcomePage.class);

        Database.retrieveListener roleListener = new Database.retrieveListener() {
            @Override
            public void onDataReceived(Object data) {
               String dataString = data.toString();
                if(dataString.equals("COOK")){
                    Log.d("HERE","HERE");
                    Database.retrieveListener suspendedListener = new Database.retrieveListener() {
                        @Override
                        public void onDataReceived(Object data) {
                            Boolean isSuspended = Boolean.valueOf(data.toString());
                            if(isSuspended){
                                Log.d("HIT","HIT");
                                Database.retrieveListener dateListener = new Database.retrieveListener() {
                                    @Override
                                    public void onDataReceived(Object data) {
                                        Intent intent = new Intent(getApplicationContext(), SuspendedCookPage.class);
                                        intent.putExtra("date",data.toString());
                                        startActivity(intent);
                                    }

                                    @Override
                                    public void onError() {

                                    }
                                };
                                dtb.retrieveInfo(ClientDatabase.dataField.SUSPENSIONDATE, dateListener);
                            }
                        }

                        @Override
                        public void onError() {

                        }
                    };
                    dtb.retrieveInfo(ClientDatabase.dataField.ISSUSPENDED,suspendedListener);
                }

                intent.putExtra("role", dataString);
                startActivity(intent);
            }

            @Override
            public void onError() {
                //Toast.makeText(getApplicationContext(), "Invalid Login Credentials", Toast.LENGTH_SHORT);

            }
        };

        dtb.retrieveInfo(ClientDatabase.dataField.ROLE,roleListener);

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