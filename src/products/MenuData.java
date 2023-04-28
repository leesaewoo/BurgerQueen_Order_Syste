package products;
import java.util.HashMap;

public class MenuData {
    private HashMap<Integer, Product> menuMap = new HashMap<>(){{
        put(1, new Burger("BURGER", "    SHRIMP BURGER    ", 3500, 500, ""));
        put(2, new Burger("BURGER", "   CHICKEN BURGER    ", 4000, 600, ""));
        put(3, new Sides("SIDES", "     FRENCH FRIES    ", 1000, 300, ""));
        put(4, new Sides("SIDES", "      ONION RINGS    ", 1000, 300, ""));
        put(5, new Drinks("DRINKS", "      COCA COLA      ", 1000, 200, ""));
        put(6, new Drinks("DRINKS", "COCA COLA(ZERO SUGAR)", 1000, 0, ""));
    }};

    public Product getProduct(int id) {
        return menuMap.get(id);
    }

    public int getNumberOfMenu() {
        return menuMap.size();
    }

    @Override
    public String toString() {
        return "Burger"
                + "\n(1)\t" + menuMap.get(1)
                + "\n(2)\t" + menuMap.get(2)
                + "\n\nSides"
                + "\n(3)\t" + menuMap.get(3)
                + "\n(4)\t" + menuMap.get(4)
                + "\n\nDrinks"
                + "\n(5)\t" + menuMap.get(5)
                + "\n(6)\t" + menuMap.get(6)
                + "\n\n";
    }
}
