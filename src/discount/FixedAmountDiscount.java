package discount;

public class FixedAmountDiscount extends DiscountPolicy {
    private int discountAmount = 0;

    public FixedAmountDiscount(String name, String type, int discountAmount, boolean apply) {
        super(name, type, apply);
        this.discountAmount = discountAmount;
    }

    public void setDiscountNumber(int discountAmount) {
        this.discountAmount = discountAmount;
    }

    public int getDiscountNumber() {
        return discountAmount;
    }
}
