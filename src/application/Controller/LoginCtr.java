package application.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.DBConnection;
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
		    String password = PassField.getText();
		    if (username.isEmpty() || password.isEmpty()) {
		        WelcomeLabel.setText("Please fill in the username and password fields");
		        return;
		    }

		    // Connect to the database
		    
		    try {
		        
		    	Connection c = DBConnection.getKoneksi();
		      
		        String sql = "SELECT * FROM users WHERE UserUsername = ? AND UserPassword = ?";
		        PreparedStatement stmt = c.prepareStatement(sql);
		        stmt.setString(1, username);
		        stmt.setString(2, password);
		        ResultSet rs = stmt.executeQuery();
		        if (rs.next()) {
		            String role = rs.getString("UserRole");
		            if (role.equals("User")) {
		            	Stage primaryStage = new Stage();
						FXMLLoader xmlloader = new FXMLLoader();
						xmlloader.setLocation(getClass().getResource("/application/view/MainForm.fxml"));
						BorderPane root = (BorderPane) xmlloader.load();
						Scene scene = new Scene(root, 600, 600);
						primaryStage.setScene(scene);
						MainFormCtr ctr = xmlloader.getController();
						ctr.ifadmin(false);
						primaryStage.show();
						
						Stage formerStage = (Stage) LoginButton.getScene().getWindow();
						formerStage.close();
		            } 
		            
		            else if (role.equals("Admin")) {
		            	Stage primaryStage = new Stage();
						FXMLLoader xmlloader = new FXMLLoader();
						xmlloader.setLocation(getClass().getResource("/application/view/MainForm.fxml"));
						BorderPane root = (BorderPane) xmlloader.load();
						Scene scene = new Scene(root, 600, 600);
						primaryStage.setScene(scene);
						MainFormCtr ctr = xmlloader.getController();
						ctr.ifadmin(true);
						primaryStage.show();
						
						Stage formerStage = (Stage) LoginButton.getScene().getWindow();
						formerStage.close();
		            }
		        } 
		        
		        else 
		        {
		            WelcomeLabel.setText("Incorrect username or password");
		        }
		        
		    } catch (SQLException ex) {
		        ex.printStackTrace();
		        WelcomeLabel.setText("Error: " + ex.getMessage());
		    } catch (IOException ex) {
		        ex.printStackTrace();
		        WelcomeLabel.setText("Error: " + ex.getMessage());
		    } 

	}

	@FXML
	void RegisterButtonPressed(ActionEvent event) {
		try {
			Stage primaryStage = new Stage();
			FXMLLoader xmlloader = new FXMLLoader();
			xmlloader.setLocation(getClass().getResource("/application/view/Register.fxml"));
			AnchorPane root = (AnchorPane) xmlloader.load();
			Scene scene = new Scene(root, 400, 600);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
