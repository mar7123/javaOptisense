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
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RegisterCompanyCtr {

    @FXML
    private TextField CompanyNameField;

    @FXML
    private Text CompanyNameLabel;

    @FXML
    private Text CompanyRoleLabel;

    @FXML
    private TextField GeneratedCodeField;

    @FXML
    private Text GeneratedCodeLabel;
    
    @FXML
    private ComboBox<String> CompanyRoleCombo;

    @FXML
    private Hyperlink LoginEmployeeHL;

    @FXML
    private Button RegisterButton;

    @FXML
    private Hyperlink RegisterEmployeeHL;

    @FXML
    private Text UsernameLabel1;

    @FXML
    private Text WelcomeLabel;

    @FXML
    void LoginEmployeeHLPressed(ActionEvent event) {
    	 // Close the register company form and open the employee login form
    	LoginEmployeeHL.getScene().getWindow().hide();
        openEmployeeLoginForm();
    }
    
    @FXML
    void RegisterEmployeeHLPressed(ActionEvent event) {
    	// Close the register company form and open the register employee form
    	RegisterEmployeeHL.getScene().getWindow().hide();
        openRegisterEmployeeForm();

    }
    
    public void initialize() {
        // Add options to the nationality combo box
        ObservableList<String> options = FXCollections.observableArrayList("Vendor", "Client");
        // Set the items of the ComboBox
        CompanyRoleCombo.setItems(options);

        // Set the default selected value
        CompanyRoleCombo.setValue("Vendor");
        
    }
    
    @FXML
    void RegisterButtonPressed(ActionEvent event) {
        String companyName = CompanyNameField.getText();
        String companyCode = GeneratedCodeField.getText();
        String companyRole = CompanyRoleCombo.getValue();

        if (companyName.isEmpty() || companyCode.isEmpty() || companyRole == null) {
            // Show an error message if any of the fields is empty
            Alert alert = new Alert(Alert.AlertType.ERROR, "All fields must be filled.");
            alert.show();
            return;
        }

        if (companyName.length() < 5 || companyName.length() > 24) {
            // Show an error message if the company name is not between 5-24 characters
            Alert alert = new Alert(Alert.AlertType.ERROR, "Company name must be between 5-24 characters.");
            alert.show();
            return;
        }
        
        try (Connection connection = DBConnection.getKoneksi()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO companies (CompanyCode, CompanyName, CompanyRole) VALUES (?, ?, ?)");
            statement.setString(1, companyCode);
            statement.setString(2, companyName);
            statement.setString(3, companyRole);
            statement.executeUpdate();

            // Show a success message
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Company has been registered successfully.");
            alert.show();
         
        } catch (SQLException e) {
            e.printStackTrace();
        }
        CompanyNameField.setText("");
        GeneratedCodeField.setText("");
        
        
    }

    @FXML
    void onCompanyNameFieldChanged(){
    	String companyName = CompanyNameField.getText();
        if (companyName.length() >= 2) {
            // Generate company code
            String companyCode = companyName.substring(0, 1) + companyName.substring(companyName.length() - 1);
            try {
            	Connection connection = DBConnection.getKoneksi();
                PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) FROM companies WHERE CompanyCode LIKE ?");
                statement.setString(1, companyCode + "%");
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    int companyCount = resultSet.getInt(1);
                    companyCode += String.format("%02d", companyCount + 1);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            GeneratedCodeField.setText(companyCode);
        }
    }
         
     

    private void openEmployeeLoginForm() {
        // code to open the employee login form
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

    private void openRegisterEmployeeForm() {
        // code to open the register employee form
    	try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/RegisterEmployee.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Employee Register");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    	
    }
    
    @FXML
    void onKeyTypeCompanyName(KeyEvent event) {
    	onCompanyNameFieldChanged();
    }

    
    
}
    
    

    


