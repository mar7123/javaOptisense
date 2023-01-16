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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class OrderFormCtr {

    @FXML
    private Button cancelOrderBttn;

    @FXML
    private Button makeOrderBttn;

    @FXML
    private ComboBox<String> newSensorCombo;

    @FXML
    private ComboBox<String> newVendorCombo;

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
	
	private int sensorStock;

    private void loadTableData() {
    	orderTable.getItems().clear();
    	orderTable.setRowFactory(rf -> {
			TableRow<Orders> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (!row.isEmpty()) {
					Orders selected = row.getItem();
					orderVendor.setText(selected.getVendorName());
					orderSensor.setText(selected.getSensorName());
					orderQty.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, selected.getSensorStock() - selected.getOrderQuantityInt(), selected.getOrderQuantityInt()));
					updateOrderBttn.setVisible(true);
					cancelOrderBttn.setVisible(true);
				}
			});
			return row;
		});
    	vendorColumn.setCellValueFactory(new PropertyValueFactory<>("VendorName"));
    	productColumn.setCellValueFactory(new PropertyValueFactory<>("SensorName"));
    	orderQtyColumn.setCellValueFactory(new PropertyValueFactory<>("OrderQuantity"));
    	totalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("TotalPrice"));
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
    	listview.clear();
    	this.loadTableData();
    	this.loadComboBoxData();
    	orderVendor.setText("");
    	orderSensor.setText("");
    	updateOrderBttn.setVisible(false);
    	cancelOrderBttn.setVisible(false);
    	newVendorCombo.setValue(null);
    	newSensorCombo.setValue(null);
    	orderQty.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10000, 0));
    	newOrderQty.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10000, 0));
    }

    @FXML
    void cancelOrderPressed(ActionEvent event) {
    	try {
			Connection c = DBConnection.getKoneksi();
			PreparedStatement statement = c.prepareStatement("DELETE FROM orders WHERE OrderID = ?");
			Orders selected = orderTable.getSelectionModel().getSelectedItem();
			statement.setString(1, selected.getOrderID());
			statement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Alert alert = new Alert(Alert.AlertType.INFORMATION, "Order deleted");
        alert.show();
		this.loadInterface();
    }
    
    void loadComboBoxData() {
    	ObservableList<String> options = FXCollections.observableArrayList();
		try {
			Connection c = DBConnection.getKoneksi();
			PreparedStatement statement = c.prepareStatement("SELECT CompanyName FROM companies e inner join sensors f on e.CompanyCode = f.VendorCode");
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				options.add(rs.getString("CompanyName"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		newVendorCombo.setItems(options);
    }

    @FXML
    void newSensorClicked(ActionEvent event) {
    	if(newSensorCombo.getSelectionModel().getSelectedItem() != null) {
    		int option = 0;
    		try {
    			Connection c = DBConnection.getKoneksi();
    			PreparedStatement statement = c.prepareStatement("SELECT SensorStock FROM sensors WHERE SensorName = ?");
    			statement.setString(1, newSensorCombo.getSelectionModel().getSelectedItem());
    			ResultSet rs = statement.executeQuery();
    			if(rs.next()) {
    				option = rs.getInt("SensorStock");
    				this.sensorStock = option;
    			}
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
        	newOrderQty.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, option, 0));
    	}
    }

    @FXML
    void newVendorClicked(ActionEvent event) {
    	if(newVendorCombo.getSelectionModel().getSelectedItem() != null) {
    		ObservableList<String> options = FXCollections.observableArrayList();
    		try {
    			Connection c = DBConnection.getKoneksi();
    			PreparedStatement statement = c.prepareStatement("SELECT SensorName FROM sensors e INNER JOIN companies f on e.VendorCode=f.CompanyCode WHERE CompanyName = ?");
    			statement.setString(1, newVendorCombo.getSelectionModel().getSelectedItem());
    			ResultSet rs = statement.executeQuery();
    			while(rs.next()) {
    				options.add(rs.getString("SensorName"));
    			}
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    		newSensorCombo.setItems(options);
    	}
    }

    @FXML
    void makeOrderPressed(ActionEvent event) {
    	if(newVendorCombo.getSelectionModel().getSelectedItem() == null) {
    		Alert alert = new Alert(Alert.AlertType.ERROR, "Vendor Name must be inserted");
	        alert.show();
    	} else if(newSensorCombo.getSelectionModel().getSelectedItem() == null) {
    		Alert alert = new Alert(Alert.AlertType.ERROR, "Sensor Name must be inserted");
	        alert.show();
    	} else if(newOrderQty.getValue() == null || newOrderQty.getValue() <= 0) {
    		Alert alert = new Alert(Alert.AlertType.ERROR, "Order Quantity must be inserted or greater than 0");
	        alert.show();
    	} else {
        	try {
    			Connection c = DBConnection.getKoneksi();
    			PreparedStatement statement = c.prepareStatement("SELECT CompanyCode from companies WHERE CompanyName = ?");
    			statement.setString(1, newVendorCombo.getSelectionModel().getSelectedItem());
    			ResultSet rs = statement.executeQuery();
    			rs.next();
    			String clientCode = rs.getString("CompanyCode");
    			statement = c.prepareStatement("SELECT SensorID from sensors WHERE SensorName = ?");
    			statement.setString(1, newSensorCombo.getSelectionModel().getSelectedItem());
    			rs = statement.executeQuery();
    			rs.next();
    			int sensorID = rs.getInt("SensorID");
    			statement = c.prepareStatement("INSERT INTO orders (SensorID, ClientCode, OrderQuantity) values (?, ?, ?)");
    			statement.setInt(1, sensorID);
    			statement.setString(2, clientCode);
    			statement.setInt(3, newOrderQty.getValue());
    			statement.execute();
    			statement = c.prepareStatement("UPDATE sensors SET SensorStock = ? WHERE SensorID = ?");
				statement.setInt(1, this.sensorStock - newOrderQty.getValue());
				statement.setInt(2, sensorID);
				statement.execute();
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
        	Alert alert = new Alert(Alert.AlertType.INFORMATION, "Successfully made new order!");
	        alert.show();
    		this.loadInterface();
    	}
    }
    
    @FXML
    void orderQtyChanged(MouseEvent event) {
    	int selectedQty = orderTable.getSelectionModel().getSelectedItem().getOrderQuantityInt();
    	if(orderQty.getValue() != selectedQty) {
    		updateOrderBttn.setDisable(false);
    	}else {
    		updateOrderBttn.setDisable(true);
    	}
    }

    @FXML
    void updateOrderPressed(ActionEvent event) {
    	if(orderQty.getValue() <= 0) {
			Alert alert = new Alert(Alert.AlertType.ERROR, "Order quantity must be greater than 0.");
            alert.show();
		}else {
			try {
				Connection c = DBConnection.getKoneksi();
				PreparedStatement statement = c.prepareStatement("UPDATE orders SET OrderQuantity = ? WHERE OrderID = ?");
				Orders selected = orderTable.getSelectionModel().getSelectedItem();
				statement.setInt(1, selected.getOrderQuantityInt());
				statement.setString(2, selected.getOrderID());
				statement.execute();
			} catch (Exception e) {
				e.printStackTrace();
			}
			Alert alert = new Alert(Alert.AlertType.INFORMATION, "Order Quantity has been updated");
            alert.show();
			this.loadInterface();
		}
    }

}
