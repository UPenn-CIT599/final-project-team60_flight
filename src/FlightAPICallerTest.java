import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mashape.unirest.http.exceptions.UnirestException;

public class FlightAPICallerTest {

	FlightAPICaller caller;
	String mockURL = "http://partners.api.skyscanner.net/apiservices/deeplink/v2?_cje=NqagL4PVt5peZGMG189S4zg4dm39CEoR%2bV8UrnEXQEuJZ36mI8cG9Fi4G8qRAMyN&url=https%3a%2f%2fwww.skyscanner.net%2ftransport_deeplink%2f4.0%2fUS%2fen-US%2fUSD%2fetrp%2f2%2f16177.5772.2020-01-01%2c5772.16177.2020-01-10%2fair%2ftrava%2fflights%3fitinerary%3dflight%7c-32593%7c790%7c16177%7c2020-01-01T23%3a15%7c11442%7c2020-01-02T07%3a26%7c311%7c-%7c-%7c-%2cflight%7c-32593%7c1059%7c12712%7c2020-01-10T09%3a30%7c16177%7c2020-01-10T12%3a55%7c385%7c-%7c-%7c-%26carriers%3d-32593%26operators%3d-32593%2c-32593%26passengers%3d1%26channel%3ddataapi%26cabin_class%3deconomy%26facilitated%3dfalse%26ticket_price%3d326.58%26is_npt%3dfalse%26is_multipart%3dfalse%26client_id%3dskyscanner_b2b%26q_ids%3detrp.6993.5772.200101.200110.1..E%7c-5960477945768868459%26q_sources%3dJACQUARD%26commercial_filters%3dfalse%26q_datetime_utc%3d2019-12-05T19%3a59%3a00%26pqid%3dfalse";
	String outID = "16177-2001012315--32593-0-11442-2001020726";
	String inID = "12712-2001100930--32593-0-16177-2001101255";
	final double EPS = 1E-14;

	@Before
	public void setUp() throws Exception {
		caller = new FlightAPICaller("Seattle", "United States", "New York", "United States", "2020-01-01", "2020-01-10", new NetworkCallerMock());
	}

	@Test
	public void testCallFlightAPI() throws NullPointerException, UnirestException, IOException {
		caller.callFlightAPI();
		JsonObject apiBody = caller.getApiBody();
		String carrier = apiBody.getAsJsonArray("Carriers").get(0).getAsJsonObject().get("Name").getAsString();
		JsonElement leg = apiBody.getAsJsonArray("Legs").get(0);
		String flightNumber = leg.getAsJsonObject().get("FlightNumbers").getAsJsonArray().get(0).getAsJsonObject().get("FlightNumber").getAsString();
		assertEquals("jetBlue", carrier);
		assertTrue(Integer.parseInt(flightNumber) == 264);
	}

	@Test
	public void testGetCountryCode() throws NullPointerException, UnirestException, IOException {
		assertEquals("US", caller.getCountryCode("United States"));
		assertEquals("ZW", caller.getCountryCode("Zimbabwe"));
	}

	@Test
	public void testGetCityCode() throws NullPointerException, UnirestException, IOException {
		String cityName = "Seattle";
		String countryName = "United States";
		assertEquals("SEAA-sky", caller.getCityCode(cityName, countryName));
	}

	@Test
	public void testGetTimesAndStops() throws NullPointerException, UnirestException, IOException {
		caller.callFlightAPI();
		String date = "2020-01-01";
		assertTrue(caller.getTimesAndStops(outID, inID).size() == 6);
		assertEquals(date, caller.getTimesAndStops(outID, inID).get(0).substring(0, 10));
	}

	@Test
	public void testGetInboundLegID() throws NullPointerException, UnirestException, IOException {
		caller.callFlightAPI();
		caller.getLegIDsPriceAndBookingURL();
		assertEquals(inID, caller.getInboundLegID());
	}

	@Test
	public void testGetOutboundLegID() throws NullPointerException, UnirestException, IOException {
		caller.callFlightAPI();
		caller.getLegIDsPriceAndBookingURL();
		assertEquals(outID, caller.getOutboundLegID());
	}
	
	@Test
	public void testGetPrice() throws NullPointerException, UnirestException, IOException {
		caller.callFlightAPI();
		caller.getLegIDsPriceAndBookingURL();
		double price = Double.parseDouble(caller.getPrice());
		assertEquals(326.58, price, EPS);
	}

	@Test
	public void testGetBookingURL() throws NullPointerException, UnirestException, IOException {
		caller.callFlightAPI();
		caller.getLegIDsPriceAndBookingURL();
		assertEquals(mockURL, caller.getBookingURL());
	}

}
