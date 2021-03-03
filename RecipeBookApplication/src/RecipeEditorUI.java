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
            System.out.println("===== CREATE NEW RECIPE =====");
            System.out.print("Name: ");
            in = new Scanner(System.in);
            recipe.name = in.nextLine();
            if (recipe.name == "") {
                return Choices().length + 1;
            }
            System.out.print("Portions: ");
            recipe.portions = in.nextDouble();
            //ArrayList ingredients = new ArrayList<IngredientsListEntry>();
            while (true) {
                System.out.println("===== ADD INGREDIENT =====");
                System.out.print("Ingredient: ");
                in = new Scanner(System.in);
                String ingredientName = in.nextLine();
                if (ingredientName == null || ingredientName == "") {
                    break;
                } else {
                    Ingredient ingredient = ingredientsData.GetIngredient(ingredientName);
                    if (ingredient == null) {
                        System.out.println("\nIngredient not found.");
                        break;
                    } else {
                        System.out.print("Amount: ");
                        Double amount = in.nextDouble();
                        System.out.print("Comment: ");
                        in = new Scanner(System.in);
                        String comment = in.nextLine();
                        System.out.println("");
                        IngredientsListEntry ingredientsListEntry = new IngredientsListEntry(ingredient, amount, comment);
                        recipe.AddIngredientToList(ingredientsListEntry);
                        System.out.println(ingredientsListEntry.amount + " " + ingredientsListEntry.ingredient.unit + "(s)" + " of " + ingredientsListEntry.ingredient.name
                                           + " was added to ingredients list.");
                    }
                }
            }
            return Choices().length + 1;
        } else {
            System.out.println("===== EDIT RECIPE =====");
            System.out.println("");
            int amountOfChoices = PrintChoices(this);
            System.out.println((amountOfChoices + 1) + ". Exit");
            System.out.print("\nPlease enter a number: ");
            return in.nextInt();
        }
    }

    public String[] Choices() {
        return new String[]{
            "Change Recipe Name",
            "Add Ingredient To Recipe",
            "Create New Ingredient And Add To Recipe",
            "Delete Ingredient From Recipe",
            "Add Instructions"
        };
    }

    public void OnChoice(int choice) {
        switch (choice) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                break;
        }
    }
}
