package com.example.mealerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import android.widget.AdapterView;
import android.widget.TextView;

public class CreateComplaint extends AppCompatActivity {

    Spinner spinner;
    //Spinner spinner2;
    DatabaseReference databaseReference;
    List<String> cookNames;
    Object item;
    //List<String> clientNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_complaint);

        spinner = findViewById(R.id.spinner);
        cookNames = new ArrayList<>();

        //spinner2 = findViewById(R.id.spinner2);
        //clientNames = new ArrayList<>();


        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("USERS").addValueEventListener((new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                cookNames.add("Choose a cook");
                //clientNames.add("Choose your email");

                for(DataSnapshot childSnapshot:snapshot.getChildren()) {
                    String spinnerName = childSnapshot.child("email").getValue(String.class);

                    //String userType = snapshot.child("role").getValue(String.class);
                    /**
                    if (userType == "COOK") {
                        cookNames.add(spinnerName);
                    }
                     */

                    cookNames.add(spinnerName);
                    //clientNames.add(spinnerName);
                    //cookNames.add(userType);
                }

                //cookNames.add("Kyle");
                //cookNames.add("rob");

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(CreateComplaint.this, android.R.layout.simple_spinner_item, cookNames);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
                spinner.setAdapter(arrayAdapter);

                //ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<>(CreateComplaint.this, android.R.layout.simple_spinner_item, clientNames);
                //arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_item);
                //spinner2.setAdapter(arrayAdapter2);




            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        }));

        /**

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                item = parent.getItemAtPosition(pos);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
         */

        //TextView myTextView = (TextView) findViewById(R.id.Testing);
        //myTextView.setText(item.toString());
    }
}