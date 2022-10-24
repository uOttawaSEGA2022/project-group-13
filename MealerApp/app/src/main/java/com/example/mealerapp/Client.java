package com.example.mealerapp;

import android.provider.ContactsContract;

public class Client extends User {

    private String creditCardInfo;

    public Client( String firstName, String lastName, String emailAddress, String accountPassword, String address, String creditCardInfo) {
        super(firstName, lastName, emailAddress, accountPassword, userType.CLIENT, address);
        this.creditCardInfo = creditCardInfo;
    }

    public void registerClient(){

       ClientDatabase dtb = new ClientDatabase();
        dtb.registerUser(this);
    }

    public String getCreditCardInfo() {
        return creditCardInfo;
    }

    public void setCreditCardInfo(String newCCInfo) {
        creditCardInfo = newCCInfo;
    }

    public String toString() {
        return "\nAccount Information  \nFirst name: " + firstName + "\n" + "Last name: " + lastName + "\n" + "Email: " + email + "\n" + "Password: " + password + "\n" + "Credit Card Information " + creditCardInfo;
    }
}
