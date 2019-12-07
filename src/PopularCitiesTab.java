import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;
import javax.swing.*;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * This class is a panel that displays
 * the top 10 most popular cities and the user is allowed
 * to pick one of them and gets an itinerary
 * @author sivah
 *
 */
public class PopularCitiesTab {

	static JRadioButton jb1;
	static JRadioButton jb2;
	static JRadioButton jb3;
	static JRadioButton jb4;
	static JRadioButton jb5;
	static JRadioButton jb6;
	static JRadioButton jb7;
	static JRadioButton jb8;
	static JRadioButton jb9;
	static JRadioButton jb10;
	static JTextField departureDateText;
	static JTextField returningDateText;
	static CityReader cities = new CityReader();
	static JTextField currentCityText;
	static JTextField currentCountryText;
	static JLabel outText = new JLabel();
	static JLabel inText = new JLabel();
	static JLabel outboundDepInfo = new JLabel();
	static JLabel outboundArrInfo = new JLabel();
	static JLabel outboundStopInfo = new JLabel();
	static JLabel inboundDepInfo = new JLabel();
	static JLabel inboundArrInfo = new JLabel();
	static JLabel inboundStopInfo = new JLabel();
	static JLabel inboundInfo = new JLabel();
	static JLabel priceInfo = new JLabel();
	static JLabel bookingInfo = new JLabel();
	static private String bookText = "Book your flight here";
	static private JLabel hyperlink = new JLabel();
	static private JLabel error = new JLabel();
	static JLabel line = new JLabel();
	static JLabel line2 = new JLabel();
	static JLabel line3 = new JLabel();
	static JLabel line4 = new JLabel();

