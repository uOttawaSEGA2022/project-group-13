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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Locale;

public class PurchaseRequestAdapter extends RecyclerView.Adapter<PurchaseRequestAdapter.PurchaseRequestViewHolder> {

    String roleString;
   ArrayList<PurchaseRequest> list;
   Context context;
   PurchaseRequestAdapter.RecyclerViewInterface recyclerViewInterface;

    public PurchaseRequestAdapter(ArrayList<PurchaseRequest> list, Context context, PurchaseRequestAdapter.RecyclerViewInterface recyclerViewInterface, String role) {
        this.list = list;
        this.context = context;
        this.recyclerViewInterface = recyclerViewInterface;
        this.roleString = role;
    }

    @NonNull
    @Override
    public PurchaseRequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.purchase_request_item,parent,false);
        return new PurchaseRequestViewHolder(v,recyclerViewInterface);

    }

    @Override
    public void onBindViewHolder(@NonNull PurchaseRequestViewHolder holder, int position) {
        PurchaseRequest request = list.get(position);
        holder.meal.setText(request.getMeal());
        holder.status.setText(request.getStatus().toString().toLowerCase());
        holder.date.setText(request.getDate());
        DatabaseReference priceRef = FirebaseDatabase.getInstance().getReference("USERS").child(request.getCookUID()).child("MENU")
                        .child(request.getMeal()).child("price");
        if(roleString.equals("COOK")){
            holder.roleTitle.setText("Client: ");
        }
        else{
            holder.roleTitle.setText("Cook: ");
        }

        priceRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String priceString = snapshot.getValue().toString();
                holder.price.setText("$"+priceString);

                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("USERS").child(request.getCookUID()).child("email");

                if(roleString.equals("COOK")){
                    ref = FirebaseDatabase.getInstance().getReference("USERS").child(request.getClientUID()).child("email");
                }

                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        holder.role.setText(snapshot.getValue().toString());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class PurchaseRequestViewHolder extends RecyclerView.ViewHolder{

        TextView meal,price,role,status,date;
        TextView roleTitle;

        public PurchaseRequestViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);


            meal = itemView.findViewById(R.id.mealEditTextView);
            price = itemView.findViewById(R.id.priceEditTextview);
            role = itemView.findViewById(R.id.roleEditTextView);
            status = itemView.findViewById(R.id.statusEditTextView);
            date = itemView.findViewById(R.id.dateEditTextView);

            roleTitle = itemView.findViewById(R.id.roleTextView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(roleTitle.getText().toString().equals("Client: ") && status.getText().toString().equals("pending")){
                        if(recyclerViewInterface!=null){
                            int position = getAdapterPosition();

                            String mealString = meal.getText().toString();
                            String priceString =price.getText().toString();
                            String clientString = role.getText().toString();
                            String dateString = date.getText().toString();

                            if(position!=RecyclerView.NO_POSITION){
                                recyclerViewInterface.onItemClick(position,mealString,priceString, clientString, dateString);
                            }

                        }
                    }

                }
            });
        }
    }

//WHAT SHOULD IT DO WHEN CLICKED?
    public interface RecyclerViewInterface{
        public void onItemClick(int position,String meal, String priceString, String clientString, String dateString);
    }
}
