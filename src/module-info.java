module JavaTShirt {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	requires mysql.connector.j;
	requires javafx.graphics;
	
	opens application to javafx.graphics, javafx.fxml;
	opens application.Controller to javafx.graphics, javafx.fxml;
}
