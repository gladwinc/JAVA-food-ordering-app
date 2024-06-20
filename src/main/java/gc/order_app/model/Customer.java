package gc.order_app.model;

public class Customer {

    private String name;
    private String phoneNumber;

    public Customer(String customerName, String phoneNumber) {
        this.name = customerName;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
