import java.io.IOException;
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
		String jsonText = networkCaller.Call(cityURL);
		JSONArray jsonArray = new JSONArray(jsonText);
		return jsonArray;
	}

	/**
	 * Get the city's "Key" value.
	 */
	public String getCityKey() throws JSONException, IOException {
		JSONArray jsonArray = readCityURL();
		JSONObject json = jsonArray.getJSONObject(0);
		return json.getString("Key");
	}

	/**
	 * Get weather forecasts.
	 */
	public JSONObject readForecastURL() throws JSONException, IOException {
		String url = "http://dataservice.accuweather.com/forecasts/v1/daily/";
		String apiKey = "";
		String forecastHead = url + count + "day/";
		String forecastTail = "?apikey=" + apiKey;
		String jsonText = networkCaller.Call(forecastHead, getCityKey(), forecastTail);
		JSONObject json = new JSONObject(jsonText);
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
		ArrayList<CityWeather> forecasts = new ArrayList<>();
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject daily = jsonArray.getJSONObject(i);
			forecasts.add(weatherParser(daily));
		}
		return forecasts;
	}

}

