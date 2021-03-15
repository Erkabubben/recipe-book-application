import java.util.ArrayList;
import java.util.Scanner;

public class SearchByNameBegins implements ISearchStrategy {

    ValidateInput validIn;

    public SearchByNameBegins(ValidateInput vi) {
        validIn = vi;
    }

    public String RequestSearchParameterFromUser() {
        System.out.print("Enter recipe name: ");
        String recipeName = validIn.nextLine();
        return recipeName;
    }

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
