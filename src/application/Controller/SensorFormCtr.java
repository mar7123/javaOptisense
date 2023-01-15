package application.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class SensorFormCtr {

    @FXML
    private Button addSensorBttn;

    @FXML
    private Button deleteSensorBttn;

    @FXML
    private Button insertSensorBttn;

    @FXML
    private TextField newSensorName;

    @FXML
    private Spinner<?> newSensorPrice;

    @FXML
    private Spinner<?> newSensorSpeed;

    @FXML
    private Spinner<?> newSensorStock;

    @FXML
    private TextField sensorName;

    @FXML
    private TextField sensorPrice;

    @FXML
    private Spinner<?> sensorQty;

    @FXML
    private TextField sensorSpeed;

    @FXML
    private TextField sensorStock;

    @FXML
    private TableView<?> sensorTable;

    @FXML
    private TextField sensorType;

    @FXML
    private ToggleGroup sensortype;

    @FXML
    void addSensorPressed(ActionEvent event) {

    }

    @FXML
    void deleteSensorPressed(ActionEvent event) {

    }

    @FXML
    void insertNewSensorPressed(ActionEvent event) {

    }

}
