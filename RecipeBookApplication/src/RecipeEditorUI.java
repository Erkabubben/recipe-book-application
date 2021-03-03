import java.util.Scanner;
import java.util.ArrayList;

public class RecipeEditorUI extends UserInterface {
    
    private Scanner in;
    private PrettyPrints prettyPrints;
    private IngredientsData ingredientsData;

    public RecipeEditorUI(Scanner scanner, PrettyPrints pp, IngredientsData ingrData) {
        in = scanner;
        prettyPrints = pp;
        ingredientsData = ingrData;
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

    public void MakeChoice(int choice) {
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
