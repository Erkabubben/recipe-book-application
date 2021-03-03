import java.util.ArrayList;

public class Recipe {

    public String name;
    public double portions;
    public ArrayList<IngredientsListEntry> ingredients;
    public ArrayList<String> instructions;

    public Recipe(String n) {
        name = n;
        portions = 0;
        ingredients = new ArrayList<IngredientsListEntry>();
        instructions = new ArrayList<String>();
    }

    public Recipe(String n, double p, ArrayList<IngredientsListEntry> ingr, ArrayList<String> instr) {
        name = n;
        portions = p;
        ingredients = ingr;
        instructions = instr;
    }

    public String GetRecipeAsString() {
        return GetRecipeAsString (portions);
    }

    public String GetRecipeAsString(double portionsAmount) {
        String s = name + " (" + portionsAmount + " portions)";
        s += "\n--- Ingredients ---";
        int id = 1;
        for (IngredientsListEntry ingredientsListEntry : ingredients) {
            s += "\n" + id + ") " + ingredientsListEntry.amount + " " + ingredientsListEntry.ingredient.unit + "(s) of " + ingredientsListEntry.ingredient.name;
            id++;
        }
        s += "\n--- Instructions ---";
        id = 1;
        for (String instruction : instructions) {
            s += "\n" + id + ") " + instruction;
            id++;
        }
        s += "\n----------------------";
        
        return s;
    }

    public double GetTotalPrice(double portionsAmount) { return 0; }

    public void AddIngredientToList (IngredientsListEntry ingredient) {
        ingredients.add(ingredient);
    }

    public void DeleteIngredientFromList  (int ingredientListEntryID) {

    }

    public void InsertInstructionLine(String instructionLine, int atLine) {}

    public void DeleteInstructionLine(int atLine) {}
}
