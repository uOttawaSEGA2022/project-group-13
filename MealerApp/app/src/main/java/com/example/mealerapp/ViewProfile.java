package com.example.mealerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewProfile extends AppCompatActivity {


    String email = "";
    String fn = "";
    String ln = "";
    String ad = "";
    String des = "";
    String menu = "";
    DatabaseReference databaseReference, dtb;
    Button welcomeButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        /**
        welcomeButton = (Button)findViewById(R.id.welcomePage);
        welcomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                welcome(v);
            }
        });
        */


        setContentView(R.layout.activity_view_profile);
        String uid = UserDatabase.getUID();

        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("USERS").child(uid).addValueEventListener((new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //DataSnapshot childSnapshot = snapshot.getChildren();

                email = snapshot.child("email").getValue(String.class);
                TextView myTextView = findViewById(R.id.email3);
                myTextView.setText(email);


                fn = snapshot.child("firstName").getValue(String.class);
                TextView fnview = findViewById(R.id.firstname3);
                fnview.setText(fn);

                ln = snapshot.child("lastName").getValue(String.class);
                TextView lnview = findViewById(R.id.lastname3);
                lnview.setText(ln);

                ad = snapshot.child("address").getValue(String.class);
                TextView adview = findViewById(R.id.address3);
                adview.setText(ad);

                des = snapshot.child("description").getValue(String.class);
                TextView desview = findViewById(R.id.des3);
                desview.setText(des);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        }));


        // Delete
        /**
        dtb = FirebaseDatabase.getInstance().getReference();
        dtb.child("USERS").child(uid).child("MENU").addValueEventListener((new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //DataSnapshot childSnapshot = snapshot.getChildren();

                for(DataSnapshot childSnapshot: snapshot.getChildren()){
                    menu = menu + childSnapshot.getValue(String.class);
                }
                TextView menuview = findViewById(R.id.menuitem);
                menuview.setText(menu);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        }));
         */








    }

    /**
    public void welcome(View view){
        Intent returnToWelcome = new Intent(getApplicationContext(),WelcomePage.class);
        returnToWelcome.putExtra("role","COOK");
        startActivity(returnToWelcome);
    }
     */


}