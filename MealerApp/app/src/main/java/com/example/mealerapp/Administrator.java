package com.example.mealerapp;

import com.google.firebase.database.DatabaseReference;

public class Administrator extends User{

    public Administrator(String fName, String lName, String mail, String pswrd, userType role, String adrs) {
        super(fName, lName, mail, pswrd, role, adrs);
    }

    //Delete the complaint stored at the param reference
    public void deleteComplaint(DatabaseReference reference){

    }

    //Suspend the cook at the param cookUID
    public void suspendCook(DatabaseReference cookUID){

    }

    //Retrieve the complaint at the param complaint
    public String viewComplaint(DatabaseReference complaint){

        return "";
    }

}
