import exceptions.CustomerNotExistException;
import exceptions.ProductNotExistException;
import exceptions.QuantityException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) throws CustomerNotExistException, ProductNotExistException, QuantityException {
        System.out.println("online shop");

        Customer femaleCustomer = new Customer("Svetlana Sidorova", LocalDate.of(1990, 5, 25), "+78593456376", Gender.OTHER);
        femaleCustomer.setGender(Gender.FEMALE);
        OnlineShop.getCustomerList().add(femaleCustomer);
        OnlineShop.getCustomerList().add(new Customer("Ivanov Ivan", LocalDate.of(1987, 12, 9), "+9867566473", Gender.MALE));
        OnlineShop.getCustomerList().add(new Customer("Petrov Petr", LocalDate.of(1988, 12, 20), "+9867566475", Gender.MALE));
        OnlineShop.getProductList().add(new Product("apple", BigDecimal.valueOf(10L)));
        OnlineShop.getProductList().add(new Product("pineapple", BigDecimal.valueOf(50L)));


        try {
            Order order = null;
            order = OnlineShop.buyProduct("Ivanov Ivan", "apple", "20");


            OnlineShop.getOrderList().add(order);
            System.out.println(OnlineShop.getOrderList());

            Order order2 = OnlineShop.buyProduct("Ivanov Ivan", "apple", "20");
            OnlineShop.getOrderList().add(order2);
            System.out.println(OnlineShop.getOrderList());

            Order order3 = OnlineShop.buyProduct("Petrov Petr", "pineapple", "300");
            OnlineShop.getOrderList().add(order3);


        } catch (QuantityException e) {
            OnlineShop.getOrderList().add(OnlineShop.buyProduct(e.getCustomer(), e.getProduct(), "1"));
        } catch (ProductNotExistException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            throw e;
        }

        System.out.println(OnlineShop.getOrderList());
        System.out.println(OnlineShop.getOrderList().size() + " orders received");

        congratulateWithHolidays(OnlineShop.getCustomerList());

    }

    public static void congratulateWithHolidays(List<Customer> customers) {
        LocalDate todayDate = LocalDate.now();
        Holiday holiday = Holiday.NONE;
        if (todayDate.getDayOfMonth() == 1 && todayDate.getMonthValue() == 1) {
            holiday = Holiday.NEW_YEAR;
        }
        if (todayDate.getDayOfMonth() == 8 && todayDate.getMonthValue() == 3) {
            holiday = Holiday.EIGHT_OF_MARCH;
        }
        if (todayDate.getDayOfMonth() == 23 && todayDate.getMonthValue() == 2) {
            holiday = Holiday.TWENTY_THREE_OF_FEBRUARY;
        }

        for (Customer customer : customers) {
            switch (holiday) {
                case NEW_YEAR:
                    System.out.println(customer.getFIO() + " happy new year!");
                    break;
                case TWENTY_THREE_OF_FEBRUARY:
                    if (customer.getGender() == Gender.MALE) {
                        System.out.println(customer.getFIO() + " happy february 23!");
                    }
                    break;
                case EIGHT_OF_MARCH:
                    if (customer.getGender() == Gender.FEMALE) {
                        System.out.println(customer.getFIO() + " happy march 8!");
                    }
                    break;
            }
        }

    }


}
