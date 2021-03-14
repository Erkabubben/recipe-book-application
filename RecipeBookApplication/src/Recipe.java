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
        s += GetIngredientsAsString(portionsAmount);
        s += "\n--- Instructions ---";
        s += GetInstructionsAsString();
        s += "\n----------------------";
        return s;
    }

    public String GetIngredientsAsString(double portionsAmount) {
        String s = "";
        int id = 1;
        for (IngredientsListEntry ingredientsListEntry : ingredients) {
            s += "\n" + id + ") " + (ingredientsListEntry.amount * (portionsAmount / portions)) + " " + ingredientsListEntry.ingredient.unit + "(s) of " + ingredientsListEntry.ingredient.name;
            id++;
        }
        return s;
    }

    public String GetInstructionsAsString() {
        String s = "";
        int id = 1;
        for (String instruction : instructions) {
            s += "\n" + id + ") " + instruction;
            id++;
        }
        return s;
    }

    public double GetTotalPrice(double portionsAmount) {
        int total = 0;
        for (IngredientsListEntry ingredientsListEntry : ingredients) {
            total += (ingredientsListEntry.ingredient.price * (ingredientsListEntry.amount * portionsAmount / portions)) ;
        }
        return total;
    }

    public void AddIngredientToList (IngredientsListEntry ingredient) {
        ingredients.add(ingredient);
    }

    public boolean DeleteIngredientFromList (String ingredientName) {
        for (int i = 0; i < ingredients.size(); i++) {
            IngredientsListEntry ingredientsListEntry = ingredients.get(i);
            if (ingredientsListEntry.ingredient.name.equals(ingredientName)) {
                ingredients.remove(i);
                return true;
            }
        }
        return false;
    }

    public void AddInstructionLine(String instructionLine) {
        instructions.add(instructionLine); 
    }

    public void AddInstructionLine(String instructionLine, int atLine) {
        instructions.add(atLine, instructionLine); 
    }

    public void DeleteInstructionLine(int atLine) {

    }
}
