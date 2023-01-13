package application.Controller;

import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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


