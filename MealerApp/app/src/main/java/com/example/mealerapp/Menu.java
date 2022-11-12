package com.example.mealerapp;

import com.google.firebase.database.DatabaseReference;

import java.util.LinkedList;
import java.util.List;

public class Menu {
    private MenuDatabase database;


    public Menu(){
        database = new MenuDatabase();
    }

    public void addMeal(String cookUID, Meal meal){
        database.addMeal(cookUID,meal);
    }

}
