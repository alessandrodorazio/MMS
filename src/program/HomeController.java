package program;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import motorway.MotorwayView;
import tollbooth.TollboothView;
import vehicle.VehicleView;

public class HomeController {

	@FXML
	private AnchorPane anchor;

	@FXML
	private void initialize() {

		try {
			MotorwayView.show(anchor);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@FXML
	void home(MouseEvent event) {
		try {
			MotorwayView.show(anchor);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void btn_vehicles(MouseEvent event) {
		VehicleView.index(anchor);
	}

	@FXML
	void btn_toll(MouseEvent event) {
		MotorwayView.toll(anchor);
	}

	@FXML
	void btn_tollbooth(MouseEvent event) {
		TollboothView.index(anchor);
	}

	public AnchorPane getRootLayout() {
		return anchor;
	}

}
