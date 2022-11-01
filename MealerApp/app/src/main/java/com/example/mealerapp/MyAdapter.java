package com.example.mealerapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    ArrayList<Model> mList;
    Context context;

    public MyAdapter(Context context, ArrayList<Model> mList){
        this.mList = mList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //return null;
        View v = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new MyViewHolder((v));

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Model model = mList.get(position);
        holder.complaint.setText(model.getComplaint());
        holder.cook.setText(model.getCook());
        holder.client.setText(model.getClient());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView complaint, client, cook;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            complaint = itemView.findViewById(R.id.complainttext);
            cook = itemView.findViewById(R.id.cooktext);
            client = itemView.findViewById(R.id.clienttext);
        }
    }
}
