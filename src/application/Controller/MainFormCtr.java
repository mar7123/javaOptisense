package application.Controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;


public class MainFormCtr {

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

	void isvendor(boolean flag) {
		if (!flag) {
			MenuItem Orders = new MenuItem();
			Orders.setId("OrderMenuItem");
			Orders.setText("Orders");
			ManageMenu.getItems().clear();
			ManageMenu.getItems().add(Orders);
			Orders.setOnAction(odr -> {
				try {
					FXMLLoader xmlloader = new FXMLLoader();
					xmlloader.setLocation(getClass().getResource("/application/view/OrderForm.fxml"));
					VBox centerPane = (VBox) xmlloader.load();
					Insets inset = new Insets(13, 0, 0, 0);
					centerPane.setPadding(inset);
					SensorFormCtr ctr = xmlloader.getController();
					ctr.loadInterface();
					MainBorderPane.setCenter(centerPane);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		}
	}

	@FXML
	void SensorMenuPressed(ActionEvent event) {
		try {
			FXMLLoader xmlloader = new FXMLLoader();
			xmlloader.setLocation(getClass().getResource("/application/view/SensorForm.fxml"));
			VBox centerPane = (VBox) xmlloader.load();
			Insets inset = new Insets(13, 0, 0, 0);
			centerPane.setPadding(inset);
			SensorFormCtr ctr = xmlloader.getController();
			ctr.loadInterface();
			MainBorderPane.setCenter(centerPane);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void LogOutBttnPressed(ActionEvent event) {
		try {
			Stage primaryStage = new Stage();
			FXMLLoader xmlloader = new FXMLLoader();
			xmlloader.setLocation(getClass().getResource("/application/view/LoginPanel.fxml"));
			AnchorPane root = (AnchorPane) xmlloader.load();
			Scene scene = new Scene(root, 600, 600);
			primaryStage.setScene(scene);
			primaryStage.show();

			Stage formerStage = (Stage) LogOutMenuItem.getParentPopup().getOwnerWindow();
			formerStage.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
