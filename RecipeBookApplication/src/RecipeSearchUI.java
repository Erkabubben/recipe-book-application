import java.util.ArrayList;

public class RecipeSearchUI extends UserInterface {

    private IngredientsData ingredientsData;
    private RecipesData recipesData;

    public RecipeSearchUI(ValidateInput vi, PrettyPrints pp, RecipesData rcpData, IngredientsData ingrData) {
        super(vi, pp);
        ingredientsData = ingrData;
        recipesData = rcpData;
    }

    public int Main() {
        DisplayMenu("SEARCH RECIPES");
        return DisplayInputRequest();
    }

    public String[] Choices() {
        return new String[]{
            "Name Begins With",
            "Contains Ingredient",
            "Maximum Total Price"
        };
    }

    public void OnChoice(int choice) {
        switch (choice) {
            case 1:
                DisplaySearchResults(new SearchByNameBegins(validIn));
                break;
            case 2:
                DisplaySearchResults(new SearchByContainsIngredient(validIn));
                break;
            case 3:
                DisplaySearchResults(new SearchByMaxTotalPrice(validIn));
                break;
            default:
                break;
        }
    }

    private void DisplaySearchResults(ISearchStrategy searchStrategy) {
        ArrayList<Recipe> results = searchStrategy.GetSearchResults(recipesData.GetAllRecipes());
        if (results.size() > 0) {
            prettyPrints.SurroundPrintln(" Search Results ", '-');
            System.out.println();
            for (Recipe recipe : results) {
                prettyPrints.SurroundPrintln(recipe.name, ' ');
            }
            System.out.println();
            prettyPrints.SurroundPrintln("", '-');
        } else {
            prettyPrints.SurroundPrintln(" The search gave no results. ", '-');
        }
    }
}
