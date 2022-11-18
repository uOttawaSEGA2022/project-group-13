



import android.widget.CompoundButton;
import org.junit.Test;
import org.junit.Assert.*;

// Whta small unit tests may look like



public class testCook{
    @Test
    R Cook("Joel", "Tar" , "JoelTar@gmail.com", "Password", "334 Iberville" , "Likes Baking")= new Cook;
    public void testCookFisrtName(){ assertEquals("Joel", R.getFirstName());}
    public void testCookLastName(){ assertEquals("Tar", R.getLastName());}
    public void testEmail(){ assertEquals("JoelTar@gmail.com", R.getEmail());}
    public void testPassword(){ assertEquals("Password", R.getPassword());}
    public void testAdress(){ assertEquals("334 Iberville", R.getAddress());}
    public void testDescription(){ assertEquals("Likes Baking", R.getDescription());}
    




}
public class testMeal{
    @Test
    Pancake meal("pancake", "breakfast", "British","Milk, Flour, Water, Sugar", "Dairy", "fluffy pancakes", 11.50, True) = new meal();
    public void testMealName(){ assertEquals("pancake", Pancake.getName());}
    public void testMealType(){ assertEquals("breakfast", Pancake.getMealType());}
    public void testCuisine(){ assertEquals("British", Pancake.getCuisine());}
    public void testIngredients(){ assertEquals("Milk, Flour, Water, Sugar", "Dairy", Pancake.getIngredients());}
    public void testAllergen(){ assertEquals("Dairy", Pancake.getAllergens());}
    public void testDescription(){ assertEquals("fluffy pancakes", Pancake.getDescription());}
    public void testPrice(){ assertEquals(11.50, Pancake.getPrice());}
    public void testOffered(){ assertEquals(True, Pancake.getCurrentlyOffered());}


    


}
public class testAddMeal{
    @Test
    R Cook("Joel", "Tar" , "JoelTar@gmail.com", "Password", "334 Iberville" , "Likes Baking")= new Cook;
    Pancake meal("pancake", "breakfast", "British","Milk, Flour, Water, Sugar", "Dairy", "fluffy pancakes", 11.50, True) = new meal();
    addMeal(R, Pancake);
    public void testAddedMeal(){ assertEquals("pancake", R.Pancake.getName());} // woule probably need something different in the assert equals paramter




}

