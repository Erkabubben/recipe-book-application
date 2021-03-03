import java.util.Scanner;
import java.util.ArrayList;

public class IngredientsDataUI extends UserInterface {

    private Scanner in;
    private PrettyPrints prettyPrints;
    private IngredientsData ingredientsData;
    

    public IngredientsDataUI(Scanner scanner, PrettyPrints pp, IngredientsData ingrData) {
        in = scanner;
        prettyPrints = pp;
        ingredientsData = ingrData;
    }

    public void Before() {

    }

    public void After() {
        
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

                Ingredient i = ingredientsData.CreateIngredient(name, price, unit);

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
