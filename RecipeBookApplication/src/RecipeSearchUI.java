/**
 * RecipeSearchUI
 * 
 * @version 1.0 17 Mar 2021
 * @author Erik Lindholm
 */
import java.util.ArrayList;
/**
 * The RecipeSearchUI allows the user to search for recipes based on different search
 * criteria. The search criteria uses the strategy pattern - when initiating a search,
 * the user choses a search strategy that implements the interface ISearchStrategy.
 */
public class RecipeSearchUI extends UserInterface {

    /* Constructor */
    public RecipeSearchUI(ValidateInput vi, PrettyPrints pp, IngredientsData ingrData, RecipesData rcpData) {
        super(vi, pp, ingrData, rcpData);
    }

    /* Returns the title of the UI class */
    protected String Title() { return "Search Recipes"; }

    /* Returns a String array containing the different options that will be displayed to the user */
    protected String[] Choices() {
        return new String[]{
            "Name Begins With",
            "Contains Ingredient",
            "Maximum Total Price"
        };
    }

    /* Contains a Switch statement that will trigger code based on what menu option the user has selected */
    protected void OnChoice(int choice) {
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

    /* Displays the user interface of the selected search strategy, then prints the results. */
    private void DisplaySearchResults(SearchStrategy searchStrategy) {
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
