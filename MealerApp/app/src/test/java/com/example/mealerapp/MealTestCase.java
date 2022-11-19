package com.example.mealerapp;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class MealTestCase {

    private Meal meal;

    @Before
    public void createMeal(){
        meal = new Meal("chicken", "main course", "western","chicken", "none",
                "chicken", 10.50,true);
    }

    @Test
    public void getAndSetCurrentlyOffered_isCorrect(){

        Meal meal = new Meal("chicken", "main course", "western","chicken", "none",
                "chicken", 10.50,true);

        assertTrue(meal.getCurrentlyOffered());

        meal.setCurrentlyOffered(false);

        assertFalse(meal.getCurrentlyOffered());
    }
}
