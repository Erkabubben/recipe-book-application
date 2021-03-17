import java.util.ArrayList;

public class RecipeEditorUI extends UserInterface {

    private IngredientsData ingredientsData;
    private RecipesData recipesData;
    private Recipe recipe;

    public RecipeEditorUI(ValidateInput vi, PrettyPrints pp, Recipe r, IngredientsData ingrData, RecipesData rcpData) {
        super(vi, pp);
        ingredientsData = ingrData;
        recipe = r;
        recipesData = rcpData;
    }

    protected int Main() {
        if (recipe.name == "") {
            return CreateNewRecipe();
        } else {
            return EditRecipe();
        }
    }

    protected String[] Choices() {
        return new String[]{
            "Change Recipe Name",
            "Change Portions",
            "Add Ingredients To Recipe",
            "Create New Ingredient And Add To Recipe",
            "Delete Ingredients From Recipe",
            "Add Instructions",
            "Delete Instruction Line"
        };
    }

    protected void OnChoice(int choice) {
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
            case 4:
                CreateNewIngredientAndAddToRecipe();
                break;
            case 5:
                DeleteIngredients();
                break;
            case 6:
                AddInstructions();
                break;
            case 7:
                DeleteInstructions();
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
        AddInstructions();
        return Choices().length + 1;
    }

    private int EditRecipe() {
        prettyPrints.SurroundPrintln(" EDIT RECIPE ", '=');
        System.out.println(recipe.GetRecipeAsString(recipe.portions, prettyPrints) + "\n");
        int amountOfChoices = PrintChoices();
        System.out.println((amountOfChoices + 1) + ". Exit");
        return validIn.nextIntInRange("\nPlease enter a number: ", 1, amountOfChoices());
    }

    private void EditName() {
        recipe.name = validIn.nextLine("Name: ");
    }

    private void EditPortions() {
        double newPortions = validIn.nextDoubleInRange("Portions: ", 0, 9999999);
        // Only ask whether to adjust ingredient amounts if editing a recipe that contains ingredients
        if (recipe.ingredients.size() > 0) {
            boolean adjustIngredients = validIn.YesOrNo("Adjust ingredient amounts to new amount of portions? (Y/N) ");
            if (adjustIngredients) {
                for (IngredientsListEntry ingredientsListEntry : recipe.ingredients) {
                    ingredientsListEntry.amount = ingredientsListEntry.amount * (newPortions / recipe.portions);
                }
            }
        }
        recipe.portions = newPortions;
    }

    private void CreateNewIngredientAndAddToRecipe() {
        IngredientsDataUI ingredientsDataUI = new IngredientsDataUI(validIn, prettyPrints, ingredientsData, recipesData);
        Ingredient newIngredient = ingredientsDataUI.CreateNewIngredient();
        if (newIngredient != null) {
            IngredientsListEntry ingredientsListEntry = CreateIngredientsListEntry(newIngredient);
            if (ingredientsListEntry != null) {
                recipe.AddIngredientToList(ingredientsListEntry);
                System.out.println(ingredientsListEntry.amount + " " + ingredientsListEntry.ingredient.unit + "(s)" + " of " + ingredientsListEntry.ingredient.name
                + " was added to the ingredients list of the recipe.");
            }
        }
    }

    private void AddIngredients() {
        while (true) {
            prettyPrints.Println(recipe.GetIngredientsAsString(recipe.portions, prettyPrints));
            prettyPrints.SurroundPrintln(" ADD INGREDIENT ", '=');
            String ingredientName = validIn.nextLine("Ingredient (leave empty to exit): ");
            if (ingredientName == null || ingredientName.equals("")) {
                break;
            } else {
                Ingredient ingredient = ingredientsData.GetIngredient(ingredientName);
                if (ingredient == null) {
                    System.out.println("\nIngredient not found.");
                } else {
                    IngredientsListEntry ingredientsListEntry = CreateIngredientsListEntry(ingredient);
                    recipe.AddIngredientToList(ingredientsListEntry);
                    System.out.println(ingredientsListEntry.amount + " " + ingredientsListEntry.ingredient.unit + "(s)" + " of " + ingredientsListEntry.ingredient.name
                    + " was added to ingredients list.");
                }
            }
        }
    }

    private IngredientsListEntry CreateIngredientsListEntry(Ingredient ingredient) {
        Double amount;
        // Ask for different input type depending on whether the ingredient is divisible
        if (ingredient.divisible) {
            amount = validIn.nextDoubleInRange("Amount: ", 0, 9999999);
        } else {
            amount = Double.valueOf(validIn.nextIntInRange("Amount (must be an even number as ingredient is non-divisible): ", 0, 9999999));
        }
        String comment = validIn.nextLine("Comment: ");
        System.out.println("");
        return new IngredientsListEntry(ingredient, amount, comment);
    }

    private void AddInstructions() {
        prettyPrints.SurroundPrintln(" ADD INSTRUCTIONS ", '=');
        while (true) {
            String instruction = validIn.nextLine("Instruction (leave empty to exit): ");
            if (instruction != null && !instruction.isBlank()) {
                recipe.AddInstructionLine(instruction);
                System.out.println("\"" + instruction + "\"" + " was added to recipe instructions.");
            } else {
                break;
            }
        }
    }

    private void DeleteIngredients() {
        prettyPrints.SurroundPrintln(" DELETE INGREDIENTS ", '=');
        while (true) {
            String ingredientName = validIn.nextLine("Ingredient to delete from recipe (leave empty to exit): ");
            if (ingredientName != null && !ingredientName.isBlank()) {
                ArrayList<Integer> ingredientsFound = recipe.FindIngredientsInList(ingredientName);
                if (ingredientsFound.size() == 0) {
                    prettyPrints.Println(ingredientName + " wasn't found in recipe ingredients list.");
                } else if (ingredientsFound.size() == 1) {
                    recipe.DeleteIngredientFromList(ingredientsFound.get(0));
                    prettyPrints.Println(ingredientName + " was deleted from recipe.");
                } else {
                    prettyPrints.Println("Which ingredients list entry do you want to delete?\n");
                    int choiceID = 1;
                    for (int i : ingredientsFound) {
                        prettyPrints.Println(choiceID + ". " + recipe.ingredients.get(i).GetDetails());
                        choiceID++;
                    }
                    prettyPrints.Println(choiceID + ". Exit");
                    prettyPrints.Println("");
                    int ingredientToDeleteID = validIn.nextIntInRange("Select an option: ", 1, ingredientsFound.size() + 1);
                    if (ingredientToDeleteID == ingredientsFound.size() + 1) {
                        break;
                    } else {
                        recipe.DeleteIngredientFromList(ingredientsFound.get(ingredientToDeleteID - 1));
                        prettyPrints.Println(ingredientName + " was deleted from recipe.");
                    }
                }
            } else {
                break;
            }
        }
    }

    private void DeleteInstructions() {
        prettyPrints.SurroundPrintln(" DELETE INSTRUCTIONS ", '=');
        do {
            System.out.println("\n" + recipe.GetInstructionsAsString(prettyPrints) + "\n");
            int choice = validIn.nextIntInRange("Enter the index of the instruction you want to delete (enter 0 to exit): ", 0, recipe.instructions.size());
            if (choice == 0) {
                break;
            } else {
                recipe.DeleteInstructionLine(choice - 1);
                prettyPrints.Println("The instruction was deleted from the recipe.");
            }
        } while (true);
    }
}
