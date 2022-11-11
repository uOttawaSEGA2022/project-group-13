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

    ArrayList<MealModel> list;
    Context context;
    private final MenuAdapter.RecyclerViewInterface recyclerViewInterface;

    public MenuAdapter (Context context, ArrayList<MealModel> list, RecyclerViewInterface recyclerViewInterface){
        this.list = list;
        this.context = context;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public MenuAdapter.MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new MenuViewHolder(v,recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuAdapter.MenuViewHolder holder, int position) {
        MealModel model = list.get(position);
        holder.meal.setText(model.getMeal());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MenuViewHolder extends RecyclerView.ViewHolder{

        TextView meal;

        public MenuViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            meal = itemView.findViewById(R.id.mealTextView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    String mealString = meal.getText().toString();

                    if(position!=RecyclerView.NO_POSITION){
                        recyclerViewInterface.onItemClick(position,mealString);
                    }
                }
            });
        }

    }

    public interface RecyclerViewInterface{
        public void onItemClick(int position, String name);
    }
}
