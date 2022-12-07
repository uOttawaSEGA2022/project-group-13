package com.example.mealerapp;

import android.content.Context;
import android.util.Log;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
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


    /*This test checks if Suspending a cook is working properly*/
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


    /*This test checks if setting and getting information is working properly (in this case the info is the user's address)*/
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

    /*This test checks if a user (either Client or Cook) can succesfuly login and logoff (does so by checking if UID is not null once logged in and null once logged off)*/
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



     /*
     * Test will check if adding and removing a meal from the offered meals list is working correctly
     */
    @Test
    public void addingAndRemovingMealFromOfferedMealList_isCorrect(){
        MenuDatabase mD= new MenuDatabase();
        Meal meal1= new Meal("Tacos", "Tacos", "Mexican", "Tortilla, cheese, ground beef, lettuce, sour cream", "Soy, Corn, Beans", "Three soft shell Tacos", 12.99, true);
        mD.addMeal("7Ge4oqXDzBWrvL8CnWqudoR2c7m1", meal1);
        DatabaseReference offeredMealRef = FirebaseDatabase.getInstance().getReference("USERS").child("7Ge4oqXDzBWrvL8CnWqudoR2c7m1").child("MENU").child("Tacos").child("currentlyOffered");
        assertNotNull(offeredMealRef);
        mD.setCurrentlyOffered("7Ge4oqXDzBWrvL8CnWqudoR2c7m1", meal1,false);

        Database.retrieveListener listener = new Database.retrieveListener() {
            @Override
            public void onDataReceived(Object data) {
                assertFalse(Boolean.valueOf(data.toString()));
            }

            @Override
            public void onError() {

            }
        };

        mD.getInformation(FirebaseDatabase.getInstance().getReference("USERS").child("7Ge4oqXDzBWrvL8CnWqudoR2c7m1").child("MENU").child("Tacos").child("currentlyOffered"), listener);
    }


    /*This test checks creating a meal, adding it to the database, and deleting it from the database is working properly */
    @Test
    public void addingAndDeletingMeal_isCorrect(){

        MenuDatabase mD= new MenuDatabase();
        Meal meal2= new Meal("Butter chicken", "Butter chicken", "Indian", "Chicken, cream, butter, spices", "milk", "Butter chicken with a side of Naan bread", 19.99, true);
        mD.addMeal("7Ge4oqXDzBWrvL8CnWqudoR2c7m1",meal2);

        Database.retrieveListener readListener = new Database.retrieveListener() {
            @Override
            public void onDataReceived(Object data) {

                assertNotNull(data);

            }

            @Override
            public void onError() {

            }
        };

        mD.getInformation(FirebaseDatabase.getInstance().getReference("USERS").child("7Ge4oqXDzBWrvL8CnWqudoR2c7m1").child("MENU").child("Butter chicken"), readListener);

        mD.deleteMeal("7Ge4oqXDzBWrvL8CnWqudoR2c7m1", "Butter chicken");

        Database.retrieveListener listener = new Database.retrieveListener() {
            @Override
            public void onDataReceived(Object data) {
                assertTrue((boolean)data);
            }

            @Override
            public void onError() {

            }
        };

       mD.checkDeleted("7Ge4oqXDzBWrvL8CnWqudoR2c7m1", "Butter chicken", listener);

    }


     /*This test checks if creating and adding a purchase request to the database is working properly*/
     @Test
     public void creatingAndAddingRequest_isCorrect(){
         RequestDatabase rD= new RequestDatabase();
         PurchaseRequest request= new PurchaseRequest("7Ge4oqXDzBWrvL8CnWqudoR2c7m1", "HmJm4S0TtwZAllFDa4onUMSaCpw2","grilled cheese",PENDING,"December 7 2022");
         rD.addRequest(request);

         Database.retrieveListener readListener = new Database.retrieveListener() {
            @Override
            public void onDataReceived(Object data) {

                assertNotNull(data);

            }

            @Override
            public void onError() {

            }
        };

        mD.getInformation(FirebaseDatabase.getInstance().getReference("USERS").child("7Ge4oqXDzBWrvL8CnWqudoR2c7m1").child("REQUESTS").child(request.getClientUID())., readListener);
     }


    /*This test checks if creating a request and then changing its status from PENDING to APPROVED is working*/
     @Test
     public void creatingRequestAndChangingStatus_isCorrect(){
        RequestDatabase rD= new RequestDatabase();
        PurchaseRequest request= new PurchaseRequest("7Ge4oqXDzBWrvL8CnWqudoR2c7m1", "mdpVvIy1BPhn8QQ4A09FYLtiDzB3","water",PENDING,"December 8 2022");
        rD.addRequest(request);
        DatabaseReference requestStatus = FirebaseDatabase.getInstance().getReference("USERS").child("7Ge4oqXDzBWrvL8CnWqudoR2c7m1").child("REQUESTS").child("mdpVvIy1BPhn8QQ4A09FYLtiDzB3").child("status");
        assertEquals(requestStatus,PENDING);
        rD.setApproved(APPROVED);
        assertEquals(requestStatus,APPROVED);
     }
     

     /*This class checks if attributes of the Meal are the same as the fields within the database*/
     @Test
     public void attributesOfMeal_isCorrect(){
        MenuDatabase mD= new MenuDatabase();
        Meal food= new Meal("Sushi", "Sushi", "Japanese", "Rice, fish, SeaWeed", "Fish and SeaFood", "3 sushi rolls", 14.99, true);
        mD.addMeal("7Ge4oqXDzBWrvL8CnWqudoR2c7m1",food);
        DatabaseReference foodPrice = FirebaseDatabase.getInstance().getReference("USERS").child("7Ge4oqXDzBWrvL8CnWqudoR2c7m1").child("MENU").child("price");
        DatabaseReference foodCuisine = FirebaseDatabase.getInstance().getReference("USERS").child("7Ge4oqXDzBWrvL8CnWqudoR2c7m1").child("MENU").child("cuisine");
        DatabaseReference foodAllergens = FirebaseDatabase.getInstance().getReference("USERS").child("7Ge4oqXDzBWrvL8CnWqudoR2c7m1").child("MENU").child("allergens");
        DatabaseReference foodIngredients = FirebaseDatabase.getInstance().getReference("USERS").child("7Ge4oqXDzBWrvL8CnWqudoR2c7m1").child("MENU").child("ingredients");
        DatabaseReference foodDescription = FirebaseDatabase.getInstance().getReference("USERS").child("7Ge4oqXDzBWrvL8CnWqudoR2c7m1").child("MENU").child("description");
        DatabaseReference foodCurrOff = FirebaseDatabase.getInstance().getReference("USERS").child("7Ge4oqXDzBWrvL8CnWqudoR2c7m1").child("MENU").child("currentlyOffered");
        DatabaseReference foodName = FirebaseDatabase.getInstance().getReference("USERS").child("7Ge4oqXDzBWrvL8CnWqudoR2c7m1").child("MENU").child("name");
        DatabaseReference foodMealType = FirebaseDatabase.getInstance().getReference("USERS").child("7Ge4oqXDzBWrvL8CnWqudoR2c7m1").child("MENU").child("Sushi");
        assertEquals(foodPrice,food.getPrice());
        assertEquals(foodCuisine,food.getCuisine());
        assertEquals(foodAllergens,food.getAllergens());
        assertEquals(foodIngredients,food.getIngredients());
        assertEquals(foodDescription,food.getDescription());
        assertEquals(foodCurrOff,food.getCurrentlyOffered());
        assertEquals(foodName,food.getName());
        assertEquals(foodMealType,food.getMealType());
    }

     @Test
     public void addingAndDeletingMeal_isCorrect(){}

/* Deletes the test meal created
    @After
    public void deleteTestMeal() {
        MenuDatabase mD= new MenuDatabase();
        String mealID=FirebaseAuth.getInstance().getUID();
        mD.deleteMeal(mealID);
    }

*/

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


