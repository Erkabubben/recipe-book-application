import java.io.IOException;
import java.util.Scanner;
import java.util.TreeMap;

public class IngredientsData {

    private TreeMap<String, Ingredient> ingredients;

    public IngredientsData() {
        ingredients = new TreeMap<String, Ingredient>();
    }

    public String GetAllIngredients() {
        return "";
    }

    public String IngredientsToString() {

        String returnString = "";

        for (Ingredient i : ingredients.values()) {
            String s = "";
            s += i.name + ";";
            s += i.price + ";";
            s += i.unit + ";";

            returnString += s + "¤";
        }

        System.out.print(returnString);

        return returnString;
    }

    public void StringToIngredients() {

    }

    public void DeleteIngredient(String ingredientName) {}
    public void CreateIngredient(String name, float price, String unit) {}
}
