package application.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainFormCtr {

	@FXML
	private MenuItem BuyMenuItem;

	@FXML
	private BorderPane MainBorderPane;

	@FXML
	private MenuItem SignOutMenuItem;

	@FXML
	private MenuItem TransactionHistoryMenuItem;

	@FXML
	private Menu TransactionMenu;

	@FXML
	private Menu UserMenu;

	void ifadmin(boolean flag) {
		if (flag) {
			UserMenu.setText("My Admin");
			TransactionMenu.getItems().clear();
			MenuItem ManageTShirt = new MenuItem();
			ManageTShirt.setId("ManageTShirt");
			ManageTShirt.setText("Manage T-Shirt");
			TransactionMenu.getItems().add(ManageTShirt);
		}
	}

	void setCenterPane(String viewname) {
		try {
			FXMLLoader xmlloader = new FXMLLoader();
			xmlloader.setLocation(getClass().getResource("/application/view/" + viewname + ".fxml"));
			MainBorderPane.setCenter((AnchorPane) xmlloader.load());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void BuyMenuPressed(ActionEvent event) {
		setCenterPane("BuyMenuItem");
	}
	
	@FXML
    void TransactionHistoryPressed(ActionEvent event) {
		setCenterPane("TransactionHistoryMenuItem");
    }

	@FXML
	void SignOutBttnPressed(ActionEvent event) {
		try {
			Stage primaryStage = new Stage();
			FXMLLoader xmlloader = new FXMLLoader();
			xmlloader.setLocation(getClass().getResource("/application/view/LoginPanel.fxml"));
			AnchorPane root = (AnchorPane) xmlloader.load();
			Scene scene = new Scene(root, 600, 600);
			primaryStage.setScene(scene);
			primaryStage.show();

			Stage formerStage = (Stage) SignOutMenuItem.getParentPopup().getOwnerWindow();
			formerStage.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
