package com.example.mealerapp;

public class Model {

    String complaint, client, cook;

    public Model(String complaint, String cook, String client){
        this.complaint = complaint;
        this.cook = cook;
        this.client = client;
    }

    public String getComplaint() {
        return complaint;
    }

    public String getClient() {
        return client;
    }

    public String getCook() {
        return cook;
    }

}
