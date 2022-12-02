package com.example.mealerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
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
    private TextView noRequests;
    private Button welcomeButton;
    private ArrayList<PurchaseRequest> list;
    private DatabaseReference ref;
    private String UID;
    private PurchaseRequestAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_purchase_requests);

        Bundle bundle = getIntent().getExtras();
        UID = bundle.getString("UID");

        ref = FirebaseDatabase.getInstance().getReference("USERS").child(UID).child("REQUESTS");

        list = new ArrayList<>();
        adapter = new PurchaseRequestAdapter(list,this,this);

        recyclerView = (RecyclerView) findViewById(R.id.purchaseRequestsRecycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        noRequests = (TextView) findViewById(R.id.noRequestsTextView);
        noRequests.setVisibility(View.GONE);

        welcomeButton = (Button)findViewById(R.id.welcomeButton);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    String[] requestFields = new String[5];
                    int counter = 0;
                    for(DataSnapshot field : dataSnapshot.getChildren()){
                        requestFields[counter] = field.getValue().toString();
                        counter++;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void returnToWelcome(View view){
        Intent returnToWelcome = new Intent(getApplicationContext(),WelcomePage.class);
        returnToWelcome.putExtra("role","COOK");
        startActivity(returnToWelcome);
    }

    @Override
    public void onItemClick(int position) {

    }
}