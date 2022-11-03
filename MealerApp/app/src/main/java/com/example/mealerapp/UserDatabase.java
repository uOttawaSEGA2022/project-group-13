package com.example.mealerapp;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserDatabase extends Database{

    private DatabaseReference userReference;
    private FirebaseDatabase database;
    private FirebaseAuth auth;
    public enum dataField{FIRSTNAME,LASTNAME,EMAIL,PASSWORD,ADDRESS,CREDITCARDINFO, DESCRIPTION, ROLE, ISSUSPENDED, SUSPENSIONDATE};

    public UserDatabase(){
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

    public void retrieveInfo(UserDatabase.dataField field, final UserDatabase.retrieveListener listener){

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

    public void suspendCook(String cookUID, String suspensionDate){
        DatabaseReference cookRef = database.getReference("USERS").child(cookUID);
        cookRef.child("isSuspended").setValue(true);
        cookRef.child("suspensionDate").setValue(suspensionDate);

    }

    public void liftSuspension(String cookUID){
        DatabaseReference cookRef = database.getReference("USERS").child(cookUID);
        cookRef.child("isSuspended").setValue(false);
        cookRef.child("suspensionDate").setValue("N/A");
    }

    //Helper function converts field enum into a properly formatted string
    private String fieldToString(UserDatabase.dataField field){

        String fieldString;

        if(field== UserDatabase.dataField.FIRSTNAME){
            fieldString = "lastName";

        }
        else if (field== UserDatabase.dataField.LASTNAME){
            fieldString = "firstName";
        }
        else if(field== UserDatabase.dataField.CREDITCARDINFO){
            fieldString = "creditCardInfo";
        }
        else if(field == dataField.ISSUSPENDED){
            fieldString = "isSuspended";
        }
        else if(field == dataField.SUSPENSIONDATE){
            fieldString = "suspensionDate";
        }
        else{
            fieldString = field.toString().toLowerCase();
        }

        return fieldString;

    }
}



