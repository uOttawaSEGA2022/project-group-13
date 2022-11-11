package com.example.mealerapp;

public class Cook extends User{

    private String description, suspensionDate;
    private boolean isSuspended;
    private Menu menu;

    public Cook(String firstName, String lastName, String emailAddress, String accountPassword, String address, String description) {
        super(firstName, lastName, emailAddress, accountPassword, userType.COOK, address);
        this.description = description;
        suspensionDate = "N/A";
        isSuspended = false;
        menu = new Menu();
    }

    public Cook(String firstName, String lastName, String emailAddress, String accountPassword, String address, String description, Boolean isSuspended, String suspensionDate) {
        super(firstName, lastName, emailAddress, accountPassword, userType.COOK, address);
        this.description = description;
        this.suspensionDate = suspensionDate;
        this.isSuspended = isSuspended;
        menu = new Menu();

    }

    public String getSuspensionDate() {
        return suspensionDate;
    }

    public void setSuspensionDate(String suspensionDate) {
        this.suspensionDate = suspensionDate;
    }

    public boolean isSuspended() {
        return isSuspended;
    }

    public void setSuspended(boolean suspended) {
        isSuspended = suspended;
    }

    public void registerCook(){

        UserDatabase dtb = new UserDatabase();
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

     public void addMealtoMenu(Meal meal){
        menu.addMeal(meal);
     }

     public void addMealToCurrentlyOffered(Meal meal){
        meal.getCurrentlyOffered();
     }


}
