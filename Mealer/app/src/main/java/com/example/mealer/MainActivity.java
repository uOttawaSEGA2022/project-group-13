package com.example.mealer;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mealer.User.userType;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Button runButton = (Button) findViewById(R.id.runButton);
        runButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                run();
            }
        });

    }
    public void run(){

        Database dtb = new Database();
        User ella = new User("ella","smith","cats@gmail.com",
                "thisisthepassword1234", userType.CLIENT, "1234 1st Street");
        dtb.registerAuth(ella);
        dtb.registerInfo(ella);


    }
}