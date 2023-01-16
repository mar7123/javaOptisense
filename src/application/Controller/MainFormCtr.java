package application.Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import application.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.util.List;
import java.util.ArrayList;

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
			Orders.setOnAction(e -> {
				List<String> tablename = new ArrayList<String>();
				tablename.add("products");
//				setCenterPane("ManageTShirt");
			});
		}
	}

//	FXMLLoader setCenterPane(String viewname) {
//		try {
//			FXMLLoader xmlloader = new FXMLLoader();
//			xmlloader.setLocation(getClass().getResource("/application/view/" + viewname + ".fxml"));
//			AnchorPane centerPane = (AnchorPane) xmlloader.load();
//			Insets inset = new Insets(13, 0, 0, 0);
//			centerPane.setPadding(inset);
//			return centerPane;
//			} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}

	ResultSet loadTable(String table_name) {
		try {
			Connection c = DBConnection.getKoneksi();

			String sql = "SELECT * FROM " + table_name;
			PreparedStatement stmt = c.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			return rs;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@FXML
	void SensorMenuPressed(ActionEvent event) {
//		List<String> tablename = new ArrayList<String>();
//		tablename.add("products");
//		tablename.add("cart");
//		try {
//			FXMLLoader xmlloader = new FXMLLoader();
//			xmlloader.setLocation(getClass().getResource("/application/view/BuyMenuItem.fxml"));
//			AnchorPane centerPane = (AnchorPane) xmlloader.load();
//			Insets inset = new Insets(13, 0, 0, 0);
//			centerPane.setPadding(inset);
//			BuyMenuCtr ctr = xmlloader.getController();
//			MainBorderPane.setCenter(centerPane);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
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
