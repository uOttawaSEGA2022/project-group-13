package com.example.secondderivable;

public class Complaints extends Client {
    String cookName;
    String complaint;
    String action;

    public Complaints(String complaint, String action,String cookName){
        super(firstName,lastName,emailAddress,accountPassword,address,creditCardInfo);
        this.action = action;
        this.complaint = complaint;
        this.cookName = cookName;
    }

    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getCookName() {
        return cookName;
    }

    public void setCookName(String cookName) {
        this.cookName = cookName;
    }
}
