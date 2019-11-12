import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * This class is the Flight Info tab that displays itineraries
 * based on user's inputs
 * @author sivah
 *
 */
public class FlightInfoTab {
	
	static JLabel flightInfo;
	
	/**
	 * This method builds the Flight Info tab that displays itineraries
	 * @param panel
	 */
	public static void buildFlightInfoTab(JPanel panel) {
		String result;
		
		// get result from Skyscanner API
		result = FlightAPICaller.callFlightAPI(WelcomeTab.currentCountry, WelcomeTab.currency, WelcomeTab.currentCity, 
				DestinationTab.selectedCity, DestinationTab.departureDate, DestinationTab.returningDate);
		
		flightInfo = new JLabel();
		panel.add(flightInfo);
		
		flightInfo.setText(result);
		
		createButtons(panel);
		
	}
	
	/**
	 * This method creates "Next" and "Back" buttons
	 * that allow user to move between tabs
	 * @param panel
	 */
	public static void createButtons(JPanel panel) {
		Button button = new Button("Next");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JTabbedPane jtp = new JTabbedPane();
				jtp.setSelectedIndex(3);
			}
			
		});
		
		Button button2 = new Button("Back");
		button2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JTabbedPane jtp = new JTabbedPane();
				jtp.setSelectedIndex(1);
			}
			
		});
		
		panel.add(button);
		panel.add(button2);
	}
	
}
