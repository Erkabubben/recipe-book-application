/**
 * RecipesData
 * 
 * @version 1.0 17 Mar 2021
 * @author Erik Lindholm
 */
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
/**
 * The RecipesData class contains all the user's stored recipes. This class
 * only contains methods for managing the data - all interaction with the user
 * is managed by its corresponding UI class (RecipesDataUI).
 */
public class RecipesData {

    private TreeMap<String, Recipe> recipes;
    private IngredientsData ingredientsData;

    private String mainDelimiter = "\n";
    private String delimiter1 = ";";
    private String delimiter2 = "_";
    private String delimiter3 = "@";

    /* Constructor */
    public RecipesData(IngredientsData ingrData) {
        recipes = new TreeMap<String, Recipe>();
        ingredientsData = ingrData;
    }

    /* Returns an alphabetically ordered ArrayList list containing all recipes in the recipes collection. */
    public ArrayList<Recipe> GetAllRecipes() {
        ArrayList<Recipe> recipeList = new ArrayList<Recipe>();
        for (Map.Entry<String, Recipe> entry : recipes.entrySet()) {
            recipeList.add(entry.getValue());
        }
        return recipeList;
    }

    /* Returns an ArrayList with the names of all recipes. */
    public ArrayList<String> GetAllRecipeNames() {
        ArrayList<String> recipeStrings = new ArrayList<String>();
        for (Map.Entry<String, Recipe> entry : recipes.entrySet()) {
            String s = entry.getValue().name;
            recipeStrings.add(s);
        }
        return recipeStrings;
    }

    /* Serializes the contents of the recipes collection to a text string. */
    public String RecipesToString() {

        String returnString = "";

        for (Map.Entry<String, Recipe> entry : recipes.entrySet()) {

            Recipe r = entry.getValue();

            String s = "";
            s += r.name + delimiter1;
            s += r.portions + delimiter1;
            for (IngredientsListEntry ingredientsListEntry : r.ingredients) {
                s += ingredientsListEntry.ingredient.name + delimiter3;
                s += ingredientsListEntry.amount + delimiter3;
                s += ingredientsListEntry.comment + delimiter3;
                s += delimiter2;
            }
            s += delimiter1;
            for (String instruction : r.instructions) {
                s += instruction + delimiter2;
            }
            s += delimiter1;

            returnString += s + mainDelimiter;
        }

        return returnString;
    }

    /* Deserializes the contents of a string saved by RecipesToString and adds the data to the recipes collection. */
    public void StringToRecipes(String inData) {
        String[] lines = inData.split(mainDelimiter); // Split string into lines
        for (String line : lines) { // Iterate lines
            if (!line.equals("")) {
                // Parse line into name, portions, ingredients list and instructions list
                String[] elements = line.split(delimiter1, -1);
                // Parse ingredients list
                String[] ingredients = elements[2].split(delimiter2, -1);
                ArrayList<IngredientsListEntry> ingredientList = new ArrayList<IngredientsListEntry>();
                for (String ingredient : ingredients) {
                    if (!ingredient.equals("")) {
                        String[] ingredientsListEntry = ingredient.split(delimiter3, -1);
                        if (ingredientsListEntry.length > 1) {
                            ingredientList.add(new IngredientsListEntry(
                                ingredientsData.GetIngredient(ingredientsListEntry[0]),
                                Double.parseDouble(ingredientsListEntry[1]),
                                ingredientsListEntry[2]
                                )
                            );
                        }
                    }
                }
                // Parse instructions list
                String[] instructions = elements[3].split(delimiter2, -1);
                ArrayList<String> instructionsList = new ArrayList<String>();
                for (String instruction : instructions) {
                    if (instruction != null && !instruction.isBlank()) {
                        instructionsList.add(instruction);
                    }
                }
                // Create Recipe object and add to recipes collection
                Recipe r = new Recipe(elements[0], Double.parseDouble(elements[1]), ingredientList, instructionsList);
                AddRecipe(r);
            }
        }
    }

    /* Returns a recipe from the recipes collection. */
    public Recipe GetRecipe(String recipeName) {
        return recipes.get(recipeName);
    }

    /* Adds a recipe to the recipes collection. */
    public void AddRecipe(Recipe recipe) {
        recipes.put(recipe.name, recipe);
    }

    /* Deletes the recipe matching the given name, if one is found in the recipes collection.
       Returns true if successful and false otherwise. */
    public boolean DeleteRecipe(String recipeName) {
        if (recipes.containsKey(recipeName)) {
            recipes.remove(recipeName);
            return true;
        } else {
            return false;
        }
    }
}
