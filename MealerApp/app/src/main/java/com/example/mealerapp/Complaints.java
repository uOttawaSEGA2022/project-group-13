package com.example.mealerapp;

public class Complaints {
    String cookUID;
    String complaint;
    String clientUID;
    Boolean read;

    public Complaints(String complaint, String clientUID,String coolUID){
        this.clientUID = clientUID;
        this.complaint = complaint;
        this.cookUID = cookUID;
        read = false;
    }

    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }

    public String getClientUID() {
        return clientUID;
    }

    public void setClientName(String clientUID) {
        this.clientUID = clientUID;
    }

    public String getCookUID() {
        return cookUID;
    }

    public void setCookUID(String cookUID) {
        this.cookUID = cookUID;
    }

    public void setRead(Boolean read){this.read = read;}

    public Boolean getRead(){return read;}
}
