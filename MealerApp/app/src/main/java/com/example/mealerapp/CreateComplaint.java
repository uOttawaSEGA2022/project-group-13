package com.example.mealerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CreateComplaint extends AppCompatActivity {

    Spinner spinner;
    DatabaseReference databaseReference;
    List<String> cookNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_complaint);

        spinner = findViewById(R.id.spinner);
        cookNames = new ArrayList<>();


        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("USERS").addValueEventListener((new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String spinnerName = snapshot.child("email").getValue(String.class);
                String userType = snapshot.child("role").getValue(String.class);
                if(userType == "COOK"){
                    cookNames.add(spinnerName);
                }

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(CreateComplaint.this, android.R.layout.simple_spinner_item, cookNames);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
                spinner.setAdapter(arrayAdapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        }));
    }
}