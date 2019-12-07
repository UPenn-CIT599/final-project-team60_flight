import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import com.google.gson.JsonParser;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * Mock API call results, for unit test purpose.
 * @author xueyingli
 *
 */
public class NetworkCallerMock extends NetworkCaller {
	
	@Override
	public String Call(String url) throws IOException {
		String jsonArrayStr = "[{\"Version\":1,\"Key\":\"4-315078_1_AL\",\"Type\":\"City\",\"Rank\":20,\"LocalizedName\":\"Taipei\",\"EnglishName\":\"Taipei City\",\"PrimaryPostalCode\":\"\",\"Region\":{\"ID\":\"ASI\",\"LocalizedName\":\"Asia\",\"EnglishName\":\"Asia\"},\"Country\":{\"ID\":\"TW\",\"LocalizedName\":\"Taiwan\",\"EnglishName\":\"Taiwan\"},\"AdministrativeArea\":{\"ID\":\"TPE\",\"LocalizedName\":\"Taipei City\",\"EnglishName\":\"Taipei City\",\"Level\":1,\"LocalizedType\":\"Special Municipality\",\"EnglishType\":\"Special Municipality\",\"CountryID\":\"TW\"},\"TimeZone\":{\"Code\":\"CST\",\"Name\":\"Asia/Taipei\",\"GmtOffset\":8.0,\"IsDaylightSaving\":false,\"NextOffsetChange\":null},\"GeoPosition\":{\"Latitude\":25.048,\"Longitude\":121.514,\"Elevation\":{\"Metric\":{\"Value\":8.0,\"Unit\":\"m\",\"UnitType\":5},\"Imperial\":{\"Value\":26.0,\"Unit\":\"ft\",\"UnitType\":0}}},\"IsAlias\":true,\"ParentCity\":{\"Key\":\"315078\",\"LocalizedName\":\"Taipei City\",\"EnglishName\":\"Taipei City\"},\"SupplementalAdminAreas\":[],\"DataSets\":[\"AirQuality\",\"PremiumAirQuality\"]}]";
		return jsonArrayStr;
	}
	
	@Override
	public String Call(String urlHead, String key, String urlTail) throws IOException {
		String jsonObjStr = "{\"Headline\":{\"EffectiveDate\":\"2019-12-05T07:00:00+08:00\",\"EffectiveEpochDate\":1575500400,\"Severity\":3,\"Text\":\"Expect rainy weather Thursday morning through Saturday morning\",\"Category\":\"rain\",\"EndDate\":\"2019-12-07T13:00:00+08:00\",\"EndEpochDate\":1575694800,\"MobileLink\":\"http://m.accuweather.com/en/tw/taipei-city/4-315078_1_al/extended-weather-forecast/4-315078_1_al?lang=en-us\",\"Link\":\"http://www.accuweather.com/en/tw/taipei-city/4-315078_1_al/daily-weather-forecast/4-315078_1_al?lang=en-us\"},\"DailyForecasts\":[{\"Date\":\"2019-12-05T07:00:00+08:00\",\"EpochDate\":1575500400,\"Temperature\":{\"Minimum\":{\"Value\":60.0,\"Unit\":\"F\",\"UnitType\":18},\"Maximum\":{\"Value\":60.0,\"Unit\":\"F\",\"UnitType\":18}},\"Day\":{\"Icon\":18,\"IconPhrase\":\"Rain\",\"HasPrecipitation\":true,\"PrecipitationType\":\"Rain\",\"PrecipitationIntensity\":\"Light\",\"LocalSource\":{\"Id\":7,\"Name\":\"Huafeng\",\"WeatherCode\":\"08\"}},\"Night\":{\"Icon\":18,\"IconPhrase\":\"Rain\",\"HasPrecipitation\":true,\"PrecipitationType\":\"Rain\",\"PrecipitationIntensity\":\"Moderate\",\"LocalSource\":{\"Id\":7,\"Name\":\"Huafeng\",\"WeatherCode\":\"08\"}},\"Sources\":[\"AccuWeather\",\"Huafeng\"],\"MobileLink\":\"http://m.accuweather.com/en/tw/taipei-city/4-315078_1_al/daily-weather-forecast/4-315078_1_al?day=1&lang=en-us\",\"Link\":\"http://www.accuweather.com/en/tw/taipei-city/4-315078_1_al/daily-weather-forecast/4-315078_1_al?day=1&lang=en-us\"}]}";	
		return jsonObjStr;
	}
	
	@Override
	public String Call(String url, HashMap<String, String> headers) throws IOException {
		JsonParser parser = new JsonParser();
		return parser.parse(new FileReader("data.json")).toString();
		
	}
	
	@Override
	public String sessionKeyHelper(String host, String body, HashMap<String, String> headers) throws UnirestException {
		return "";
	}
	
}
