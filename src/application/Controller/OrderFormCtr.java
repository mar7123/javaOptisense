package application.Controller;

import application.model.Orders;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class OrderFormCtr {

    @FXML
    private Button cancelOrderBttn;

    @FXML
    private Button makeOrderBttn;

    @FXML
    private ComboBox<?> newOrderCombo;

    @FXML
    private Spinner<?> newOrderQty;

    @FXML
    private Spinner<?> orderQty;

    @FXML
    private TableColumn<Orders, String> orderQtyColumn;

    @FXML
    private TextField orderSensor;

    @FXML
    private TableView<Orders> orderTable;

    @FXML
    private TextField orderVendor;

    @FXML
    private TableColumn<Orders, String> productColumn;

    @FXML
    private TableColumn<Orders, String> totalPriceColumn;

    @FXML
    private Button updateOrderBttn;

    @FXML
    private TableColumn<Orders, String> vendorColumn;
    
    void loadInterface() {
    	
    }

    @FXML
    void cancelOrderPressed(ActionEvent event) {

    }

    @FXML
    void makeOrderPressed(ActionEvent event) {

    }

    @FXML
    void updateOrderPressed(ActionEvent event) {

    }

}
