import java.awt.FlowLayout;
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
		PopularCitiesTab.createPopularCitiesTab((panel1));
		panel1.setOpaque(false);
		
		JPanel panel3 = new JPanel();
		DestinationTab.createDestinationTab(panel3);
		panel3.setOpaque(false);
		
		JPanel panel2 = new JPanel();
		WeatherInfoTab.createWeatherTab(panel2);
		panel2.setOpaque(false);
		
		JTabbedPane tab = new JTabbedPane();
		tab.add("Pick a popular cities", panel1);
		tab.add("Weather Info", panel2);
		tab.add("or choose your own destination", panel3);
		
		
		frame.setSize(660,600);
		frame.setLayout(new FlowLayout());
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
