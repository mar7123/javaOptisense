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
                	onCompanyAndPositionSelected();
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

        if (companyCode == null || position == null) {
            return;
        }

        try {
            // Get the position index in the list
            int positionIndex = PositionCombo.getItems().indexOf(position);
            // Get the current employee count in the company
            Connection connection = DBConnection.getKoneksi();
            PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) FROM employees WHERE CompanyCode = ?");
            statement.setString(1, companyCode);
            ResultSet result = statement.executeQuery();
            result.next();
            int employeeCount = result.getInt(1);

            // Generate the employee ID
            String employeeID = companyCode + String.format("%02d", positionIndex) + String.format("%04d", employeeCount + 1);
            GeneratedIDField.setText(employeeID);
        } catch (SQLException e) {
            e.printStackTrace();
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
    	        
    	     // Show a success message
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Company has been registered successfully.");
                alert.show();
        	  
    	    } catch (SQLException e) {
    	        e.printStackTrace();
    	    }
         
    	    FullNameField.setText("");
    	    PassField.setText("");
    	    GeneratedIDField.setText("");
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
    	onCompanyAndPositionSelected();
    }
    
    @FXML
    void onSelectedOptionCompanyID(MouseEvent event) {
    	onCompanyAndPositionSelected();

    }

    @FXML
    void onSelectedOptionPosition(MouseEvent event) {
    	onCompanyAndPositionSelected();

    }


}
