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
    public void deleteMeal(String cookUID){
        reference.child(cookUID).child("MENU").child("MEAL").removeValue();
    }

    /*public void getMenu(String cookUID, retrieveListener listener){
        Log.d("HERE", "HERE");

        DatabaseReference ref = reference.child(cookUID).child("MENU");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<MealModel> meals = new ArrayList<>();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    MealModel model = dataSnapshot.getValue(MealModel.class);
                    meals.add(model);
                    Log.d("MEAL",model.toString());
                }
                listener.onDataReceived(meals);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        potential delete meal function;

        public void deleteMeal(String cookUID){
        reference.child(cookUID).child("MENU").child("MEAL").removeValue();
    }
    }*/


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
