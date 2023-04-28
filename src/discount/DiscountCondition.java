package discount;
import java.util.Scanner;

public class DiscountCondition {
    public int applyDiscount(int totalPrice) {
        DiscountEventList discountList = new DiscountEventList();
        double discountRate = 0;
        int discountAmount = 0;

        checkDiscountCondition(discountList);

        int count = 0;
        for(int i = 0 ; i < discountList.getTotalEventNumber() ; i++) {
            if(discountList.getDiscountPolicy(i).isApplyIt() == false
                    || (discountList.getDiscountPolicy(i).isCondition() == false && discountList.getDiscountPolicy(i).isApplyIt() == true)) {
                count++;
            }
        }

        if(count == discountList.getTotalEventNumber()) {
            return -1;
        }

        for(int i = 0 ; i < discountList.getTotalEventNumber() ; i++) {
            if(discountList.getDiscountPolicy(i).getType().equals("AMOUNT")
                    && discountList.getDiscountPolicy(i).isCondition() == true) {
                discountAmount += discountList.getDiscountPolicy(i).getDiscountNumber();
            }
            else if(discountList.getDiscountPolicy(i).getType().equals("RATE")
                    && discountList.getDiscountPolicy(i).isCondition() == true) {
                discountRate += discountList.getDiscountPolicy(i).getDiscountNumber();
            }
        }

        if(totalPrice <= discountAmount) {
            return 0;
        }
        else {
            double result = (double)(totalPrice - discountAmount) * ((100 - discountRate) / 100);
            return (int)result;
        }
    }

    public void checkDiscountCondition(DiscountEventList discountList) {
        Scanner scanner = new Scanner(System.in);

        for(int i = 0 ; i < discountList.getTotalEventNumber() ; i++) {
            while(true) {
                try {
                    if(discountList.getDiscountPolicy(i).getName().equals("CodestatesDiscount")
                            && discountList.getDiscountPolicy(i).isApplyIt() == true) {
                        System.out.println("\n※Are you coursed in CodeStates Boot Camp?\n(1) YES   (2) NO");
                        System.out.print("\n※Choose option : ");
                        String inputNum = scanner.nextLine();

                        if(inputNum.equals("1")) {
                            discountList.getDiscountPolicy(i).setCondition(true);
                            break;
                        }
                        else if(inputNum.equals("2")) {
                            discountList.getDiscountPolicy(i).setCondition(false);
                            break;
                        }
                        else {
                            throw new Exception();
                        }
                    }
                    else if(discountList.getDiscountPolicy(i).getName().equals("KidsDiscount")
                            && discountList.getDiscountPolicy(i).isApplyIt() == true) {
                        System.out.println("\n※Enter your age");
                        System.out.print("\n※Your age : ");
                        int inputAge = Integer.parseInt(scanner.nextLine());
                        if(inputAge <= 20 && inputAge > 0) {
                            discountList.getDiscountPolicy(i).setCondition(true);
                            break;
                        }
                        else if(inputAge > 20) {
                            discountList.getDiscountPolicy(i).setCondition(false);
                            break;
                        }
                        else {
                            throw new Exception();
                        }
                    }
                }catch(Exception e) {
                    System.out.println("※Wrong input, please re-insert");
                }
            }
        }
    }
}
