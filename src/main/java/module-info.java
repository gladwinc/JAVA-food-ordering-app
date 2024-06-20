module gc.workshop_2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens gc.order_app to javafx.fxml;
    exports gc.order_app;
    exports gc.order_app.controller;
    opens gc.order_app.controller to javafx.fxml;
}