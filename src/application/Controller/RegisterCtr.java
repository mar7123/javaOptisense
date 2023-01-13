package application.Controller;

import java.util.Random;
import java.util.Arrays;
import java.util.List;

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
    private ComboBox<?> nationalityCombo;

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
        nationalityCombo.getItems().removeAll(nationalityCombo.getItems());
        nationalityCombo.getItems().addAll("America", "Indonesia", "Singapore");
        nationalityCombo.getSelectionModel().select("America");
        nationalityCombo.setStyle("Choose One");
        
    }
}

}
