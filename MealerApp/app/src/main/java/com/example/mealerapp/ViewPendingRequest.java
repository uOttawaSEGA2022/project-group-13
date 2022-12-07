package com.example.mealerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.firebase.database.DatabaseReference;

public class ViewPendingRequest extends AppCompatActivity {

    private RequestDatabase database;

    private String meal, price, date, client;
    private PurchaseRequest request;
    private Button accept,decline,requests;
    private TextView mealTextView, priceTextView, clientTextView, dateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pending_request);

        Bundle bundle = getIntent().getExtras();
        meal = bundle.getString("meal");
        price = bundle.getString("price");
        date = bundle.getString("date");
        client = bundle.getString("client");

        mealTextView = (TextView) findViewById(R.id.mealTextView);
        priceTextView = (TextView) findViewById(R.id.priceTextView);
        clientTextView = (TextView) findViewById(R.id.clientTextView);
        dateTextView = (TextView) findViewById(R.id.dateTextView);

        mealTextView.setText("Meal: "+meal);
        priceTextView.setText("Price: " + price);
        dateTextView.setText("Date: "+date);
        clientTextView.setText("Client: "+client);

        accept = (Button)findViewById(R.id.acceptButton);
        decline = (Button)findViewById(R.id.declineButton);
        requests = (Button)findViewById(R.id.requestPageButton);

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                acceptRequest(v);


            }
        });

        decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                declineRequest(v);

            }
        });

        requests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnToRequestPage(v);
            }
        });


    }
    public void acceptRequest(View v){
        database.setAccepted(request);


    }

    public void declineRequest(View v){
        database.setRejected(request);


    }

    public void returnToRequestPage(View v){
        Intent returnToRequestPage = new Intent(getApplicationContext(),ViewPurchaseRequests.class);
        returnToRequestPage.putExtra("role","COOK");
        startActivity(returnToRequestPage);
    }

    }

