public class Flight
{
	private String flightNumber;
	private String departingAirport;
	private String arrivingAirport;
	private String departureTime;
	private String arrivalTime;
	private String daysOfWeek;
	private String departureDate;
	private String arrivalDate;
	
	Flight(String aFlightNumber, String aDepartingAirport, String aArrivingAirport, String aDepartureTime, 
		   String aArrivalTime, String aDaysOfWeek, String aDepartureDate, String aArrivalDate)
	{
	   flightNumber = aFlightNumber;
	   departingAirport = aDepartingAirport;
	   arrivingAirport = aArrivingAirport;
	   departureTime = aDepartureTime;
	   arrivalTime = aArrivalTime;
	   daysOfWeek = aDaysOfWeek;
	   departureDate = aDepartureDate;
	   arrivalDate = aArrivalDate;
	}
	
	public String getFlightNumber()
	{
		return flightNumber;
	}
	
	public void setFlightNumber(String aFlightNumber)
	{
		flightNumber = aFlightNumber;
	}
	
	public String getDepartingAirport()
	{
		return departingAirport;
	}
	
	public void setDepartingAirport(String aDepartingAirport)
	{
		departingAirport = aDepartingAirport;
	}
	
	public String getArrivingAirport()
	{
		return arrivingAirport;
	}
	
	public void setArrivingAirport(String aArrivingAirport)
	{
		arrivingAirport = aArrivingAirport;
	}
	
	public String getDepartureTime()
	{
		return departureTime;
	}
	
	public void setDepartureTime(String aDepartureTime)
	{
		departureTime = aDepartureTime;
	}
	
	public String getArrivalTime()
	{
		return arrivalTime;
	}
	
	public void setArrivalTime(String aArrivalTime)
	{
		arrivalTime = aArrivalTime;
	}
	
	public String getDaysOfWeek()
	{
		return daysOfWeek;
	}
	
	public void setDaysOfWeek(String aDaysOfWeek)
	{
		daysOfWeek = aDaysOfWeek;
	}
	
	public String getDepartureDate()
	{
		return departureDate;
	}
	
	public void setDeparureDate(String aDepartureDate)
	{
		departureDate = aDepartureDate;
	}
	
	public String getArrivalDate()
	{
		return arrivalDate;
	}
	
	public void setArrivalDate(String aArrivalDate)
	{
		arrivalDate = aArrivalDate;
	}
	
	@Override
	public String toString() 
	{
    return (this.getFlightNumber() + "," + this.getDepartingAirport() + "," 
			+ this.getArrivingAirport() + "," + this.getDepartureTime() + "," + this.getArrivalTime()
			+ "," + this.getDaysOfWeek() + "," + this.getDepartureDate() + "," + this.getArrivalDate());
	}
}
