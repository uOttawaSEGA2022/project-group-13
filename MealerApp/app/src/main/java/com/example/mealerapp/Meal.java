package com.example.mealerapp;

import java.io.Serializable;

public class Meal implements Serializable {

    private String name, mealType, cuisine, ingredients, allergens, description;
    private double price;
    private Boolean currentlyOffered;
    private double rating;

    public Meal(String name, String mealType, String cuisine,
                     String ingredients, String allergens, String description, double price, boolean currentlyOffered){

        this.name = name;
        this.mealType = mealType;
        this.cuisine = cuisine;
        this.ingredients = ingredients;
        this.allergens = allergens;
        this.description = description;
        this.price = price;
        this.currentlyOffered = currentlyOffered;
        this.rating = -1;

    }
    public Meal(String name, String mealType, String cuisine,
                String ingredients, String allergens, String description, double price, boolean currentlyOffered, double rating){

        this.name = name;
        this.mealType = mealType;
        this.cuisine = cuisine;
        this.ingredients = ingredients;
        this.allergens = allergens;
        this.description = description;
        this.price = price;
        this.currentlyOffered = currentlyOffered;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getAllergens() {
        return allergens;
    }

    public void setAllergens(String allergens) {
        this.allergens = allergens;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Boolean getCurrentlyOffered() {
        return currentlyOffered;
    }

    public void setCurrentlyOffered(Boolean currentlyOffered) {
        this.currentlyOffered = currentlyOffered;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
