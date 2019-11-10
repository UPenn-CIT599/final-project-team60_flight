/**
 * Store information of daily weather forecast for a given city.
 * @author xueyingli
 *
 */
public class CityWeather {

	private String cityName;
	private String date;
	private int minTemperature;
	private int maxTemperature;
	private String unit = "F";
	
	private String dayCondition; //e.g. Mostly sunny, Clear, etc.
	private String nightCondition;
	
	public CityWeather(String cityName, String date, int minTemperature, int maxTemperature, String dayCondition,
			String nightCondition) {
		this.cityName = cityName;
		this.date = date;
		this.minTemperature = minTemperature;
		this.maxTemperature = maxTemperature;
		this.dayCondition = dayCondition;
		this.nightCondition = nightCondition;
	}
	
	public String getCityName() {
		return cityName;
	}
	
	public String getDate() {
		return date;
	}
	public int getMinTemperature() {
		return minTemperature;
	}
	public int getMaxTemperature() {
		return maxTemperature;
	}
	public String getUnit() {
		return unit;
	}
	public String getDayCondition() {
		return dayCondition;
	}
	public String getNightCondition() {
		return nightCondition;
	}

}
