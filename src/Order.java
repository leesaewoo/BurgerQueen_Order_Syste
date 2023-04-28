import products.*;
import discount.*;
import java.util.Scanner;
public class Order {
    public Order() {
        System.out.println("※BurgerQueen Order Service");
    }
    public void orderService() {
        MenuData menuData = new MenuData();
        Cart cart = new Cart();
        OrderLogic orderLogic = new OrderLogic();
        DiscountCondition discountCondition = new DiscountCondition();
        Scanner scanner = new Scanner(System.in);
        String tempString = "";

        while(true) {
            try {
                System.out.println("▼ Menu");
                System.out.println("------------------------------------------");
                System.out.println(menuData);
                System.out.println("(0) Show Cart");
                System.out.println("(+) Confirm Order");
                System.out.println("(-) Exit");
                System.out.println("------------------------------------------");
                System.out.print("※Choose Menu : ");

                String selectedMenu = scanner.nextLine();

                if(!selectedMenu.equals("+") && !selectedMenu.equals("-")) {
                    if(Integer.parseInt(selectedMenu) < 0 || Integer.parseInt(selectedMenu)
                            > menuData.getNumberOfMenu()) {
                        throw new Exception();
                    }
                }

                if(selectedMenu.equals("+")) {
                    //TODO:
                    //Confirm Order -> Check discount
                    //show total cost & total cost if after discount

                    if(cart.getItemQuantity() == 0) {
                        System.out.println("※Empty cart");
                    }
                    else {
                        System.out.println("※Confirmed Order.");
                        System.out.println("------------------------------------------");
                        cart.cartInfo();
                        System.out.println("------------------------------------------");
                        System.out.printf("※Total price before discount : %d\n", cart.getTotalPrice());
                        int result = discountCondition.applyDiscount(cart.getTotalPrice());

                        if(result == -1) {
                            System.out.println("※Not applicable to discount policy");
                            System.out.printf("※Total price : %d\n", cart.getTotalPrice());
                            break;
                        }
                        else {
                            System.out.printf("※Total price after discount : %d\n", result);
                            break;
                        }
                    }
                }
                else if(selectedMenu.equals("-")) {
                    System.out.println("※Program finished");
                    break;
                }
                else if(selectedMenu.equals("0")) {
                    cart.cartInfo();

                    System.out.println("※Please insert Enter to return Menu");

                    while(true) {
                        if("".equals(scanner.nextLine())) {
                            break;
                        }
                    }
                }
                else if(Integer.parseInt(selectedMenu) <= menuData.getNumberOfMenu()) {
                    while(true) {
                        try {
                            if(menuData.getProduct(Integer.parseInt(selectedMenu)).getType().equals("BURGER")) {
                                orderLogic.isCombo(menuData, selectedMenu, cart);

                                break;
                            }
                            else if(menuData.getProduct(Integer.parseInt(selectedMenu)).getType().equals("SIDES")) {

                                tempString = orderLogic.needKetchup(menuData);

                                menuData.getProduct(Integer.parseInt(selectedMenu)).setAdditionalOption(tempString);

                                cart.addToCart(menuData.getProduct(Integer.parseInt(selectedMenu)));

                                menuData.getProduct(Integer.parseInt(selectedMenu)).setAdditionalOption("");

                                break;
                            }
                            else if(menuData.getProduct(Integer.parseInt(selectedMenu)).getType().equals("DRINKS")) {

                                tempString = orderLogic.needStraw(menuData);

                                menuData.getProduct(Integer.parseInt(selectedMenu)).setAdditionalOption(tempString);

                                cart.addToCart(menuData.getProduct(Integer.parseInt(selectedMenu)));

                                menuData.getProduct(Integer.parseInt(selectedMenu)).setAdditionalOption("");

                                break;
                            }
                        }catch(Exception e) {
                            System.out.println("※Wrong input, please re-insert");
                        }
                    }

                    //콤보메뉴일 때 추가 주문
                    if(menuData.getProduct(Integer.parseInt(selectedMenu)).getType().equals("COMBO")) {
                        while(true) {
                            try {
                                System.out.println("\n※Choose Sides menu");

                                int count = 0;
                                int checkId = 0;

                                for(int i = 1 ; i <= menuData.getNumberOfMenu() ; i++) {
                                    if(menuData.getProduct(i).getType().equals("SIDES")) {
                                        count++;
                                        checkId = i;
                                        System.out.printf("(%d) %s\n", count, menuData.getProduct(i).getName());
                                    }
                                }

                                checkId = checkId - count + 1; // menuData 안에 SIDES의 KEY 값 중 제일 작은 값

                                System.out.print("\n※Choose one : ");

                                String inputSidesOption = scanner.nextLine();

                                if(Integer.parseInt(inputSidesOption) <= count && Integer.parseInt(inputSidesOption) > 0) {
                                    tempString = "Sides: " + menuData.getProduct(checkId + Integer.parseInt(inputSidesOption) - 1).getName().trim();
                                    break;
                                }
                                else {
                                    throw new Exception();
                                }
                            }catch(Exception e) {
                                System.out.println("※Wrong input, please re-insert");
                            }
                        }

                        while(true) {
                            try {
                                tempString += orderLogic.needKetchup(menuData);

                                break;
                            }catch(Exception e) {
                                System.out.println("※Wrong input, please re-insert");
                            }
                        }

                        while(true) {
                            try {
                                System.out.println("\n※Choose Drinks menu");

                                int count = 0;
                                int checkId = 0;

                                for(int i = 1 ; i <= menuData.getNumberOfMenu() ; i++) {
                                    if(menuData.getProduct(i).getType().equals("DRINKS")) {
                                        count++;
                                        checkId = i;
                                        System.out.printf("(%d) %s\n", count, menuData.getProduct(i).getName());
                                    }
                                }

                                checkId = checkId - count + 1; // menuData 안에 DRINKS의 KEY 값 중 제일 작은 값

                                System.out.print("\n※Choose one : ");

                                String inputDrinksOption = scanner.nextLine();

                                if(Integer.parseInt(inputDrinksOption) <= count && Integer.parseInt(inputDrinksOption) > 0) {
                                    tempString = tempString + ", Drinks: " + menuData.getProduct(checkId + Integer.parseInt(inputDrinksOption) - 1).getName().trim();
                                    break;
                                }
                                else {
                                    throw new Exception();
                                }
                            }catch(Exception e) {
                                System.out.println("※Wrong input, please re-insert");
                            }
                        }

                        while(true) {
                            try {
                                tempString += orderLogic.needStraw(menuData);

                                menuData.getProduct(Integer.parseInt(selectedMenu)).setAdditionalOption(tempString);

                                cart.addToCart(menuData.getProduct(Integer.parseInt(selectedMenu)));

                                menuData.getProduct(Integer.parseInt(selectedMenu)).setType("BURGER");
                                menuData.getProduct(Integer.parseInt(selectedMenu)).setPrice(menuData.getProduct(Integer.parseInt(selectedMenu)).getPrice() - 1000);
                                menuData.getProduct(Integer.parseInt(selectedMenu)).setAdditionalOption("");

                                break;
                            }catch(Exception e) {
                                System.out.println("※Wrong input, please re-insert");
                            }
                        }
                    }
                }
            }catch(Exception e) {
                System.out.println("※Wrong input, please re-insert");
            }
        }
    }

