package com.example.mealerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class HandleComplaintPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handle_complaint_page);

        Bundle bundle = getIntent().getExtras();
        String cookUID = bundle.getString("cookUID");
        String clientUID = bundle.getString("clientUID");
        String complaint = bundle.getString("complaint");

        TextView cookTextView = (TextView) findViewById(R.id.cookUIDTextView);
        TextView clientTextView = (TextView) findViewById(R.id.clientUIDTextView);
        TextView complaintTextView = (TextView) findViewById(R.id.complaintTextView);

        cookTextView.setText("Cook: "+cookUID);
        clientTextView.setText("Client: "+clientUID);
        complaintTextView.setText("Complaint: "+complaint);

        Button dismissButton = (Button) findViewById(R.id.dismissButton);
        Button suspendButton = (Button) findViewById(R.id.suspendButton);

        dismissButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ComplaintsDataBase dtb = new ComplaintsDataBase();
                dtb.setRead(cookUID);
                Toast.makeText(getApplicationContext(), "Complaint Dismissed", Toast.LENGTH_LONG).show();
                Intent returnToComplaints = new Intent(getApplicationContext(),SuspensionPage.class);
                startActivity(returnToComplaints);
            }
        });

        suspendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent suspendCook = new Intent(getApplicationContext(),SuspensionPage.class);
                suspendCook.putExtra("cookUID",cookUID);
                startActivity(suspendCook);
            }
        });

    }
}