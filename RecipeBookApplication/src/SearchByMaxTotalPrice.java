import java.util.ArrayList;

public class SearchByMaxTotalPrice implements ISearchStrategy {

    ValidateInput validIn;

    public SearchByMaxTotalPrice(ValidateInput vi) {
        validIn = vi;
    }

    private Double RequestSearchParameterFromUser() {
        Double price = validIn.nextDoubleInRange("Enter maximum total price: ", 0, 9999999);
        return price;
    }

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
