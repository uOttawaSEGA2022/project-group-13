package com.example.mealerapp;

public class PurchaseRequest {

    public enum STATUS{APPROVED, DENIED, PENDING};

    private String cookUID, clientUID;
    private Meal meal;
    private STATUS status;

    public PurchaseRequest(String cookUID, String clientUID, Meal meal) {
        this.cookUID = cookUID;
        this.clientUID = clientUID;
        this.meal = meal;
        this.status = STATUS.PENDING;
    }

    public String getCookUID() {
        return cookUID;
    }

    public void setCookUID(String cookUID) {
        this.cookUID = cookUID;
    }

    public String getClientUID() {
        return clientUID;
    }

    public void setClientUID(String clientUID) {
        this.clientUID = clientUID;
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    public STATUS getStatus() {
        return status;
    }

    public void setStatus(STATUS status) {
        this.status = status;
    }



}
