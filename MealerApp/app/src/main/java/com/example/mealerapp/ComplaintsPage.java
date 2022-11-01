package com.example.mealerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
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

public class ComplaintsPage extends AppCompatActivity implements MyAdapter.RecyclerViewInterface {

    private RecyclerView recyclerView;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference().child("COMPLAINTS"); //user is the name of the list
    private MyAdapter adapter;
    private ArrayList<Model> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaints_page);

        TextView noComplaints = (TextView) findViewById(R.id.noComplaintsTextView);
        noComplaints.setVisibility(View.GONE);

        Button welcomePage = (Button) findViewById(R.id.welcomePageButton);
        welcomePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnToWelcome = new Intent(getApplicationContext(), WelcomePage.class);
                startActivity(returnToWelcome);
            }
        });

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        list = new ArrayList<>();
        adapter = new MyAdapter(this, list,this);

        recyclerView.setAdapter(adapter);

        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Boolean isRead = (Boolean) dataSnapshot.child("read").getValue();

                    if(!isRead) {
                        Model model = dataSnapshot.getValue(Model.class);
                        list.add(model);
                    }
                }
                adapter.notifyDataSetChanged();
                if(list.isEmpty()){
                    noComplaints.setVisibility(View.VISIBLE);
                }

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }

    @Override
    public void onItemClick(int position, String cookUID, String clientUID, String complaint) {
        Intent handleComplaintPage = new Intent(getApplicationContext(), HandleComplaintPage.class);
        handleComplaintPage.putExtra("cookUID", cookUID);
        handleComplaintPage.putExtra("clientUID",clientUID);
        handleComplaintPage.putExtra("complaint",complaint);
        startActivity(handleComplaintPage);

    }
}