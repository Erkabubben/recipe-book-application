import java.util.ArrayList;
import java.util.Scanner;

public class SearchByContainsIngredient implements ISearchStrategy {

    ValidateInput validIn;

    public SearchByContainsIngredient(ValidateInput vi) {
        validIn = vi;
    }

    public String RequestSearchParameterFromUser() {
        System.out.print("Ingredient to search for: ");
        //in = new Scanner(System.in);
        String ingredient = validIn.nextLine();
        return ingredient;
    }

    public ArrayList<Recipe> GetSearchResults (ArrayList<Recipe> listToSearch) {
        String searchParam = RequestSearchParameterFromUser();
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
}