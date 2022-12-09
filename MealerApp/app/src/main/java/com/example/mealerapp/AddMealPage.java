package com.example.mealerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

public class AddMealPage extends AppCompatActivity {

    private EditText name,description,ingredients,allergerns, cuisine, mealType, price;
    private Switch currentlyOffered;
    private Button addButton;

    private String mealName,descriptionString,ingredientsString,allergensString, cuisineString, mealTypeString, UID;
    private boolean isOffered;
    private double priceDouble;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meal_page);

        Bundle bundle = getIntent().getExtras();
        UID = bundle.getString("UID");


        name = (EditText) findViewById(R.id.mealNameEditText);
        description = (EditText) findViewById(R.id.descriptionEditText);
        ingredients = (EditText) findViewById(R.id.ingredientsEditText);
        allergerns = (EditText) findViewById(R.id.allergensEditText);
        cuisine = (EditText)findViewById(R.id.cuisineEditText);
        mealType = (EditText)findViewById(R.id.mealNameEditText);
        price = (EditText)findViewById(R.id.priceNumberEdit);
        addButton = (Button)findViewById(R.id.addButton);
        currentlyOffered = (Switch)findViewById(R.id.currentlyOfferedSwitch);
        currentlyOffered.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isOffered = isChecked;
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mealName = name.getText().toString();
                descriptionString = description.getText().toString();
                allergensString = allergerns.getText().toString();
                ingredientsString = ingredients.getText().toString();
                cuisineString = cuisine.getText().toString();
                mealTypeString = mealType.getText().toString();
                priceDouble = Double.parseDouble(price.getText().toString());

                if(checkFields()) {
                    mealName = name.getText().toString();
                    descriptionString = description.getText().toString();
                    allergensString = allergerns.getText().toString();
                    ingredientsString = ingredients.getText().toString();
                    cuisineString = cuisine.getText().toString();
                    mealTypeString = mealType.getText().toString();
                    priceDouble = Double.parseDouble(price.getText().toString());

                    addNewMeal(v);
                    returnToMenu(v);
                }
            }
        });
    }

    public void addNewMeal(View view){
        Meal newMeal = new Meal(mealName,mealTypeString,cuisineString,ingredientsString,allergensString,descriptionString,priceDouble,isOffered);
        Menu menu = new Menu();
        menu.addMeal(UID,newMeal);
    }

    public void returnToMenu(View view){
        Intent returnToMenu = new Intent(getApplicationContext(),MenuPage.class);
        returnToMenu.putExtra("UID",UID);
        startActivity(returnToMenu);
    }

    private boolean checkFields(){
        boolean status = true;

        name = (EditText) findViewById(R.id.mealNameEditText);
        description = (EditText) findViewById(R.id.descriptionEditText);
        ingredients = (EditText) findViewById(R.id.ingredientsEditText);
        allergerns = (EditText) findViewById(R.id.allergensEditText);
        cuisine = (EditText)findViewById(R.id.cuisineEditText);
        mealType = (EditText)findViewById(R.id.mealNameEditText);
        price = (EditText)findViewById(R.id.priceNumberEdit);

        if(name.length() == 0){
            name.setError("This field is required");
            status = false;
        }
        if(description.length() == 0){
            description.setError("This field is required");
            status = false;
        }
        if(ingredients.length() == 0){
            ingredients.setError("This field is required");
            status = false;
        }
        if(allergerns.length() == 0){
            allergerns.setError("This field is required");
            status = false;
        }
        if(cuisine.length() == 0){
            cuisine.setError("This field is required");
            status = false;
        }
        if(mealType.length() == 0){
            mealType.setError("This field is required");
            status = false;
        }
        if(price.length() == 0){
            price.setError("This field is required");
            status = false;
        }


        return status;
    }
}