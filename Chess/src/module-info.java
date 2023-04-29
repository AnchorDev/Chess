module Chess {
	requires javafx.controls;
	requires java.base;
	
	opens application to javafx.graphics, javafx.fxml;
}
