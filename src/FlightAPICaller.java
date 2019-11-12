import java.util.List;

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
	
	/**
	 * This method calls Skyscanner API and returns itineraries
	 * @param userCountry
	 * @param currency
	 * @param originPlace
	 * @param destination
	 * @param outboundDate
	 * @param inboundDate
	 * @return itineraries
	 * @throws UnirestException 
	 */
	public static String callFlightAPI(String userCountry, String currency, String originPlace, 
			String destination, String outboundDate, String inboundDate) throws UnirestException {
		String itinaries = "";
		// Host url
		String host = "https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com/apiservices/pricing/uk2/v1.0/";
		String charset = "UTF-8";
		// Headers for a request
		String x_rapidapi_host = "skyscanner-skyscanner-flight-search-v1.p.rapidapi.com";
		String x_rapidapi_key = "";
		// get session key
		List<String> sessionKey = getSessionKey(userCountry, currency, originPlace, 
				destination, outboundDate,inboundDate);
		// send a request
		HttpResponse<JsonNode> response = Unirest.get(host + sessionKey + "?sortType=price&pageIndex=0&pageSize=10")
				.header("x-rapid-host", x_rapidapi_host)
				.header("x-rapidapi-key", x_rapidapi_key)
				.asJson();
		
		// get itinaries from response body
		itinaries = response.getBody().toString();
		
		return itinaries;
	}
	
	/**
	 * This method creates Skyscanner API session to get the session key
	 * that is required to view the itineraries
	 * @param userCountry
	 * @param currency
	 * @param originPlace
	 * @param destination
	 * @param outboundDate
	 * @param inboundDate
	 * @return session key
	 * @throws UnirestException
	 */
	public static List<String> getSessionKey(String userCountry, String currency, String originPlace, 
			String destination, String outboundDate, String inboundDate) throws UnirestException {
		
		List<String> sessionKey;
		
		// Host url
		String host = "https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com/apiservices/pricing/v1.0";
		String charset = "UTF-8";
		// Headers for a request
		String x_rapidapi_host = "skyscanner-skyscanner-flight-search-v1.p.rapidapi.com";
		String x_rapidapi_key = "";
		String content_type = "application/x-www-form-urlencoded";
		String body = "inboundDate=" + inboundDate + "&" + "cabinClass=business&children=0&infants=0&"
				+ "country=" + userCountry + "&currency=" + currency + "&locale=en-US&originPlace=" + originPlace
				+ "&destinationPlace=" + destination + "&outboundDate=" + outboundDate + "&adults=1";
		// send a request
		HttpResponse<JsonNode> response = Unirest.post(host)
				.header("x-rapidapi-host", x_rapidapi_host)
				.header("x-rapidapid-key", x_rapidapi_key)
				.body(body)
				.asJson();
		// get session key
		sessionKey = response.getHeaders().get("Location");
		
		return sessionKey;
	}
}
