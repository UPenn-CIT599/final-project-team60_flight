import javax.swing.*;
/**
 * This class runs the whole program by combining
 * all the tabs and sets up JFrame
 * @author sivah
 *
 */
public class ProgramRunner extends JFrame {
	
	/**
	 * This method builds the runner GUI
	 * @param frame
	 */
	public static void buildMainRunner(JFrame frame) {
		JPanel panel1 = new JPanel();
		WelcomeTab.buildWelcomeTab(panel1);
		
		JPanel panel2 = new JPanel();
		DestinationTab.buildDestinationTab(panel2);
		
		JPanel panel3 = new JPanel();
		FlightInfoTab.buildFlightInfoTab(panel3);
		
		JPanel panel4 = new JPanel();
		WeatherInfoTab.buildWeatherInfoTab(panel4);
		
		JTabbedPane tab = new JTabbedPane();
		tab.add("Welcome", panel1);
		tab.add("Choose destination", panel2);
		tab.add("Flight Info", panel3);
		tab.add("Weather", panel4);
		
		frame.setSize(800, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setContentPane(tab);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame("Spur-of-the-moment Trip");
				buildMainRunner(frame);
			}
		});
	}

}
