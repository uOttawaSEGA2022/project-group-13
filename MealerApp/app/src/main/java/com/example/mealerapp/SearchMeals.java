package com.example.mealerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SearchView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchMeals extends AppCompatActivity implements MenuAdapter.RecyclerViewInterface{

    private MenuAdapter adapter;
    private ArrayList<Meal> list, searchResults;
    private RecyclerView recyclerView;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_meals);

        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("USERS");

        list = new ArrayList<>();
        searchResults = new ArrayList<>();
        adapter = new MenuAdapter(this, searchResults,this);
        recyclerView = (RecyclerView) findViewById(R.id.searchMealsRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot userSnapshot: snapshot.getChildren()){
                    String role = userSnapshot.child("role").getValue().toString();
                    if(role.equals("COOK")){
                        boolean isSuspended = Boolean.valueOf(userSnapshot.child("suspended").getValue().toString());
                        if(!isSuspended){
                            DatabaseReference menuRef = userSnapshot.child("MENU").getRef();
                            menuRef.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    for(DataSnapshot mealSnapshot : snapshot.getChildren()){
                                        String[] mealFields = new String[8];
                                        int counter = 0;
                                        for(DataSnapshot mealInfoSnapshot: mealSnapshot.getChildren()){
                                            if (counter==8){break;}
                                            mealFields[counter] = mealInfoSnapshot.getValue().toString();
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

                                        DatabaseReference ratingRef = userSnapshot.getRef().child("RATING");

                                        ratingRef.child(name).addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                double total = 0;
                                                int numRatings = 0;
                                                for(DataSnapshot rating : snapshot.getChildren()){
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


                                                if(list.isEmpty()){
                                                    //Set a textview to visible that says no search results
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
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        searchView = (SearchView)findViewById(R.id.mealSearchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(!searchResults.isEmpty()){
                    for(Meal searchResult: searchResults){
                        searchResults.remove(searchResult);
                    }
                }
                for(Meal mealItem:list){
                    if(mealItem.getName().contains(query)){
                        searchResults.add(mealItem);
                        adapter.notifyDataSetChanged();
                    }
                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    public void onItemClick(int position, String meal, String description, String allergens, String ingredients, String cuisine, String mealType, boolean currentlyOffered, Double price, double rating) {

    }
}