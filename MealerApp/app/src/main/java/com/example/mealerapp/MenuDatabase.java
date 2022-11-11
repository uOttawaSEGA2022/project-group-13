package com.example.mealerapp;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MenuDatabase extends Database implements Database.retrieveListener{

    FirebaseDatabase database;
    DatabaseReference reference;

    public MenuDatabase(){
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("USERS");
    }

    public void addMeal(String cookUID, Meal meal){
        reference.child(cookUID).child("MENU").setValue(meal);
    }

    public void getMenu(String cookUID, retrieveListener listener){
        DatabaseReference ref = reference.child(cookUID).child("MENU");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Meal> meals = new ArrayList<>();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    meals.add((Meal) dataSnapshot.getValue());
                }
                listener.onDataReceived(meals);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void setMealOffered(String cookUID, Meal meal, Boolean currentlyOffered){
        reference.child(cookUID).child("MENU").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    if (dataSnapshot.getValue().toString().equals(meal)){
                        dataSnapshot.child("currentlyOffered").getRef().setValue(currentlyOffered);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onDataReceived(Object data) {

    }

    @Override
    public void onError() {

    }
}
