package products;

public class Drinks extends Product {
    String straw;

    public Drinks(String type, String name, int price, int calorie, String straw) {
        super(type, name, price, calorie);
        this.straw = straw;
    }

    public void setAdditionalOption(String option) {
        this.straw = option;
    };

    public String getAdditionalOption() {
        return straw;
    };
}
