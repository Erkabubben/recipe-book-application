import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.ArrayList;

public class IngredientsData {

    private TreeMap<String, Ingredient> ingredients;

    public IngredientsData() {
        ingredients = new TreeMap<String, Ingredient>();
    }

    public ArrayList<String> GetAllIngredients() {

        ArrayList<String> ingredientStrings = new ArrayList<String>();

        int id = 1;
        for (Map.Entry<String, Ingredient> entry : ingredients.entrySet()) {
            String s = entry.getValue().GetDetails();
            ingredientStrings.add(s);
            id++;
        }

        return ingredientStrings;
    }

    public String IngredientsToString() {

        String returnString = "";

        for (Map.Entry<String, Ingredient> entry : ingredients.entrySet()) {

            Ingredient i = entry.getValue();

            String s = "";
            s += i.name + ";";
            s += i.price + ";";
            s += i.unit + ";";

            returnString += s + "\n";
        }

        return returnString;
    }

    public void StringToIngredients(String inData) {
        String[] lines = inData.split("\n");
        for (String line : lines) {
            String[] elements = line.split(";");
            CreateIngredient(elements[0], Double.parseDouble(elements[1]), elements[2]);
        }
    }

    public boolean DeleteIngredient(String ingredientName) {
        if (ingredients.containsKey(ingredientName)) {
            ingredients.remove(ingredientName);
            return true;
        } else {
            return false;
        }
    }

    public Ingredient CreateIngredient(String name, double price, String unit) {
        Ingredient i = new Ingredient(name, price, unit);
        ingredients.put(name, i);
        return i;
    }
}
