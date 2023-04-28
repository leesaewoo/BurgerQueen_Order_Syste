package products;

public class Burger extends Product {
    String isCombo;

    public Burger(String type, String name, int price, int calorie, String isCombo) {
        super(type, name, price, calorie);
        this.isCombo = isCombo;
    }

    public void setAdditionalOption(String option) {
        this.isCombo = option;
    };

    public String getAdditionalOption() {
        return isCombo;
    };
}
