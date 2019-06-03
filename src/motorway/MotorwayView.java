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
		/*
		 * Motorway motorway = MotorwayController.show();

		motorway_name.setText("ciao");
		rate_a = new TextField(motorway.getUnitRate().get("A").toString());
		rate_b = new TextField(motorway.getUnitRate().get("B").toString());
		rate_3 = new TextField(motorway.getUnitRate().get("3").toString());
		rate_4 = new TextField(motorway.getUnitRate().get("4").toString());
		rate_5 = new TextField(motorway.getUnitRate().get("5").toString());
    	Parent root;
        try {
            root = FXMLLoader.load(MotorwayView.class.getResource("view/show.fxml") );
            stage = new Stage();
            stage.setTitle("Dettagli autostrada");
            stage.setScene(new Scene(root, 600, 310));
            stage.setX(0);
            stage.setY(0);
            stage.show();
            stage.toFront();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
		 */
		
		if(MotorwayController.show().getName() == null) {
			MotorwayView.create(rootLayout);
			return ;
		}
		
		pane = FXMLLoader.load(MotorwayView.class.getResource("view/show.fxml"));
		
		Label motorway_name = ((Label) pane.lookup("#motorway_name"));
        motorway_name.setText("Autostrada " + MotorwayController.show().getName());
        
        TableView<Map<String, Float>> unit_rate_table =  ((TableView) pane.lookup("#unit_rate_table"));
        ObservableList<Map<String, Float>> unit_rate_data = FXCollections.observableArrayList();
        unit_rate_data.add(MotorwayController.show().getUnitRate());
        
        unit_rate_table.setItems(unit_rate_data);
        
        rootLayout.getChildren().setAll(pane);
        
    }
	
	@FXML
	void btn_save_create(ActionEvent event) {
		
		String motorway_name, rate_a, rate_b, rate_3, rate_4, rate_5;
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
