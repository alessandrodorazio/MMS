package exception;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class SameTollboothException extends Exception{
	
		 public SameTollboothException() {
			 super("I due caselli coincidono!");
			 Alert alert = new Alert(AlertType.ERROR, "I due caselli coincidono", ButtonType.OK);
				alert.showAndWait();

				if (alert.getResult() == ButtonType.OK)
					alert.close();
		 }
		 
		 public String toString() {
			 return "I due caselli coincidono!";
		 }
		 
}
