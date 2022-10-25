package com.example.mealerapp;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ClientDatabase extends Database{

    private DatabaseReference userReference;
    private FirebaseDatabase database;
    private FirebaseAuth auth;
    public enum dataField{FIRSTNAME,LASTNAME,EMAIL,PASSWORD,ADDRESS,CREDITCARDINFO, DESCRIPTION, ROLE};

    public ClientDatabase(){
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

                setInformation(userReference.child(auth.getCurrentUser().getUid()), user);

            }
        });

    }

    public void deleteUser(User user){

        //Delete user information
        deleteInformation(userReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()));

        //Delete user authentication information
        auth.getCurrentUser().delete();

    }


    public void retrieveInfo(ClientDatabase.dataField field, final ClientDatabase.retrieveListener listener){

        DatabaseReference fieldReference = userReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(fieldToString(field));

        getInformation(fieldReference, listener);

    }
    //Login user using email and password
    public void login(String email, String password){

            auth.signInWithEmailAndPassword(email,password);

    }

    //Log out current user
    public void logoff(){

        if(auth.getCurrentUser()!=null) {
            auth.signOut();
        }

    }

    //Helper function converts field enum into a properly formatted string
    private String fieldToString(ClientDatabase.dataField field){

        String fieldString;

        if(field== ClientDatabase.dataField.FIRSTNAME){
            fieldString = "lastName";

        }
        else if (field== ClientDatabase.dataField.LASTNAME){
            fieldString = "firstName";
        }
        else if(field== ClientDatabase.dataField.CREDITCARDINFO){
            fieldString = "creditCardInfo";
        }
        else{
            fieldString = field.toString().toLowerCase();
        }

        return fieldString;

    }
}



