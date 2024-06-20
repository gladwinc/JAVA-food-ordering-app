    package gc.order_app.controller;

    import javafx.collections.FXCollections;
    import javafx.collections.ObservableList;
    import javafx.event.ActionEvent;
    import javafx.fxml.FXML;
    import javafx.scene.control.*;

    import java.util.*;

    import gc.order_app.model.*;

    public class PizzaOrderController {

        @FXML private CheckBox cbAnchovies;
        @FXML private CheckBox cbBacon;
        @FXML private CheckBox cbDriedShrimp;
        @FXML private CheckBox cbExtraCheese;
        @FXML private CheckBox cbGrilledChicken;
        @FXML private CheckBox cbGroundBeef;
        @FXML private CheckBox cbHam;
        @FXML private CheckBox cbJalapeno;
        @FXML private CheckBox cbMushroom;
        @FXML private CheckBox cbPepperoni;
        @FXML private CheckBox cbPineapple;
        @FXML private CheckBox cbRoastedGarlic;
        @FXML private CheckBox cbShreddedChicken;
        @FXML private CheckBox cbSpinach;
        @FXML private CheckBox cbSunDriedTomatoes;

        @FXML private ChoiceBox<PizzaSize> cbPizzaSize;

        @FXML
        private ChoiceBox<String> cbCrustType;
        private final String[] crustTypes = {"Normal", "Thin", "Deep Dish"};
        private final ObservableList<String> crustTypeList = FXCollections.observableArrayList(crustTypes);

        @FXML
        private TextArea txtAreaOrderSummary;

        @FXML
        private TextField txtFieldCustomerName;

        @FXML
        private TextField txtFieldPhoneNumber;

        @FXML
        private TextField txtFieldQuantity;

        @FXML
        private CheckBox[] toppings;

        private Map<CheckBox, Topping> toppingsMap;

        @FXML
        public void initialize() {
            cbCrustType.setItems(crustTypeList);
            cbPizzaSize.setItems(FXCollections.observableArrayList(PizzaSize.values()));
            toppings = new CheckBox[]{
                    cbAnchovies, cbBacon, cbDriedShrimp, cbExtraCheese, cbGrilledChicken,
                    cbGroundBeef, cbHam, cbJalapeno, cbMushroom, cbPepperoni, cbPineapple,
                    cbRoastedGarlic, cbShreddedChicken, cbSpinach, cbSunDriedTomatoes
            };
            setToppingsMap();
        }

        private void setToppingsMap() {
            toppingsMap = new HashMap<>();
            toppingsMap.put(cbAnchovies, Topping.ANCHOVIES);
            toppingsMap.put(cbBacon, Topping.BACON);
            toppingsMap.put(cbDriedShrimp, Topping.DRIED_SHRIMP);
            toppingsMap.put(cbExtraCheese, Topping.EXTRA_CHEESE);
            toppingsMap.put(cbGrilledChicken, Topping.GRILLED_CHICKEN);
            toppingsMap.put(cbGroundBeef, Topping.GROUND_BEEF);
            toppingsMap.put(cbHam, Topping.HAM);
            toppingsMap.put(cbJalapeno, Topping.JALAPENO);
            toppingsMap.put(cbMushroom, Topping.MUSHROOM);
            toppingsMap.put(cbPepperoni, Topping.PEPPERONI);
            toppingsMap.put(cbPineapple, Topping.PINEAPPLE);
            toppingsMap.put(cbRoastedGarlic, Topping.ROASTED_GARLIC);
            toppingsMap.put(cbShreddedChicken, Topping.SHREDDED_CHICKEN);
            toppingsMap.put(cbSpinach, Topping.SPINACH);
            toppingsMap.put(cbSunDriedTomatoes, Topping.SUN_DRIED_TOMATOES);
        }

        @FXML
        void handleClearButtonAction(ActionEvent event) {
            txtFieldCustomerName.clear();
            txtFieldPhoneNumber.clear();
            cbPizzaSize.setValue(null);
            cbCrustType.setValue(null);
            txtFieldQuantity.clear();
            txtAreaOrderSummary.clear();

            for (CheckBox topping : toppings) {
                topping.setSelected(false);
            }
        }

        @FXML
        void handleOrderButtonAction(ActionEvent event) {
            try {
                if (txtFieldCustomerName.getText().isEmpty() || txtFieldPhoneNumber.getText().isEmpty() ||
                        cbPizzaSize.getValue() == null || cbCrustType.getValue() == null || txtFieldQuantity.getText().isEmpty()) {
                    throw new IllegalArgumentException("All fields must be filled.");
                }

                PizzaOrder order = getPizzaOrder();
                String orderSummary = order.displayOrderSummary();
                txtAreaOrderSummary.setText("Confirming...");

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirm Order");
                alert.setHeaderText("Please confirm your order:");
                alert.setContentText(orderSummary + "\nPress OK to confirm, or CANCEL to go back.\n");

                alert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        txtAreaOrderSummary.setText("Order confirmed. Thank you!" + "\n\n" + orderSummary);
                        clearFields();
                    } else if (response == ButtonType.CANCEL) {
                        // Exit alert dialog without doing anything
                    }
                });

            } catch (NumberFormatException e) {
                txtAreaOrderSummary.setText("Quantity must be a number.");
            } catch (IllegalArgumentException e) {
                txtAreaOrderSummary.setText(e.getMessage());
            }
        }

        private PizzaOrder getPizzaOrder() {
            String customerName = txtFieldCustomerName.getText();
            String phoneNumber = txtFieldPhoneNumber.getText();
            int quantity = Integer.parseInt(txtFieldQuantity.getText());

            if (quantity <= 0) {
                throw new IllegalArgumentException("Quantity must be greater than 0.");
            }

            Customer customer = new Customer(customerName, phoneNumber);
            PizzaSize selectedPizzaSize = cbPizzaSize.getValue();
            String crustType = cbCrustType.getValue();
            Set<Topping> selectedToppings = getSelectedToppings();

            PizzaOrder order = new PizzaOrder(selectedPizzaSize, selectedToppings, crustType, quantity, customer);
            return order;
        }

        private Set<Topping> getSelectedToppings() {
            Set<Topping> selectedToppings = new HashSet<>();
            for (CheckBox checkbox : toppings) {
                if (checkbox.isSelected()) {
                    selectedToppings.add(toppingsMap.get(checkbox));
                }
            }
            return selectedToppings;
        }

        private void clearFields() {
            txtFieldCustomerName.clear();
            txtFieldPhoneNumber.clear();
            cbPizzaSize.setValue(null);
            cbCrustType.setValue(null);
            txtFieldQuantity.clear();
            for (CheckBox topping : toppings) {
                topping.setSelected(false);
            }
        }

    }