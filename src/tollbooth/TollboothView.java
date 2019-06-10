package tollbooth;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import motorway.MotorwayView;

public class TollboothView {
	
	public static void index(AnchorPane rootLayout) {

		AnchorPane pane = null;
		try {
			pane = FXMLLoader.load(TollboothView.class.getResource("view/index.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        rootLayout.getChildren().setAll(pane);

		
	}

}
