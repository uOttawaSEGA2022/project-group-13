package com.example.mealerapp;

import android.net.wifi.aware.PublishConfig;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RequestDatabase extends Database{

    private FirebaseDatabase database;
    private DatabaseReference reference;
    private boolean stop = false;

    public RequestDatabase(){
        database = FirebaseDatabase.getInstance();
        reference = FirebaseDatabase.getInstance().getReference("USERS");
    }

    public void addRequest(PurchaseRequest request){
        if(!stop){
            String requestID = request.getClientUID()+System.currentTimeMillis();
            reference.child(request.getCookUID()).child("REQUESTS").child(requestID).setValue(request);
            reference.child(request.getClientUID()).child("REQUESTS").child(requestID).setValue(request);
            stop = true;
        }
    }

    public void deleteRequest(PurchaseRequest request){

    }
}
