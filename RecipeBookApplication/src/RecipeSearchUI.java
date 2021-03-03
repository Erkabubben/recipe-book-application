import java.util.Scanner;
import java.util.ArrayList;

public class RecipeSearchUI extends UserInterface {

    private IngredientsData ingredientsData;
    private RecipesData recipesData;

    public RecipeSearchUI(Scanner scanner, PrettyPrints pp, RecipesData rcpData, IngredientsData ingrData) {
        super(scanner, pp);
        ingredientsData = ingrData;
        recipesData = rcpData;
    }

    public int Main() {
        System.out.println("SEARCH RECIPES: Please choose a search method.");
        System.out.println("");
        int amountOfChoices = PrintChoices();
        System.out.println((amountOfChoices + 1) + ". Exit");
        System.out.print("\nPlease enter a number: ");

        return in.nextInt();
    }

    public String[] Choices() {
        return new String[]{
            "Name Begins With",
            "Contains Ingredient",
        };
    }

    public void OnChoice(int choice) {
        switch (choice) {
            case 1:
                DisplaySearchResults(new SearchByNameBegins(in));
                break;
            case 2:
                DisplaySearchResults(new SearchByContainsIngredient(in));
                break;
            default:
                break;
        }
    }

    private void DisplaySearchResults(ISearchStrategy searchStrategy) {
        ArrayList<Recipe> results = searchStrategy.GetSearchResults(recipesData.GetAllRecipes());
        if (results.size() > 0) {
            System.out.println("Search Results:");
            for (Recipe recipe : results) {
                System.out.println(recipe.name);
            }
        } else {
            System.out.println("The search gave no results.");
        }
    }
}
