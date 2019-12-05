import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;
import org.json.JSONException;

/**
 * This class is the Weather Info tab that asks the user
 * enter the city name to check its one day forecast.
 * @author sivah
 *
 */
public class WeatherInfoTab {
	
	static CityReader cr = new CityReader();
	static JTextField getCity;
	static JTextField getDays;
	static JLabel error = new JLabel();
	static JLabel date1 = new JLabel();
	static JLabel dayCondition1 = new JLabel();
	static JLabel nightCondition1 = new JLabel();
	static JLabel maxTemp1 = new JLabel();
	static JLabel minTemp1 = new JLabel();
	static JLabel line = new JLabel();
	
	static JLabel date2 = new JLabel();
	static JLabel dayCondition2 = new JLabel();
	static JLabel nightCondition2 = new JLabel();
	static JLabel maxTemp2 = new JLabel();
	static JLabel minTemp2 = new JLabel();
	static JLabel line2 = new JLabel();
	
	static JLabel date3 = new JLabel();
	static JLabel dayCondition3 = new JLabel();
	static JLabel nightCondition3 = new JLabel();
	static JLabel maxTemp3 = new JLabel();
	static JLabel minTemp3 = new JLabel();
	static JLabel line3 = new JLabel();
	
	static JLabel date4 = new JLabel();
	static JLabel dayCondition4 = new JLabel();
	static JLabel nightCondition4 = new JLabel();
	static JLabel maxTemp4 = new JLabel();
	static JLabel minTemp4 = new JLabel();
	static JLabel line4 = new JLabel();
	
	static JLabel date5 = new JLabel();
	static JLabel dayCondition5 = new JLabel();
	static JLabel nightCondition5 = new JLabel();
	static JLabel maxTemp5 = new JLabel();
	static JLabel minTemp5 = new JLabel();
	
	static JLabel line5 = new JLabel();
	
