import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class reads a file with the 
 * data and parse information from the csv file to create a list of City objects.
 */
public class CityReader {
	
	ArrayList<City> cities = new ArrayList<>();
	
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
				
				City city = new City(rank, name, country);
				
				cities.add(city);
				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 
	 * @return a list of City objects
	 */
	public ArrayList<City> getAllCities() {
		return cities;
	}
	
	/**
	 * take an ArrayList and returns a String which
	 * displays top 10 popular cities to travel to
	 * @param list - ArrayList that stores city objects
	 * @return numbered list of elements
	 */
	public String prettyPrint(ArrayList<City> list) {
		String display = "";
		
		for (int i = 0; i < 10; i++) {
			display += i+1 + "." + " " + list.get(i).getName() + "\n";
		}
		
		return display;
	}
	
	public static void main(String[] args) {
		CityReader cr = new CityReader();
		System.out.println(cr.cities.toString());
		System.out.println(cr.prettyPrint(cr.getAllCities()));
	}
}
