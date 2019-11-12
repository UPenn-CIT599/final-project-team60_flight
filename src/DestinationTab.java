import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
/**
 * This class is the Destination tab that asks the user
 * to pick or enter a destination and departure and returning dates.
 * @author sivah
 *
 */
public class DestinationTab {
	
	static String selectedCity;
	static ArrayList<JRadioButton> buttons; // stores top 10 popular destinations' JRadioButtons
	static JTextField destinationText;
	static JTextField departureDateText;
	static JTextField returningDateText;
	static String userDestination;
	static String departureDate;
	static String returningDate;
	
	/**
	 * This method builds the Destination tab that allows
	 * the user to select or enter their destination,
	 * departure and returning dates
	 * @param panel
	 */
	public static void buildDestinationTab(JPanel panel) {
		CityReader cr = new CityReader();
		
		JLabel label1 = new JLabel();
		label1.setText("Select one of the top 10 popular destinations or enter your own destination: ");
		panel.add(label1);
		
		ButtonGroup G1 = new ButtonGroup();
		
		// creates JRadioButton for top 10 popular destinations
		for (City city : cr.cities) {
			buttons.add(new JRadioButton(city.getName()));
		}
		
		// group JRadioButtons so that the user can only select one of them
		for(JRadioButton button : buttons) {
			button.setActionCommand(button.getText());
			G1.add(button);
			panel.add(button);
		}
		
		selectedCity = G1.getSelection().getActionCommand();
		
		JLabel label2 = new JLabel();
		label2.setText("Your destination: ");
		panel.add(label2);
		
		// get destination from user
		destinationText = new JTextField(10);
		panel.add(destinationText);
		userDestination = destinationText.getText();
		
		// get departure date from user
		JLabel label3 = new JLabel();
		label3.setText("Departure date(yyyy-mm-dd): ");
		panel.add(label3);
		
		departureDateText = new JTextField(10);
		panel.add(departureDateText);
		departureDate = destinationText.getText();
		
		// get returning date from user
		JLabel label4 = new JLabel();
		label4.setText("Returning date(yyyy-mm-dd): ");
		panel.add(label4);
		
		returningDateText = new JTextField(10);
		panel.add(returningDateText);
		returningDate = returningDateText.getText();
		
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
				jtp.setSelectedIndex(2);
			}
			
		});
		
		Button button2 = new Button("Back");
		button2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JTabbedPane jtp = new JTabbedPane();
				jtp.setSelectedIndex(0);
			}
			
		});
		
		panel.add(button);
		panel.add(button2);
	}
}
