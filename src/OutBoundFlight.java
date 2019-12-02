/**
 * This class represents an outbound flight that 
 * has its departure and arrival times and number of layovers
 * @author sivah
 *
 */
public class OutBoundFlight {
	
	private String departTime;
	private String arrTime;
	private String numOfStops;
	
	public OutBoundFlight(String departTime, String arrTime, String numOfStops) {
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
