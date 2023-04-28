package products;

public class Sides extends Product {
    String ketchup;

    public Sides(String type, String name, int price, int calorie, String ketchup) {
        super(type, name, price, calorie);
        this.ketchup = ketchup;
    }

    public void setAdditionalOption(String option) {
        this.ketchup = option;
    };

    public String getAdditionalOption() {
        return ketchup;
    };
}
