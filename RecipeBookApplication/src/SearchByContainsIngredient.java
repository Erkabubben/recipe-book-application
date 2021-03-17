import java.util.ArrayList;

public class SearchByContainsIngredient implements ISearchStrategy {

    ValidateInput validIn;

    public SearchByContainsIngredient(ValidateInput vi) {
        validIn = vi;
    }

    private String RequestSearchParameterFromUser() {
        System.out.print("Ingredient to search for: ");
        String ingredient = validIn.nextLine();
        return ingredient;
    }

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
