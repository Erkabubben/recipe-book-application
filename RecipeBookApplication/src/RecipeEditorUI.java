import java.util.Scanner;
import java.util.ArrayList;

public class RecipeEditorUI extends UserInterface {

    private IngredientsData ingredientsData;
    private Recipe recipe;

    public RecipeEditorUI(ValidateInput vi, PrettyPrints pp, Recipe r, IngredientsData ingrData) {
        super(vi, pp);
        ingredientsData = ingrData;
        recipe = r;
    }

    public int Main() {
        if (recipe.name == "") {
            return CreateNewRecipe();
        } else {
            return EditRecipe();
        }
    }

    public String[] Choices() {
        return new String[]{
            "Change Recipe Name",
            "Change Portions",
            "Add Ingredient To Recipe",
            "Create New Ingredient And Add To Recipe",
            "Delete Ingredient From Recipe",
            "Add Instructions"
        };
    }

    public void OnChoice(int choice) {
        switch (choice) {
            case 1:
                EditName();
                break;
            case 2:
                EditPortions();
                break;
            case 3:
                AddIngredients();
                break;
            default:
                break;
        }
    }

    private int CreateNewRecipe() {
        prettyPrints.SurroundPrintln(" CREATE NEW RECIPE ", '=');
        EditName();
        if (recipe.name == "") {
            return Choices().length + 1;
        }
        EditPortions();
        AddIngredients();
        return Choices().length + 1;
    }

    private int EditRecipe() {
        prettyPrints.SurroundPrintln(" EDIT RECIPE ", '=');
        System.out.println(recipe.GetRecipeAsString(recipe.portions));
        int amountOfChoices = PrintChoices();
        System.out.println((amountOfChoices + 1) + ". Exit");
        return validIn.nextIntInRange("\nPlease enter a number: ", 1, amountOfChoices());
    }

    private void EditName() {
        recipe.name = validIn.nextLine("Name: ");
    }

    private void EditPortions() {
        recipe.portions = validIn.nextDoubleInRange("Portions: ", 0, 9999999);
    }

    private void AddIngredients() {
        while (true) {
            prettyPrints.SurroundPrintln(" ADD INGREDIENT ", '=');
            IngredientsListEntry ingredientsListEntry = CreateIngredientsListEntry();
            if (ingredientsListEntry != null) {
                recipe.AddIngredientToList(ingredientsListEntry);
                System.out.println(ingredientsListEntry.amount + " " + ingredientsListEntry.ingredient.unit + "(s)" + " of " + ingredientsListEntry.ingredient.name
                + " was added to ingredients list.");
            } else {
                break;
            }
        }
    }

    private IngredientsListEntry CreateIngredientsListEntry() {
        System.out.print("Ingredient (leave empty to exit): ");
        //in = new Scanner(System.in);
        String ingredientName = validIn.nextLine();
        if (ingredientName == null || ingredientName == "") {
            return null;
        } else {
            Ingredient ingredient = ingredientsData.GetIngredient(ingredientName);
            if (ingredient == null) {
                System.out.println("\nIngredient not found.");
                return null;
            } else {
                Double amount = validIn.nextDoubleInRange("Amount: ", 0, 9999999);
                String comment = validIn.nextLine("Comment: ");
                System.out.println("");
                return new IngredientsListEntry(ingredient, amount, comment);
            }
        }
    }
}
