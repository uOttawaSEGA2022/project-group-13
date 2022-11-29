package com.example.mealerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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
    //bject item;
    List<String> cookUIDs;
    //List<String> clientNames;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_complaint);

        spinner = findViewById(R.id.spinner);
        cookNames = new ArrayList<>();
        cookUIDs = new ArrayList<>();

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
                    //String cookUID = childSnapshot.getValue(String.class);

                    /**
                    if (userType == "COOK") {
                        cookNames.add(spinnerName);
                    }
                     */

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

    public void createComplaint(View view){

        Spinner mySpinner = (Spinner) findViewById(R.id.spinner);
        String text = mySpinner.getSelectedItem().toString();

        EditText complaint = (EditText) findViewById(R.id.Complaint);
        EditText clientEmail = (EditText) findViewById(R.id.clientEmail);

        Button create = (Button) findViewById(R.id.CreateComplaint);

        create.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String complaintText = complaint.getText().toString();
                String clientEmailOne = clientEmail.getText().toString();

                if(checkComplaint()){

                    /**

                    int count = 0;
                    int count2 = 0;

                    for(int i = 0; i < cookNames.size(); i++){
                        if(cookNames.equals(text)){
                            count = i;
                        }
                        if(cookNames.equals(clientEmailOne)){
                            count2 = i;
                        }

                    }
                    */

                    //String cookID = text.getUID().getText().toString();
                    //String clientID = cookUIDs.get(count2 -1);

                    ComplaintsDataBase dtb = new ComplaintsDataBase();

                    Complaints newComplaint = new Complaints(complaintText, getEmailUID(clientEmailOne) , getEmailUID(text));
                    dtb.addComplaint(getEmailUID(clientEmailOne), newComplaint);

                    Intent intent = new Intent(getApplicationContext(), WelcomePage.class);
                    //intent.putExtra("role", "client");
                    startActivity(intent);
                }



            }

        });
    }


    //String emailUID = "";
    private String getEmailUID(String emailText){

        String emailUID;
        /**
        database = FirebaseDatabase.getInstance().getReference();
        database.child("USERS").addValueEventListener((new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot childSnapshot:snapshot.getChildren()) {
                    String spinnerName = childSnapshot.child("email").getValue(String.class);
                    //String cookUID = childSnapshot.getValue(String.class);

                    if(spinnerName.equals(emailText)){
                        emailUID = childSnapshot.getValue(String.class);
                    }

                    //String userType = snapshot.child("role").getValue(String.class);
                    /**
                     if (userType == "COOK") {
                     cookNames.add(spinnerName);
                     }
                     */



               // }
           // }


            //@Override
            //public void onCancelled(@NonNull DatabaseError error) {

            //}
        //}));

        final String[] holder = new String[1];

        FirebaseDatabase.getInstance().getReference().child("USERS")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String emailUID = "";
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            String user = snapshot.child("email").getValue(String.class);
                            //System.out.println(user.email);

                            if(user.equals(emailText)){
                               emailUID = snapshot.getValue(String.class);
                               //return emailUID;
                            }


                        }

                        holder[0] = emailUID;

                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });




        return holder[0];

    }


    private boolean checkComplaint(){
        return true;
    }


}