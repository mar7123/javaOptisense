package application.Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import application.DBConnection;
import application.model.Orders;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class OrderFormCtr {

    @FXML
    private Button cancelOrderBttn;

    @FXML
    private Button makeOrderBttn;

    @FXML
    private ComboBox<?> newOrderCombo;

    @FXML
    private Spinner<Integer> newOrderQty;

    @FXML
    private Spinner<Integer> orderQty;

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
    
	ObservableList<Orders> listview = FXCollections.observableArrayList();

    
    private void loadTableData() {
    	orderTable.getItems().clear();
    	orderTable.setRowFactory(rf -> {
			TableRow<Orders> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (!row.isEmpty()) {
					Orders selected = row.getItem();
					orderVendor.setText(selected.getVendorName());
					orderSensor.setText(selected.getSensorName());
					updateOrderBttn.setVisible(true);
					cancelOrderBttn.setVisible(true);
				}
			});
			return row;
		});
    	vendorColumn.setCellValueFactory(new PropertyValueFactory<>("SensorName"));
    	productColumn.setCellValueFactory(new PropertyValueFactory<>("SensorPrice"));
    	orderQtyColumn.setCellValueFactory(new PropertyValueFactory<>("SensorSpeed"));
    	totalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("SensorStock"));
		try {
			Connection c = DBConnection.getKoneksi();
			PreparedStatement statement = c.prepareStatement("SELECT * FROM orders");
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				listview.add(new Orders(rs.getInt("OrderID"), rs.getInt("SensorID"), rs.getString("ClientCode"),
						rs.getInt("OrderQuantity")));
			}
			orderTable.setItems(listview);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    void loadInterface() {
    	this.loadTableData();
    	orderVendor.setText("");
    	orderSensor.setText("");
    	updateOrderBttn.setVisible(true);
    	cancelOrderBttn.setVisible(true);
    	orderQty.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10000, 0));
    	newOrderQty.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10000, 0));
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
