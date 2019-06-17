/**
 * 
 */
package tollbooth;

import javafx.beans.property.SimpleStringProperty;

/**
 * @author alessandrodorazio
 *
 */
public class Tollbooth {

	private int id;
	private String name;
	private int km;

	public Tollbooth(int id, String name, int km) {
		this.name = name;
		this.km = km;
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKm() {
		return km;
	}

	public void setKm(int km) {
		this.km = km;
	}

	public SimpleStringProperty idProperty() {
		return new SimpleStringProperty(String.valueOf(this.getId()));
	}

	public SimpleStringProperty kmProperty() {
		return new SimpleStringProperty(String.valueOf(this.getKm()));
	}

	public SimpleStringProperty nameProperty() {
		return new SimpleStringProperty(this.getName());
	}

}
