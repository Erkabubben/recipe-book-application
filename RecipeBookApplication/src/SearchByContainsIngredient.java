/**
 * SearchByContainsIngredient
 * 
 * @version 1.0 17 Mar 2021
 * @author Erik Lindholm
 */
import java.util.ArrayList;
/**
 * Performs a recipe search that returns all recipes containing a certain ingredient.
 */
public class SearchByContainsIngredient implements ISearchStrategy {

    ValidateInput validIn;

    /* Constructor */
    public SearchByContainsIngredient(ValidateInput vi) {
        validIn = vi;
    }

    /* Requests the user to input search parameters */
    private String RequestSearchParameterFromUser() {
        System.out.print("Ingredient to search for: ");
        String ingredient = validIn.nextLine();
        return ingredient;
    }

    /* Called to execute the search strategy - will request search parameters from the user,
       perform the search and return the results as an ArrayList of recipes. */
    public ArrayList<Recipe> GetSearchResults (ArrayList<Recipe> listToSearch, String ingredientName) {
        String searchParam;
        if (ingredientName == null) {
            searchParam = RequestSearchParameterFromUser();
        } else {
            searchParam = ingredientName;
        }
        ArrayList<Recipe> results = new ArrayList<Recipe>();
        for (Recipe recipe : listToSearch) {
            for (IngredientsListEntry ingredientsListEntry : recipe.ingredients) {
                if (ingredientsListEntry.ingredient.name.equals(searchParam)) {
                    results.add(recipe);
                }
            }
        }
        return results;
    }

    public ArrayList<Recipe> GetSearchResults (ArrayList<Recipe> listToSearch) {
        return GetSearchResults(listToSearch, null);
    }
}
