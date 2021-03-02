import java.util.Scanner;

public class RecipeBookApplication {

    private static IngredientsData ingredientsData;
    private static RecipesData recipesData;
    
    private static void LoadIngredientsData (String ingredientsDataPath) {}
    private static void SaveIngredientsData (String ingredientsDataPath) {}
    private static void LoadRecipesData (String recipesDataPath) {}
    private static void SaveRecipessData (String recipesDataPath) {}
    public static void main(String[] args) throws Exception {

        Scanner in = new Scanner(System.in);

        if (ingredientsData == null) ingredientsData = new IngredientsData();
        if (recipesData == null) recipesData = new RecipesData();

        IUserInteractionsStrategy currentStrategy = new IngredientsDataUserInteractions(in, ingredientsData);

        System.out.println("Welcome to the Recipe Book!");
        System.out.println("What would you like to do?");

        IUserInteractionsStrategy.PrintChoices(currentStrategy.Choices());
        currentStrategy.MakeChoice(in.nextInt());
    }
}
