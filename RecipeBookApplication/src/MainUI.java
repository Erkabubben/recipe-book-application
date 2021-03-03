import java.util.Scanner;
import java.util.ArrayList;

public class MainUI extends UserInterface {

    private IngredientsData ingredientsData;
    private RecipesData recipesData;

    public MainUI(Scanner scanner, PrettyPrints pp, IngredientsData ingrData, RecipesData rcpData) {
        super(scanner, pp);
        ingredientsData = ingrData;
        recipesData = rcpData;
    }

    public int Main() {
        System.out.println("MAIN: What would you like to do?");
        System.out.println("");
        int amountOfChoices = PrintChoices();
        System.out.println((amountOfChoices + 1) + ". Exit");
        System.out.print("\nPlease enter a number: ");

        return in.nextInt();
    }

    public String[] Choices() {
        return new String[]{
            "Check/Edit Ingredients",
            "Check/Edit Recipes"
        };
    }

    public void OnChoice(int choice) {
        switch (choice) {
            case 1:
                UserInterface ingredientsDataUI = new IngredientsDataUI(in, prettyPrints, ingredientsData);
                ingredientsDataUI.Enter();
                break;
            case 2:
                UserInterface recipesDataUI = new RecipesDataUI(in, prettyPrints, recipesData, ingredientsData);
                recipesDataUI.Enter();
                break;
            default:
                break;
        }
    }
}
