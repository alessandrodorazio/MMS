package exception;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class FieldMissingException extends Exception{
	
	 public FieldMissingException() {
		 super("Compilare tutti i campi!");
		 Alert alert = new Alert(AlertType.ERROR, "Compila i campi", ButtonType.OK);
			alert.showAndWait();

			if (alert.getResult() == ButtonType.OK)
				alert.close();
	 }
	 
	 public String toString() {
		 return "Compilare tutti i campi!";
	 }
	 
}
