import java.util.Scanner;
import java.util.ArrayList;

public class IngredientsDataUserInteractions implements IUserInteractionsStrategy {

    private Scanner in;
    private IngredientsData ingredientsData;

    public IngredientsDataUserInteractions(Scanner scanner, IngredientsData ingrData) {
        in = scanner;
        ingredientsData = ingrData;
    }

    public String[] Choices() {
        return new String[]{
            "Get All Ingredients",
            "Create New Ingredient",
            "Delete Ingredient"
        };
    }

    public void MakeChoice(int choice) {
        switch (choice) {
            case 1:
                System.out.println(IUserInteractionsStrategy.SurroundString(" INGREDIENTS ", 128, '*'));
                ArrayList<String> ingredients = ingredientsData.GetAllIngredients();
                for (String string : ingredients) {
                    System.out.println(string);
                }
                System.out.println(IUserInteractionsStrategy.SurroundString("", 128, '*'));
                break;
            case 2:
                System.out.println(IUserInteractionsStrategy.SurroundString(" CREATE NEW INGREDIENT ", 128, '*'));

                System.out.print("Name: ");
                String name = in.next();
                System.out.print("Price: ");
                double price = in.nextDouble();
                System.out.print("Unit: ");
                String unit = in.next();

                Ingredient i = ingredientsData.CreateIngredient(name, price, unit);

                System.out.println(IUserInteractionsStrategy.SurroundString(" A new ingredient was added: " + i.GetDetails() + " ", 128, '*'));
                break;
            case 3:
                System.out.print("Enter the name of the ingredient you want to delete: ");
                String ingredient = in.next();
                boolean ingredientWasDeleted = ingredientsData.DeleteIngredient(ingredient);
                if (ingredientWasDeleted) {
                    System.out.println(ingredient + " was successfully deleted from the list of available ingredients.");
                } else {
                    System.out.println(ingredient + " could not be found - did you already delete it?");
                }
                break;
            default:
                break;
        }
    }
}
