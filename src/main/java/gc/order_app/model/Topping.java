package gc.order_app.model;

import java.math.BigDecimal;
import java.text.NumberFormat;


public enum Topping {
    ANCHOVIES("Anchovies", new BigDecimal(1.10)),
    BACON("Bacon", new BigDecimal(2.15)),
    DRIED_SHRIMP("Dried Shrimp", new BigDecimal(1.10)),
    EXTRA_CHEESE("Extra Cheese", new BigDecimal(1.10)),
    GRILLED_CHICKEN("Grilled Chicken", new BigDecimal(2.15)),
    GROUND_BEEF("Ground Beef", new BigDecimal(2.15)),
    HAM("Ham", new BigDecimal(2.15)),
    JALAPENO("Jalapeno", new BigDecimal(1.10)),
    MUSHROOM("Mushroom", new BigDecimal(1.10)),
    PEPPERONI("Pepperoni", new BigDecimal(2.15)),
    PINEAPPLE("Pineapple", new BigDecimal(1.10)),
    ROASTED_GARLIC("Roasted Garlic", new BigDecimal(1.10)),
    SHREDDED_CHICKEN("Shredded Chicken", new BigDecimal(2.15)),
    SPINACH("Spinach", new BigDecimal(1.10)),
    SUN_DRIED_TOMATOES("Sun Dried Tomato", new BigDecimal(1.10));

    private static final NumberFormat CURRENCY = NumberFormat.getCurrencyInstance();
    private String name;
    private BigDecimal price;

    Topping(String toppingName, BigDecimal price) {
        this.name = toppingName;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return name + " (" + CURRENCY.format(price) + ")";
    }
}
