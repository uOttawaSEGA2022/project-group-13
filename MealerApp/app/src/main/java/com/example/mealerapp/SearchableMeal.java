package com.example.mealerapp;

public class SearchableMeal extends Meal{
    private String cook;

    public SearchableMeal(String name, String mealType, String cuisine, String ingredients, String allergens, String description, double price, boolean currentlyOffered,String cook) {
        super(name, mealType, cuisine, ingredients, allergens, description, price, currentlyOffered);
        this.cook = cook;
    }

    public SearchableMeal(String name, String mealType, String cuisine, String ingredients, String allergens, String description, double price, boolean currentlyOffered, double rating,String cook) {
        super(name, mealType, cuisine, ingredients, allergens, description, price, currentlyOffered, rating);
        this.cook = cook;
    }

    public String getCook() {
        return cook;
    }

    public void setCook(String cook) {
        this.cook = cook;
    }
}
