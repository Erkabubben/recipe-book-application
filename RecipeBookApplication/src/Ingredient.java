public class Ingredient {

    public static String CURRENCY_SIGN = "$";
    public String name;
    public double price;
    public String unit;

    public Ingredient(String n, double p, String u) {
        name = n;
        price = p;
        unit = u;
    }

    public String GetDetails() {
        return name + "(" + price + CURRENCY_SIGN + "/ " + unit + ")"; 
    }
}
