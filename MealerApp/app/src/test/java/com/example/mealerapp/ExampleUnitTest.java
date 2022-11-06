package com.example.mealerapp;

import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.*;

import android.service.autofill.UserData;
import android.util.Log;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest{
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    /**
     * Test will check if suspend and lifting a suspension on a cook is working correctly
     */

    @Test
    public void suspendCook_isCorrect(){
        UserDatabase dtb = new UserDatabase();


        dtb.suspendCook("diWhRfZIqRdWSgqki9aKDpn1vDk2", "June 10 2022");
        Database.retrieveListener isSuspendedListener = new Database.retrieveListener() {
            @Override
            public void onDataReceived(Object data) {
                assertTrue((Boolean)data);

            }

            @Override
            public void onError() {

            }
        };
        dtb.getInformation(FirebaseDatabase.getInstance().getReference("USERS")
                .child("diWhRfZIqRdWSgqki9aKDpn1vDk2").child("isSuspended"), isSuspendedListener);

        Database.retrieveListener dateListener = new Database.retrieveListener() {
            @Override
            public void onDataReceived(Object data) {
                assertEquals("June 10 2022", data.toString());
            }

            @Override
            public void onError() {

            }
        };
        dtb.getInformation(FirebaseDatabase.getInstance().getReference("USERS")
                .child("diWhRfZIqRdWSgqki9aKDpn1vDk2").child("suspensionDate"), dateListener);


    }

    /**
     * Resets the values of the test cook in the database so that the test may be preformed again
     */
    @After
    public void resetCook(){
        UserDatabase dtb = new UserDatabase();
        dtb.liftSuspension("diWhRfZIqRdWSgqki9aKDpn1vDk2");
    }

    /**
     * Tests if adding and dismissing complaints is working as expected
     */
    @Test
    public void addAndDismissComplain_isCorrect(){
        ComplaintsDataBase dtb = new ComplaintsDataBase();
        Complaints complaint = new Complaints("I did not like the food",
                "CLIENTUID", "diWhRfZIqRdWSgqki9aKDpn1vDk2");
        dtb.addComplaint("diWhRfZIqRdWSgqki9aKDpn1vDk2",complaint);

        Database.retrieveListener complaintListener = new Database.retrieveListener() {
            @Override
            public void onDataReceived(Object data) {
                Complaints dtbComplaint = (Complaints) data;
                assertEquals(complaint,dtbComplaint);
            }

            @Override
            public void onError() {

            }
        };
        dtb.getInformation(FirebaseDatabase.getInstance().getReference("COMPLAINTS")
                .child("diWhRfZIqRdWSgqki9aKDpn1vDk2"),complaintListener);

        dtb.setRead("diWhRfZIqRdWSgqki9aKDpn1vDk2");

        Database.retrieveListener readListener = new Database.retrieveListener() {
            @Override
            public void onDataReceived(Object data) {
                Boolean isRead = (boolean)data;
                assertTrue(isRead);
            }

            @Override
            public void onError() {

            }
        };
        dtb.getInformation(FirebaseDatabase.getInstance().getReference("COMPLAINTS")
                .child("diWhRfZIqRdWSgqki9aKDpn1vDk2").child("isRead"), readListener);
    }

    /**
     * Deletes the test complaint created
     */
    @After public void deleteTestComplaint(){
        ComplaintsDataBase dtb = new ComplaintsDataBase();
        dtb.deleteComplaint("diWhRfZIqRdWSgqki9aKDpn1vDk2");
    }


    /**
     * Test will check if registering and deleting a client is working correctly
     */
    @Test
    public void registerAndDeleteUser_isCorrect(){
        UserDatabase dtb = new UserDatabase();
        User client= new Client("John", "Doe", "Jdoe@gmail.com", "Ilikefood/123", "213 Celtic road","1638299384651290");
        dtb.registerUser(client);

        Database.retrieveListener registrationListener = new Database.retrieveListener() {
            @Override
            public void onDataReceived(Object data) {
                User dtbClient = data;
                assertEquals(client,dtbClient); //Checks if the expected "value" (client) is the same as the actual "value"  (dtbClient) within the DataBase
            }

            @Override
            public void onError() {

            }
        };

        String userID=FirebaseAuth.getInstance().getUID();
        dtb.getInformation(FirebaseDatabase.getInstance().getReference("USERS").child(userID), registrationListener);
    
    }

    /**
     * Deletes the test Client created
     */
    @After public void deleteTestClient(){
        UsersDataBase dtb = new UsersDataBase();
        dtb.deleteUser(client);
    }


    /**
     * Test will check if the login works correctly
     */
    @Test
    public void userLogin_isCorrect(){ 
        UserDatabase dtb = new UserDatabase();
        User client= new Client("John", "Doe", "Jdoe@gmail.com", "Ilikefood/123", "213 Celtic road","1638299384651290");
        dtb.registerUser(client);

        Database.retrieveListener registrationListener = new Database.retrieveListener() {
            @Override
            public void onDataReceived(Object data) {
                User dtbClient = data;
                assertEquals(client,dtbClient); //Checks if the expected "value" (client) is the same as the actual "value"  (dtbClient) within the DataBase
            }

            @Override
            public void onError() {

            }
        };

        String userID=FirebaseAuth.getInstance().getUID();
        dtb.getInformation(FirebaseDatabase.getInstance().getReference("USERS").child(userID), registrationListener);




}