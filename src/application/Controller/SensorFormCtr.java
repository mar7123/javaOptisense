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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
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
	private Spinner<Integer> newSensorPrice;

	@FXML
	private Spinner<Integer> newSensorSpeed;

	@FXML
	private Spinner<Integer> newSensorStock;

	@FXML
	private TextField sensorName;

	@FXML
	private TableColumn<Sensor, String> sensorNameColumn;

	@FXML
	private TextField sensorPrice;

	@FXML
	private TableColumn<Sensor, String> sensorPriceColumn;

	@FXML
	private Spinner<Integer> sensorQty;

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

	public SensorFormCtr() {
//		sensorQty.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 20, 3));
	}

	private void loadTableData() {
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
					sensorQty.setEditable(true);
				}
			});
			row.setOnMouseReleased(event -> {
				sensorName.setText("");
			});
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
	
	void loadInterface() {
		this.loadTableData();
		sensorName.setText("");
		sensorType.setText("");
		sensorSpeed.setText("");
		sensorPrice.setText("");
		sensorStock.setText("");
		addSensorBttn.setVisible(false);
		deleteSensorBttn.setVisible(false);
		sensorQty.setEditable(false);
		sensorQty.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10000, 0));
		newSensorPrice.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10000, 0));
		newSensorSpeed.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10000, 0));
		newSensorStock.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10000, 0));
	}

	@FXML
	void addSensorPressed(ActionEvent event) {
		if(sensorQty.getValue() <= 0) {
			Alert alert = new Alert(Alert.AlertType.ERROR, "Number of added stock must be greater than 0.");
            alert.show();
		}else {
			try {
				Connection c = DBConnection.getKoneksi();
				PreparedStatement statement = c.prepareStatement("UPDATE sensors SET SensorStock = ? WHERE SensorID = ?");
				Sensor selected = sensorTable.getSelectionModel().getSelectedItem();
				statement.setInt(1, selected.getSensorStockInt() + sensorQty.getValue());
				statement.setString(2, selected.getSensorID());
				statement.execute();
			} catch (Exception e) {
				e.printStackTrace();
			}
			Alert alert = new Alert(Alert.AlertType.INFORMATION, "Stock has been added");
            alert.show();
			this.loadInterface();
		}
	}

	@FXML
	void deleteSensorPressed(ActionEvent event) {
		try {
			Connection c = DBConnection.getKoneksi();
			PreparedStatement statement = c.prepareStatement("DELETE FROM sensors WHERE SensorID = ?");
			Sensor selected = sensorTable.getSelectionModel().getSelectedItem();
			statement.setString(1, selected.getSensorID());
			statement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Alert alert = new Alert(Alert.AlertType.INFORMATION, "Sensor deleted");
        alert.show();
		this.loadInterface();
	}

	@FXML
	void insertNewSensorPressed(ActionEvent event) {
		if(newSensorName.getText().isEmpty()) {
			Alert alert = new Alert(Alert.AlertType.ERROR, "Sensor Name must be inserted");
	        alert.show();
		}
	}

}
