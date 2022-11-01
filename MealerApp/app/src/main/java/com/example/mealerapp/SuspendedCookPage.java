package com.example.mealerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SuspendedCookPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suspended_cook_page);

        Bundle bundle = getIntent().getExtras();
        String suspensionDate = bundle.getString("date");

        TextView suspensionText = (TextView) findViewById(R.id.suspendedTextView);
        suspensionText.setText("You are suspended until "+suspensionDate);

        Button logoff = (Button) findViewById(R.id.logoffButton);

        logoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}