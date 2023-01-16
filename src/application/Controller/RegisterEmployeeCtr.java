package application.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RegisterEmployeeCtr {

    @FXML
    private ComboBox<String> CompanyIDCombo;

    @FXML
    private Text CompanyLabel;

    @FXML
    private Text CompanyNameLabel;

    @FXML
    private Text CompanyNameLabel2;

    @FXML
    private Text CompanyRoleLabel;

    @FXML
    private Text CompanyRoleLabel1;

    @FXML
    private Text CompanyRoleLabel11;

    @FXML
    private TextField FullNameField;

    @FXML
    private TextField GeneratedIDField;

    @FXML
    private Hyperlink LoginEmployeeHL;

    @FXML
    private PasswordField PassField;

    @FXML
    private ComboBox<String> PositionCombo;

    @FXML
    private Button RegisterButton;

    @FXML
    private Hyperlink RegisterCompanyHL;

    @FXML
    private Text UsernameLabel1;

    @FXML
    private Text WelcomeLabel;
    
    @FXML
    void initialize() {
    	
    	ObservableList<String> options = FXCollections.observableArrayList("Chairman", "Director", "CEO", "Manager", "Staff");
        // Set the items of the ComboBox
        PositionCombo.setItems(options);

        // Set the default selected value
        PositionCombo.setValue("Chairman");
        try {
            // Fill company combo box with registered company codes
        	Connection connection = DBConnection.getKoneksi();
            PreparedStatement statement = connection.prepareStatement("SELECT CompanyCode, CompanyName FROM companies");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
            	CompanyIDCombo.getItems().add(resultSet.getString("CompanyCode"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        CompanyIDCombo.valueProperty().addListener((obs, oldVal, newVal) -> {
            try {
                // Set company name label when company is selected
            	Connection connection = DBConnection.getKoneksi();
                PreparedStatement statement = connection.prepareStatement("SELECT CompanyName FROM companies WHERE CompanyCode = ?");
                statement.setString(1, newVal);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                	CompanyNameLabel.setText(resultSet.getString("CompanyName"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
    
    @FXML
    void onCompanyAndPositionSelected() {
        String companyCode = CompanyIDCombo.getValue();
        String position = PositionCombo.getValue();
        if (companyCode != null && position != null) {
            // Generate employee ID
            String employeeID = companyCode + position;
            try {
            	Connection connection = DBConnection.getKoneksi();
                PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) FROM employees WHERE EmployeeID LIKE ?");
                statement.setString(1, employeeID + "%");
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    int employeeCount = resultSet.getInt(1);
                    employeeID += String.format("%02d", employeeCount + 1);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            GeneratedIDField.setText(employeeID);
            }
     }

    @FXML
    void LoginEmployeeHLPressed(ActionEvent event) {
    	LoginEmployeeHL.getScene().getWindow().hide();
    	try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/LoginPanel.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Employee Login");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void RegisterButtonPressed(ActionEvent event) {
    	 String companyCode = CompanyIDCombo.getValue();
    	    String fullName = FullNameField.getText();
    	    String position = PositionCombo.getValue();
    	    String password = PassField.getText();
    	    String employeeID = GeneratedIDField.getText();

    	    if (companyCode == null || companyCode.isEmpty()) {
    	        // Show error message for missing company
    	    	Alert alert = new Alert(Alert.AlertType.ERROR, "Please Select a company");
                alert.show();
    	        return;
    	    }
    	    if (fullName.isEmpty()) {
    	        // Show error message for missing full name
    	    	Alert alert = new Alert(Alert.AlertType.ERROR, "Full Name fields must be filled.");
                alert.show();
    	        return;
    	    }
    	    if (position == null || position.isEmpty()) {
    	        // Show error message for missing position
    	    	Alert alert = new Alert(Alert.AlertType.ERROR, "Position fields must be filled.");
                alert.show();
    	        return;
    	    }
    	    if (password.isEmpty()) {
    	        // Show error message for missing password
    	    	Alert alert = new Alert(Alert.AlertType.ERROR, "Password fields must be filled.");
                alert.show();
    	        return;
    	    }

    	    try {
    	        // Insert new employee data into database
    	    	Connection connection = DBConnection.getKoneksi();
    	        PreparedStatement statement = connection.prepareStatement("INSERT INTO employees (EmployeeID, CompanyCode, EmployeePosition, EmployeePassword, EmployeeFullName) VALUES (?, ?, ?, ?, ?)");
    	        statement.setString(1, employeeID);
    	        statement.setString(2, companyCode);
    	        statement.setString(3, position);
    	        statement.setString(4, password);
    	        statement.setString(5, fullName);
    	        statement.executeUpdate();
    	    } catch (SQLException e) {
    	        e.printStackTrace();
    	    }
    	    // Close the Register Employee form and open the Employee Login form
    	    
    	    RegisterButton.getScene().getWindow().hide();
    	    try {
    	        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("employee_login.fxml"));
    	        Parent root1 = (Parent) fxmlLoader.load();
    	        Stage stage = new Stage();
    	        stage.setScene(new Scene(root1));
    	        stage.show();
    	    } catch (IOException e) {
    	        e.printStackTrace();
    	    }
    	}
    	    

    @FXML
    void RegisterCompanyHLPressed(ActionEvent event) {
    	RegisterCompanyHL.getScene().getWindow().hide();
    	try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/LoginPanel.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Employee Login");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void onCompanyNameFieldChanged(MouseEvent event) {

    }

}
