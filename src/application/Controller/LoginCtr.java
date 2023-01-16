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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginCtr {

	private String CompanyCode;

	@FXML
	private Button LoginButton;

	@FXML
	private PasswordField PassField;

	@FXML
	private Text PasswordLabel;

	@FXML
	private Hyperlink RegisterCompanyHL;

	@FXML
	private Hyperlink RegisterEmployeeHL;

	@FXML
	private TextField EmployeeIDField;

	@FXML
	private Text EmployeeIDLabel;

	@FXML
	private Text UsernameLabel1;

	@FXML
	private Text WelcomeLabel;

	@FXML
	void LoginButtonPressed(ActionEvent event) {
		String employeeID = EmployeeIDField.getText();
		String password = PassField.getText();

		if (employeeID.isEmpty() || password.isEmpty()) {
			// Show an error message if the employee ID or password field is empty
			Alert alert = new Alert(Alert.AlertType.ERROR, "Employee ID and password fields must be filled.");
			alert.show();
			return;
		}

		// Connect to the database and check if the employee ID and password are correct
		try {
			// check the employee id and password in the database
			Connection connection = DBConnection.getKoneksi();
			PreparedStatement statement = connection.prepareStatement(
					"SELECT COUNT(*), CompanyCode FROM employees WHERE EmployeeID = ? AND EmployeePassword = ?");
			statement.setString(1, employeeID);
			statement.setString(2, password);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next() && resultSet.getInt(1) > 0) {
				// Close the login form and open the home form
				this.CompanyCode = resultSet.getString("CompanyCode");
				LoginButton.getScene().getWindow().hide();
				statement = connection.prepareStatement("SELECT CompanyRole FROM companies WHERE CompanyCode = ?");
				statement.setString(1, this.CompanyCode);
				resultSet = statement.executeQuery();
				resultSet.next();
				System.out.println(resultSet.getString("CompanyRole"));
				if (resultSet.getString("CompanyRole").equals("Vendor")) {
					openHomeForm(true);
				} else {
					openHomeForm(false);
				}
			} else {
				// Show an error message if the employee ID or password is incorrect
				Alert alert = new Alert(Alert.AlertType.ERROR, "Incorrect employee ID or password.");
				alert.show();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void RegisterCompanyHLClicked(ActionEvent event) {
		LoginButton.getScene().getWindow().hide();
		openRegisterCompanyForm();

	}

	@FXML
	void RegisterEmployeeHLClicked(ActionEvent event) {
		LoginButton.getScene().getWindow().hide();
		openRegisterEmployeeForm();

	}

	public void companyCode() {

	}

	public String getCompanyCode() {
		return this.CompanyCode;
	}

	private void openHomeForm(boolean vendor) {
		// code to open the home form
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/MainForm.fxml"));
			Parent root = loader.load();
			MainFormCtr ctr = loader.getController();
			ctr.isvendor(vendor, this.CompanyCode);
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("Home Form");
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void openRegisterCompanyForm() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/RegisterCompany.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("Register Company Form");
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void openRegisterEmployeeForm() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/RegisterEmployee.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("Register Employee Form");
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
