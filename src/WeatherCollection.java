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

	private NetworkCaller networkCaller;

	public WeatherCollection(City city, int count, NetworkCaller networkCaller) {
		this.city = city;
		this.count = count;
		this.networkCaller = networkCaller;
	}

	public WeatherCollection(City city, int count) {
		this(city, count, new NetworkCaller());
	}

	/**
	 * Get the city's information.
	 */
	public JSONArray readCityURL() throws IOException, JSONException {
		String url = "http://dataservice.accuweather.com/locations/v1/cities/";
		String apiKey = "";
		String cityURL = url + city.getCountryCode() + "/search?apikey=" + apiKey +"&q=" + city.getName();

		//		InputStream is = new URL(cityURL).openStream();
		//		BufferedReader br = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8"))); //response
		//		StringBuilder sb = new StringBuilder();
		//		int next = br.read();
		//		while (next != -1) {
		//			sb.append((char)next);
		//			next = br.read();
		//		}			
		//		String jsonText = sb.toString();

		String jsonText = networkCaller.Call(cityURL, null);
		System.out.println(jsonText);
		JSONArray jsonArray = new JSONArray(jsonText);
		//		is.close();
		return jsonArray;
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
		String apiKey = "";
		String forecastURL = url + count + "day/" + getCityKey() + "?apikey=" + apiKey;

		//		InputStream is = new URL(forecastURL).openStream();
		//		BufferedReader br = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8"))); //response
		//		StringBuilder sb = new StringBuilder();
		//		int next = br.read();
		//		while (next != -1) {
		//			sb.append((char)next);
		//			next = br.read();
		//		}			

		String jsonText = networkCaller.Call(forecastURL, null);
		System.out.println(jsonText);
		JSONObject json = new JSONObject(jsonText);

		//		is.close();
		return json;
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

}

