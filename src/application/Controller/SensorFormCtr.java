package application.Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import application.model.Sensor;
import application.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableRow;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;

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
	private TableColumn<Sensor, String> sensorNameColumn;

	@FXML
	private TextField sensorPrice;

	@FXML
	private TableColumn<Sensor, String> sensorPriceColumn;

	@FXML
	private Spinner<?> sensorQty;

	@FXML
	private TextField sensorSpeed;

	@FXML
	private TableColumn<Sensor, String> sensorSpeedColumn;

	@FXML
	private TextField sensorStock;

	@FXML
	private TableColumn<Sensor, String> sensorStockColumn;

	@FXML
	private TableView<Sensor> sensorTable;

	@FXML
	private TextField sensorType;

	@FXML
	private ToggleGroup sensortype;

	ObservableList<Sensor> listview = FXCollections.observableArrayList();

	/**
	 * 
	 */
	void loadTableData() {
		sensorTable.getItems().clear();
		sensorTable.setRowFactory(rf -> {
			TableRow<Sensor> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (!row.isEmpty()) {
					Sensor selected = row.getItem();
					sensorName.setText(selected.getSensorName());
					sensorType.setText(selected.getSensorType());
					sensorSpeed.setText(selected.getSensorSpeed());
					sensorPrice.setText(selected.getSensorPrice());
					sensorStock.setText(selected.getSensorStock());
					addSensorBttn.setVisible(true);
					deleteSensorBttn.setVisible(true);
				}
			});
//			row.setOnMouseExited(event -> {
//				sensorName.setText("");
//			});
			return row;
		});
		sensorNameColumn.setCellValueFactory(new PropertyValueFactory<>("SensorName"));
		sensorPriceColumn.setCellValueFactory(new PropertyValueFactory<>("SensorPrice"));
		sensorSpeedColumn.setCellValueFactory(new PropertyValueFactory<>("SensorSpeed"));
		sensorStockColumn.setCellValueFactory(new PropertyValueFactory<>("SensorStock"));
		try {
			Connection c = DBConnection.getKoneksi();
			PreparedStatement statement = c.prepareStatement("SELECT * FROM sensors");
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				listview.add(new Sensor(rs.getInt("SensorID"), rs.getString("VendorCode"), rs.getString("SensorName"),
						rs.getString("SensorType"), rs.getInt("SensorSpeed"), rs.getInt("SensorPrice"),
						rs.getInt("SensorStock")));
			}
			sensorTable.setItems(listview);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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
