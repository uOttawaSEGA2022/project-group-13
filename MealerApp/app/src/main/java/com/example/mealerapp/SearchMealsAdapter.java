package com.example.mealerapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchMealsAdapter extends RecyclerView.Adapter<SearchMealsAdapter.SearchMealViewHolder>{
    ArrayList<SearchableMeal> list;
    Context context;
    SearchMealsAdapter.RecyclerViewInterface recyclerViewInterface;

    public SearchMealsAdapter (Context context, ArrayList<SearchableMeal> list, SearchMealsAdapter.RecyclerViewInterface recyclerViewInterface){
        this.list = list;
        this.context = context;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public SearchMealsAdapter.SearchMealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.search_meal_item, parent, false);
        return new SearchMealsAdapter.SearchMealViewHolder(v,recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchMealsAdapter.SearchMealViewHolder holder, int position) {
        SearchableMeal meal = list.get(position);
        holder.meal.setText(meal.getName());
        holder.ingredients.setText(meal.getIngredients());
        holder.allergens.setText(meal.getAllergens());
        holder.description.setText(meal.getDescription());
        holder.mealType.setText(meal.getMealType());
        holder.cuisine.setText(meal.getCuisine());
        holder.price.setText("$"+meal.getPrice());
        String currentlyOfferedString = "No";
        if(meal.getCurrentlyOffered()){
            currentlyOfferedString = "Yes";
        }

        holder.currentlyOffered.setText(currentlyOfferedString);

        String ratingString = String.valueOf(meal.getRating());
        if(ratingString.equals("-1.0")){
            ratingString = "No ratings";
        }
        holder.rating.setText(ratingString);

       holder.cook.setText(meal.getCook());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class SearchMealViewHolder extends RecyclerView.ViewHolder{

        TextView meal, ingredients, allergens, description, cuisine, currentlyOffered, mealType, price, rating,cook;

        public SearchMealViewHolder(@NonNull View itemView, SearchMealsAdapter.RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            meal = itemView.findViewById(R.id.mealNameTextView);
            ingredients = itemView.findViewById(R.id.ingredientsEditTextView);
            allergens = itemView.findViewById(R.id.allergensEditTextView);
            description = itemView.findViewById(R.id.descriptionEditTextView);
            cuisine = itemView.findViewById(R.id.cuisineEditTextView);
            currentlyOffered = itemView.findViewById(R.id.currentlyOfferedEditTextView);
            mealType = itemView.findViewById(R.id.mealTypeEditTextView);
            price = itemView.findViewById(R.id.priceEditTextView);
            rating = itemView.findViewById(R.id.ratingEditTextView);
            cook = itemView.findViewById(R.id.cookNameEditTextView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(recyclerViewInterface!=null) {
                        int position = getAdapterPosition();
                        String mealString = meal.getText().toString();
                        String descriptionString = description.getText().toString();
                        String allergensString = allergens.getText().toString();
                        String ingredientsString = ingredients.getText().toString();
                        String mealTypeString = mealType.getText().toString();
                        String cuisineString = cuisine.getText().toString();
                        String cookString = cook.getText().toString();
                        Boolean currentlyOfferedBool = false;
                        if(currentlyOffered.getText().toString().equals("Yes")){
                            currentlyOfferedBool = true;
                        }
                        Double priceDouble = Double.parseDouble(price.getText().toString().substring(1));
                        double ratingDouble;
                        if(rating.getText().toString().equals("No ratings")){
                            ratingDouble = -1;
                        }
                        else{
                            ratingDouble = Double.parseDouble(rating.getText().toString());
                        }
                        if(position!=RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(position,mealString, descriptionString,allergensString,ingredientsString,cuisineString
                                    ,mealTypeString,currentlyOfferedBool,priceDouble,ratingDouble,cookString);
                        }
                    }
                }
            });
        }

    }

    public interface RecyclerViewInterface{
        public void onItemClick(int position, String meal, String description, String allergens,
                                String ingredients, String cuisine, String mealType, boolean currentlyOffered, Double price, double rating, String cook);
    }
}
