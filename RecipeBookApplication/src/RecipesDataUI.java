import java.util.Scanner;
import java.util.ArrayList;

public class RecipesDataUI extends UserInterface {

    private RecipesData recipesData;
    private IngredientsData ingredientsData;

    public RecipesDataUI(Scanner scanner, PrettyPrints pp, RecipesData rcpData, IngredientsData ingrData) {
        super(scanner, pp);
        recipesData = rcpData;
        ingredientsData = ingrData;
    }

    public int Main() {
        System.out.println("RECIPES: What would you like to do?");
        System.out.println("");
        int amountOfChoices = PrintChoices();
        System.out.println((amountOfChoices + 1) + ". Exit");
        System.out.print("\nPlease enter a number: ");

        return in.nextInt();
    }

    public String[] Choices() {
        return new String[]{
            "List All Recipes",
            "Create New Recipe",
            "Edit Recipe",
            "Search Recipes",
            "Delete Recipes"
        };
    }

    public void OnChoice(int choice) {
        switch (choice) {
            case 1:
                PrintRecipesList();
                break;
            case 2:
                CreateNewRecipe();
                break;
            case 3:
                EditRecipe();
                break;
            case 4:
                Search();
                break;
            case 5:
                DeleteRecipe();
                break;
            default:
                break;
        }
    }

    private void PrintRecipesList() {
        prettyPrints.SurroundPrintln(" RECIPES ");
        ArrayList<String> recipes = recipesData.GetAllRecipeNames();
        for (String string : recipes) {
            System.out.println(string);
        }
        prettyPrints.SurroundPrintln("");
    }

    private void CreateNewRecipe() {
        prettyPrints.SurroundPrintln(" CREATE NEW RECIPE ");
        Recipe createRecipe = new Recipe("");
        UserInterface recipeEditorUI = new RecipeEditorUI(in, prettyPrints, createRecipe, ingredientsData);
        recipeEditorUI.Enter();
        if (createRecipe.name != "") {
            prettyPrints.SurroundPrintln(" A new recipe was added: " + createRecipe.name + " ");
            recipesData.AddRecipe(createRecipe);
        } else {
            System.out.println("No new recipe was added.");
        }
    }

    private void EditRecipe() {
        System.out.print("Enter the name of the recipe you want to edit (leave empty to exit): ");
        in = new Scanner(System.in);
        String recipeName = in.nextLine();
        Recipe recipe = recipesData.GetRecipe(recipeName);
        if (recipe != null) {
            UserInterface recipeEditorUI = new RecipeEditorUI(in, prettyPrints, recipe, ingredientsData);
            recipeEditorUI.Enter();
        } else {
            System.out.println("No recipe by name \"" + recipeName + "\" could be found.");
        }
    }

    private void Search() {
        UserInterface recipeSearchUI = new RecipeSearchUI(in, prettyPrints, recipesData, ingredientsData);
        recipeSearchUI.Enter();
    }

    private void DeleteRecipe() {
        System.out.print("Enter the name of the recipe you want to delete (leave empty to exit): ");
        in = new Scanner(System.in);
        String recipeName = in.nextLine();
        //Recipe recipe = recipesData.GetRecipe(recipeName);
        if (recipesData.DeleteRecipe(recipeName)) {
            System.out.println("\"" + recipeName + "\" was deleted from Recipe Book.");
        } else {
            System.out.println("No recipe by name \"" + recipeName + "\" could be found.");
        }
    }
}
