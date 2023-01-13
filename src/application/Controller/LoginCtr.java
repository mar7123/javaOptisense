package application.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginCtr {

	@FXML
	private Button LoginButton;

	@FXML
	private PasswordField PassField;

	@FXML
	private Text PasswordLabel;

	@FXML
	private Hyperlink RegisterHyperlink;

	@FXML
	private TextField UsernameField;

	@FXML
	private Text UsernameLabel;

	@FXML
	private Text WelcomeLabel;

	@FXML
	void LoginButtonPressed(ActionEvent event) {
		String username = UsernameField.getText();
		String pass = PassField.getText();
		if (username.isEmpty() || pass.isEmpty()) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Error");
			alert.setContentText("username or password can't be empty");
			alert.showAndWait();
		} else {
			try {
				Stage primaryStage = new Stage();
				FXMLLoader xmlloader = new FXMLLoader();
				xmlloader.setLocation(getClass().getResource("/application/view/MainForm.fxml"));
				BorderPane root = (BorderPane) xmlloader.load();
				Scene scene = new Scene(root, 600, 600);
				primaryStage.setScene(scene);
				primaryStage.show();
				Stage formerStage = (Stage) LoginButton.getScene().getWindow();
				formerStage.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@FXML
	void RegisterButtonPressed(ActionEvent event) {
		try {
			Stage primaryStage = new Stage();
			FXMLLoader xmlloader = new FXMLLoader();
			xmlloader.setLocation(getClass().getResource("/application/view/Register.fxml"));
			AnchorPane root = (AnchorPane) xmlloader.load();
			Scene scene = new Scene(root, 600, 600);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
