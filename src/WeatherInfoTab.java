import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
	static JLabel date = new JLabel();
	static JLabel dayCondition = new JLabel();
	static JLabel nightCondition = new JLabel();
	static JLabel maxTemp = new JLabel();
	static JLabel minTemp = new JLabel();
	
	public static void createWeatherTab(JPanel panel) {
		
		JLabel cityName = new JLabel("City name: ");
		panel.add(cityName);
		
		getCity = new JTextField(10);
		panel.add(getCity);
		
		
		JButton enterButton = new JButton("Enter");
		panel.add(enterButton);
		
		
		panel.add(error);
		
		panel.add(date);
		panel.add(dayCondition);
		panel.add(nightCondition);
		panel.add(maxTemp);
		panel.add(minTemp);
		
		enterButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String city = parseCity(getCity.getText());
				
				if (cr.cityToCountry.containsKey(city)) {
					City c = new City(1, city, cr.cityToCountry.get(city), cr.cityToCountryCode.get(city));
					WeatherCollection wc = new WeatherCollection(c, 1);
					try {
						ArrayList<CityWeather> ar = wc.getWeatherList();
						date.setText("Date: " + ar.get(0).getDate());
						dayCondition.setText("Day condition: " + ar.get(0).getDayCondition());
						nightCondition.setText("Night condition: " + ar.get(0).getNightCondition());
						maxTemp.setText("Max temperature: " + Double.toString(ar.get(0).getMaxTemperature()) + " F");
						minTemp.setText("Min temperature: " + Double.toString(ar.get(0).getMinTemperature()) + " F");
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
