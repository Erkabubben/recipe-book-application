import java.util.ArrayList;

public class RecipesData {

    ArrayList<Recipe> recipes;

    public ArrayList<Recipe> GetAllRecipes() { return new ArrayList<Recipe>();}
    public ArrayList<Recipe> Search(ISearchStrategy searchStrategies, ArrayList<Recipe> recipes) { return new ArrayList<Recipe>(); }
    public void CreateRecipe(String recipeName) {}
    public void DeleteRecipe(String recipeName) {}
}
