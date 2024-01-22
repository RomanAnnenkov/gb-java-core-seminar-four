import exceptions.CustomerNotExistException;
import exceptions.ProductNotExistException;
import exceptions.QuantityException;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class OnlineShop {
    private static List<Customer> customerList = new ArrayList<>();
    private static List<Product> productList = new ArrayList<>();
    private static List<Order> orderList = new ArrayList<>();

    public static List<Customer> getCustomerList() {
        return customerList;
    }

    public static List<Product> getProductList() {
        return productList;
    }

    public static List<Order> getOrderList() {
        return orderList;
    }
//    public OnlineShop(List<Customer> customerList, List<Product> productList, List<Order> orderList) {
//        this.customerList = customerList;
//        this.productList = productList;
//        this.orderList = orderList;
//    }

    public static Order buyProduct(String customerFIO, String productName, String quantityOfProducts) throws QuantityException, CustomerNotExistException, ProductNotExistException {
        Customer currentCustomer = null;
        for (Customer customer : customerList) {
            if (customer.getFIO().equals(customerFIO)) {
                currentCustomer = customer;
                break;
            }
        }
        Product currentProduct = null;
        for (Product product : productList) {
            if (product.getName().equals(productName)) {
                currentProduct = product;
                break;
            }
        }
        int currentQuantity = Integer.parseInt(quantityOfProducts);
        if (currentQuantity <= 0 || currentQuantity > 100) {
            throw new QuantityException(customerFIO, productName);
        }
        if (currentCustomer == null) {
            throw new CustomerNotExistException();
        }
        if (currentProduct == null) {
            throw new ProductNotExistException();
        }

        return new Order(currentCustomer, currentProduct, currentQuantity);

    }
}
