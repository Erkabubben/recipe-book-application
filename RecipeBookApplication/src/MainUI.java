/**
 * MainUI
 * 
 * @version 1.0 17 Mar 2021
 * @author Erik Lindholm
 * 
 * The main menu of the application.
 */
public class MainUI extends UserInterface {

    private IngredientsData ingredientsData;
    private RecipesData recipesData;

    /* Constructor */
    public MainUI(ValidateInput vi, PrettyPrints pp, IngredientsData ingrData, RecipesData rcpData) {
        super(vi, pp);
        ingredientsData = ingrData;
        recipesData = rcpData;
    }

    protected int Main() {
        DisplayMenu("MAIN MENU");
        return DisplayInputRequest();
    }

    protected String[] Choices() {
        return new String[]{
            "Check/Edit Ingredients",
            "Check/Edit Recipes"
        };
    }

    protected void OnChoice(int choice) {
        switch (choice) {
            case 1:
                UserInterface ingredientsDataUI = new IngredientsDataUI(validIn, prettyPrints, ingredientsData, recipesData);
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
