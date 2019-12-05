import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * This class calls Skyscanner API to get itinaries
 * @author sivah
 *
 */
public class FlightAPICaller {
	
	private String destination;
	private String originPlace;
	private String outboundDate;
	private String inboundDate;
	public String inboundLegID;
	public String outboundLegID;
	public String price;
	public String bookingURL;
	public OutBoundFlight outboundFlight;
	public InBoundFlight inboundFlight;
	private JsonObject apiBody;
	
	public FlightAPICaller(String originPlace, String originCountry, String destPlace, String destCountry, String outboundDate, String inboundDate) 
			throws UnirestException, NullPointerException {
		
		this.originPlace = getCityCode(originPlace, originCountry);
		this.destination = getCityCode(destPlace, destCountry);
		this.outboundDate = outboundDate;
		this.inboundDate = inboundDate;
	}
	
	/**
	 * This method calls Skyscanner API and stores the result of api request in apiBody instance variable
	 * @throws UnirestException, NullPointerException 
	 */
	public void callFlightAPI() throws UnirestException, NullPointerException {
		
		String sessionKey = getSessionKey();
		// User needs to put their api key here
		String x_rapidapi_key = "";
		
		HttpResponse<JsonNode> response = Unirest.get("https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com/apiservices/pricing/uk2/v1.0/"
				+ sessionKey + "?sortType=price&sortOrder=asc&pageIndex=0&pageSize=10")
				.header("x-rapidapi-host", "skyscanner-skyscanner-flight-search-v1.p.rapidapi.com")
				.header("x-rapidapi-key", x_rapidapi_key)
				.asJson();
		
		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(response.getBody().toString());
		apiBody = je.getAsJsonObject();
		
	}
	
	/**
	 * This method calls Skyscanner API to get country codes
	 * @return HashMap that maps countries to country codes
	 * @throws UnirestException
	 */
	public HashMap<String, String> getCountryCodeMap() throws UnirestException {
		
		HashMap<String, String> codeToCountry = new HashMap<String, String>();
		String x_rapidapi_key = "";
		
		HttpResponse<JsonNode> response = Unirest.get("https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com/apiservices/reference/v1.0/countries/en-US")
				.header("x-rapidapi-host", "skyscanner-skyscanner-flight-search-v1.p.rapidapi.com")
				.header("x-rapidapi-key", x_rapidapi_key)
				.asJson();
		
		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(response.getBody().toString());
		JsonObject obj = je.getAsJsonObject();
		
		JsonArray countryArr = obj.getAsJsonArray("Countries");
		
		for (JsonElement country : countryArr) {
			JsonObject countryObj = country.getAsJsonObject();
			String code = countryObj.get("Code").toString().substring(1, countryObj.get("Code").toString().length() - 1);
			String name = countryObj.get("Name").toString().substring(1, countryObj.get("Name").toString().length() - 1);
			codeToCountry.put(name, code);
		}
		
		return codeToCountry;
	}
	
	/**
	 * This method takes in a country name and returns the country code from a HashMap that maps country to country codes
	 * @param country
	 * @return country code
	 * @throws UnirestException
	 * @throws NullPointerException
	 */
	public String getCountryCode(String country) throws UnirestException, NullPointerException {
		
		String code = "";
		HashMap<String, String> codeToCountry = getCountryCodeMap();
		
		code = codeToCountry.get(country);
		
		return code;
		
	}
	
	/**
	 * This method gets inbound and outbound leg IDs, price of a flight, and the booking URL from api body instance variable
	 * and stores them in instance variable
	 * @throws UnirestException
	 * @throws IndexOutOfBoundsException
	 */
	public void getLegIDsPriceAndBookingURL() throws UnirestException, IndexOutOfBoundsException {
		
		JsonParser jp = new JsonParser();
		
		JsonArray itinerariesArr = apiBody.getAsJsonArray("Itineraries");
		JsonElement itinerariesElement0 = jp.parse(itinerariesArr.get(0).toString());
		
		JsonObject itinerariesObj = itinerariesElement0.getAsJsonObject();
		
		inboundLegID = itinerariesObj.get("InboundLegId").toString().substring(1, itinerariesObj.get("InboundLegId").toString().length() - 1);
		outboundLegID = itinerariesObj.get("OutboundLegId").toString().substring(1, itinerariesObj.get("OutboundLegId").toString().length() - 1);
		
		JsonArray pricingOptions = itinerariesObj.getAsJsonArray("PricingOptions");
		JsonElement pricingElement0 = jp.parse(pricingOptions.get(0).toString());
		JsonObject pricingObj = pricingElement0.getAsJsonObject();
		
		price = pricingObj.get("Price").toString();
		
		bookingURL = pricingObj.get("DeeplinkUrl").toString().substring(1, pricingObj.get("DeeplinkUrl").toString().length() - 1);
	}

