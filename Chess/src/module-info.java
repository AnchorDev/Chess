module Chess {
	requires javafx.controls;
	requires java.base;
	requires javafx.graphics;
	
	opens application to javafx.graphics, javafx.fxml;
}