	/**
	 * This method constructs a panel that displays top 10
	 * most popular cities and allows user to pick one of them and
	 * receives an itinerary
	 * @param panel
	 */
	public static void createPopularCitiesTab(JPanel panel) {

		JLabel label1 = new JLabel();
		label1.setText("Select one of the top 10 popular destinations: ");
		panel.add(label1);

		ButtonGroup G1 = new ButtonGroup();

		jb1 = new JRadioButton("Hong Kong");
		G1.add(jb1);
		panel.add(jb1);

		jb2 = new JRadioButton("Bangkok");
		G1.add(jb2);
		panel.add(jb2);

		jb3 = new JRadioButton("London");
		G1.add(jb3);
		panel.add(jb3);

		jb4 = new JRadioButton("Singapore");
		G1.add(jb4);
		panel.add(jb4);

		jb5 = new JRadioButton("Macau");
		G1.add(jb5);
		panel.add(jb5);

		jb6 = new JRadioButton("Dubai");
		G1.add(jb6);
		panel.add(jb6);

		jb7 = new JRadioButton("Paris");
		G1.add(jb7);
		panel.add(jb7);

		jb8 = new JRadioButton("New York City");
		G1.add(jb8);
		panel.add(jb8);

		jb9 = new JRadioButton("Shenzhen");
		G1.add(jb9);
		panel.add(jb9);

		jb10 = new JRadioButton("Kuala Lumpur");
		G1.add(jb10);
		panel.add(jb10);
		
		JLabel space3 = new JLabel("                          ");
		panel.add(space3);

		JLabel currCity = new JLabel("Your current city: ");
		panel.add(currCity);

		currentCityText = new JTextField(10);
		panel.add(currentCityText);

		JLabel space = new JLabel("             ");
		panel.add(space);

		JLabel currCountry = new JLabel("Your current country: ");
		panel.add(currCountry);

		currentCountryText = new JTextField(10);
		panel.add(currentCountryText);

		// get departure date from user
		JLabel label3 = new JLabel();
		label3.setText("Departure date(yyyy-mm-dd): ");
		panel.add(label3);

		departureDateText = new JTextField(10);
		panel.add(departureDateText);

		// get returning date from user
		JLabel label4 = new JLabel();
		label4.setText("Returning date(yyyy-mm-dd): ");
		panel.add(label4);

		returningDateText = new JTextField(10);
		panel.add(returningDateText);
		
		JLabel space4 = new JLabel("                         ");
		panel.add(space4);

		JButton enterButton = new JButton("Enter");
		panel.add(enterButton);
		
		panel.add(line4);

		panel.add(error);
		panel.add(priceInfo);
		panel.add(line);

		panel.add(outText);
		panel.add(outboundDepInfo);
		panel.add(outboundArrInfo);
		panel.add(outboundStopInfo);
		panel.add(line2);

		panel.add(inText);
		panel.add(inboundDepInfo);
		panel.add(inboundArrInfo);
		panel.add(inboundStopInfo);
		panel.add(line3);

		panel.add(hyperlink);

		enterButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				error.setText("");
				priceInfo.setText("");
				outText.setText("");
				outboundDepInfo.setText("");
				outboundArrInfo.setText("");
				outboundStopInfo.setText("");
				inText.setText("");
				inboundDepInfo.setText("");
				inboundArrInfo.setText("");
				inboundStopInfo.setText("");
				hyperlink.setText("");
				
				String destCity = "";
				String destCountry = "";
				String userCity = currentCityText.getText();
				String userCountry = parseCountry(currentCountryText.getText());
				String departureDate = departureDateText.getText();
				String returningDate = returningDateText.getText();

				if (jb1.isSelected()) {
					destCity = jb1.getText();
					destCountry = cities.cityToCountry.get(destCity);
				}
				else if (jb2.isSelected()) {
					destCity = jb2.getText();
					destCountry = cities.cityToCountry.get(destCity);
				}
				else if (jb3.isSelected()) {
					destCity = jb3.getText();
					destCountry = cities.cityToCountry.get(destCity);
				}
				else if (jb4.isSelected()) {
					destCity = jb4.getText();
					destCountry = cities.cityToCountry.get(destCity);
				}
				else if (jb5.isSelected()) {
					destCity = jb5.getText();
					destCountry = cities.cityToCountry.get(destCity);
				}
				else if (jb6.isSelected()) {
					destCity = jb6.getText();
					destCountry = cities.cityToCountry.get(destCity);
				}
				else if (jb7.isSelected()) {
					destCity = jb7.getText();
					destCountry = cities.cityToCountry.get(destCity);
				}
				else if (jb8.isSelected()) {
					destCity = jb8.getText();
					destCountry = cities.cityToCountry.get(destCity);
				}
				else if (jb9.isSelected()) {
					destCity = jb9.getText();
					destCountry = cities.cityToCountry.get(destCity);
				}
				else if (jb10.isSelected()) {
					destCity = jb10.getText();
					destCountry = cities.cityToCountry.get(destCity);
				}

				try {
					FlightAPICaller flight = new FlightAPICaller(userCity, userCountry, destCity, destCountry, departureDate, returningDate);
					flight.callFlightAPI();
					flight.getLegIDsPriceAndBookingURL();
					flight.makeFlights();

					String price = "             Price: " + flight.price + " USD";
					final String bookURL = flight.bookingURL;

					String outLabel = "Outbound flight:";
					String outDepart = "Departure Time: " + flight.outboundFlight.getDepartTime();
					String outArr = "Arrival Time: " + flight.outboundFlight.getArrTime();
					String outStops = "Number of Stops: " + flight.outboundFlight.getNumOfStops();

					String inLabel = "Inbound flight:";
					String inDepart = "Departure Time: " + flight.inboundFlight.getDepartTime();
					String inArr = "Arrival Time: " + flight.inboundFlight.getArrTime();
					String inStops = "Number of Stops: " + flight.inboundFlight.getNumOfStops();

					priceInfo.setText(price);

					hyperlink.setText(bookText);
					hyperlink.setForeground(Color.BLUE.darker());
					hyperlink.setCursor(new Cursor(Cursor.HAND_CURSOR));

					hyperlink.addMouseListener(new MouseAdapter() {

						@Override
						public void mouseClicked(MouseEvent e) {
							try {
								Desktop.getDesktop().browse(new URI(bookURL));
							} catch (IOException | URISyntaxException e1) {
								e1.printStackTrace();
							}
						}

						@Override
						public void mouseExited(MouseEvent e) {
							hyperlink.setText(bookText);
						}

						@Override
						public void mouseEntered(MouseEvent e) {
							hyperlink.setText("<html><a href=''>" + bookText + "</a></html>");
						}

					});

					outText.setText(outLabel);
					outboundDepInfo.setText(outDepart);
					outboundArrInfo.setText(outArr);
					outboundStopInfo.setText(outStops);
					
					line.setText("-------------------------------------------------------------------------------------------------------------------------------------------------------");
					line2.setText("-------------------------------------------------------------------------------------------------------------------------------------------------------");
					line3.setText("-------------------------------------------------------------------------------------------------------------------------------------------------------");
					line4.setText("-------------------------------------------------------------------------------------------------------------------------------------------------------");
					inText.setText(inLabel);
					inboundDepInfo.setText(inDepart);
					inboundArrInfo.setText(inArr);
					inboundStopInfo.setText(inStops);


				} catch (UnirestException | NullPointerException | IndexOutOfBoundsException | IOException e1) {
					String errorMsg = "API request error. Try again                                                                                          ";
					error.setText(errorMsg);
					return;
				}
			}

		});

	}

	/**
	 * This method capitalizes the first letter of a country name
	 * @param country
	 * @return modified country name
	 */
	private static String parseCountry(String country) {

		String converted = "";

		Scanner wordScan = new Scanner(country);

		while (wordScan.hasNext()) {
			String word = wordScan.next();
			converted += Character.toUpperCase(word.charAt(0)) + word.substring(1) + " ";
		}

		converted = converted.trim();

		return converted;
	}


}
