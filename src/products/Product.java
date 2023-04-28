package products;

public abstract class Product {
    private String type;

    private String name;

    private int price;

    private int calorie;

    public Product(String type, String name, int price, int calorie) {
        this.type = type;
        this.name = name;
        this.price = price;
        this.calorie = calorie;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCalorie() {
        return calorie;
    }

    public abstract void setAdditionalOption(String option);

    public abstract String getAdditionalOption();

    @Override
    public String toString() {
        return name + "\t" + calorie + "Kcal\t" + price + "원";
    }
}
