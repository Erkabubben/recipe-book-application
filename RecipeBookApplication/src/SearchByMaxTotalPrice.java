/**
 * SearchByMaxTotalPrice
 * 
 * @version 1.0 17 Mar 2021
 * @author Erik Lindholm
 */
import java.util.ArrayList;
/**
 * Performs a recipe search that returns all recipes whose total price of ingredients
 * is lower or equal to the given value.
 */
public class SearchByMaxTotalPrice implements ISearchStrategy {

    ValidateInput validIn;

    /* Constructor */
    public SearchByMaxTotalPrice(ValidateInput vi) {
        validIn = vi;
    }

    /* Requests the user to input search parameters */
    private Double RequestSearchParameterFromUser() {
        Double price = validIn.nextDoubleInRange("Enter maximum total price: ", 0, 9999999);
        return price;
    }

    /* Called to execute the search strategy - will request search parameters from the user,
       perform the search and return the results as an ArrayList of recipes. */
    public ArrayList<Recipe> GetSearchResults (ArrayList<Recipe> listToSearch) {
        Double searchParam = RequestSearchParameterFromUser();
        ArrayList<Recipe> results = new ArrayList<Recipe>();
        for (Recipe recipe : listToSearch) {
            if (recipe.GetTotalPrice(recipe.portions) <= searchParam) {
                results.add(recipe);
            }
        }
        return results;
    }
}
