package com.example.mealerapp;

import java.util.Date;
import java.util.Locale;

public class PurchaseRequest {

    public enum STATUS{APPROVED, DENIED, PENDING};

    private String cookUID, clientUID, meal;
    private STATUS status;
    private String date;

    public PurchaseRequest(String cookUID, String clientUID, String meal) {
        this.cookUID = cookUID;
        this.clientUID = clientUID;
        this.meal = meal;
        this.status = STATUS.PENDING;
        Date date = new Date();
        this.date = date.toString();
    }

    public PurchaseRequest(String cookUID, String clientUID, String meal, STATUS status, String date) {
        this.cookUID = cookUID;
        this.clientUID = clientUID;
        this.meal = meal;
        this.status = status;
        this.date = date;
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

    public String getMeal() {
        return meal;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }

    public STATUS getStatus() {
        return status;
    }

    public void setStatus(STATUS status) {
        this.status = status;
    }

    public static STATUS stringToStatus(String statusString){
        String status = statusString.toLowerCase();
        switch (status){
            case "approved": return STATUS.APPROVED;
            case "pending": return STATUS.PENDING;
            case "denied": return STATUS.DENIED;
        }

        return STATUS.PENDING;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
