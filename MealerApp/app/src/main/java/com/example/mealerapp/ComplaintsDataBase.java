package com.example.mealerapp;
import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ComplaintsDataBase extends Database implements Database.retrieveListener{

    FirebaseDatabase database;
    DatabaseReference complaintsRef;

    public ComplaintsDataBase(){
        database = FirebaseDatabase.getInstance();
        complaintsRef = database.getReference("USERS").child("rQlNmFRGIBMhC0MhJnWdUVTgKM03").child("COMPLAINTS");
    }

    private void getComplaints (final retrieveListener listener){
        complaintsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Object> complaints = new ArrayList<Object>();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    complaints.add(dataSnapshot.getValue());
                }
                listener.onDataReceived(String.valueOf(complaints));
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    public void addComplaint(String cookUID, Complaints complaint){
        setInformation(complaintsRef.child(cookUID), complaint);
    }
    public void deleteComplaint(String cookUID){
        deleteInformation(complaintsRef.child(cookUID));
    }


    @Override
    public void onDataReceived(Object data) {
    }

    @Override
    public void onError() {
    }
}