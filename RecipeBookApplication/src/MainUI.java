import java.util.Scanner;
import java.util.ArrayList;

public class MainUI extends UserInterface {

    private IngredientsData ingredientsData;
    private RecipesData recipesData;

    public MainUI(ValidateInput vi, PrettyPrints pp, IngredientsData ingrData, RecipesData rcpData) {
        super(vi, pp);
        ingredientsData = ingrData;
        recipesData = rcpData;
    }

    public int Main() {
        DisplayMenu("MAIN MENU");
        return DisplayInputRequest();
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
                UserInterface ingredientsDataUI = new IngredientsDataUI(validIn, prettyPrints, ingredientsData);
                ingredientsDataUI.Enter();
                break;
            case 2:
                UserInterface recipesDataUI = new RecipesDataUI(validIn, prettyPrints, recipesData, ingredientsData);
                recipesDataUI.Enter();
                break;
            default:
                break;
        }
    }
}
