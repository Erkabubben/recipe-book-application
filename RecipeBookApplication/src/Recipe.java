import java.util.ArrayList;

public class Recipe {

    public String name;
    private double portions;
    private ArrayList<IngredientsListEntry> ingredients;
    private String[] instructions;

    public String GetRecipeAsString (double portionsAmount) { return ""; }
    public double GetTotalPrice(double portionsAmount) { return 0; }
    public void AddIngredientToList (IngredientsListEntry ingredient) {}
    public void DeleteIngredientFromList  (int ingredientListEntryID) {}
    public void InsertInstructionLine(String instructionLine, int atLine) {}
    public void DeleteInstructionLine(int atLine) {}
}
