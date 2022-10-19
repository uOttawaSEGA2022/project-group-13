package com.example.mealer;

import android.net.Uri;

import com.google.firebase.database.Exclude;

public class User {
    private String firstName, lastName, email, password, address;
    private Uri avatar;
    public enum userType {COOK, CLIENT};
    private userType type;

    public User(String fName, String lName, String mail, String pswrd, userType role, String adrs){
        firstName=fName;
        lastName=lName;
        email = mail;
        password = pswrd;
        type = role;
        address = adrs;

    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String roleToString(){return type.toString();}

    @Exclude
    public userType getRole(){return type;}

    public void setRole(userType role){type = role;}




}
