import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * This class reads a file with the 
 * data and parse information from the csv file to create a list of City objects.
 */
public class CityReader {
	
	ArrayList<City> cities = new ArrayList<>();
	HashMap<String, String> cityToCountry = new HashMap<String, String>();
	HashMap<String, String> cityToCountryCode = new HashMap<String, String>();
	
	public CityReader() {
		try {
			Scanner in = new Scanner(new FileReader("Popular_cities.csv"));
			in.nextLine(); // Skip the column labels
			
			while(in.hasNextLine()) {
				String cityRow = in.nextLine();
				String[] columnData = cityRow.split(",");
				
				int rank = Integer.parseInt(columnData[0]);
				String name = columnData[1];
				String country = columnData[2];
				String countryCode = columnData[2];
				
				City city = new City(rank, name, country, countryCode);
				
				cities.add(city);
				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		mapCity();
		
	}
	
	/**
	 * This method creates a HashMap that maps city to its country
	 * and city to its country code
	 */
	private void mapCity() {
		for (City c : cities) {
			cityToCountry.put(c.getName(), c.getCountry());
			cityToCountryCode.put(c.getName(), c.getCountryCode());
		}
	}
	
	public static void main(String[] args) {
		CityReader cr = new CityReader();
		System.out.println(cr.cities.toString());
	}
}
