/**
 * Recipe
 * 
 * @version 1.0 17 Mar 2021
 * @author Erik Lindholm
 */
import java.util.ArrayList;
/**
 * A recipe for the RecipeBookApplication.
 */
public class Recipe {

    public String name;
    public double portions;
    public ArrayList<IngredientsListEntry> ingredients;
    public ArrayList<String> instructions;

    /* Constructor */
    public Recipe(String n) {
        name = n;
        portions = 0;
        ingredients = new ArrayList<IngredientsListEntry>();
        instructions = new ArrayList<String>();
    }

    /* Constructor */
    public Recipe(String n, double p, ArrayList<IngredientsListEntry> ingr, ArrayList<String> instr) {
        name = n;
        portions = p;
        ingredients = ingr;
        instructions = instr;
    }

    /* Returns the whole recipe as a string to be displayed to the user */
    public String GetRecipeAsString(double portionsAmount, PrettyPrints pp) {
        String s = pp.SurroundString(" " + name + " (" + portionsAmount + " portions) ", '¤');
        s += "\n\n" + pp.SurroundString(" Ingredients (total price: " + GetTotalPrice(portionsAmount) + ") ", '-') + "\n";
        s += GetIngredientsListAsString(portionsAmount, pp);
        s += "\n\n" + pp.SurroundString(" Instructions ", '-') + "\n";
        s += GetInstructionsAsString(pp);
        s += "\n\n" + pp.SurroundString("", '¤');
        return s;
    }

    public String GetRecipeAsString(PrettyPrints pp) {
        return GetRecipeAsString(portions, pp);
    }

    /* Returns the ingredients list as a string to be displayed to the user */
    public String GetIngredientsListAsString(double portionsAmount, PrettyPrints pp) {
        String s = "";
        for (IngredientsListEntry ingredientsListEntry : ingredients) {
            s += "\n" + pp.SurroundString(ingredientsListEntry.GetDetails(portionsAmount / portions), ' ');
        }
        return s;
    }

    /* Returns the instructions list as a string to be displayed to the user */
    public String GetInstructionsAsString(PrettyPrints pp) {
        String s = "";
        int id = 1;
        for (String instruction : instructions) {
            String instructionString = id + ") " + instruction;
            s += "\n" + pp.SurroundString(instructionString, ' ');
            id++;
        }
        return s;
    }

    /* Calculates and returns the total price of the ingredients of the recipe, based on the amount of portions */
    public double GetTotalPrice(double portionsAmount) {
        int total = 0;
        for (IngredientsListEntry ingredientsListEntry : ingredients) {
            total += (ingredientsListEntry.ingredient.price * (ingredientsListEntry.amount * portionsAmount / portions)) ;
        }
        return total;
    }

    /* Adds a new entry to the ingredients list */
    public void AddIngredientToList (IngredientsListEntry ingredient) {
        ingredients.add(ingredient);
    }

    /* Finds all instances of a specified ingredient in the ingredients list - returns an array
       with the list entry indexes */
    public ArrayList<Integer> FindIngredientsInList(String ingredientName) {
        ArrayList<Integer> results = new ArrayList<Integer>();
        for (int i = 0; i < ingredients.size(); i++) {
            IngredientsListEntry ingredientsListEntry = ingredients.get(i);
            if (ingredientsListEntry.ingredient.name.equals(ingredientName)) {
                results.add(i);
            }
        }
        return results;
    }

    /* Deletes an ingredient list entry */
    public void DeleteIngredientFromList (int index) {
        ingredients.remove(index);
    }

    /* Adds a string as an instruction to the instructions list */
    public void AddInstructionLine(String instructionLine, int atLine) {
        instructions.add(atLine, instructionLine); 
    }

    public void AddInstructionLine(String instructionLine) {
        instructions.add(instructionLine); 
    }

    /* Deletes the instruction line at the given index */
    public void DeleteInstructionLine(int atLine) {
        instructions.remove(atLine);
    }
}
