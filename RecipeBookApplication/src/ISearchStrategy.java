/**
 * ISearchStrategy
 * 
 * @version 1.0 17 Mar 2021
 * @author Erik Lindholm
 */
import java.util.ArrayList;
/**
 * Represents a search strategy to be used for searching for recipes in the RecipesData.
 */
public interface ISearchStrategy {
    /* Called to execute the search strategy - will request search parameters from the user,
       perform the search and return the results as an ArrayList of recipes. */
    public ArrayList<Recipe> GetSearchResults (ArrayList<Recipe> listToSearch);
}
