/**
 * SearchByNameBegins
 * 
 * @version 1.0 17 Mar 2021
 * @author Erik Lindholm
 */
import java.util.ArrayList;
/**
 * Performs a recipe search that returns all recipes whose names begin with
 * the given combination of characters.
 */
public class SearchByNameBegins extends SearchStrategy {

    /* Constructor */
    public SearchByNameBegins(ValidateInput vi) {
        super(vi);
    }

    /* Requests the user to input search parameters */
    private String RequestSearchParameterFromUser() {
        System.out.print("Enter recipe name: ");
        String recipeName = validIn.nextLine();
        return recipeName;
    }

    /* Called to execute the search strategy - will request search parameters from the user,
       perform the search and return the results as an ArrayList of recipes. */
    public ArrayList<Recipe> GetSearchResults (ArrayList<Recipe> listToSearch) {
        String searchParam = RequestSearchParameterFromUser();
        ArrayList<Recipe> results = new ArrayList<Recipe>();
        for (Recipe recipe : listToSearch) {
            if (recipe.name.startsWith(searchParam)) {
                results.add(recipe);
            }
        }
        return results;
    }
}
