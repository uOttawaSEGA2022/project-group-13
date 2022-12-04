package com.example.mealerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewPurchaseRequests extends AppCompatActivity implements PurchaseRequestAdapter.RecyclerViewInterface{

    private RecyclerView recyclerView;
    private TextView noRequests, roleTextView;
    private Button welcomeButton;
    private ArrayList<PurchaseRequest> list;
    private DatabaseReference ref;
    private String UID,role;
    private PurchaseRequestAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_purchase_requests);

        Bundle bundle = getIntent().getExtras();
        role = bundle.getString("role");

        UID = UserDatabase.getUID();

        ref = FirebaseDatabase.getInstance().getReference("USERS").child(UID).child("REQUESTS");

        list = new ArrayList<>();
        adapter = new PurchaseRequestAdapter(list,this,this, role);

        recyclerView = (RecyclerView) findViewById(R.id.purchaseRequestsRecycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        noRequests = (TextView) findViewById(R.id.noRequestsTextView);
        noRequests.setVisibility(View.GONE);


        welcomeButton = (Button)findViewById(R.id.welcomeButton);
        welcomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnToWelcome(v);
            }
        });

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String clientUID, cookUID, mealString, status, date;
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    String[] requestFields = new String[5];
                    int counter = 0;
                    for(DataSnapshot field : dataSnapshot.getChildren()){
                        requestFields[counter] = field.getValue().toString();
                        counter++;
                    }

                    clientUID = requestFields[0];
                    cookUID = requestFields[1];
                    date = requestFields[2];
                    mealString = requestFields[3];
                    status = requestFields[4];


                    PurchaseRequest purchaseRequest = new PurchaseRequest(cookUID,clientUID,mealString,PurchaseRequest.stringToStatus(status),date);
                    list.add(purchaseRequest);
                    adapter.notifyDataSetChanged();

                    if(list.isEmpty()){
                        noRequests.setVisibility(View.VISIBLE);
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    public void returnToWelcome(View view){
        Intent returnToWelcome = new Intent(getApplicationContext(),WelcomePage.class);
        returnToWelcome.putExtra("role",role);
        startActivity(returnToWelcome);
    }

    @Override
    public void onItemClick(int position, String meal,String price, String clientString, String dateString) {
       Intent viewPendingRequest = new Intent(getApplicationContext(),ViewPendingRequest.class);
        viewPendingRequest.putExtra("meal",meal);
        viewPendingRequest.putExtra("price", price);
        viewPendingRequest.putExtra("date", dateString);
        viewPendingRequest.putExtra("client",clientString);
        startActivity(viewPendingRequest);
    }
}