/**
 * MainUI
 * 
 * @version 1.0 17 Mar 2021
 * @author Erik Lindholm
 * 
 * The main menu of the application.
 */
public class MainUI extends UserInterface {

    /* Constructor */
    public MainUI(ValidateInput vi, PrettyPrints pp, IngredientsData ingrData, RecipesData rcpData) {
        super(vi, pp, ingrData, rcpData);
    }

    /* Returns the title of the UI class */
    protected String Title() { return "Main Menu"; }

    protected String[] Choices() {
        return new String[]{
            "Check/Edit Ingredients",
            "Check/Edit Recipes"
        };
    }

    /* Contains a Switch statement that will trigger code based on what menu option the user has selected */
    protected void OnChoice(int choice) {
        switch (choice) {
            case 1: // Displays the user interface for interacting with the IngredientsData.
                UserInterface ingredientsDataUI = new IngredientsDataUI(validIn, prettyPrints, ingredientsData, recipesData);
                ingredientsDataUI.Enter();
                break;
            case 2: // Displays the user interface for interacting with the RecipesData.
                UserInterface recipesDataUI = new RecipesDataUI(validIn, prettyPrints, ingredientsData, recipesData);
                recipesDataUI.Enter();
                break;
            default:
                break;
        }
    }
}
