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

    public String GetRecipeAsString (double portionsAmount) { return ""; }
    public double GetTotalPrice(double portionsAmount) { return 0; }

    public void AddIngredientToList (IngredientsListEntry ingredient) {
        ingredients.add(ingredient);
    }

    public void DeleteIngredientFromList  (int ingredientListEntryID) {}
    public void InsertInstructionLine(String instructionLine, int atLine) {}
    public void DeleteInstructionLine(int atLine) {}
}