	public static void createWeatherTab(JPanel panel) {
		
		JLabel cityName = new JLabel("Popular City name: ");
		panel.add(cityName);
		
		getCity = new JTextField(10);
		panel.add(getCity);
		
		
		JButton enterButton = new JButton("Enter");
		panel.add(enterButton);
		
		panel.add(line5);
		panel.add(error);
		
		panel.add(date1);
		panel.add(dayCondition1);
		panel.add(nightCondition1);
		panel.add(maxTemp1);
		panel.add(minTemp1);
		panel.add(line);
		
		panel.add(date2);
		panel.add(dayCondition2);
		panel.add(nightCondition2);
		panel.add(maxTemp2);
		panel.add(minTemp2);
		panel.add(line2);
		
		panel.add(date3);
		panel.add(dayCondition3);
		panel.add(nightCondition3);
		panel.add(maxTemp3);
		panel.add(minTemp3);
		panel.add(line3);
		
		panel.add(date4);
		panel.add(dayCondition4);
		panel.add(nightCondition4);
		panel.add(maxTemp4);
		panel.add(minTemp4);
		panel.add(line4);
		
		panel.add(date5);
		panel.add(dayCondition5);
		panel.add(nightCondition5);
		panel.add(maxTemp5);
		panel.add(minTemp5);
		
		enterButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				error.setText("");
				date1.setText("");
				date2.setText("");
				date3.setText("");
				date4.setText("");
				date5.setText("");

				dayCondition1.setText("");
				dayCondition2.setText("");
				dayCondition3.setText("");
				dayCondition4.setText("");
				dayCondition5.setText("");
				nightCondition1.setText("");
				nightCondition2.setText("");
				nightCondition3.setText("");
				nightCondition4.setText("");
				nightCondition5.setText("");
				maxTemp1.setText("");
				maxTemp2.setText("");
				maxTemp3.setText("");
				maxTemp4.setText("");
				maxTemp5.setText("");
				minTemp1.setText("");
				minTemp2.setText("");
				minTemp3.setText("");
				minTemp4.setText("");
				minTemp5.setText("");
				
				String city = parseCity(getCity.getText());
				
				if (cr.cityToCountry.containsKey(city)) {
					City c = new City(1, city, cr.cityToCountry.get(city), cr.cityToCountryCode.get(city));
					WeatherCollection wc = new WeatherCollection(c, 5);
					try {
						ArrayList<CityWeather> ar = wc.getWeatherList();
						date1.setText("Date: " + ar.get(0).getDate());
						dayCondition1.setText("Day condition: " + ar.get(0).getDayCondition());
						nightCondition1.setText("Night condition: " + ar.get(0).getNightCondition());
						maxTemp1.setText("Max temperature: " + Double.toString(ar.get(0).getMaxTemperature()) + " F");
						minTemp1.setText("Min temperature: " + Double.toString(ar.get(0).getMinTemperature()) + " F");
						line.setText("-------------------------------------------------------------------------------------------------------------------------------------------------------");
						
						date2.setText("Date: " + ar.get(1).getDate());
						dayCondition2.setText("Day condition: " + ar.get(1).getDayCondition());
						nightCondition2.setText("Night condition: " + ar.get(0).getNightCondition());
						maxTemp2.setText("Max temperature: " + Double.toString(ar.get(1).getMaxTemperature()) + " F");
						minTemp2.setText("Min temperature: " + Double.toString(ar.get(1).getMinTemperature()) + " F");
						line2.setText("-------------------------------------------------------------------------------------------------------------------------------------------------------");
						
						date3.setText("Date: " + ar.get(2).getDate());
						dayCondition3.setText("Day condition: " + ar.get(2).getDayCondition());
						nightCondition3.setText("Night condition: " + ar.get(2).getNightCondition());
						maxTemp3.setText("Max temperature: " + Double.toString(ar.get(2).getMaxTemperature()) + " F");
						minTemp3.setText("Min temperature: " + Double.toString(ar.get(2).getMinTemperature()) + " F");
						line3.setText("-------------------------------------------------------------------------------------------------------------------------------------------------------");
						
						date4.setText("Date: " + ar.get(3).getDate());
						dayCondition4.setText("Day condition: " + ar.get(3).getDayCondition());
						nightCondition4.setText("Night condition: " + ar.get(3).getNightCondition());
						maxTemp4.setText("Max temperature: " + Double.toString(ar.get(3).getMaxTemperature()) + " F");
						minTemp4.setText("Min temperature: " + Double.toString(ar.get(3).getMinTemperature()) + " F");
						line4.setText("-------------------------------------------------------------------------------------------------------------------------------------------------------");
						
						date5.setText("Date: " + ar.get(4).getDate());
						dayCondition5.setText("Day condition: " + ar.get(4).getDayCondition());
						nightCondition5.setText("Night condition: " + ar.get(4).getNightCondition());
						maxTemp5.setText("Max temperature: " + Double.toString(ar.get(4).getMaxTemperature()) + " F");
						minTemp5.setText("Min temperature: " + Double.toString(ar.get(4).getMinTemperature()) + " F");
						line5.setText("-------------------------------------------------------------------------------------------------------------------------------------------------------");
					} catch (JSONException | IOException e1) {
						String errorMsg = "                  No results found.                                                                                      ";
						error.setText(errorMsg);
						return;
					}
				}
				else {
					error.setText("                     This city is not supported by this program.       ");
				}
			}
			
		});
	}
	
	/**
	 * This method capitalizes the first letter of a city name
	 * @param city
	 * @return modified city name
	 */
	private static String parseCity(String city) {
		
		String converted = "";
		
		Scanner wordScan = new Scanner(city);
		
		while (wordScan.hasNext()) {
			String word = wordScan.next();
			converted += Character.toUpperCase(word.charAt(0)) + word.substring(1) + " ";
		}
		
		converted = converted.trim();
		
		return converted;
	}

}
