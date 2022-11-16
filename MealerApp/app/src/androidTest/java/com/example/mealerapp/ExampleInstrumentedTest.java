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
        dtb.liftSuspension();
    }

    /**
     * Tests if adding and dismissing complaints is working as expected
     */
    @Test
    public void addAndDismissComplaint_isCorrect() {
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
                assertEquals(data.toString(),"12345 Main Street");
            }

            @Override
            public void onError() {

            }
        };
        DatabaseReference addressRef = FirebaseDatabase.getInstance()
                .getReference("USERS").child("diWhRfZIqRdWSgqki9aKDpn1vDk2")
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
                .getReference("USERS").child("diWhRfZIqRdWSgqki9aKDpn1vDk2")
                .child("address");

        dtb.setInformation(addressRef,"12345 Main Street");
    }


    @Test
    public void loginAndLogoff_isCorrect(){
        UserDatabase dtb = new UserDatabase();
        dtb.login("testcook@gmail.com", "12345Password");
        String UID = FirebaseAuth.getInstance().getUid();

        assertNotNull(UID);
        dtb.logoff();
        UID = FirebaseAuth.getInstance().getUid();
        assertNull(UID);
    }



     /**
     * Test will check if adding and removing a meal from the offered meals list is working correctly
     */
    @Test
    public void addingAndRemovingMealFromOfferedMealList_isCorrect(){
        MenuDatabase mD= new MenuDatabase();
        Meal meal1= new Meal("Tacos", "Tacos", "Mexican", "Tortilla, cheese, ground beef, lettuce, sour cream", "Soy, Corn, Beans", "Three soft shell Tacos", 12.99, true);
//      String mealID=FirebaseAuth.getInstance().getUID();
        mD.addMeal("7Ge4oqXDzBWrvL8CnWqudoR2c7m1", meal1);
        DatabaseReference offeredMealRef = FirebaseDatabase.getInstance().getReference("USERS").child("7Ge4oqXDzBWrvL8CnWqudoR2c7m1").getReference("MENU").getReference("Tacos").child("currentlyOffered");
        assertTrue(offeredMealRef);
        mD.setCurrentlyOffered(false);
        assertFalse(meal1.getCurrentlyOffered());
    }


         /**
     * Test will check if adding and removing a meal from the offered meals list is working correctly
     */
    @Test
    public void addingAndDeletingMeal_isCorrect(){
        MenuDatabase mD= new MenuDatabase();
        Meal meal2= new Meal("Butter chicken", "Butter chicken", "Indian", "Chicken, cream, butter, spices", "milk", "Butter chicken with a side of Naan bread", 19.99, true);
        String mealID=FirebaseAuth.getInstance().getUID();
        mD.addMeal(mealID,meal2);
        mD.setCurrentlyOffered(false);

        Database.retrieveListener readListener = new Database.retrieveListener() {
            @Override
            public void onDataReceived(Object data) {

                assertEquals(data.toString(), "false");
            }

            @Override
            public void onError() {

            }
        };

        mD.getInformation(FirebaseDatabase.getInstance().getReference("USERS").child(mealID).getReference("MENU").getReference("Tacos").child("currentlyOffered"), readListener);      
    }


    /**
     * Deletes the test meal created
     */
    @After
    public void deleteTestMeal() {
        MenuDatabase mD= new MenuDatabase();
        String mealID=FirebaseAuth.getInstance().getUID();
        mD.deleteMeal(mealID);
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
     * Deletes the test Cook created
     */
    /*@After public void deleteTestCook(){
        UsersDataBase dtb = new UsersDataBase();
        dtb.deleteUser(cook);
    }*/
}


