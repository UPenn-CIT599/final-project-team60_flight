import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.json.JSONException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WeatherCollectionTest {
	
	WeatherCollection wc;

	@BeforeEach
	void setUp() throws Exception {
		City tp = new City(15, "Taipei", "Taiwan", "TW");
		wc = new WeatherCollection(tp, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	public void testWeatherCollectionNotNull() {
		assertNotNull(wc, "WeatherCollection cannot be null");
	}

	@Test
	void testGetCityKey() throws JSONException, IOException {
		assertEquals("4-315078_1_AL", wc.getCityKey());
	}

}
