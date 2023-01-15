package application.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class BuyMenuCtr {

    @FXML
    private Button addCartBttn;

    @FXML
    private Button buyBttn;

    @FXML
    private TableView<?> cartTable;

    @FXML
    private Button closeFormBttn;

    @FXML
    private Button removeTshirtBttn;

    @FXML
    private TextField tshirtIDField;

    @FXML
    private TextField tshirtNameField;

    @FXML
    private TextField tshirtPriceField;

    @FXML
    private Spinner<?> tshirtQtField;

    @FXML
    private TableView<?> tshrtTable;

    @FXML
    private Button updateTshirtBttn;
    
    void loadData(TableView<?> tableData, String tableName) {
    	
    }

    @FXML
    void BuyBttnPressed(ActionEvent event) {

    }

    @FXML
    void addCartPressed(ActionEvent event) {

    }

    @FXML
    void closeFormPressed(ActionEvent event) {

    }

    @FXML
    void removeTShirtPressed(ActionEvent event) {

    }

    @FXML
    void updateTShirtPressed(ActionEvent event) {

    }

}
