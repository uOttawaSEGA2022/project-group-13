package com.example.mealerapp;

import android.net.wifi.aware.PublishConfig;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class RequestDatabase extends Database{

    private FirebaseDatabase database;
    private DatabaseReference reference;
    private DatabaseReference statusRef;
    private boolean stop = false;

    public RequestDatabase(){
        database = FirebaseDatabase.getInstance();
        reference = FirebaseDatabase.getInstance().getReference("USERS");
        statusRef = FirebaseDatabase.getInstance().getReference("STATUS");


    }

    public void addRequest(PurchaseRequest request){
        if(!stop) {
            String requestID = request.getClientUID() + request.getDate();
            reference.child(request.getCookUID()).child("REQUESTS").child(requestID).setValue(request);
            reference.child(request.getClientUID()).child("REQUESTS").child(requestID).setValue(request);
            stop = true;
        }
    }

    public void deleteRequest(PurchaseRequest request) {

    }

    public void setAccepted(PurchaseRequest request){

        reference.child(request.getCookUID()).child("REQUESTS").child("STATUS").setValue("APPROVED");
        reference.child(request.getClientUID()).child("REQUESTS").child("STATUS").setValue("APPROVED");

    }
    public void setRejected(PurchaseRequest request){
        reference.child(request.getCookUID()).child("REQUESTS").child("STATUS").setValue("DENIED");
        reference.child(request.getClientUID()).child("REQUESTS").child("STATUS").setValue("DENIED");

    }
}
