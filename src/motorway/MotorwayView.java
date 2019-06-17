/**
 * 
 */
package motorway;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import exception.FieldMissingException;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
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
import tollbooth.Tollbooth;
import tollbooth.TollboothController;
import tollbooth.TollboothView;
import vehicle.Vehicle;
import vehicle.VehicleController;

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

	static AnchorPane pane = new AnchorPane();
	static AnchorPane root = new AnchorPane();

	@FXML
	private void initialize() {

	}

	public static void create(AnchorPane rootLayout) {
		try {
			pane = FXMLLoader.load(MotorwayView.class.getResource("view/create.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rootLayout.getChildren().setAll(pane);
	}

	public static void show() {

		String motorway_name;
		TextField rate_a, rate_b, rate_3, rate_4, rate_5, rate_e1, rate_e2, rate_e3, rate_e4, rate_e5, rate_e6;

		if (Motorway.getInstance().getName() == null) {
			MotorwayView.create(root);
			return;
		}

		try {
			pane = FXMLLoader.load(MotorwayView.class.getResource("view/show.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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

		rate_a.setText(Float.toString(MotorwayController.show().getUnitRateSingle("A")));
		rate_b.setText(Float.toString(MotorwayController.show().getUnitRateSingle("B")));
		rate_3.setText(Float.toString(MotorwayController.show().getUnitRateSingle("3")));
		rate_4.setText(Float.toString(MotorwayController.show().getUnitRateSingle("4")));
		rate_5.setText(Float.toString(MotorwayController.show().getUnitRateSingle("5")));

		rate_e1.setText(Float.toString(MotorwayController.show().getUnitRateSingle("E1")));
		rate_e2.setText(Float.toString(MotorwayController.show().getUnitRateSingle("E2")));
		rate_e3.setText(Float.toString(MotorwayController.show().getUnitRateSingle("E3")));
		rate_e4.setText(Float.toString(MotorwayController.show().getUnitRateSingle("E4")));
		rate_e5.setText(Float.toString(MotorwayController.show().getUnitRateSingle("E5")));
		rate_e6.setText(Float.toString(MotorwayController.show().getUnitRateSingle("E6")));
		
		((Button) pane.lookup("#btn_save_edit")).setVisible(false);

		root.getChildren().setAll(pane);

	}

	public static void show(AnchorPane rootLayout) throws IOException {
		root = rootLayout;
		show();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@FXML
	public static void toll(AnchorPane rootLayout) {
		// TODO Auto-generated method stub
		try {
			pane = FXMLLoader.load(MotorwayView.class.getResource("view/tollcalc.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (pane == null)
			throw new Error(); // TODO

		ArrayList<String> vehicles = VehicleController.indexPlateNumber();
		ComboBox vehiclesComboBox = (ComboBox) pane.lookup("#vehicle");

		ArrayList<String> tollbooths = TollboothController.indexName();
		ComboBox tollboothInComboBox = (ComboBox) pane.lookup("#tollbooth_in");
		ComboBox tollboothOutComboBox = (ComboBox) pane.lookup("#tollbooth_out");

		vehiclesComboBox.getItems().addAll(vehicles);
		tollboothInComboBox.getItems().addAll(tollbooths);
		tollboothOutComboBox.getItems().addAll(tollbooths);

		rootLayout.getChildren().setAll(pane);
	}

	@FXML
	void tollCalcBtn(ActionEvent e) throws FieldMissingException {
		Vehicle v = null;
		Tollbooth in = null, out = null;
		Label displayCost;

		try {
			v = VehicleController.show(((ComboBox) pane.lookup("#vehicle")).getValue().toString());
			in = TollboothController.show(((ComboBox) pane.lookup("#tollbooth_in")).getValue().toString());
			out = TollboothController.show(((ComboBox) pane.lookup("#tollbooth_out")).getValue().toString());
		}catch(NullPointerException e1) {
			throw new FieldMissingException();
		}
		
	
			
		float cost = MotorwayController.tollCalc(v, in, out);
		displayCost = (Label) pane.lookup("#cost");
		displayCost.setText("Costo del pedaggio: " + String.valueOf(cost) + "0€");
	}

	@FXML
	void btn_save_create(ActionEvent event) {

		String motorway_name, rate_a, rate_b, rate_3, rate_4, rate_5, rate_e1, rate_e2, rate_e3, rate_e4, rate_e5,
				rate_e6;
		Map<String, Float> unitRates = new HashMap<String, Float>();
		int type = ((CheckBox) pane.lookup("#type")).isSelected() ? 1 : 2;
		
		motorway_name = ((TextField) pane.lookup("#motorway_name")).getText().toString();
		rate_a = ((TextField) pane.lookup("#rate_a")).getText().toString();
		rate_b = ((TextField) pane.lookup("#rate_b")).getText().toString();
		rate_3 = ((TextField) pane.lookup("#rate_3")).getText().toString();
		rate_4 = ((TextField) pane.lookup("#rate_4")).getText().toString();
		rate_5 = ((TextField) pane.lookup("#rate_5")).getText().toString();

		rate_e1 = ((TextField) pane.lookup("#rate_e1")).getText().toString();
		rate_e2 = ((TextField) pane.lookup("#rate_e2")).getText().toString();
		rate_e3 = ((TextField) pane.lookup("#rate_e3")).getText().toString();
		rate_e4 = ((TextField) pane.lookup("#rate_e4")).getText().toString();
		rate_e5 = ((TextField) pane.lookup("#rate_e5")).getText().toString();
		rate_e6 = ((TextField) pane.lookup("#rate_e6")).getText().toString();

		unitRates.put("A", Float.parseFloat(rate_a));
		unitRates.put("B", Float.parseFloat(rate_b));
		unitRates.put("3", Float.parseFloat(rate_3));
		unitRates.put("4", Float.parseFloat(rate_4));
		unitRates.put("5", Float.parseFloat(rate_5));

		unitRates.put("E1", Float.parseFloat(rate_e1));
		unitRates.put("E2", Float.parseFloat(rate_e2));
		unitRates.put("E3", Float.parseFloat(rate_e3));
		unitRates.put("E4", Float.parseFloat(rate_e4));
		unitRates.put("E5", Float.parseFloat(rate_e5));
		unitRates.put("E6", Float.parseFloat(rate_e6));
		
		if(motorway_name.isBlank()) {
			Alert alert = new Alert(AlertType.ERROR, "Il nome dell'autostrada è obbligatorio!", ButtonType.OK);
			alert.showAndWait();

			if (alert.getResult() == ButtonType.OK)
				alert.close();
			return ;
		}

		if (MotorwayController.create(motorway_name, unitRates, type)) {
			Alert alert = new Alert(AlertType.INFORMATION, "Autostrada creata", ButtonType.OK);
			alert.showAndWait();
			if (alert.getResult() == ButtonType.OK)
				alert.close();

		} else {
			Alert alert = new Alert(AlertType.ERROR, "Autostrada già inserita", ButtonType.OK);
			alert.showAndWait();

			if (alert.getResult() == ButtonType.OK)
				alert.close();
		}

		show();

	}
	
	@FXML
	void edit(ActionEvent e) {
		
		((Label) pane.lookup("#motorway_name")).setText("Modifica " + ((Label) pane.lookup("#motorway_name")).getText());
		
		((TextField) pane.lookup("#rate_a_val")).setEditable(true);
		((TextField) pane.lookup("#rate_b_val")).setEditable(true);
		((TextField) pane.lookup("#rate_3_val")).setEditable(true);
		((TextField) pane.lookup("#rate_4_val")).setEditable(true);
		((TextField) pane.lookup("#rate_5_val")).setEditable(true);
		
		((TextField) pane.lookup("#rate_e1_val")).setEditable(true);
		((TextField) pane.lookup("#rate_e2_val")).setEditable(true);
		((TextField) pane.lookup("#rate_e3_val")).setEditable(true);
		((TextField) pane.lookup("#rate_e4_val")).setEditable(true);
		((TextField) pane.lookup("#rate_e5_val")).setEditable(true);
		((TextField) pane.lookup("#rate_e6_val")).setEditable(true);
		
		((Button) pane.lookup("#btn_save_edit")).setVisible(true);
	}
	
	@FXML
	void btn_edit(ActionEvent e) {
		String rate_a, rate_b, rate_3, rate_4, rate_5, rate_e1, rate_e2, rate_e3, rate_e4, rate_e5,
		rate_e6;
		
		Map<String, Float> unitRates = new HashMap<String, Float>();
		
		rate_a = ((TextField) pane.lookup("#rate_a_val")).getText().toString();
		rate_b = ((TextField) pane.lookup("#rate_b_val")).getText().toString();
		rate_3 = ((TextField) pane.lookup("#rate_3_val")).getText().toString();
		rate_4 = ((TextField) pane.lookup("#rate_4_val")).getText().toString();
		rate_5 = ((TextField) pane.lookup("#rate_5_val")).getText().toString();
		
		rate_e1 = ((TextField) pane.lookup("#rate_e1_val")).getText().toString();
		rate_e2 = ((TextField) pane.lookup("#rate_e2_val")).getText().toString();
		rate_e3 = ((TextField) pane.lookup("#rate_e3_val")).getText().toString();
		rate_e4 = ((TextField) pane.lookup("#rate_e4_val")).getText().toString();
		rate_e5 = ((TextField) pane.lookup("#rate_e5_val")).getText().toString();
		rate_e6 = ((TextField) pane.lookup("#rate_e6_val")).getText().toString();
		
		unitRates.put("A", Float.parseFloat(rate_a));
		unitRates.put("B", Float.parseFloat(rate_b));
		unitRates.put("3", Float.parseFloat(rate_3));
		unitRates.put("4", Float.parseFloat(rate_4));
		unitRates.put("5", Float.parseFloat(rate_5));
		
		unitRates.put("E1", Float.parseFloat(rate_e1));
		unitRates.put("E2", Float.parseFloat(rate_e2));
		unitRates.put("E3", Float.parseFloat(rate_e3));
		unitRates.put("E4", Float.parseFloat(rate_e4));
		unitRates.put("E5", Float.parseFloat(rate_e5));
		unitRates.put("E6", Float.parseFloat(rate_e6));
		
		MotorwayController.updateUnitRates(unitRates);
		
		Alert alert = new Alert(AlertType.INFORMATION, "Tariffe unitarie aggiornate", ButtonType.OK);
		alert.showAndWait();
		if (alert.getResult() == ButtonType.OK)
			alert.close();
		show();
	}

}
