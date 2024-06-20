package gc.order_app.model;

import java.math.BigDecimal;
import java.text.NumberFormat;

public enum PizzaSize {
    SMALL("Small", new BigDecimal(7.00)),
    MEDIUM("Medium", new BigDecimal(10.00)),
    LARGE("Large", new BigDecimal(13.00)),
    EXTRA_LARGE("Extra Large", new BigDecimal(15.00));

    private String name;
    private BigDecimal price;
    private static final NumberFormat CURRENCY = NumberFormat.getCurrencyInstance();

    PizzaSize(String sizeName, BigDecimal price) {
        this.name = sizeName;
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
        return name + " (" + CURRENCY.format(getPrice()) + ")";
    }
}
