package com.example.mealerapp;

import com.google.firebase.database.DatabaseReference;

public class Administrator extends Database{
// changed parameters because admin parameters that are known are only mail and password
    public Administrator(String mail, String pswrd, userType role) { 
        super( mail, pswrd, role);
    }

    //Delete the complaint stored at the param reference
    public void deleteComplaint(DatabaseReference reference){

        database.ref(reference).remove();

    }

    //Suspend the cook at the param cookUID
    public void suspendCook(DatabaseReference cookUID){

        Toast.makeText( "cook is suspended").show();

    }

    //Retrieve the complaint at the param complaint
    public String viewComplaint(DatabaseReference complaint){

        return "";
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

}