    class OrderLogic {
        public void isCombo(MenuData menuData, String selectedMenu, Cart cart) throws Exception {
            Scanner scanner = new Scanner(System.in);

            System.out.println("\n※Combo or Just a burger?\n(1) Combo ("
                    + (menuData.getProduct(Integer.parseInt(selectedMenu)).getPrice() + 1000)
                    + "원)\n(2) Just a burger ("
                    + menuData.getProduct(Integer.parseInt(selectedMenu)).getPrice()
                    + "원)\n");
            System.out.print("※Choose one : ");

            String inputBurgerOption = scanner.nextLine();

            if(inputBurgerOption.equals("1")) {
                menuData.getProduct(Integer.parseInt(selectedMenu)).setType("COMBO");
                menuData.getProduct(Integer.parseInt(selectedMenu)).setPrice(menuData.getProduct(Integer.parseInt(selectedMenu)).getPrice() + 1000);
            }
            else if(inputBurgerOption.equals("2")) {
                menuData.getProduct(Integer.parseInt(selectedMenu)).setAdditionalOption("Just a Burger");
                cart.addToCart(menuData.getProduct(Integer.parseInt(selectedMenu)));
            }
            else {
                throw new Exception();
            }
        }

        public String needKetchup(MenuData menuData) throws Exception {
            Scanner scanner = new Scanner(System.in);
            String tempString;

            System.out.println("\n※How many ketchup? (0 ~ 5)");

            System.out.print("\n※Enter number : ");

            String inputNumber = scanner.nextLine();

            if (Integer.parseInt(inputNumber) >= 0 && Integer.parseInt(inputNumber) <= 5) {
                tempString = "(Ketchup : " + inputNumber + ")";
            }
            else {
                throw new Exception();
            }

            return tempString;
        }

        public String needStraw(MenuData menuData) throws Exception {
            Scanner scanner = new Scanner(System.in);
            String tempString;

            System.out.println("\n※Need a straw?\n(1) YES\n(2) NO");

            System.out.print("\n※Choose option : ");

            String inputNeedStraw = scanner.nextLine();

            if (Integer.parseInt(inputNeedStraw) == 1) {
                tempString = "(Straw : YES)";
            } else if (Integer.parseInt(inputNeedStraw) == 2) {
                tempString = "(Straw : NO)";
            } else {
                throw new Exception();
            }

            return tempString;
        }
    }
}