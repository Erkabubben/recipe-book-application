import java.util.Map;
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
            if (!i.divisible) s += String.valueOf(i.divisible) + ";";

            returnString += s + "\n";
        }

        return returnString;
    }

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

    public boolean DeleteIngredient(String ingredientName) {
        if (ingredients.containsKey(ingredientName)) {
            ingredients.remove(ingredientName);
            return true;
        } else {
            return false;
        }
    }

    public void AddIngredient(Ingredient i) {
        ingredients.put(i.name, i);
    }

    public Ingredient GetIngredient(String ingredientName) {
        return ingredients.get(ingredientName);
    }
}
