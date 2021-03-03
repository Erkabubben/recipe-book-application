import java.util.Scanner;
import java.util.ArrayList;

public class RecipesDataUI extends UserInterface {

    private Scanner in;
    private PrettyPrints prettyPrints;
    private RecipesData recipesData;
    private IngredientsData ingredientsData;

    public RecipesDataUI(Scanner scanner, PrettyPrints pp, RecipesData rcpData, IngredientsData ingrData) {
        in = scanner;
        prettyPrints = pp;
        recipesData = rcpData;
        ingredientsData = ingrData;
    }

    public String[] Choices() {
        return new String[]{
            "Get All Recipes",
            "Create New Recipe",
            "Search Recipes",
            "Delete Recipes"
        };
    }

    public void MakeChoice(int choice) {
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
                int amountOfChoices = UserInterface.PrintChoices(recipeEditorUI.Choices());
                break;
            case 3:
                /*System.out.print("Enter the name of the ingredient you want to delete: ");
                String ingredient = in.next();
                boolean ingredientWasDeleted = ingredientsData.DeleteIngredient(ingredient);
                if (ingredientWasDeleted) {
                    System.out.println(ingredient + " was successfully deleted from the list of available ingredients.");
                } else {
                    System.out.println(ingredient + " could not be found - did you already delete it?");
                }*/
                break;
            default:
                break;
        }
    }
}
