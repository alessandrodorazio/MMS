/**
 * 
 */
package motorway;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import helper.Costant;
import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import program.Program;

/**
 * @author alessandrodorazio
 *
 */
public class MotorwayView {

	@FXML
	private static TextField motorway_name = new TextField();
	@FXML
	private static TextField rate_a = new TextField();
	@FXML
	private static TextField rate_b = new TextField();
	@FXML
	private static TextField rate_3 = new TextField();
	@FXML
	private static TextField rate_4 = new TextField();
	@FXML
	private static TextField rate_5 = new TextField();
	private static Stage stage;
	static AnchorPane pane = null;
	
	@FXML
    private void initialize() {
        
    }
	
	@FXML
    public static void create(AnchorPane rootLayout) {
		try {
			pane = FXMLLoader.load(MotorwayView.class.getResource("view/create.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        rootLayout.getChildren().setAll(pane);
    }
	
	@FXML
    public static void show(AnchorPane rootLayout) throws IOException {
		
		String motorway_name;
		TextField rate_a, rate_b, rate_3, rate_4, rate_5, rate_e1, rate_e2, rate_e3, rate_e4, rate_e5, rate_e6;
		
		if(MotorwayController.show().getName() == null) {
			MotorwayView.create(rootLayout);
			return ;
		}
		
		pane = FXMLLoader.load(MotorwayView.class.getResource("view/show.fxml"));
		
		((Label) pane.lookup("#motorway_name")).setText("Autostrada " + MotorwayController.show().getName());
        
		rate_a = (TextField) pane.lookup("#rate_a_val");
		rate_b = (TextField) pane.lookup("#rate_b_val");
		rate_3 = (TextField) pane.lookup("#rate_3_val");
		rate_4 = (TextField) pane.lookup("#rate_4_val");
		rate_5 = (TextField) pane.lookup("#rate_5_val");
		
		rate_e1 = (TextField) pane.lookup("#rate_e1_val");
		rate_e2 = (TextField) pane.lookup("#rate_e2_val");
		rate_e3 = (TextField) pane.lookup("#rate_e3_val");
		rate_e4 = (TextField) pane.lookup("#rate_e4_val");
		rate_e5 = (TextField) pane.lookup("#rate_e5_val");
		rate_e6 = (TextField) pane.lookup("#rate_e6_val");
		
		rate_a.setText(Float.toString(Motorway.getInstance().getUnitRateSingle("A")));
		rate_b.setText(Float.toString(Motorway.getInstance().getUnitRateSingle("B")));
		rate_3.setText(Float.toString(Motorway.getInstance().getUnitRateSingle("3")));
		rate_4.setText(Float.toString(Motorway.getInstance().getUnitRateSingle("4")));
		rate_5.setText(Float.toString(Motorway.getInstance().getUnitRateSingle("5")));

		//ERROR IF NOT ADDED
		rate_e1.setText(Float.toString(Motorway.getInstance().getUnitRateSingle("A")));
		rate_e2.setText(Float.toString(Motorway.getInstance().getUnitRateSingle("A")));
		rate_e3.setText(Float.toString(Motorway.getInstance().getUnitRateSingle("A")));
		rate_e4.setText(Float.toString(Motorway.getInstance().getUnitRateSingle("A")));
		rate_e5.setText(Float.toString(Motorway.getInstance().getUnitRateSingle("A")));
		rate_e6.setText(Float.toString(Motorway.getInstance().getUnitRateSingle("A")));

        rootLayout.getChildren().setAll(pane);
        
    }
	
	@FXML
	void btn_save_create(ActionEvent event) {
		
		String motorway_name, rate_a, rate_b, rate_3, rate_4, rate_5;
		//TODO Unit Rates for E1-E6
		motorway_name = ((TextField) pane.lookup("#motorway_name")).getText().toString();
		rate_a = ((TextField) pane.lookup("#rate_a")).getText().toString();
		rate_b = ((TextField) pane.lookup("#rate_b")).getText().toString();
		rate_3 = ((TextField) pane.lookup("#rate_3")).getText().toString();
		rate_4 = ((TextField) pane.lookup("#rate_4")).getText().toString();
		rate_5 = ((TextField) pane.lookup("#rate_5")).getText().toString();
		

		Map<String, Float> unitRates = new HashMap<String,Float>();
		unitRates.put("A", Float.parseFloat(rate_a));
		unitRates.put("B", Float.parseFloat(rate_b));
		unitRates.put("3", Float.parseFloat(rate_3));
		unitRates.put("4", Float.parseFloat(rate_4));
		unitRates.put("5", Float.parseFloat(rate_5));
		
		if(MotorwayController.create(motorway_name, unitRates)) {
			Alert alert = new Alert(AlertType.INFORMATION, "Autostrada creata", ButtonType.OK);
			alert.showAndWait();

			if (alert.getResult() == ButtonType.OK) alert.close();
			

		}else{
			Alert alert = new Alert(AlertType.ERROR, "Autostrada già inserita", ButtonType.OK);
			alert.showAndWait();

			if (alert.getResult() == ButtonType.OK) alert.close();
		}
		
		try {
			show((AnchorPane) pane.getParent());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
  
	
}
