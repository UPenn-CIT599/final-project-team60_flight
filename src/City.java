/**
 * This class represents a city
 * @author sivah
 *
 */
public class City {
	private int rank; // city's popularity ranking (1 = most popular)
	private String name; // city's name
	private String country; // country of the city
	
	public City(int rank, String name, String country) {
		this.rank = rank;
		this.name = name;
		this.country = country;
	}

	/**
	 * 
	 * @return city's rank
	 */
	public int getRank() {
		return rank;
	}

	/**
	 * 
	 * @return city's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @return country name
	 */
	public String getCountry() {
		return country;
	}
	
	/**
	 * display city objects by printing
	 * its rank, name, and country
	 */
	@Override
	public String toString() {
		return rank + "," + name + "," + country;
			   
	}
	
}
