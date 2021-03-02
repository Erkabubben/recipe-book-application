import java.util.Scanner;

public class RecipeBookApplication {

    private static IngredientsData ingredientsData;
    private static RecipesData recipesData;

    private static String INGREDIENTS_DATA_PATH = "./ingredientsData.txt";
    private static String RECIPES_DATA_PATH = "./recipesData.txt";

    public static void main(String[] args) throws Exception {

        FileOperations fileOperations = new FileOperations();
        Scanner in = new Scanner(System.in);

        ingredientsData = new IngredientsData();
        ingredientsData.StringToIngredients(fileOperations.LoadToString(INGREDIENTS_DATA_PATH));

        if (recipesData == null) recipesData = new RecipesData();

        System.out.println(IUserInterfaceStrategy.SurroundString("", 128, '*'));
        System.out.println(IUserInterfaceStrategy.SurroundString("     Welcome to the Recipe Book!     ", 128, '*'));
        System.out.println(IUserInterfaceStrategy.SurroundString("", 128, '*'));

        IUserInterfaceStrategy ingredientsStrategy = new IngredientsDataUI(in, ingredientsData);

        int lastChoice = -1;
        while (true) {
            System.out.println("What would you like to do?");
            System.out.println("");
            int amountOfChoices = IUserInterfaceStrategy.PrintChoices(ingredientsStrategy.Choices());
            System.out.println((amountOfChoices + 1) + ". Exit");
            System.out.print("\nPlease enter a number: ");

            lastChoice = in.nextInt();
            ingredientsStrategy.MakeChoice(lastChoice);
            if (lastChoice == amountOfChoices + 1) {
                break;
            }
        }

        System.out.println("\nHave a nice day!\n");

        fileOperations.SaveText(INGREDIENTS_DATA_PATH, ingredientsData.IngredientsToString());
    }
}
