package com.example.mealerapp;

import android.content.Context;
import android.util.Log;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.mealerapp", appContext.getPackageName());
    }

    @Test
    public void suspendCook_isCorrect() throws Exception {
        UserDatabase dtb = new UserDatabase();
        dtb.suspendCook("diWhRfZIqRdWSgqki9aKDpn1vDk2", "03/04/2023");
        Database.retrieveListener isSuspendedListener = new Database.retrieveListener() {
            @Override
            public void onDataReceived(Object data) {
                Boolean isSuspended = Boolean.valueOf(data.toString());
                assertTrue(isSuspended);

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
                assertEquals("03/04/2023", data.toString());
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
    public void resetCook() {
        UserDatabase dtb = new UserDatabase();
        dtb.liftSuspension("diWhRfZIqRdWSgqki9aKDpn1vDk2");
    }

    /**
     * Tests if adding and dismissing complaints is working as expected
     */
    @Test
    public void addAndDismissComplain_isCorrect() {
        ComplaintsDataBase dtb = new ComplaintsDataBase();
        Complaints complaint = new Complaints("I did not like the food",
                "CLIENTUID", "diWhRfZIqRdWSgqki9aKDpn1vDk2");
        dtb.addComplaint("diWhRfZIqRdWSgqki9aKDpn1vDk2", complaint);
        assertNotNull(FirebaseDatabase.getInstance().getReference("COMPLAINTS").child("diWhRfZIqRdWSgqki9aKDpn1vDk2"));
        dtb.setRead("diWhRfZIqRdWSgqki9aKDpn1vDk2");

        Database.retrieveListener readListener = new Database.retrieveListener() {
            @Override
            public void onDataReceived(Object data) {

                assertEquals(data.toString(), "true");
            }

            @Override
            public void onError() {

            }
        };
        dtb.getInformation(FirebaseDatabase.getInstance().getReference("COMPLAINTS")
                .child("diWhRfZIqRdWSgqki9aKDpn1vDk2").child("read"), readListener);
    }

    /**
     * Deletes the test complaint created
     */
    @After
    public void deleteTestComplaint() {
        ComplaintsDataBase dtb = new ComplaintsDataBase();
        dtb.deleteComplaint("diWhRfZIqRdWSgqki9aKDpn1vDk2");
    }

    @Test
    public void setAndGetInfo_isCorrect(){
        UserDatabase dtb = new UserDatabase();
        Database.retrieveListener listener = new Database.retrieveListener() {
            @Override
            public void onDataReceived(Object data) {
                assertEquals(data.toString(),"123 Main st");
            }

            @Override
            public void onError() {

            }
        };
        DatabaseReference addressRef = FirebaseDatabase.getInstance()
                .getReference("USERS").child("25vrVWZNuVVyuWI3H4IX01rpzgs1")
                .child("address");
        dtb.getInformation(addressRef,listener);

        dtb.setInformation(addressRef,"44 Apple Avenue");

        Database.retrieveListener retrieveListener = new Database.retrieveListener() {
            @Override
            public void onDataReceived(Object data) {
                assertEquals(data.toString(),"44 Apple Avenue");
            }

            @Override
            public void onError() {

            }
        };
        dtb.getInformation(addressRef,retrieveListener);
    }

    @After
    public void resetAddress(){
        UserDatabase dtb = new UserDatabase();
        DatabaseReference addressRef = FirebaseDatabase.getInstance()
                .getReference("USERS").child("25vrVWZNuVVyuWI3H4IX01rpzgs1")
                .child("address");

        dtb.setInformation(addressRef,"123 Main st");
    }





    /**
     * Test will check if registering and deleting a client is working correctly
     */




    /**
     * Test will check if registering, suspending, and deleting a cook user is working correctly
     */
    /*@Test
    public void userRegisterAndSuspension_isCorrect(){ 
        UserDatabase dtb = new UserDatabase();
        User cook= new Cook("Jane", "Doe", "Janedoe@gmail.com", "Imakefood/123", "213 teron road","I make food");
        dtb.registerUser(cook);
        String userID=FirebaseAuth.getInstance().getUID();
        dtb.suspendCook(userID, "09/07/2023"); 

        Database.retrieveListener cookListener = new Database.retrieveListener() {
            @Override
            public void onDataReceived(Object data) {
                Boolean isSuspended = Boolean.valueOf(data.toString());
                assertTrue(isSuspended);
            }

            @Override
            public void onError() {

            }
        };

        dtb.getInformation(FirebaseDatabase.getInstance().getReference("USERS").child(userID).child("isSuspended"), cookListener);

}
    
    /**
     * Deletes the test Client created
     */
    /*@After public void deleteTestCook(){
        UsersDataBase dtb = new UsersDataBase();
        dtb.deleteUser(cook);
    }*/
}


