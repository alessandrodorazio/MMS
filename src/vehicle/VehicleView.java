/**
 * 
 */
package vehicle;

import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import motorway.MotorwayView;

/**
 * @author alessandrodorazio
 *
 */
public class VehicleView {

	public void index(AnchorPane rootLayout) { 
		AnchorPane pane = null;
		try {
			pane = FXMLLoader.load(MotorwayView.class.getResource("view/index.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        rootLayout.getChildren().setAll(pane);
	}
	
	public Vehicle create() { Vehicle a = new VehicleLight(null, null, null, (char) 0, 0, 0, 0, 0, 0); return a; }
	
	public void show(AnchorPane rootLayout) { 
		AnchorPane pane = null;
		try {
			pane = FXMLLoader.load(MotorwayView.class.getResource("view/show.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        rootLayout.getChildren().setAll(pane);
	}
	
	public Vehicle update() { Vehicle a = new VehicleLight(null, null, null, (char) 0, 0, 0, 0, 0, 0); return a; }
	
	public void delete(Vehicle a) { }
	
	public ArrayList<Vehicle> history(Vehicle a) { return null; }
	
}
