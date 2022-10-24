package com.example.mealerapp;

public class Cook extends User{

    private String description;

    public Cook(String firstName, String lastName, String emailAddress, String accountPassword, String address, String description) {
        super(firstName, lastName, emailAddress, accountPassword, userType.COOK, address);
        this.description = description;
    }

    public void registerCook(){

        ClientDatabase dtb = new ClientDatabase();
        dtb.registerUser(this);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String newDescription) {
        description = newDescription;
    }

     public String toString() {
     return "\nAccount Information \n************************* \nFirst name: " + firstName + "\n" + "Last name: " + lastName + "\n" + "Email: " + email + "\n" + "Password: " + password + "\n" + "Description: " + description;
     }


}
