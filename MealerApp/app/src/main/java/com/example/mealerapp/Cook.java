package com.example.mealerapp;

public class Cook extends User{

    /**
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String accountPassword;
    private String address;
    */
    private String description;

    public Cook(String firstName, String lastName, String emailAddress, String accountPassword, String address, String description) {
        super(firstName, lastName, emailAddress, accountPassword, userType.COOK, address);
        /**
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.accountPassword = accountPassword;
        this.address = address;
         */
        this.description = description;
    }

     /**
     public String getFirst() {
     return firstName;
     }

     public String getLast() {
     return lastName;
     }

     public String getEmail() {
     return emailAddress;
     }

     public String getPassword() {
     return accountPassword;
     }


     public String getAddress() {
     return address;
     }

    */

    public String getDescription() {
        return description;
    }

     /**
     public void setFirst(String newname) {
     firstName = newname;
     }

     public void setLastname(String newname) {
     address = newname;
     }

     public void setEmail(String newname) {
     emailAddress = newname;
     }

     public void setPassword(String newname) {
     accountPassword = newname;
     }


     public void setAddress(String newname) {
     address = newname;
     }

    */
    public void setDescription(String newDescription) {
        description = newDescription;
    }

     public String toString() {
     return "\nAccount Information \n************************* \nFirst name: " + firstName + "\n" + "Last name: " + lastName + "\n" + "Email: " + emailAddress + "\n" + "Password: " + accountPassword + "\n" + "Description: " + description;
     }


}
