/**
 * 
 */
package motorway;

import helper.Helper;
import javafx.beans.property.SimpleStringProperty;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import helper.Costant;
import tollbooth.Tollbooth;
import tollbooth.TollboothController;
import vehicle.Vehicle;

/**
 * @author alessandrodorazio
 *
 */
public class Toll {
	
	int id;
	String vehicle;
	float cost;
	int in, out;
	Date date;

	public Toll(Vehicle v, Tollbooth in, Tollbooth out) {
		this.vehicle = v.getPlateNumber();
		this.in = in.getId();
		this.out = out.getId();
		this.cost = tollCalc(in, out, v);
		//send data
	}

	
	public Toll(int id, Vehicle v, float cost, Tollbooth in, Tollbooth out, Date date) {
		this.id = id;
		this.vehicle = v.getPlateNumber();
		this.cost = cost;
		this.in = in.getId();
		this.out = out.getId();
		this.date = date;
	}

	public float tollCalc(Tollbooth in, Tollbooth out, Vehicle v) {
		int year = Calendar.getInstance().get(Calendar.YEAR);
		float km = routeCalc(in, out);
		float cost;
        if(km == 0) throw new Error("I due caselli coincidono"); //throw exception
        //if 2021 and heavy or 2026
        if( ((year >= 2021) && (v.getUnitRateInt() > 1)) || (year >= 2026) ) {
            cost = km * Motorway.getInstance().getEcoRateSingle(v) * (1 + Costant.IVA);
        }else {
            cost = km * Motorway.getInstance().getUnitRateSingle(v) * (1 + Costant.IVA);
        }
        cost*Motorway.getInstance().getType();
        return Helper.round(cost);
	}

	public static int routeCalc(Tollbooth in, Tollbooth out) {
		return (in.getKm() > out.getKm())?(in.getKm() - out.getKm()):(out.getKm() - in.getKm());
	}
	
	public SimpleStringProperty inProperty() {
		return new SimpleStringProperty(TollboothController.show(in).getName());
	}
	
	public SimpleStringProperty outProperty() {
		return new SimpleStringProperty(TollboothController.show(out).getName());
	}
	
	public SimpleStringProperty costProperty() {
		return new SimpleStringProperty(Float.toString(cost));
	}


	public SimpleStringProperty dateProperty() {
		return new SimpleStringProperty( new SimpleDateFormat("dd/MM/yyyy").format(date).toString());
	}
	
}
