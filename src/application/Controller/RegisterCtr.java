package application.Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

import application.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class RegisterCtr{

	@FXML
    private TextArea addressField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private RadioButton femaleRadio;

    @FXML
    private TextField idField;

    @FXML
    private RadioButton maleRadio;

    @FXML
    private ComboBox<String> nationalityCombo;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label registerlabel;

    @FXML
    private CheckBox termsCheck;

    @FXML
    private TextField usernameField;

    @FXML
    void ButtonONClick(ActionEvent event) {
    	
    	// Get the values from the input fields
        String username = usernameField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();
        String address = addressField.getText();
        String gender = "";
        if (maleRadio.isSelected()) {
            gender = "male";
        } else if (femaleRadio.isSelected()) {
            gender = "female";
        }
        String nationality = nationalityCombo.getValue();
        boolean terms = termsCheck.isSelected();
        String id = idField.getText();
        // Check if the password and confirm password match
        if (!password.equals(confirmPassword)) {
            registerlabel.setText("Passwords do not match.");
            return;
        }
        // Check if the terms and conditions are accepted
        if (!terms) {
            registerlabel.setText("Please accept the terms and conditions.");
            return;
        }
        // Check if the nationality is selected
        if (nationality.equals("Choose One")) {
            registerlabel.setText("Please select your nationality.");
            return;
        }
        
        try {
        	Connection c = DBConnection.getKoneksi();
            String sql = "INSERT INTO users (UserUsername, UserPassword, UserGender, UserAddress, UserNationality, UserID, UserRole) VALUES (?,?,?,?,?,?,'User')";
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, gender);
            stmt.setString(4, address);
            stmt.setString(5, nationality);
            stmt.setString(6, id);
            stmt.execute();
            registerlabel.setText("Successfully Registered");
        } 
        catch (SQLException ex) {
            ex.printStackTrace();
            registerlabel.setText("Error: " + ex.getMessage());
            }
        
        

    }
    
    @FXML
    public void initialize() {
        // Generate the ID when the form is opened
        Random random = new Random();
        String id = "US-" + random.nextInt(1000) + (char)(random.nextInt(26) + 'A') + (char)(random.nextInt(26) + 'A');
        idField.setText(id);
        // Add options to the nationality combo box
        ObservableList<String> options = FXCollections.observableArrayList("Choose One", "America", "Australia", "Brazil", "Indonesia", "Malaysia", "Singapore");
        // Set the items of the ComboBox
        nationalityCombo.setItems(options);

        // Set the default selected value
        nationalityCombo.setValue("Choose One");

   
        
    }
    
    
    
}


