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

    protected int Main() {
        DisplayMenu("RECIPES");
        return DisplayInputRequest();
    }

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

    private void PrintRecipesList() {
        prettyPrints.SurroundPrintln(" Stored Recipes ", '-');
        ArrayList<String> recipes = recipesData.GetAllRecipeNames();
        for (String string : recipes) {
            System.out.println(string);
        }
        prettyPrints.SurroundPrintln("", '-');
    }

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

    private void Search() {
        UserInterface recipeSearchUI = new RecipeSearchUI(validIn, prettyPrints, recipesData);
        recipeSearchUI.Enter();
    }

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
