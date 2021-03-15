import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class RecipesData {

    private TreeMap<String, Recipe> recipes;
    private IngredientsData ingredientsData;

    private String mainDelimiter = "\n";
    private String delimiter1 = ";";
    private String delimiter2 = "_";
    private String delimiter3 = "@";

    public RecipesData(IngredientsData ingrData) {
        recipes = new TreeMap<String, Recipe>();
        ingredientsData = ingrData;
    }

    public ArrayList<Recipe> GetAllRecipes() {
        ArrayList<Recipe> recipeList = new ArrayList<Recipe>();
        int id = 1;
        for (Map.Entry<String, Recipe> entry : recipes.entrySet()) {
            recipeList.add(entry.getValue());
            id++;
        }
        return recipeList;
    }

    public ArrayList<String> GetAllRecipeNames() {
        ArrayList<String> recipeStrings = new ArrayList<String>();
        int id = 1;
        for (Map.Entry<String, Recipe> entry : recipes.entrySet()) {
            String s = entry.getValue().name;
            recipeStrings.add(s);
            id++;
        }
        return recipeStrings;
    }

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

    public void StringToRecipes(String inData) {
        String[] lines = inData.split(mainDelimiter);    // Split string into lines
        for (String line : lines) {    // Iterate lines
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
                // Create Recipe object and add to RecipeData
                Recipe r = new Recipe(elements[0], Double.parseDouble(elements[1]), ingredientList, instructionsList);
                AddRecipe(r);
            }
        }
    }

    public Recipe GetRecipe(String recipeName) {
        return recipes.get(recipeName);
    }

    public void AddRecipe(Recipe recipe) {
        recipes.put(recipe.name, recipe);
    }
    
    public ArrayList<Recipe> Search(ISearchStrategy searchStrategies, ArrayList<Recipe> recipes) {
        return new ArrayList<Recipe>();
    }

    public boolean DeleteRecipe(String recipeName) {
        if (recipes.containsKey(recipeName)) {
            recipes.remove(recipeName);
            return true;
        } else {
            return false;
        }
    }
}
