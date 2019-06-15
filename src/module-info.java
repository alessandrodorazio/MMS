module mms {
	requires mysql.connector;
	requires java.sql;
	requires javafx.graphics;
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.base;
	requires java.base;
	exports program;
	exports motorway;
	exports vehicle;
	exports tollbooth;
	opens program to javafx.graphics, javafx.fxml;
	opens motorway to javafx.graphics, javafx.fxml;
	opens vehicle to javafx.graphics, javafx.fxml, javafx.base;
	opens tollbooth to javafx.graphics, javafx.fxml;
}