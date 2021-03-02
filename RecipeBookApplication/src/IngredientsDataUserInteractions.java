import java.util.Scanner;

public class IngredientsDataUserInteractions implements IUserInteractionsStrategy {

    private Scanner in;

    public IngredientsDataUserInteractions(Scanner scanner, IngredientsData ingredientsData) {
        in = scanner;
    }

    public String[] Choices() {
        return new String[]{
            "Get All Ingredients",
            "Delete Ingredient",
            "Create New Ingredient"
        };
    }

    public void MakeChoice(int choice) {
        switch (choice) {
            case 1:
                
                break;
            case 2:
            
                break;
            case 3:
                System.out.println("Please enter the details of the new ingredient");
                System.out.println("----------------------------------------------");

                System.out.print("Name: ");
                String name = in.next();
                System.out.print("Price: ");
                double price = in.nextDouble();
                System.out.print("Unit: ");
                String unit = in.next();

                Ingredient i = new Ingredient(name, price, unit);

                System.out.println("*** A new ingredient was added: " + i.GetDetails() + "***");

                break;
            default:
                break;
        }
    }
}
