package com.example.mealerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class EditMealPage extends AppCompatActivity {

    private String name, mealType, description,cuisine,allergens,ingredients, UID;
    private boolean currentlyOffered;
    private Double price;

    private EditText nameEditText,mealTypeEditText,EditText,cuisineEditText,allergensEditText,ingredientsEditText,priceEditText, descriptionEditText;
    private Switch currentlyOfferedSwitch;
    private Button saveEditButton;
    private Button deleteMealButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_meal_page);

        Bundle bundle = getIntent().getExtras();
        name = bundle.getString("name");
        mealType = bundle.getString("mealType");
        description = bundle.getString("description");
        cuisine = bundle.getString("cuisine");
        allergens = bundle.getString("allergens");
        ingredients = bundle.getString("ingredients");
        currentlyOffered = Boolean.valueOf(bundle.getString("currentlyOffered"));
        price = Double.parseDouble(bundle.getString("price"));
        UID = bundle.getString("UID");

        nameEditText = (EditText)findViewById(R.id.nameEditText2);
        mealTypeEditText = (EditText)findViewById(R.id.mealTypeEditText2);
        descriptionEditText = (EditText)findViewById(R.id.descriptionEditText2);
        cuisineEditText = (EditText)findViewById(R.id.cuisineEditText2);
        allergensEditText = (EditText)findViewById(R.id.allergensEditText2);
        ingredientsEditText = (EditText)findViewById(R.id.ingredientsEditText2);
        priceEditText = (EditText)findViewById(R.id.priceNumberEdit2);
        currentlyOfferedSwitch = (Switch)findViewById(R.id.currentlyOfferedSwitch2);

        nameEditText.setText(name);
        mealTypeEditText.setText(mealType);
        descriptionEditText.setText(description);
        cuisineEditText.setText(cuisine);
        allergensEditText.setText(allergens);
        ingredientsEditText.setText(ingredients);
        priceEditText.setText(String.valueOf(price));
        currentlyOfferedSwitch.setChecked(currentlyOffered);
        saveEditButton = (Button)findViewById(R.id.saveEditsButton);
        saveEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editMeal(v);
                returnToMenuPage(v);
            }
        });
        /*
        deleteMealButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentlyOffered==false){
                    deleteMeal(v);
                    returnToMenuPage(v);



                }else{
                    Toast.makeText(getApplicationContext(), "cannot delete meal since it's currently offered", Toast.LENGTH_SHORT).show();

                }

            }
        });
        */

    }

    private void editMeal(View view){
        String mealName = nameEditText.getText().toString();
        String descriptionString = descriptionEditText.getText().toString();
        String allergensString = allergensEditText.getText().toString();
        String ingredientsString = ingredientsEditText.getText().toString();
        String cuisineString = cuisineEditText.getText().toString();
        String mealTypeString = mealTypeEditText.getText().toString();
        Double priceDouble = Double.parseDouble(priceEditText.getText().toString());
        currentlyOfferedSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Meal editedMeal = new Meal(mealName,mealTypeString,cuisineString,ingredientsString,allergensString
                ,descriptionString,priceDouble,isChecked);
                Menu menu = new Menu();
                menu.addMeal(UID,editedMeal);
            }
        });

    }
    private void returnToMenuPage(View view){
        Intent returnToMenu = new Intent(getApplicationContext(),MenuPage.class);
        returnToMenu.putExtra("UID",UID);
        startActivity(returnToMenu);
    }
}