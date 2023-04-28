package discount;

public class FixedRateDiscount extends DiscountPolicy {
    private int discountRate = 1;

    public FixedRateDiscount(String name, String type, int discountRate, boolean apply) {
        super(name, type, apply);
        this.discountRate = discountRate;
    }

    public void setDiscountNumber(int discountRate) {
        this.discountRate = discountRate;
    }

    public int getDiscountNumber() {
        return discountRate;
    }
}
