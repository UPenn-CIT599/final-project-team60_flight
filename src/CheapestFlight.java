/**
 * Store information for a flight with the cheapest quotes.
 * @author xueyingli
 *
 */
public class CheapestFlight {
	private String departureDate;
	private boolean isDirect;
	private double minPrice;
	private String currency = "USD";
	private String carrierName;
	private String originCityName;
	private String originAirportCode;
	private String destinationCityName;
	private String destinationAirportCode;
	
	public CheapestFlight(String departureDate, boolean isDirect, double minPrice, String carrierName,
			String originCityName, String originAirportCode, String destinationCityName,
			String destinationAirportCode) {
		this.departureDate = departureDate;
		this.isDirect = isDirect;
		this.minPrice = minPrice;
		this.carrierName = carrierName;
		this.originCityName = originCityName;
		this.originAirportCode = originAirportCode;
		this.destinationCityName = destinationCityName;
		this.destinationAirportCode = destinationAirportCode;
	}

	public String getDepartureDate() {
		return departureDate;
	}

	public boolean isDirect() {
		return isDirect;
	}

	public double getMinPrice() {
		return minPrice;
	}

	public String getCurrency() {
		return currency;
	}

	public String getCarrierName() {
		return carrierName;
	}

	public String getOriginCityName() {
		return originCityName;
	}

	public String getOriginAirportCode() {
		return originAirportCode;
	}

	public String getDestinationCityName() {
		return destinationCityName;
	}

	public String getDestinationAirportCode() {
		return destinationAirportCode;
	}
	
}
