import java.util.Scanner;
import java.util.ArrayList;

public class RecipesDataUI implements IUserInterfaceStrategy {

    private Scanner in;
    private RecipesData recipesData;

    public RecipesDataUI(Scanner scanner, RecipesData rcpData) {
        in = scanner;
        recipesData = rcpData;
        //ingredientsData = ingrData;
    }

    public String[] Choices() {
        return new String[]{
            "Get All Recipes",
            "Create New Recipe",
            "Search Recipes",
            "Delete Recipes"
        };
    }

    public void MakeChoice(int choice) {
        switch (choice) {
            case 1:
                System.out.println(IUserInterfaceStrategy.SurroundString(" RECIPES ", 128, '*'));
                ArrayList<Recipe> recipes = recipesData.GetAllRecipes();
                for (Recipe recipe : recipes) {
                    System.out.println(recipe.name);
                }
                System.out.println(IUserInterfaceStrategy.SurroundString("", 128, '*'));
                break;
            case 2:
                /*System.out.println(IUserInterfaceStrategy.SurroundString(" CREATE NEW INGREDIENT ", 128, '*'));

                System.out.print("Name: ");
                String name = in.next();
                System.out.print("Price: ");
                double price = in.nextDouble();
                System.out.print("Unit: ");
                String unit = in.next();

                Ingredient i = ingredientsData.CreateIngredient(name, price, unit);

                System.out.println(IUserInterfaceStrategy.SurroundString(" A new ingredient was added: " + i.GetDetails() + " ", 128, '*'));*/
                break;
            case 3:
                /*System.out.print("Enter the name of the ingredient you want to delete: ");
                String ingredient = in.next();
                boolean ingredientWasDeleted = ingredientsData.DeleteIngredient(ingredient);
                if (ingredientWasDeleted) {
                    System.out.println(ingredient + " was successfully deleted from the list of available ingredients.");
                } else {
                    System.out.println(ingredient + " could not be found - did you already delete it?");
                }*/
                break;
            default:
                break;
        }
    }
}
