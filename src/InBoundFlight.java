/**
 * This class represents an inbound flight that 
 * has its departure and arrival times and number of layovers
 * @author sivah
 *
 */
public class InBoundFlight {
	
	private String departTime;
	private String arrTime;
	private String numOfStops;
	
	public InBoundFlight(String departTime, String arrTime, String numOfStops) {
		this.departTime = departTime;
		this.arrTime = arrTime;
		this.numOfStops = numOfStops;
	}

	public String getDepartTime() {
		return departTime;
	}

	public String getArrTime() {
		return arrTime;
	}

	public String getNumOfStops() {
		return numOfStops;
	}
	
	@Override
	public String toString() {
		return departTime + "," + arrTime + "," + numOfStops;
	}

}
