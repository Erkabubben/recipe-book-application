import java.util.Scanner;
import java.util.ArrayList;

public class IngredientsDataUI extends UserInterface {

    private IngredientsData ingredientsData;

    public IngredientsDataUI(Scanner scanner, PrettyPrints pp, IngredientsData ingrData) {
        super(scanner, pp);
        ingredientsData = ingrData;
    }

    public int Main() {
        System.out.println("INGREDIENTS: What would you like to do?");
        System.out.println("");
        int amountOfChoices = PrintChoices(this);
        System.out.println((amountOfChoices + 1) + ". Exit");
        System.out.print("\nPlease enter a number: ");

        return in.nextInt();
    }

    public String[] Choices() {
        return new String[]{
            "Get All Ingredients",
            "Create New Ingredient",
            "Delete Ingredient"
        };
    }

    public void OnChoice(int choice) {
        switch (choice) {
            case 1:
                prettyPrints.SurroundPrintln(" INGREDIENTS ");
                ArrayList<String> ingredients = ingredientsData.GetAllIngredients();
                for (String string : ingredients) {
                    System.out.println(string);
                }
                prettyPrints.SurroundPrintln("");
                break;
            case 2:
                prettyPrints.SurroundPrintln(" CREATE NEW INGREDIENT ");

                System.out.print("Name: ");
                String name = in.next();
                System.out.print("Price: ");
                double price = in.nextDouble();
                System.out.print("Unit: ");
                String unit = in.next();

                Ingredient i = new Ingredient(name, price, unit);
                ingredientsData.AddIngredient(i);

                prettyPrints.SurroundPrintln(" A new ingredient was added: " + i.GetDetails() + " ");
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
