module MiniProject_2 {
	
	requires javafx.controls;
	requires javafx.graphics;
	requires java.desktop;
	opens graph to javafx.graphics, javafx.fxml;
	requires java.base;
}