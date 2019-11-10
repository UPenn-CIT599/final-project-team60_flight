import java.util.ArrayList;

/**
 * Represent weather forecast for 5, 10, or 15 days.
 * @author xueyingli
 *
 */
public class WeatherCollection {
	int count; //number of days
	ArrayList<CityWeather> weatherList = new ArrayList<>();
	
	public ArrayList<CityWeather> getWeatherList() {
		return weatherList;
	}
	
}
