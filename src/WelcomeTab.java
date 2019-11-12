import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
/**
 * This class is the Welcome tab in GUI that asks the user for
 * their current city and currency.
 * @author sivah
 *
 */
public class WelcomeTab {
	
	static String currentCity;
	static JTextField currentCityText;
	static String currentCountry;
	static JTextField currentCountryText;
	static String currency;
	static JTextField currencyText;
	
	/**
	 * This method builds the Welcome tab that
	 * asks the user's current city and currency
	 * @param panel
	 */
	public static void buildWelcomeTab(JPanel panel) {
		JLabel label1 = new JLabel();
		label1.setText("Welcome to A Spur-of-the-moment Trip");
		panel.add(label1);
		
		JLabel label2 = new JLabel();
		label2.setText("Enter your current city:");
		panel.add(label2);
		
		currentCityText = new JTextField(10);
		panel.add(currentCityText);
		
		JLabel label3 = new JLabel();
		label2.setText("Enter your current country: ");
		panel.add(label3);
		
		currentCountryText = new JTextField(10);
		panel.add(currentCountryText);
		
		currentCity = currentCityText.getText();
		currentCountry = currentCountryText.getText();
		currency = currencyText.getText();
		
		createButtons(panel);
	}
	
	/**
	 * This method creates "Next" button that
	 * allows user to move to the next tab
	 * @param panel
	 */
	public static void createButtons(JPanel panel) {
		Button button = new Button("Next");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JTabbedPane jtp = new JTabbedPane();
				jtp.setSelectedIndex(1);
			}
			
		});
	}
	
}
