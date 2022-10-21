package com.example.mealer;

import android.net.Uri;

import com.google.firebase.database.Exclude;

public class User {
    private String firstName, lastName, email, password, address;
    private Uri avatar;
    public enum userType {COOK, CLIENT};
    private userType type;


    /*The user constructor. Assigns the 6 instance variables values passed to the constructor. UserType role detremines whether
     user is a Cook or a Client*/
    public User(String fName, String lName, String mail, String pswrd, userType role, String adrs){
        firstName=fName;
        lastName=lName;
        email = mail;
        password = pswrd;
        type = role;
        address = adrs;

    }

    //Getter for User's first name
    public String getFirstName() {
        return firstName;
    }

    //Setter for User's first name
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    //Getter for User's last name
    public String getLastName() {
        return lastName;
    }

    //Setter for User's last name
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    //Getter for User's email
    public String getEmail() {
        return email;
    }

    //Setter for User's email
    public void setEmail(String email) {
        this.email = email;
    }

    //Getter for User's password
    public String getPassword() {
        return password;
    }

    //Setter for User's set password
    public void setPassword(String password) {
        this.password = password;
    }

    //ToString that displays the type (Chef or Client)
    public String roleToString(){return type.toString();}


    //Getter for User's role (User can be Cook or Client)
    @Exclude
    public userType getRole(){return type;}

    //Setter for user's role (User can be Cook or Client)
    public void setRole(userType role){type = role;}




}
