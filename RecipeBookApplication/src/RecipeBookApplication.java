import java.util.Scanner;

public class RecipeBookApplication {

    private static IngredientsData ingredientsData;
    private static RecipesData recipesData;

    private static String INGREDIENTS_DATA_PATH = "./ingredientsData.txt";
    private static String RECIPES_DATA_PATH = "./recipesData.txt";
    
    private static void LoadIngredientsData (String ingredientsDataPath) {}
    private static void SaveIngredientsData (String ingredientsDataPath) {}
    private static void LoadRecipesData (String recipesDataPath) {}
    private static void SaveRecipesData (String recipesDataPath) {}

    public static void main(String[] args) throws Exception {

        FileOperations fileOperations = new FileOperations();
        Scanner in = new Scanner(System.in);

        if (ingredientsData == null) ingredientsData = new IngredientsData();
        if (recipesData == null) recipesData = new RecipesData();

        IUserInteractionsStrategy currentStrategy = new IngredientsDataUserInteractions(in, ingredientsData);

        System.out.println("Welcome to the Recipe Book!");
        System.out.println("What would you like to do?");

        IUserInteractionsStrategy.PrintChoices(currentStrategy.Choices());
        currentStrategy.MakeChoice(in.nextInt());

        fileOperations.SaveText(INGREDIENTS_DATA_PATH, ingredientsData.IngredientsToString());
    }
}
