/**
 * 
 */
package tollbooth;

/**
 * @author alessandrodorazio
 *
 */
public class Tollbooth {

	private int id;
	private String name;
	private int km;
	
	public Tollbooth(String name, int km) {
		this.name = name;
		this.km = km;
		this.id = 1;
	}

	public int getId() { return id; }
	
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public int getKm() { return km; }
	public void setKm(int km) { this.km = km; }
	
	
	
}
