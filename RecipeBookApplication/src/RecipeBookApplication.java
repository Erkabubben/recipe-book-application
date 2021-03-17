/**
 * RecipeBookApplication
 * 
 * @version 1.0 17 Mar 2021
 * @author Erik Lindholm
 * 
 * An application for storing and managing recipes and ingredients.
 */
public class RecipeBookApplication {

    private static String INGREDIENTS_DATA_PATH = "./ingredientsData.txt";
    private static String RECIPES_DATA_PATH = "./recipesData.txt";

    /* The starting method of the application */
    public static void main(String[] args) throws Exception {

        // Instantiates utility objects
        FileOperations fileOperations = new FileOperations();
        PrettyPrints prettyPrints = new PrettyPrints();
        ValidateInput validIn = new ValidateInput();

        // Instantiate IngredientsData and RecipesData and load contents if files exist
        IngredientsData ingredientsData = new IngredientsData();
        ingredientsData.StringToIngredients(fileOperations.LoadToString(INGREDIENTS_DATA_PATH));
        RecipesData recipesData = new RecipesData(ingredientsData);
        recipesData.StringToRecipes(fileOperations.LoadToString(RECIPES_DATA_PATH));

        // Display Welcome message
        prettyPrints.SurroundPrintln("");
        prettyPrints.SurroundPrintln("     Welcome to the Recipe Book!     ");
        prettyPrints.SurroundPrintln("");

        // Enter the main menu
        UserInterface MainUI = new MainUI(validIn, prettyPrints, ingredientsData, recipesData);
        MainUI.Enter();

        // Display goodbye message
        prettyPrints.Println("\nHave a nice day!\n");

        // Save contents of IngredientsData and RecipesData to text files
        fileOperations.SaveTextToFile(INGREDIENTS_DATA_PATH, ingredientsData.IngredientsToString());
        fileOperations.SaveTextToFile(RECIPES_DATA_PATH, recipesData.RecipesToString());
    }
}
