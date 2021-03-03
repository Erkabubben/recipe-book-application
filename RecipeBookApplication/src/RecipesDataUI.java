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
                ArrayList<Recipe> recipes = recipesData.GetAllRecipes();
                for (Recipe recipe : recipes) {
                    System.out.println(recipe.name);
                }
                prettyPrints.SurroundPrintln("");
                break;
            case 2:
                prettyPrints.SurroundPrintln(" CREATE NEW RECIPE ");
                UserInterface recipeEditorUI = new RecipeEditorUI(in, prettyPrints, ingredientsData);
                recipeEditorUI.Enter();
                break;
            case 3:

                break;
            default:
                break;
        }
    }
}
