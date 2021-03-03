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
        int amountOfChoices = PrintChoices(this);
        System.out.println((amountOfChoices + 1) + ". Exit");
        System.out.print("\nPlease enter a number: ");

        return in.nextInt();
    }

    public String[] Choices() {
        return new String[]{
            "Get All Recipes",
            "Create New Recipe",
            "Search Recipes",
            "Delete Recipes"
        };
    }

    public void OnChoice(int choice) {
        switch (choice) {
            case 1:
                prettyPrints.SurroundPrintln(" RECIPES ");
                ArrayList<String> recipes = recipesData.GetAllRecipes();
                for (String string : recipes) {
                    System.out.println(string);
                }
                prettyPrints.SurroundPrintln("");
                break;
            case 2:
                prettyPrints.SurroundPrintln(" CREATE NEW RECIPE ");
                Recipe recipe = new Recipe("");
                UserInterface recipeEditorUI = new RecipeEditorUI(in, prettyPrints, recipe, ingredientsData);
                recipeEditorUI.Enter();
                if (recipe.name != "") {
                    prettyPrints.SurroundPrintln(" A new recipe was added: " + recipe.name + " ");
                    recipesData.AddRecipe(recipe);
                } else {
                    System.out.println("No new recipe was added.");
                }
                break;
            case 3:

                break;
            default:
                break;
        }
    }
}
