import java.util.ArrayList;
import java.util.Scanner;

public class SearchByNameBegins implements ISearchStrategy {

    Scanner in;

    public SearchByNameBegins(Scanner scanner) {
        in = scanner;
    }

    public String RequestSearchParameterFromUser() {
        System.out.print("Enter recipe name: ");
        in = new Scanner(System.in);
        String recipeName = in.nextLine();
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
