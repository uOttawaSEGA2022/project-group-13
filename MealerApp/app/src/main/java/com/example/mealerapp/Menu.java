package com.example.mealerapp;

import java.util.LinkedList;
import java.util.List;

public class Menu {
    private List<Meal> menuList;

    public Menu (){
        menuList = new LinkedList<Meal>();
    }

    public void addMeal(Meal meal){
        menuList.add(meal);
    }

    public void removeMealFromOffered(Meal meal){
        menuList.remove(meal);
    }

}
