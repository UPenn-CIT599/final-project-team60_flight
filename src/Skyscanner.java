import java.io.IOException;

public class Skyscanner {
	
	public static void main(String[] args) throws IOException {

		try {
			HttpResponse<JsonNode> response = Unirest.post("https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com/apiservices/pricing/v1.0")
					.header("x-rapidapi-host", "skyscanner-skyscanner-flight-search-v1.p.rapidapi.com")
					.header("x-rapidapi-key", "e5f8c820f8mshde143b441ddec46p1a1443jsn70962fb6abcb")
					.header("content-type", "application/x-www-form-urlencoded")
					.body("inboundDate=2019-12-10&cabinClass=business&children=0&infants=0&country=US&currency=USD&locale=en-US&originPlace=SFO-sky&destinationPlace=LHR-sky&outboundDate=2019-12-01&adults=1")
					.asJson();
			System.out.println(response.getBody());
		} catch (UnirestException e) {
			e.printStackTrace();
		}
	}
}
