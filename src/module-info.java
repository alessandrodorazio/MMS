module mms {
	requires mysql.connector;
	requires java.sql;
	requires javafx.graphics;
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.base;
	exports program;
	exports motorway;
	opens program to javafx.graphics, javafx.fxml;
	opens motorway to javafx.graphics, javafx.fxml;
}