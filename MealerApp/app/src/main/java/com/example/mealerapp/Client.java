package com.example.mealerapp;

public class Client extends User {

    private String creditCardInfo;

    public Client( String firstName, String lastName, String emailAddress, String accountPassword, String address, String creditCardInfo) {
        super(firstName, lastName, emailAddress, accountPassword, userType.CLIENT, address);
        this.creditCardInfo = creditCardInfo;
    }

    public void registerClient(){

       UserDatabase dtb = new UserDatabase();
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

    public PurchaseRequest createRequest(String clientUID, String cookUID, String meal, String date){
        return new PurchaseRequest(cookUID,clientUID,meal);
    }
}
