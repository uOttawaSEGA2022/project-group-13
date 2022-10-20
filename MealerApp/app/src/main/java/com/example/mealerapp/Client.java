package com.example.mealerapp;

public class Client extends User {

    private String creditCardInfo;

    public Client( String firstName, String lastName, String emailAddress, String accountPassword, String address, String creditCardInfo) {
        super(firstName, lastName, emailAddress, accountPassword, address, userType.CLIENT);
        this.creditCardInfo = creditCardInfo;
    }

    public String getCreditCardInfo() {
        return creditCardInfo;
    }

    public void setCreditCardInfo(String newCCInfo) {
        creditCardInfo = newCCInfo;
    }

    public String toString() {
        return "\nAccount Information  \nFirst name: " + firstName + "\n" + "Last name: " + lastName + "\n" + "Email: " + emailAddress + "\n" + "Password: " + accountPassword + "\n" + "Credit Card Information " + creditCardInfo;
    }
}
