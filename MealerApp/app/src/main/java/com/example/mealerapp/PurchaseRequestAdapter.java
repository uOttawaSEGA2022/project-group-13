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
import java.util.Locale;

public class PurchaseRequestAdapter extends RecyclerView.Adapter<PurchaseRequestAdapter.PurchaseRequestViewHolder> {

   ArrayList<PurchaseRequest> list;
   Context context;
   PurchaseRequestAdapter.RecyclerViewInterface recyclerViewInterface;

    public PurchaseRequestAdapter(ArrayList<PurchaseRequest> list, Context context, PurchaseRequestAdapter.RecyclerViewInterface recyclerViewInterface) {
        this.list = list;
        this.context = context;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public PurchaseRequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.purchase_request_item,parent,false);
        return new PurchaseRequestViewHolder(v,recyclerViewInterface);

    }

    @Override
    public void onBindViewHolder(@NonNull PurchaseRequestAdapter.PurchaseRequestViewHolder holder, int position) {
        PurchaseRequest request = list.get(position);
        Meal mealObj = (request.getMeal());
        holder.meal.setText(mealObj.getName());
        //SHOULD GET COOK NAME??? OR EMAIL???
        holder.cook.setText(request.getCookUID());
        holder.price.setText(String.valueOf(mealObj.getPrice()));
        holder.status.setText(request.getStatus().toString().toLowerCase());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class PurchaseRequestViewHolder extends RecyclerView.ViewHolder{

        TextView meal,price,cook,status;

        public PurchaseRequestViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            meal = itemView.findViewById(R.id.mealTextView);
            price = itemView.findViewById(R.id.priceTextView);
            cook = itemView.findViewById(R.id.cookTextView);
            status = itemView.findViewById(R.id.statusTextView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(recyclerViewInterface!=null){
                        int position = getAdapterPosition();

                        String mealString = meal.getText().toString();
                        String cookString = cook.getText().toString();
                        String statusString = status.getText().toString();
                        double priceDouble = Double.parseDouble(price.getText().toString());

                        if(position!=RecyclerView.NO_POSITION){
                            //THIS SHOULD BE CHANGED
                            recyclerViewInterface.onItemClick(position);
                        }

                    }
                }
            });
        }
    }

//WHAT SHOULD IT DO WHEN CLICKED?
    public interface RecyclerViewInterface{
        public void onItemClick(int position);
    }
}
