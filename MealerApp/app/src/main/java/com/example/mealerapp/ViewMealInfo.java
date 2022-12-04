package com.example.mealerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;
import java.util.Date;

public class ViewMealInfo extends AppCompatActivity implements Serializable {

    private String cookUID, clientUID;
    private Meal meal;
    private TextView name,type,ingredients,allergens,description,price,cuisine, cookTextView;
    private Button requestMeal, cookInfo;
    private Cook cook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_meal_info);

        meal = (Meal)getIntent().getSerializableExtra("meal");
        cook = (Cook)getIntent().getSerializableExtra("cook");

        Bundle bundle = getIntent().getExtras();
        cookUID = bundle.getString("cookUID");
        clientUID = bundle.getString("clientUID");


        name = (TextView) findViewById(R.id.NameTextView);
        type = (TextView) findViewById(R.id.MealTypeTextView);
        ingredients = (TextView) findViewById(R.id.IngredientsTextView);
        allergens = (TextView) findViewById(R.id.AllergensTextView);
        description = (TextView) findViewById(R.id.DescriptionTextView);
        price = (TextView) findViewById(R.id.PriceTextView);
        cuisine = (TextView) findViewById(R.id.MealCuisineTextView);
        cookTextView = (TextView)findViewById(R.id.cookNameTextView);

        requestMeal = (Button)findViewById(R.id.requestMealButton);
        requestMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestMeal(v);
            }
        });
        cookInfo = (Button)findViewById(R.id.cookInfoButton);
        cookInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cookInfo(v);
            }
        });

        name.setText("Meal Name: "+cook.getFirstName() + " " + cook.getLastName());
        type.setText("Meal Type: "+meal.getMealType());
        ingredients.setText("Ingredients: "+meal.getIngredients());
        allergens.setText("Allergens: "+meal.getAllergens());
        price.setText("Price: $"+String.valueOf(meal.getPrice()));
        cuisine.setText("Cuisine: "+meal.getCuisine());
        cookTextView.setText("Cook Name: "+cook.getFirstName() + " " + cook.getLastName());



    }

    public void requestMeal(View view){
        PurchaseRequest request = new PurchaseRequest(cookUID,clientUID,meal.getName());
        RequestDatabase dtb = new RequestDatabase();
        dtb.addRequest(request);

        /*Intent returnToSearchMeals = new Intent(getApplicationContext(),SearchMealsPage.class);
        startActivity(returnToSearchMeals);*/
    }

    public void cookInfo(View view){
        Intent viewCookInfo = new Intent(getApplicationContext(),ViewCookInfoPage.class);
        viewCookInfo.putExtra("cook",cook);
        startActivity(viewCookInfo);
    }
}