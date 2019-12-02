import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Represent weather forecasts for a city.
 * Call API to check the weather.
 * @author xueyingli
 *
 */
public class WeatherCollection {
	private City city;
	private int count; //number of days: 1, 5, 10, or 15.
//	String test = "[{\"Version\":1,\"Key\":\"4-315078_1_AL\",\"Type\":\"City\",\"Rank\":20,\"LocalizedName\":\"Taipei\",\"EnglishName\":\"Taipei City\",\"PrimaryPostalCode\":\"\",\"Region\":{\"ID\":\"ASI\",\"LocalizedName\":\"Asia\",\"EnglishName\":\"Asia\"},\"Country\":{\"ID\":\"TW\",\"LocalizedName\":\"Taiwan\",\"EnglishName\":\"Taiwan\"},\"AdministrativeArea\":{\"ID\":\"TPE\",\"LocalizedName\":\"Taipei City\",\"EnglishName\":\"Taipei City\",\"Level\":1,\"LocalizedType\":\"Special Municipality\",\"EnglishType\":\"Special Municipality\",\"CountryID\":\"TW\"},\"TimeZone\":{\"Code\":\"CST\",\"Name\":\"Asia/Taipei\",\"GmtOffset\":8.0,\"IsDaylightSaving\":false,\"NextOffsetChange\":null},\"GeoPosition\":{\"Latitude\":25.048,\"Longitude\":121.514,\"Elevation\":{\"Metric\":{\"Value\":8.0,\"Unit\":\"m\",\"UnitType\":5},\"Imperial\":{\"Value\":26.0,\"Unit\":\"ft\",\"UnitType\":0}}},\"IsAlias\":true,\"ParentCity\":{\"Key\":\"315078\",\"LocalizedName\":\"Taipei City\",\"EnglishName\":\"Taipei City\"},\"SupplementalAdminAreas\":[],\"DataSets\":[\"AirQuality\",\"PremiumAirQuality\"]}]";

	public WeatherCollection(City city, int count) {
		this.city = city;
		this.count = count;
	}
	
	/**
	 * Get the city's information.
	 */
	public JSONArray readCityURL() throws IOException, JSONException {
		String url = "http://dataservice.accuweather.com/locations/v1/cities/";
		String cityURL = url + city.getCountryCode() + "/search?apikey=ywatq7GdyGuuvoG8JHZ86DJUVeqKIQpa&q=" + city.getName();
		InputStream is = new URL(cityURL).openStream();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8"))); //response
			StringBuilder sb = new StringBuilder();
			int next = br.read();
			while (next != -1) {
				sb.append((char)next);
				next = br.read();
			}			
			String jsonText = sb.toString();
//			System.out.println(jsonText);
			JSONArray jsonArray = new JSONArray(jsonText);
			return jsonArray;
		} finally {
			is.close();
		}
	}
	
	/**
	 * Get the city's "Key" value.
	 */
	public String getCityKey() throws JSONException, IOException {
		JSONArray jsonArray = readCityURL();
		JSONObject json = jsonArray.getJSONObject(0);
		return json.getString("Key");
//		return json.getJSONObject("Region").getString("ID");
	}

	/**
	 * Get weather forecasts.
	 */
	public JSONObject readForecastURL() throws JSONException, IOException {
		String url = "http://dataservice.accuweather.com/forecasts/v1/daily/";
		String forecastURL = url + count + "day/" + getCityKey() + "?apikey=ywatq7GdyGuuvoG8JHZ86DJUVeqKIQpa";
		InputStream is = new URL(forecastURL).openStream();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8"))); //response
			StringBuilder sb = new StringBuilder();
			int next = br.read();
			while (next != -1) {
				sb.append((char)next);
				next = br.read();
			}			
			String jsonText = sb.toString();
//			System.out.println(jsonText);
			JSONObject json = new JSONObject(jsonText);
			return json;
		} finally {
			is.close();
		}
	}

	public CityWeather weatherParser(JSONObject daily) {
		String date = daily.getString("Date").substring(0, 10);
		JSONObject temperatures = daily.getJSONObject("Temperature");
		double minTemperature = temperatures.getJSONObject("Minimum").getDouble("Value");
		double maxTemperature = temperatures.getJSONObject("Maximum").getDouble("Value");
		String dayCondition = daily.getJSONObject("Day").getString("IconPhrase");
		String nightCondition = daily.getJSONObject("Night").getString("IconPhrase");
		return new CityWeather(city.getName(), date, minTemperature, maxTemperature, dayCondition, nightCondition);
	}
	
	public ArrayList<CityWeather> getWeatherList() throws JSONException, IOException {
		JSONObject json = readForecastURL();
		JSONArray jsonArray = json.getJSONArray("DailyForecasts");
//		ArrayList<JSONObject> dailyForecasts = new ArrayList<>();
		ArrayList<CityWeather> forecasts = new ArrayList<>();
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject daily = jsonArray.getJSONObject(i);
			forecasts.add(weatherParser(daily));
		}
		return forecasts;
	}

	
	public static void main(String[] args) throws JSONException, IOException {
		City c = new City(15, "Taipei", "Taiwan", "TW");
		WeatherCollection wc = new WeatherCollection(c, 1);
//		JSONArray ja = wc.readCityURL();
		System.out.println(wc.getCityKey());
		System.out.println("Useful info:");
		ArrayList<CityWeather> ar = wc.getWeatherList();
		for (int i = 0; i < ar.size(); i++) {
			System.out.println(ar.get(i).getCityName());
			System.out.println(ar.get(i).getDate());
			System.out.println(ar.get(i).getDayCondition());
			System.out.println(ar.get(i).getMaxTemperature());
			System.out.println(ar.get(i).getMinTemperature());
			System.out.println(ar.get(i).getNightCondition());
			System.out.println(ar.get(i).getUnit());
		}
	}

}

