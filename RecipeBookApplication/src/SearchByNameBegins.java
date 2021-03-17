import java.util.ArrayList;

public class SearchByNameBegins implements ISearchStrategy {

    ValidateInput validIn;

    public SearchByNameBegins(ValidateInput vi) {
        validIn = vi;
    }

    private String RequestSearchParameterFromUser() {
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