	/**
	 * This method takes in a city name and its country name and returns the city's code
	 * @param cityName
	 * @param countryName
	 * @return city code
	 * @throws UnirestException
	 * @throws NullPointerException
	 */
	public String getCityCode(String cityName, String countryName) throws UnirestException, NullPointerException {
		
		String cityCode = "";
		String countryCode = getCountryCode(countryName);
		String charset = "UTF-8";
		String s = cityName;
		String query = "";
		try {
			query = String.format("%s", URLEncoder.encode(s, charset));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
		String x_rapidapi_key = "";
		
		HttpResponse<JsonNode> response = Unirest.get("https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com/apiservices/autosuggest/v1.0/" 
		+ countryCode + "/USD/en-US/?query=" + query)
				.header("x-rapidapi-host", "skyscanner-skyscanner-flight-search-v1.p.rapidapi.com")
				.header("x-rapidapi-key", x_rapidapi_key)
				.asJson();
		
		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(response.getBody().toString());
		JsonObject obj = je.getAsJsonObject();
		
		JsonArray placesArr = obj.getAsJsonArray("Places");
		JsonElement placesElement0 = jp.parse(placesArr.get(0).toString());
		JsonObject placesObj = placesElement0.getAsJsonObject();
		
		cityCode = placesObj.get("CityId").toString().substring(1, placesObj.get("CityId").toString().length() - 1);
		
		return cityCode;
	}
	
	/**
	 * This method takes in outbound and inbound leg IDs and returns an arrayList
	 * that stores outbound and inbound flights' departure and arrival times and
	 * the number of layovers
	 * @param outID
	 * @param inID
	 * @return arrayList that contains departure and arrival times and number of stops
	 * @throws UnirestException
	 */
	public ArrayList<String> getTimesAndStops(String outID, String inID) throws UnirestException {
		// element 0 = departure (outbound), element 1 = arrival (outbound), element 2 = departure (inbound), element 3 = arrival (inbound)
		// element 4 = number of stops (outbound), element 5 = number of stops (inbound)
		ArrayList<String> depArr = new ArrayList<String>();
		
		JsonParser jp = new JsonParser();
		
		HashMap<String, String> idToDeparture = new HashMap<String, String>();
		HashMap<String, String> idToArrival = new HashMap<String, String>();
		HashMap<String, String> idToStops = new HashMap<String, String>();
		
		JsonArray arr = apiBody.getAsJsonArray("Legs");
		
		for (JsonElement ar : arr) {
			JsonObject legsObj = ar.getAsJsonObject();
			String id = legsObj.get("Id").toString().substring(1, legsObj.get("Id").toString().length() - 1);
			String departure = legsObj.get("Departure").toString().substring(1, legsObj.get("Departure").toString().length() - 1);
			String arrival = legsObj.get("Arrival").toString().substring(1, legsObj.get("Arrival").toString().length() - 1);
			JsonArray stopArr = legsObj.getAsJsonArray("Stops");
			String numOfStops =  Integer.toString(stopArr.size());
			idToDeparture.put(id, departure);
			idToArrival.put(id, arrival);
			idToStops.put(id, numOfStops);
		}
		
		depArr.add(0, idToDeparture.get(outID));
		depArr.add(1, idToArrival.get(outID));
		depArr.add(2, idToDeparture.get(inID));
		depArr.add(3, idToArrival.get(inID));
		depArr.add(4, idToStops.get(outID));
		depArr.add(5, idToStops.get(inID));
		
		return depArr;
	}
	
	/**
	 * This method combines departure and arrival times and
	 * number of layovers to make outbound and inbound flight objects
	 * @throws UnirestException
	 */
	public void makeFlights() throws UnirestException {
		
		ArrayList<String> depArr = new ArrayList<String>();
		
		depArr = getTimesAndStops(outboundLegID, inboundLegID);
		
		outboundFlight = new OutBoundFlight(depArr.get(0), depArr.get(1), depArr.get(4));
		
		inboundFlight = new InBoundFlight(depArr.get(2), depArr.get(3), depArr.get(5));
	}
	
	/**
	 * This method creates Skyscanner API session to get the session key
	 * that is required to view the itineraries
	 * @throws UnirestException
	 * @return sessionKey
	 */
	private String getSessionKey() throws UnirestException {
		
		List<String> sessionKeyList;
		String[] sessionKeyArr;
		String sessionKey;
		String x_rapidapi_key = "";
		
		// Host url
		String host = "https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com/apiservices/pricing/v1.0";
		String charset = "UTF-8";
		// Headers for a request
		String x_rapidapi_host = "skyscanner-skyscanner-flight-search-v1.p.rapidapi.com";
		String content_type = "application/x-www-form-urlencoded";
		String body = "inboundDate=" + inboundDate + "&" + "cabinClass=economy&children=0&infants=0&"
				+ "country=US&currency=USD&locale=en-US&originPlace=" + originPlace
				+ "&destinationPlace=" + destination + "&outboundDate=" + outboundDate + "&adults=1";
		// send a request
		HttpResponse<JsonNode> response = Unirest.post(host)
				.header("x-rapidapi-host", x_rapidapi_host)
				.header("x-rapidapi-key", x_rapidapi_key)
				.header("content-type", content_type)
				.body(body)
				.asJson();
		// get session key
		sessionKeyList = response.getHeaders().get("Location");
		
		sessionKeyArr = sessionKeyList.get(0).split("/");
		
		sessionKey = sessionKeyArr[7];
		
		return sessionKey;

	}
	

}

