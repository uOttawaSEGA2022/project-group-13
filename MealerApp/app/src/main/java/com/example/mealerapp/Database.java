package com.example.mealerapp;

import android.util.Log;

import androidx.annotation.NonNull;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.EventListener;
import java.util.Locale;

abstract public class Database {

    private FirebaseDatabase database;

    public Database(){
        database = FirebaseDatabase.getInstance();
    }

    public void setInformation(DatabaseReference reference, Object information){

        reference.setValue(information);

    }

    public void deleteInformation(DatabaseReference reference){

        reference.removeValue();
    }

    public void getInformation(DatabaseReference reference, final Database.retrieveListener listener){
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String data = snapshot.getValue().toString();
                listener.onDataReceived(data);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                listener.onError();
            }
        });
    }

    public interface retrieveListener{

        void onDataReceived(Object data);
        void onError();
    }


}
