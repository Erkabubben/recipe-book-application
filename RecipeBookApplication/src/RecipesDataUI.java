/**
 * RecipesDataUI
 * 
 * @version 1.0 17 Mar 2021
 * @author Erik Lindholm
 */
import java.util.ArrayList;
/**
 * The RecipesDataUI manages all interaction between the user and the RecipesData.
 */
public class RecipesDataUI extends UserInterface {

    private RecipesData recipesData;
    private IngredientsData ingredientsData;

    /* Constructor */
    public RecipesDataUI(ValidateInput vi, PrettyPrints pp, RecipesData rcpData, IngredientsData ingrData) {
        super(vi, pp);
        recipesData = rcpData;
        ingredientsData = ingrData;
    }

    /* Returns the title of the UI class */
    protected String Title() { return "Recipes"; }

    /* Returns a String array containing the different options that will be displayed to the user */
    protected String[] Choices() {
        return new String[]{
            "List All Recipes",
            "Show Recipe",
            "Create New Recipe",
            "Edit Recipe",
            "Search Recipes",
            "Delete Recipe"
        };
    }

    /* Contains a Switch statement that will trigger code based on what menu option the user has selected */
    protected void OnChoice(int choice) {
        switch (choice) {
            case 1:
                PrintRecipesList();
                break;
            case 2:
                ShowRecipe();
                break;
            case 3:
                CreateNewRecipe();
                break;
            case 4:
                EditRecipe();
                break;
            case 5:
                Search();
                break;
            case 6:
                DeleteRecipe();
                break;
            default:
                break;
        }
    }

    /* Prints a list of all recipes in RecipesData */
    private void PrintRecipesList() {
        prettyPrints.SurroundPrintln(" Stored Recipes ", '-');
        ArrayList<String> recipes = recipesData.GetAllRecipeNames();
        for (String string : recipes) {
            System.out.println(string);
        }
        prettyPrints.SurroundPrintln("", '-');
    }

    /* Displays the user interface for printing a recipe to the console */
    private void ShowRecipe() {
        String recipeName = validIn.nextLine("Enter the name of the recipe you want to show (leave empty to exit): ");
        if (recipeName != null && !recipeName.isBlank()) {
            Recipe recipe = recipesData.GetRecipe(recipeName);
            double portions = validIn.nextDoubleInRange("Enter amount of portions (0 to use recipe default): ", 0.0, 9999999);
            if (portions == 0) {
                prettyPrints.Println(recipe.GetRecipeAsString(prettyPrints));
            } else {
                prettyPrints.Println(recipe.GetRecipeAsString(portions, prettyPrints));
            }
        }
    }

    /* Displays the user interface for creating a new recipe - enters the RecipeEditorUI. */
    private void CreateNewRecipe() {
        prettyPrints.SurroundPrintln(" Create New Recipe ", '-');
        Recipe createRecipe = new Recipe("");
        UserInterface recipeEditorUI = new RecipeEditorUI(validIn, prettyPrints, createRecipe, ingredientsData, recipesData);
        recipeEditorUI.Enter();
        if (!createRecipe.name.isBlank()) {
            prettyPrints.SurroundPrintln(" A new recipe was added: " + createRecipe.name + " ", '-');
            recipesData.AddRecipe(createRecipe);
        } else {
            prettyPrints.SurroundPrintln(" No new recipe was added. ", '-');
        }
    }

    /* Displays the user interface for editing a recipe - enters the RecipeEditorUI. */
    private void EditRecipe() {
        System.out.print("Enter the name of the recipe you want to edit (leave empty to exit): ");
        String recipeName = validIn.nextLine();
        Recipe recipe = recipesData.GetRecipe(recipeName);
        if (recipe != null) {
            UserInterface recipeEditorUI = new RecipeEditorUI(validIn, prettyPrints, recipe, ingredientsData, recipesData);
            recipeEditorUI.Enter();
        } else {
            System.out.println("No recipe by name \"" + recipeName + "\" could be found.");
        }
    }

    /* Displays the user interface for making a recipe search - enters the RecipeSearchUI. */
    private void Search() {
        UserInterface recipeSearchUI = new RecipeSearchUI(validIn, prettyPrints, recipesData);
        recipeSearchUI.Enter();
    }

    /* Displays the user interface for deleting a recipe. */
    private void DeleteRecipe() {
        System.out.print("Enter the name of the recipe you want to delete (leave empty to exit): ");
        String recipeName = validIn.nextLine();
        if (recipesData.DeleteRecipe(recipeName)) {
            System.out.println("\"" + recipeName + "\" was deleted from Recipe Book.");
        } else {
            System.out.println("No recipe by name \"" + recipeName + "\" could be found.");
        }
    }
}
