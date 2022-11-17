package com.example.mealerapp;

import android.util.Log;

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
        reference.child(cookUID).child("MENU").child(meal.getName()).setValue(meal);
    }
    public void deleteMeal(String cookUID, String meal){
        reference.child(cookUID).child("MENU").child(meal).removeValue();
    }

    public void setCurrentlyOffered(String cookUID, Meal meal, boolean isOffered){
        reference.child(cookUID).child("MENU").child(meal.getName()).child("currentlyOffered").setValue(isOffered);
    }

    @Override
    public void onDataReceived(Object data) {

    }

    @Override
    public void onError() {

    }
}
