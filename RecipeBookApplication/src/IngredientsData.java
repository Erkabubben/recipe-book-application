/**
 * IngredientsData
 * 
 * @version 1.0 17 Mar 2021
 * @author Erik Lindholm
 */
import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;
/**
 * The IngredientsData class contains all the user's stored ingredients. This class
 * only contains methods for managing the data - all interaction with the user
 * is managed by its corresponding UI class (IngredientsDataUI).
 */
public class IngredientsData {

    private TreeMap<String, Ingredient> ingredients;

    /* Constructor */
    public IngredientsData() {
        ingredients = new TreeMap<String, Ingredient>();
    }

    /* Returns a list of all ingredients along with their details to be displayed to the user */
    public ArrayList<String> GetListOfAllIngredients() {

        ArrayList<String> ingredientStrings = new ArrayList<String>();

        for (Map.Entry<String, Ingredient> entry : ingredients.entrySet()) {
            String s = entry.getValue().GetDetails();
            ingredientStrings.add(s);
        }

        return ingredientStrings;
    }

    /* Serializes the contents of the ingredients collection to a text string */
    public String IngredientsToString() {

        String returnString = "";

        for (Map.Entry<String, Ingredient> entry : ingredients.entrySet()) {

            Ingredient i = entry.getValue();

            String s = "";
            s += i.name + ";";
            s += i.price + ";";
            s += i.unit + ";";
            if (!i.divisible) s += String.valueOf(i.divisible) + ";";

            returnString += s + "\n";
        }

        return returnString;
    }

    /* Deserializes the contents of a string saved by IngredientsToString and adds the data to the ingredients collection */
    public void StringToIngredients(String inData) {
        String[] lines = inData.split("\n");
        for (String line : lines) {
            String[] elements = line.split(";");
            Ingredient i;
            if (elements.length == 4) {
                i = new Ingredient(elements[0], Double.parseDouble(elements[1]), elements[2], Boolean.parseBoolean(elements[3]));
            } else {
                i = new Ingredient(elements[0], Double.parseDouble(elements[1]), elements[2], true);
            }
            AddIngredient(i);
        }
    }

    /* Deletes an ingredient from the Ingredients collection - returns true if the ingredient
       was successfully deleted. */
    public boolean DeleteIngredient(String ingredientName) {
        if (ingredients.containsKey(ingredientName)) {
            ingredients.remove(ingredientName);
            return true;
        } else {
            return false;
        }
    }

    /* Adds an ingredient to the Ingredients collection */
    public void AddIngredient(Ingredient i) {
        ingredients.put(i.name, i);
    }

    /* Retrieves an ingredient from the Ingredients collection */
    public Ingredient GetIngredient(String ingredientName) {
        return ingredients.get(ingredientName);
    }
}
