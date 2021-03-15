import java.util.Scanner;

public class RecipeBookApplication {

    private static String INGREDIENTS_DATA_PATH = "./ingredientsData.txt";
    private static String RECIPES_DATA_PATH = "./recipesData.txt";

    public static void main(String[] args) throws Exception {

        FileOperations fileOperations = new FileOperations();
        PrettyPrints prettyPrints = new PrettyPrints();
        ValidateInput validIn = new ValidateInput();

        IngredientsData ingredientsData = new IngredientsData();
        ingredientsData.StringToIngredients(fileOperations.LoadToString(INGREDIENTS_DATA_PATH));

        RecipesData recipesData = new RecipesData(ingredientsData);
        recipesData.StringToRecipes(fileOperations.LoadToString(RECIPES_DATA_PATH));

        prettyPrints.SurroundPrintln("");
        prettyPrints.SurroundPrintln("     Welcome to the Recipe Book!     ");
        prettyPrints.SurroundPrintln("");

        UserInterface MainUI = new MainUI(validIn, prettyPrints, ingredientsData, recipesData);

        MainUI.Enter();

        prettyPrints.Println("\nHave a nice day!\n");

        fileOperations.SaveTextToFile(INGREDIENTS_DATA_PATH, ingredientsData.IngredientsToString());
        fileOperations.SaveTextToFile(RECIPES_DATA_PATH, recipesData.RecipesToString());
    }
}
