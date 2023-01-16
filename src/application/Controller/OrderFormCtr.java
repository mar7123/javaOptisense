package application.Controller;

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
    private TableColumn<?, ?> orderQtyColumn;

    @FXML
    private TextField orderSensor;

    @FXML
    private TableView<?> orderTable;

    @FXML
    private TextField orderVendor;

    @FXML
    private TableColumn<?, ?> productColumn;

    @FXML
    private TableColumn<?, ?> totalPriceColumn;

    @FXML
    private Button updateOrderBttn;

    @FXML
    private TableColumn<?, ?> vendorColumn;

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
