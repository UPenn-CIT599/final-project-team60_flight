import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
/**
 * This class is the Weather Info tab that displays
 * a city's weather forcasts
 * @author sivah
 *
 */
public class WeatherInfoTab {
	
	static JLabel weatherInfo;
	static JTextField numOfDaysText;
	static int numOfDays;
	
	/**
	 * This method builds the Weather Info tab that displays
	 * weather info
	 * @param panel
	 */
	public static void buildWeatherInfoTab(JPanel panel) {
		JLabel label1 = new JLabel();
		label1.setText("Enter a number of forcast days: ");
		panel.add(label1);
		
		// get the number of forcast days from user
		numOfDaysText = new JTextField(10);
		panel.add(numOfDaysText);
		numOfDays = Integer.parseInt(numOfDaysText.getText());
		
		WeatherCollection wc = new WeatherCollection(city, numOfDays);
		String result = "";
		
		result = //get weather info from weather collection
		
		weatherInfo = new JLabel();
		panel.add(weatherInfo);
		
		weatherInfo.setText(result);
		
		createButtons(panel);
	}
	
	/**
	 * This method creates "Back" button
	 * @param panel
	 */
	public static void createButtons(JPanel panel) {
		
		Button button = new Button("Back");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JTabbedPane jtp = new JTabbedPane();
				jtp.setSelectedIndex(0);
			}
			
		});
		
		panel.add(button);
	}
}
