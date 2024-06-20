package gc.order_app.model;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Set;

public class PizzaOrder {
    private PizzaSize size;
    private Set<Topping> toppings;
    private String crustType;
    private int quantity;
    private BigDecimal totalPrice;
    private Customer customer;
    private static final NumberFormat CURRENCY = NumberFormat.getCurrencyInstance();
    private BigDecimal taxRate = new BigDecimal(0.13);

    public PizzaOrder(PizzaSize size, Set<Topping> toppings, String crustType, int quantity, Customer customer) {
        this.size = size;
        this.toppings = toppings;
        this.crustType = crustType;
        this.quantity = quantity;
        this.totalPrice = calculateTotalPrice();
        this.customer = customer;
    }

    public PizzaSize getSize() {
        return size;
    }

    public Set<Topping> getToppings() {
        return toppings;
    }

    public String getCrustType() {
        return crustType;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal calculateTotalPrice() {
        BigDecimal total = size.getPrice();
        for (Topping topping : toppings) {
            total = total.add(topping.getPrice());
        }
        return total;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String displayOrderSummary() {
        StringBuilder orderSummary = new StringBuilder();
        BigDecimal subtotal = calculateTotalPrice().multiply(new BigDecimal(quantity));

        orderSummary.append("Customer Name: ").append(customer.getName()).append("\n")
                .append("Phone Number: ").append(customer.getPhoneNumber()).append("\n");
        orderSummary.append("----------------------------------\n");

        orderSummary.append("Order:\n");
        orderSummary.append("Size: ").append(size).append("\n");
        orderSummary.append("Crust Type: ").append(crustType).append("\n \n");

        orderSummary.append("Toppings:\n");
        int i = 1;
        for (Topping topping : toppings) {
            orderSummary.append(i++).append(". ").append(topping).append("\n");
        }

        orderSummary.append("\nTotal: ").append(CURRENCY.format(calculateTotalPrice())).append("\n");
        orderSummary.append("Quantity: ").append(quantity).append("\n");
        orderSummary.append("----------------------------------\n");

        BigDecimal tax = subtotal.multiply(taxRate);
        BigDecimal grandTotal = subtotal.add(tax);

        orderSummary.append("Subtotal: ").append(CURRENCY.format(subtotal)).append("\n");
        orderSummary.append("Tax (13%): ").append(CURRENCY.format(tax)).append("\n");
        orderSummary.append("Grand Total: ").append(CURRENCY.format(grandTotal)).append("\n");

        return orderSummary.toString();
    }
}
