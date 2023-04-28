import java.util.ArrayList;
import products.*;
import java.util.Scanner;
public class Cart {
    private ArrayList<PickedItem> cartList;

    private class PickedItem extends Product {
        String additionalOption;

        PickedItem(String type, String name, int price, int calorie, String additionalOption) {
            super(type, name, price, calorie);
            this.additionalOption = additionalOption;
        }

        public void setAdditionalOption(String option) {
            additionalOption = option;
        }

        public String getAdditionalOption() {
            return additionalOption;
        }
    }

    public Cart() {
        cartList = new ArrayList<>();
    }

    public void addToCart(Product item) {
        cartList.add(new PickedItem(item.getType(), item.getName(), item.getPrice(), item.getCalorie(), item.getAdditionalOption()));
    }

    public void cartInfo() {
        for(int i = 0 ; i < cartList.size() ; i++) {
            System.out.printf("(%d)\t%s\t%s\t%d원\t%s\n", i + 1, cartList.get(i).getType(), cartList.get(i).getName(), cartList.get(i).getPrice(), cartList.get(i).getAdditionalOption());
        }
    }

    public int getTotalPrice() {
        int totalPrice = 0;
        for(int i = 0 ; i < cartList.size() ; i++) {
            totalPrice += cartList.get(i).getPrice();
        }
        return totalPrice;
    }

    public int getItemQuantity() {
        return cartList.size();
    }

    //TODO: 주문조회 시 선택한 주문번호의 주문을 삭제하는 기능 추가
    public void cancelOrder(int index) {
        cartList.remove(index - 1);
    }
}
