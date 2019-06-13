/**
 * 
 */
package vehicle;

import java.io.IOException;
import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Window;
import motorway.MotorwayView;
import program.HomeController;
import motorway.Toll;
/**
 * @author alessandrodorazio
 *
 */
public class VehicleView {
	
	static AnchorPane pane = new AnchorPane();
	static AnchorPane root = new AnchorPane();

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@FXML
	public static void index(AnchorPane rootLayout) { 
		
		root = rootLayout;
		AnchorPane pane = new AnchorPane();
		
		try {
			pane = FXMLLoader.load(VehicleView.class.getResource("view/index.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		TableView table = (TableView) pane.lookup("#table");
		
		table.setRowFactory(tv -> {
            TableRow<Vehicle> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Vehicle rowV = row.getItem();
                    show(rowV.getPlateNumber());
                }
            });
            return row ;
        });

		TableColumn unitRateColumn = new TableColumn("Classe");
		unitRateColumn.setCellValueFactory(new PropertyValueFactory<>("unitRate"));
		
		TableColumn plateNumberColumn = new TableColumn("Targa");
		plateNumberColumn.setCellValueFactory(new PropertyValueFactory<>("plateNumber"));
		
		TableColumn brandColumn = new TableColumn("Marca");
		brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
		
		TableColumn modelColumn = new TableColumn("Modello");
		modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));

		table.getColumns().addAll(unitRateColumn, plateNumberColumn, brandColumn, modelColumn);
		
		table.getItems().addAll(VehicleController.index());
		rootLayout.getChildren().setAll(pane);
		
	}
	
	@FXML
	public static void create(AnchorPane rootLayout) { 
		
		try {
			pane = FXMLLoader.load(VehicleView.class.getResource("view/create.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
        rootLayout.getChildren().setAll(pane);
	}
	
	@FXML
	public static void show(String plateNumber) { 
		
		Label plate_number, brand_model_year, axis, unitRate, weight, height, noisePollution, environmentalClass;
		Vehicle v = VehicleController.show(plateNumber);
		
		try {
			pane = FXMLLoader.load(VehicleView.class.getResource("view/show.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		((Label) pane.lookup("#plate_number")).setText(v.getPlateNumber());
		((Label) pane.lookup("#brand_model_year")).setText(v.getBrand() + " " + v.getModel() + " (" + v.getYear() + ")");
		
		((Label) pane.lookup("#axis")).setText(Integer.toString(v.getAxis()) + " assi");
		
		((Label) pane.lookup("#unit_rate")).setText("Classe " + v.getUnitRate());
		((Label) pane.lookup("#environmental_class")).setText("Euro " + Integer.toString(v.getEnvironmentalClass()));
		
		((Label) pane.lookup("#height")).setText(Integer.toString(v.getHeight()) + "cm");
		((Label) pane.lookup("#weight")).setText(Integer.toString(v.getWeight()) + "kg");
		
		if(v.getNoisePollution() != 0) {
			((Label) pane.lookup("#noise_pollution")).setText(Integer.toString(v.getNoisePollution()) + "db");
		}else {
			((Label) pane.lookup("#noise_pollution")).setText("Inquinamento acustico non disponibile");
		}
		
        root.getChildren().setAll(pane);
        
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@FXML
	public static void toll_history(String plateNumber) {
		AnchorPane pane = new AnchorPane();
		try {
			pane = FXMLLoader.load(VehicleView.class.getResource("view/toll_history.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		TableView table = (TableView) pane.lookup("#table");

		TableColumn inColumn = new TableColumn("Entrata");
		inColumn.setCellValueFactory(new PropertyValueFactory<>("in"));
		
		TableColumn outColumn = new TableColumn("Uscita");
		outColumn.setCellValueFactory(new PropertyValueFactory<>("out"));
		
		TableColumn costColumn = new TableColumn("Costo");
		costColumn.setCellValueFactory(new PropertyValueFactory<>("cost"));

		table.getColumns().addAll(inColumn, outColumn, costColumn);
		
		table.getItems().addAll(VehicleController.tollHistory(plateNumber));
		root.getChildren().setAll(pane);
	}
	
	@FXML
	void btn_create(ActionEvent e) {
		create(root);
	}
	
	@FXML
	void btn_save_create(ActionEvent e) {
		String plateNumber, brand, model;
		int year, axis, weight, height, noisePollution;
		char environmentalClass;
		Vehicle v;

		plateNumber = ((TextField) pane.lookup("#plate_number")).getText().toString();
		brand = ((TextField) pane.lookup("#brand")).getText().toString();
		model = ((TextField) pane.lookup("#model")).getText().toString();
		year = Integer.parseInt(((TextField) pane.lookup("#year")).getText().toString());
		axis = Integer.parseInt(((TextField) pane.lookup("#axis")).getText().toString());
		weight = Integer.parseInt(((TextField) pane.lookup("#weight")).getText().toString());
		height = Integer.parseInt(((TextField) pane.lookup("#height")).getText().toString());
		
		if( ((TextField) pane.lookup("#noise_pollution")).isEditable() ) {
			noisePollution = Integer.parseInt(((TextField) pane.lookup("#noise_pollution")).getText().toString());
			environmentalClass = ((TextField) pane.lookup("#environmental_class")).getText().toString().charAt(0);
		} else {
			noisePollution = 0;
			environmentalClass = '0';
		}
		

		if(noisePollution == 0)
			v = VehicleController.create(brand, model, plateNumber, year, axis, weight, height);
		else
			v = VehicleController.create(brand, model, plateNumber, environmentalClass, year, axis, weight, height, noisePollution);
		
		if(v!=null) {
			Alert alert = new Alert(AlertType.INFORMATION, "Veicolo inserito", ButtonType.OK);
			alert.showAndWait();

			if (alert.getResult() == ButtonType.OK) alert.close();
		}
		
		index(root);
		
	}
	
	@FXML
	void toll_history_btn(ActionEvent e) { 
		toll_history( ((Label) pane.lookup("#plate_number")).getText().toString() );
	}
	
}
