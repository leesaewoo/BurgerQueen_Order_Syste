package discount;

public abstract class DiscountPolicy {
    private String name;

    private String type;

    private boolean condition;

    private boolean apply;

    public DiscountPolicy(String name, String type, boolean applyIt) {
        this.name = name;
        this.type = type;
        this.apply = applyIt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isCondition() {
        return condition;
    }

    public void setCondition(boolean condition) {
        this.condition = condition;
    }

    public boolean isApplyIt() {
        return apply;
    }

    public void setApplyIt(boolean apply) {
        this.apply = apply;
    }

    public abstract void setDiscountNumber(int num);

    public abstract int getDiscountNumber();
}
