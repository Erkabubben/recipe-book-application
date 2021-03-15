import java.util.Scanner;
import java.util.ArrayList;

public class IngredientsDataUI extends UserInterface {

    private IngredientsData ingredientsData;

    public IngredientsDataUI(ValidateInput vi, PrettyPrints pp, IngredientsData ingrData) {
        super(vi, pp);
        ingredientsData = ingrData;
    }

    public int Main() {
        DisplayMenu("INGREDIENTS");
        return DisplayInputRequest();
    }

    public String[] Choices() {
        return new String[]{
            "List All Ingredients",
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
                CreateNewIngredient();
                break;
            case 3:
                System.out.print("Enter the name of the ingredient you want to delete: ");
                String ingredient = validIn.next();
                boolean ingredientWasDeleted = ingredientsData.DeleteIngredient(ingredient);
                if (ingredientWasDeleted) {
                    System.out.println("SUCCESS:" + ingredient + " was deleted from the list of available ingredients.");
                } else {
                    System.out.println("ERROR:" + ingredient + " could not be found - did you already delete it?");
                }
                break;
            default:
                break;
        }
    }

    public Ingredient CreateNewIngredient() {
        prettyPrints.SurroundPrintln(" CREATE NEW INGREDIENT ");
        String name = validIn.nextLine("Name: ");
        if (name == null || name.isBlank()) {
            prettyPrints.SurroundPrintln(" No new ingredient was created. ");
            return null;
        } else {
            double price = validIn.nextDoubleInRange("Price: ", 0, 9999999);
            String unit = validIn.next("Unit: ");
    
            Ingredient i = new Ingredient(name, price, unit);
            ingredientsData.AddIngredient(i);
            prettyPrints.SurroundPrintln(" A new ingredient was created: " + i.GetDetails() + " ");
            return i;
        }
    }
}
