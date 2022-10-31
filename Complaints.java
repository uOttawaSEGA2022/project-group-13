package com.example.secondderivable;

public class Complaints {
    String cookName;
    String complaint;
    String clientName;

    public Complaints(String complaint, String clientName,String cookName){
        this.clientName = clientName;
        this.complaint = complaint;
        this.cookName = cookName;
    }

    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getCookName() {
        return cookName;
    }

    public void setCookName(String cookName) {
        this.cookName = cookName;
    }
}
