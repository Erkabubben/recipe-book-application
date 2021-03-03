import java.util.Scanner;
import java.util.ArrayList;

public class RecipeEditorUI extends UserInterface {

    private IngredientsData ingredientsData;
    private Recipe recipe;

    public RecipeEditorUI(Scanner scanner, PrettyPrints pp, Recipe r, IngredientsData ingrData) {
        super(scanner, pp);
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
        System.out.println("===== CREATE NEW RECIPE =====");
        EditName();
        if (recipe.name == "") {
            return Choices().length + 1;
        }
        EditPortions();
        AddIngredients();
        return Choices().length + 1;
    }

    private int EditRecipe() {
        System.out.println("===== EDIT RECIPE =====");
        System.out.println(recipe.GetRecipeAsString(recipe.portions));
        int amountOfChoices = PrintChoices();
        System.out.println((amountOfChoices + 1) + ". Exit");
        System.out.print("\nPlease enter a number: ");
        return in.nextInt();
    }

    private void EditName() {
        System.out.print("Name: ");
        in = new Scanner(System.in);
        recipe.name = in.nextLine();
    }

    private void EditPortions() {
        System.out.print("Portions: ");
        recipe.portions = in.nextDouble();
    }

    private void AddIngredients() {
        while (true) {
            System.out.println("===== ADD INGREDIENT =====");
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
        System.out.print("Ingredient: ");
        in = new Scanner(System.in);
        String ingredientName = in.nextLine();
        if (ingredientName == null || ingredientName == "") {
            return null;
        } else {
            Ingredient ingredient = ingredientsData.GetIngredient(ingredientName);
            if (ingredient == null) {
                System.out.println("\nIngredient not found.");
                return null;
            } else {
                System.out.print("Amount: ");
                Double amount = in.nextDouble();
                System.out.print("Comment: ");
                in = new Scanner(System.in);
                String comment = in.nextLine();
                System.out.println("");
                return new IngredientsListEntry(ingredient, amount, comment);
            }
        }
    }
}
