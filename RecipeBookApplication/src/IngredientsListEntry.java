public class IngredientsListEntry {

    public Ingredient ingredient;
    public double amount;
    public String comment;
    
    public IngredientsListEntry(Ingredient i, double a, String c) {
        ingredient = i;
        amount = a;
        comment = c;
    }

    public String GetDetails(double adjustPortions) {
        double ingredientAmount = (amount * adjustPortions);
        // If ingredient is non-divisible, round to the nearest integer above
        if (!ingredient.divisible) {
            ingredientAmount = Math.ceil(ingredientAmount);
        }
        String ingredientString = ingredientAmount + " " + ingredient.unit + "(s) of " + ingredient.name;
        if (comment != null && !comment.isBlank()) {
            ingredientString += " - " + comment;
        }
        return ingredientString;
    }

    public String GetDetails() {
        return GetDetails(1);
    }
}
