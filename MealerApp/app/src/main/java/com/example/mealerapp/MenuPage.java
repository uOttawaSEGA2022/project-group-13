package com.example.mealerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MenuPage extends AppCompatActivity implements MenuAdapter.RecyclerViewInterface{

    private String UID;
    private RecyclerView recyclerView;

    private MenuAdapter adapter;
    private ArrayList<Meal> list;
    private DatabaseReference ref, ratingRef;

    private Button welcomeButton, addMealButton;
    private TextView emptyMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_page);

        Bundle bundle = getIntent().getExtras();
        UID = bundle.getString("UID");
        Log.d("UID MENU",UID);

        ref = FirebaseDatabase.getInstance().getReference("USERS").child(UID).child("MENU");
        ratingRef = FirebaseDatabase.getInstance().getReference("USERS").child(UID).child("RATING");

        addMealButton = (Button)findViewById(R.id.addMealButton);
        addMealButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMeal(v);
            }
        });

        emptyMenu = (TextView) findViewById(R.id.emptyMenuTextView);
        emptyMenu.setVisibility(View.GONE);

        welcomeButton = (Button)findViewById(R.id.returnWelcomeButton);
        welcomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnToWelcome(v);
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.menuRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        adapter = new MenuAdapter(this, list,this);

        recyclerView.setAdapter(adapter);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    String[] mealFields = new String[8];
                    int counter = 0;
                    for(DataSnapshot fields :dataSnapshot.getChildren()){
                        if (counter==8){break;}
                        mealFields[counter] = fields.getValue().toString();
                        counter++;
                    }
                    String allergens = mealFields[0];
                    String cuisine = mealFields[1];
                    boolean currentlyOffered = Boolean.parseBoolean(mealFields[2]);
                    String description = mealFields[3];
                    String ingredients = mealFields[4];
                    String mealType = mealFields[5];
                    String name = mealFields[6];
                    double price = Double.parseDouble(mealFields[7]);

                    ratingRef.child(name).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            double total = 0;
                            int numRatings = 0;
                            for(DataSnapshot rating : snapshot.getChildren()){
                                Log.d("rating", rating.getValue().toString());
                                total += Double.valueOf(rating.getValue().toString());
                                numRatings++;
                            }

                            double rating;
                            if(numRatings == 0){
                                rating = -1;
                            }
                            else{
                                rating = Math.round(total/numRatings);
                            }

                            Meal meal = new Meal(name,mealType,cuisine,ingredients,allergens,description,price, currentlyOffered, rating);
                            list.add(meal);
                            adapter.notifyDataSetChanged();

                            if(list.isEmpty()){
                                emptyMenu.setVisibility(View.VISIBLE);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    @Override
    public void onItemClick(int position, String meal, String description, String allergens,
                            String ingredients, String cuisine, String mealType, boolean currentlyOffered, Double price, double rating) {
        Intent editMealPage = new Intent(getApplicationContext(),EditMealPage.class);
        editMealPage.putExtra("name",meal);
        editMealPage.putExtra("description",description);
        editMealPage.putExtra("allergens",allergens);
        editMealPage.putExtra("ingredients",ingredients);
        editMealPage.putExtra("cuisine",cuisine);
        editMealPage.putExtra("mealType",mealType);
        editMealPage.putExtra("currentlyOffered",String.valueOf(currentlyOffered));
        editMealPage.putExtra("price",String.valueOf(price));
        editMealPage.putExtra("UID",UID);
        editMealPage.putExtra("rating",String.valueOf(rating));
        startActivity(editMealPage);

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    private void returnToWelcome(View view){
        Intent returnToWelcome = new Intent(getApplicationContext(),WelcomePage.class);
        returnToWelcome.putExtra("role","COOK");
        startActivity(returnToWelcome);
    }

    private void addMeal(View view){
        Intent addMealPage = new Intent(getApplicationContext(),AddMealPage.class);
        addMealPage.putExtra("UID",UID);
        startActivity(addMealPage);
    }
}