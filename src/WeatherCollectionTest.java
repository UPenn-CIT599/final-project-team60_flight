import static org.junit.Assert.*;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

public class WeatherCollectionTest {
	
	String jsonArrayStr = "[{\"Version\":1,\"Key\":\"4-315078_1_AL\",\"Type\":\"City\",\"Rank\":20,\"LocalizedName\":\"Taipei\",\"EnglishName\":\"Taipei City\",\"PrimaryPostalCode\":\"\",\"Region\":{\"ID\":\"ASI\",\"LocalizedName\":\"Asia\",\"EnglishName\":\"Asia\"},\"Country\":{\"ID\":\"TW\",\"LocalizedName\":\"Taiwan\",\"EnglishName\":\"Taiwan\"},\"AdministrativeArea\":{\"ID\":\"TPE\",\"LocalizedName\":\"Taipei City\",\"EnglishName\":\"Taipei City\",\"Level\":1,\"LocalizedType\":\"Special Municipality\",\"EnglishType\":\"Special Municipality\",\"CountryID\":\"TW\"},\"TimeZone\":{\"Code\":\"CST\",\"Name\":\"Asia/Taipei\",\"GmtOffset\":8.0,\"IsDaylightSaving\":false,\"NextOffsetChange\":null},\"GeoPosition\":{\"Latitude\":25.048,\"Longitude\":121.514,\"Elevation\":{\"Metric\":{\"Value\":8.0,\"Unit\":\"m\",\"UnitType\":5},\"Imperial\":{\"Value\":26.0,\"Unit\":\"ft\",\"UnitType\":0}}},\"IsAlias\":true,\"ParentCity\":{\"Key\":\"315078\",\"LocalizedName\":\"Taipei City\",\"EnglishName\":\"Taipei City\"},\"SupplementalAdminAreas\":[],\"DataSets\":[\"AirQuality\",\"PremiumAirQuality\"]}]";
	String jsonObjStr = "[{\"Headline\":{\"EffectiveDate\":\"2019-12-05T07:00:00+08:00\",\"EffectiveEpochDate\":1575500400,\"Severity\":3,\"Text\":\"Expect rainy weather Thursday morning through Saturday morning\",\"Category\":\"rain\",\"EndDate\":\"2019-12-07T13:00:00+08:00\",\"EndEpochDate\":1575694800,\"MobileLink\":\"http://m.accuweather.com/en/tw/taipei-city/4-315078_1_al/extended-weather-forecast/4-315078_1_al?lang=en-us\",\"Link\":\"http://www.accuweather.com/en/tw/taipei-city/4-315078_1_al/daily-weather-forecast/4-315078_1_al?lang=en-us\"},\"DailyForecasts\":[{\"Date\":\"2019-12-05T07:00:00+08:00\",\"EpochDate\":1575500400,\"Temperature\":{\"Minimum\":{\"Value\":60.0,\"Unit\":\"F\",\"UnitType\":18},\"Maximum\":{\"Value\":60.0,\"Unit\":\"F\",\"UnitType\":18}},\"Day\":{\"Icon\":18,\"IconPhrase\":\"Rain\",\"HasPrecipitation\":true,\"PrecipitationType\":\"Rain\",\"PrecipitationIntensity\":\"Light\",\"LocalSource\":{\"Id\":7,\"Name\":\"Huafeng\",\"WeatherCode\":\"08\"}},\"Night\":{\"Icon\":18,\"IconPhrase\":\"Rain\",\"HasPrecipitation\":true,\"PrecipitationType\":\"Rain\",\"PrecipitationIntensity\":\"Moderate\",\"LocalSource\":{\"Id\":7,\"Name\":\"Huafeng\",\"WeatherCode\":\"08\"}},\"Sources\":[\"AccuWeather\",\"Huafeng\"],\"MobileLink\":\"http://m.accuweather.com/en/tw/taipei-city/4-315078_1_al/daily-weather-forecast/4-315078_1_al?day=1&lang=en-us\",\"Link\":\"http://www.accuweather.com/en/tw/taipei-city/4-315078_1_al/daily-weather-forecast/4-315078_1_al?day=1&lang=en-us\"}]}]";
	
	@Before
	public void setUp() throws Exception {
//		City tp = new City(15, "Taipei", "Taiwan", "TW");
//		wc = new WeatherCollection(tp, 1, new NetworkCallerMock(jsonArrayTest));
	}

	@Test
	public void testReadCityURL() throws JSONException, IOException {
		City tp = new City(15, "Taipei", "Taiwan", "TW");
		WeatherCollection wc = new WeatherCollection(tp, 1, new NetworkCallerMock(jsonArrayStr));
		JSONArray result = wc.readCityURL();
		assertTrue(result.length() == 1);
		assertEquals("4-315078_1_AL", result.getJSONObject(0).getString("Key"));
	}

	@Test
	public void testGetCityKey() throws JSONException, IOException {
		City tp = new City(15, "Taipei", "Taiwan", "TW");
		WeatherCollection wc = new WeatherCollection(tp, 1, new NetworkCallerMock(jsonArrayStr));
		assertEquals("4-315078_1_AL", wc.getCityKey());
	}

	@Test
	public void testReadForecastURL() throws JSONException, IOException {
		City tp = new City(15, "Taipei", "Taiwan", "TW");
		WeatherCollection wc = new WeatherCollection(tp, 1, new NetworkCallerMock(jsonArrayStr));
	}

	@Test
	public void testWeatherParser() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetWeatherList() {
		fail("Not yet implemented");
	}

}
