package com.example.mealer;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


public class Database {
    private DatabaseReference databaseReference;
    private FirebaseDatabase database;
    private FirebaseAuth auth;
    private StorageReference storageReference;

    public Database(){
        storageReference = FirebaseStorage.getInstance().getReference();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();
        auth = FirebaseAuth.getInstance();
    }

    public String returnUID(){
        return auth.getCurrentUser().getUid();

    }

    public void registerAuth(User user){
        //Register user using firebase authorization with email and password
        auth.createUserWithEmailAndPassword(user.getEmail(),user.getPassword()).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("AUTHORIZATION", e.toString());
            }
        });

    }

    public void registerUser(User user){
        DatabaseReference userRef = database.getReference().child(user.roleToString());
        userRef.child(auth.getCurrentUser().getUid()).setValue(user);
    }

    public void registerInfo(User user){
        //Use the user's role to sort chef and client in the database
        //Write the user information into the database
       // databaseReference.child(user.roleToString()).child(auth.getCurrentUser().getUid()).setValue(user);
        database.getReference().child(user.roleToString()).child(auth.getCurrentUser().getUid()).setValue(user);

    }

    public void deleteUser(User user){

        database.getReference(user.roleToString()).child(auth.getCurrentUser().getUid()).removeValue();
    }

    public void retrieveUser(){

    }

    public void changeInfo(User user, String field, String newInfo){

        database.getReference(user.roleToString()).child(auth.getCurrentUser().getUid()).child(field).setValue(newInfo);
    }
/*
    public void registerUser(String email, String password, String firstName,
    String lastName, String address) {

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    User user = new User(firstName,lastName,email,password);
                    databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(!task.isSuccessful()){
                                        Log.d("STORE TO FB", "failed");
                                    }
                                }
                            });

                }
                else{

                }

            }
        });


    }*/
}



