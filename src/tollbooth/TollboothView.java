package tollbooth;

import java.io.IOException;
import java.lang.invoke.MethodHandles;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import motorway.MotorwayView;
import vehicle.Vehicle;
import vehicle.VehicleController;
import vehicle.VehicleView;

public class TollboothView {

	static AnchorPane pane = new AnchorPane();
	static AnchorPane root = new AnchorPane();

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void index() {
		try {
			pane = FXMLLoader.load(TollboothView.class.getResource("view/index.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		TableView table = (TableView) pane.lookup("#table");

		// update tollbooth
		table.setRowFactory(tv -> {
			TableRow<Tollbooth> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (!row.isEmpty())) {
					Tollbooth rowV = row.getItem();

					((Label) pane.lookup("#id")).setText(String.valueOf(rowV.getId()));
					((TextField) pane.lookup("#name")).setText(rowV.getName());
					((TextField) pane.lookup("#km")).setText(String.valueOf(rowV.getKm()));
					pane.lookup("#btn_delete").setVisible(true);
				}

			});
			return row;
		});

		TableColumn idColumn = new TableColumn("id");
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

		TableColumn kmColumn = new TableColumn("km");
		kmColumn.setCellValueFactory(new PropertyValueFactory<>("km"));

		TableColumn nameColumn = new TableColumn("Nome casello");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

		table.getColumns().addAll(idColumn, kmColumn, nameColumn);
		table.getItems().addAll(TollboothController.index());

		table.getSortOrder();

		root.getChildren().setAll(pane);
	}

	public static void index(AnchorPane rootLayout) {
		root = rootLayout;
		index();
	}

	public static void create() {
		try {
			pane = FXMLLoader.load(TollboothView.class.getResource("view/create.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		root.getChildren().setAll(pane);
	}

	@FXML
	void btn_create(ActionEvent e) {
		create();
	}

	@FXML
	void btn_save_create(ActionEvent e) {
		int id = Integer.valueOf(((Label) pane.lookup("#id")).getText());
		if (id == 0) {
			String name = ((TextField) pane.lookup("#name")).getText().toString();
			int km = Integer.valueOf(((TextField) pane.lookup("#km")).getText().toString());
			TollboothController.create(name, km);

			Alert alert = new Alert(AlertType.INFORMATION, "Casello aggiunto", ButtonType.OK);
			alert.showAndWait();

			if (alert.getResult() == ButtonType.OK)
				alert.close();
		} else {
			String name = ((TextField) pane.lookup("#name")).getText().toString();
			int km = Integer.valueOf(((TextField) pane.lookup("#km")).getText().toString());
			TollboothController.update(id, name, km);

			Alert alert = new Alert(AlertType.INFORMATION, "Casello aggiunto", ButtonType.OK);
			alert.showAndWait();

			if (alert.getResult() == ButtonType.OK)
				alert.close();
		}

		index();
	}

	@FXML
	void delete_tollbooth(ActionEvent e) {
		int id = Integer.valueOf(((Label) pane.lookup("#id")).getText().toString());
		TollboothController.delete(id);
		index();
	}

	@FXML
	void reset_field(ActionEvent e) {
		((Label) pane.lookup("#id")).setText("");
		((TextField) pane.lookup("#name")).setText("");
		((TextField) pane.lookup("#km")).setText("");
		pane.lookup("#btn_delete").setVisible(false);
	}

}
