import java.util.Scanner;

public class RecipeBookApplication {

    private static IngredientsData ingredientsData;
    private static RecipesData recipesData;

    private static String INGREDIENTS_DATA_PATH = "./ingredientsData.txt";
    private static String RECIPES_DATA_PATH = "./recipesData.txt";

    public static void main(String[] args) throws Exception {

        FileOperations fileOperations = new FileOperations();
        PrettyPrints prettyPrints = new PrettyPrints();
        Scanner in = new Scanner(System.in);

        ingredientsData = new IngredientsData();
        ingredientsData.StringToIngredients(fileOperations.LoadToString(INGREDIENTS_DATA_PATH));

        recipesData = new RecipesData(ingredientsData);
        recipesData.StringToRecipes(fileOperations.LoadToString(RECIPES_DATA_PATH));

        prettyPrints.SurroundPrintln("");
        prettyPrints.SurroundPrintln("     Welcome to the Recipe Book!     ");
        prettyPrints.SurroundPrintln("");

        UserInterface MainUI = new MainUI(in, prettyPrints, ingredientsData, recipesData);

        MainUI.Enter();

        System.out.println("\nHave a nice day!\n");

        fileOperations.SaveText(INGREDIENTS_DATA_PATH, ingredientsData.IngredientsToString());
        fileOperations.SaveText(RECIPES_DATA_PATH, recipesData.RecipesToString());
    }
}
