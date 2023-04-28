package discount;
import java.util.ArrayList;

public class DiscountEventList {
    private ArrayList<DiscountPolicy> discountMenuList = new ArrayList<>(){{
        add(new FixedRateDiscount("CodestatesDiscount", "RATE", 10, true));
        add(new FixedAmountDiscount("KidsDiscount", "AMOUNT", 500, true));
    }};

    public DiscountPolicy getDiscountPolicy(int index) {
        return discountMenuList.get(index);
    }

    public int getTotalEventNumber() {
        return discountMenuList.size();
    }
}
