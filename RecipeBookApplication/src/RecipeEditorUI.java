import java.util.Scanner;
import java.util.ArrayList;

public class RecipeEditorUI extends UserInterface {

    private IngredientsData ingredientsData;

    public RecipeEditorUI(Scanner scanner, PrettyPrints pp, IngredientsData ingrData) {
        super(scanner, pp);
        ingredientsData = ingrData;
    }

    public int Main() {
        System.out.println("===== RECIPE EDITOR =====");
        System.out.println("");
        int amountOfChoices = PrintChoices(this);
        System.out.println((amountOfChoices + 1) + ". Exit");
        System.out.print("\nPlease enter a number: ");

        return in.nextInt();
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
