private Meal meal;

    @Before
    public void createMeal(){
        Meal meal = new Meal("chicken", "main course", "western","chicken", "none",
                "chicken", 10.50,true);
    }

    @Test
    public void getAndSetCurrentlyOffered_isCorrect(){

        assertTrue(meal.getCurrentlyOffered());

        meal.setCurrentlyOffered(false);

        assertFalse(meal.getCurrentlyOffered());
    }