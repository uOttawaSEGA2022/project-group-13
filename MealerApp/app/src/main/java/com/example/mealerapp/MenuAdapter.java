package com.example.mealerapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {

    ArrayList<Meal> list;
    Context context;
    MenuAdapter.RecyclerViewInterface recyclerViewInterface;

    public MenuAdapter (Context context, ArrayList<Meal> list, MenuAdapter.RecyclerViewInterface recyclerViewInterface){
        this.list = list;
        this.context = context;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.meal_item, parent, false);
        return new MenuViewHolder(v,recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        Meal meal = list.get(position);
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
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MenuViewHolder extends RecyclerView.ViewHolder{

        TextView meal, ingredients, allergens, description, cuisine, currentlyOffered, mealType, price, rating;

        public MenuViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
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
                            ,mealTypeString,currentlyOfferedBool,priceDouble,ratingDouble);
                        }
                    }
                }
            });
        }

    }

    public interface RecyclerViewInterface{
        public void onItemClick(int position, String meal, String description, String allergens,
                                String ingredients, String cuisine, String mealType, boolean currentlyOffered, Double price, double rating);
    }
}
