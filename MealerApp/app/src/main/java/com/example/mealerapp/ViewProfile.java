package com.example.mealerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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


    String print = "";
    //String please = "";
    //List<String> info;
    //String firstname = "k";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        //info = new ArrayList<>();

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();

        //FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        //String the_uid = user.getUid();
        String uid = UserDatabase.getUID();
        DatabaseReference additionalUserInfoRef = rootRef.child("USER").child(uid);

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String firstName = dataSnapshot.child("firstName").getValue(String.class);
                String email = dataSnapshot.child("email").getValue(String.class);

                printInfo(firstName);


                //String nameOfUser = dataSnapshot.child("name").getValue(String.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        };

        TextView myTextView = findViewById(R.id.profile);
        //myTextView.setText(firstName);




        //TextView myTextView = findViewById(R.id.profile);
        //myTextView.setText(address + email + firstName + lastName);
    }

    public void printInfo(String info){
        TextView myTextView = findViewById(R.id.profile);
        myTextView.setText("kyle");
    }

}