package program;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import motorway.MotorwayView;

public class HomeController {
	
	@FXML
	private AnchorPane anchor;

    @FXML
    private void initialize() {
        try {
			MotorwayView.show(anchor);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
    
    @FXML
    void home(MouseEvent event) {
        System.out.println("WE");
    }
    
	@FXML
    void btn_vehicles(MouseEvent event) {
        System.out.println("WE");
    }
	
	@FXML
	void btn_toll(MouseEvent event) {
        System.out.println("WE");
    }
	
	@FXML
	void btn_settings(MouseEvent event) {
        System.out.println("WE");
    }
	
}
