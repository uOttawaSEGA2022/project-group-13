package com.example.mealerapp;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

import android.content.Context;
import android.service.autofill.UserData;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    /**
     * Test will check if suspend and lifting a suspension on a cook is working correctly
     */

    // Testing for email format
    @Test
    public void checkCorrectEmail(){
        assertTrue(CookRegistration.checkEmailFormat("Testing@gmail.com"));
    }

    @Test
    public void checkNoAt(){
        assertFalse(CookRegistration.checkEmailFormat("Testinggmail.com"));
    }

    @Test
    public void checkNoDotCOM(){
        assertFalse(CookRegistration.checkEmailFormat("Testing@gmail"));
    }

    @Test
    public void checkNoEmail(){
        assertFalse(CookRegistration.checkEmailFormat(""));
    }

    @Test
    public void checkNoAtandDotCOM(){
        assertFalse(CookRegistration.checkEmailFormat("testing"));
    }


}