import static org.junit.Assert.*;
import java.io.IOException;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

public class WeatherCollectionTest {

	City tp;
	WeatherCollection wc;
	final double EPS = 1E-14;
	
	@Before
	public void setUp() throws Exception {
		tp = new City(15, "Taipei", "Taiwan", "TW");
		wc = new WeatherCollection(tp, 1, new NetworkCallerMock());
	}

	@Test
	public void testReadCityURL() throws JSONException, IOException {
		JSONArray result = wc.readCityURL();
		assertTrue(result.length() == 1);
		assertEquals("4-315078_1_AL", result.getJSONObject(0).getString("Key"));
	}

	@Test
	public void testGetCityKey() throws JSONException, IOException {
		assertEquals("4-315078_1_AL", wc.getCityKey());
	}

	@Test
	public void testReadForecastURL() throws JSONException, IOException {
		JSONObject result = wc.readForecastURL();
		JSONObject daily = result.getJSONArray("DailyForecasts").getJSONObject(0); // look at the 1st day
		assertEquals("2019-12-05", daily.getString("Date").substring(0, 10));
	}

	@Test
	public void testWeatherParser() throws JSONException, IOException {
		JSONObject daily = wc.readForecastURL().getJSONArray("DailyForecasts").getJSONObject(0);
		CityWeather result = wc.weatherParser(daily);
		assertEquals("Taipei", result.getCityName());
		assertEquals("2019-12-05", result.getDate());
		assertEquals(60, result.getMinTemperature(), EPS);
		assertEquals(60, result.getMaxTemperature(), EPS);
		assertEquals("Rain", result.getDayCondition());
		assertEquals("Rain", result.getNightCondition());
	}

	@Test
	public void testGetWeatherList() throws JSONException, IOException {
		ArrayList<CityWeather> result = wc.getWeatherList();
		assertTrue(result.size() == 1);
		assertEquals("F", result.get(0).getUnit());
	}

}
