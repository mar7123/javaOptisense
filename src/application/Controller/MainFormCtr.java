package application.Controller;


import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;


public class MainFormCtr {
	
	private String CompanyCode;

	@FXML
    private MenuItem LogOutMenuItem;

    @FXML
    private BorderPane MainBorderPane;

    @FXML
    private Menu ManageMenu;

    @FXML
    private MenuItem SensorMenuItem;

    @FXML
    private Menu UserMenu;

	void isvendor(boolean flag, String CompanyCodeParameter) {
		this.CompanyCode = CompanyCodeParameter;
		if (!flag) {
			MenuItem Orders = new MenuItem();
			Orders.setId("OrderMenuItem");
			Orders.setText("Orders");
			ManageMenu.getItems().clear();
			ManageMenu.getItems().add(Orders);
			Orders.setOnAction(odr -> {
				try {
		            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/OrderForm.fxml"));
		            Parent root = loader.load();
		            OrderFormCtr ctr = loader.getController();
		            ctr.loadInterface();
		            MainBorderPane.setCenter(root);
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
			});
		}
	}

	@FXML
	void SensorMenuPressed(ActionEvent event) {
		
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/SensorForm.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            SensorFormCtr ctr = loader.getController();
            ctr.initialize(this.CompanyCode);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Sensor");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	@FXML
	void LogOutBttnPressed(ActionEvent event) {
		 try {
	            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/LoginPanel.fxml"));
	            Parent root = loader.load();
	            Scene scene = new Scene(root);
	            Stage stage = new Stage();
	            stage.setScene(scene);
	            stage.setTitle("Login Panel");
	            stage.show();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}
}
