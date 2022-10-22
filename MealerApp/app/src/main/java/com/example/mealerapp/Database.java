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

import java.util.Locale;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.StorageReference;


public class Database {
    private DatabaseReference userReference;
    private FirebaseDatabase database;
    private FirebaseAuth auth;
    public enum dataField{FIRSTNAME,LASTNAME,EMAIL,PASSWORD,ADDRESS,CREDITCARDINFO, DESCRIPTION, ROLE};
    private String uID = "null";

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

                userReference.child(auth.getCurrentUser().getUid()).setValue(user);
            }
        });

    }

    public void deleteUser(User user){

        //Delete user information
        userReference.child(user.getRole().toString()).child(FirebaseAuth.getInstance().getCurrentUser().getUid()).removeValue();

        //Delete user authentication information
        auth.getCurrentUser().delete();

    }

    public interface retrieveListener{

        void onDataReceived(String data);
        void onError();
    }


   public void retrieveInfo(dataField field, final retrieveListener listener){

        DatabaseReference uIDReference = userReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(fieldToString(field));

        uIDReference.addListenerForSingleValueEvent(new ValueEventListener() {
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
    //Login user using email and password
    public void login(String email, String password){

        auth.signInWithEmailAndPassword(email,password);

    }

    //Log out current user
    public void logoff(){

        auth.signOut();

    }

    private String fieldToString(dataField field){

        String fieldString;

        if(field==dataField.FIRSTNAME){
            fieldString = "lastName";

        }
        else if (field==dataField.LASTNAME){
            fieldString = "firstName";
        }
        else if(field== dataField.CREDITCARDINFO){
            fieldString = "creditCardInfo";
        }
        else{
            fieldString = field.toString().toLowerCase();
        }

        return fieldString;

    }
}
