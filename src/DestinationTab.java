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
 * This class is the Destination tab that asks the user
 * enter a destination and departure and returning dates
 * to see an itinerary.
 * @author sivah
 *
 */
public class DestinationTab {
	
	static String selectedCity;
	static JRadioButton[] buttons; // stores top 10 popular destinations' JRadioButtons
	static JTextField destinationText;
	static JTextField currentCityText;
	static JTextField departureDateText;
	static JTextField returningDateText;
	static JTextField destCountryText;
	static JTextField currentCountryText;
	static String DestinationCountry;
	static String userDestination;
	static String departureDate;
	static String returningDate;
	static String userCity;
	static String userCountry;
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
	private static String bookText = "Book your flight here";
	private static JLabel hyperlink = new JLabel();
	private static JLabel error = new JLabel();
	static JLabel line = new JLabel();
	static JLabel line2 = new JLabel();
	static JLabel line3 = new JLabel();
	static JLabel line4 = new JLabel();
	
	/**
	 * This method constructs the destination panel that allows
	 * user to enter their own destinations and get the cheapest price,
	 * departure and arrival times of the flight
	 * @param panel
	 */
	public static void createDestinationTab(JPanel panel) {
		
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
		
		
		JLabel label2 = new JLabel();
		label2.setText("Your destination city: ");
		panel.add(label2);
		
		// get destination from user
		destinationText = new JTextField(10);
		panel.add(destinationText);
		
		JLabel countryLabel = new JLabel();
		countryLabel.setText("Your destination country: ");
		panel.add(countryLabel);
		
		destCountryText = new JTextField(10);
		panel.add(destCountryText);
		
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
		
		JLabel space3 = new JLabel("                               ");
		panel.add(space3);
		
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
				
				userDestination = destinationText.getText();
				DestinationCountry = parseCountry(destCountryText.getText());
				departureDate = departureDateText.getText();
				returningDate = returningDateText.getText();
				userCountry = parseCountry(currentCountryText.getText());
				userCity = currentCityText.getText();
				
				try {
					FlightAPICaller flight = new FlightAPICaller(userCity, userCountry, userDestination, DestinationCountry, departureDate, returningDate);
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
					inText.setText(inLabel);
					line.setText("-------------------------------------------------------------------------------------------------------------------------------------------------------");
					line2.setText("-------------------------------------------------------------------------------------------------------------------------------------------------------");
					line3.setText("-------------------------------------------------------------------------------------------------------------------------------------------------------");
					line4.setText("-------------------------------------------------------------------------------------------------------------------------------------------------------");
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
