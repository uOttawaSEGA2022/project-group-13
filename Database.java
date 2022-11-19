package com.example.myapplication;


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

import java.util.Locale;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.StorageReference;


public class Database {
    private DatabaseReference userReference;
    private FirebaseDatabase database;
    private FirebaseAuth auth;
    public enum dataField{FIRSTNAME,LASTNAME,EMAIL,PASSWORD,ADDRESS};


    public Database(){
        database = FirebaseDatabase.getInstance();
        userReference = database.getReference().child("USERS");
        auth = FirebaseAuth.getInstance();
    }

    public void registerUser(User user){

        //Create an account using email and password
        auth.createUserWithEmailAndPassword(user.getEmail(),user.getPassword()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override

            //If sign up with email and password is successful, stores user information into realtime database
            public void onSuccess(AuthResult authResult) {

                userReference.child(user.getRole().toString()).child(auth.getCurrentUser().getUid()).setValue(user);
            }
        });

    }

    public void deleteUser(User user){

        //Delete user information
        userReference.child(user.getRole().toString()).child(FirebaseAuth.getInstance().getCurrentUser().getUid()).removeValue();

        //Delete user authentication information
        auth.getCurrentUser().delete();

    }

    //This does not work I am trying to fix it
   /* public String retrieveInfo(User user,String field){

        FirebaseDatabase.getInstance().getReference().child(user.roleToString()).child(FirebaseAuth.getInstance().getUid()).child(field).get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                data = dataSnapshot.getValue().toString();
                Log.d("Inside on Success: " ,dataSnapshot.toString());
            }
        });
        return data;

    }*/

    //
    public void changeInfo(User user,  dataField field, String newInfo){

        //Make sure someone is logged in before trying to change their information
        if(auth.getCurrentUser()!=null)
            userReference.child(user.getRole().toString()).child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(field.toString().toLowerCase()).setValue(newInfo);
    }

    //Login user using email and password
    public void login(String email, String password){

        auth.signInWithEmailAndPassword(email,password);

    }
<<<<<<< HEAD
=======
    
>>>>>>> 4db2657f53a4fea216a5964e2d666ad1cb7b5527

    //Log out current user
    public void logoff(){

        auth.signOut();

    }
}
