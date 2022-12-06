package com.example.mealerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.Date;

public class ViewMealInfo extends AppCompatActivity implements Serializable {

    private String cookUID, clientUID;
    private SearchableMeal meal;
    private TextView name,type,ingredients,allergens,description,price,cuisine, cookTextView;
    private Button requestMeal, cookInfo;
    boolean stop = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_meal_info);

        meal = (SearchableMeal) getIntent().getSerializableExtra("meal");

        Log.d("pastMeal", "here");
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

        name.setText("Meal Name: "+meal.getName());
        type.setText("Meal Type: "+meal.getMealType());
        ingredients.setText("Ingredients: "+meal.getIngredients());
        allergens.setText("Allergens: "+meal.getAllergens());
        description.setText("Description: "+meal.getDescription());
        price.setText("Price: $"+String.valueOf(meal.getPrice()));
        cuisine.setText("Cuisine: "+meal.getCuisine());
        cookTextView.setText("Cook email: "+meal.getCook());

    }

    public void requestMeal(View view){


        clientUID = UserDatabase.getUID();
        DatabaseReference cookRef = FirebaseDatabase.getInstance().getReference("USERS");
        cookRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    if(dataSnapshot.child("email").getValue().toString().equals(meal.getCook())){
                        cookUID = dataSnapshot.getKey();

                    }
                }
                if(!stop){
                    PurchaseRequest request = new PurchaseRequest(cookUID,clientUID,meal.getName());
                    RequestDatabase dtb = new RequestDatabase();
                    dtb.addRequest(request);
                    stop  = true;
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Intent returnToSearchMeals = new Intent(getApplicationContext(),SearchMeals.class);
        startActivity(returnToSearchMeals);

    }

    public void cookInfo(View view){
        Intent viewCookInfo = new Intent(getApplicationContext(),ViewCookInfoPage.class);
        viewCookInfo.putExtra("cook",meal.getCook());
        startActivity(viewCookInfo);
    }
}