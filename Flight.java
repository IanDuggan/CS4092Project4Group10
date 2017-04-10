public class Flight
{
	private String flightNumber;
	private String departingAirport;
	private String arrivingAirport;
	private String departureTime;
	private String arrivalTime;
	private String daysOfWeek;
	private String startDate;
	private String endDate;
	
	Flight(String aFlightNumber, String aDepartingAirport, String aArrivingAirport, String aDepartureTime, 
		   String aArrivalTime, String aDaysOfWeek, String aStartDate, String aEndDate)
	{
	   flightNumber = aFlightNumber;
	   departingAirport = aDepartingAirport;
	   arrivingAirport = aArrivingAirport;
	   departureTime = aDepartureTime;
	   arrivalTime = aArrivalTime;
	   daysOfWeek = aDaysOfWeek;
	   startDate = aStartDate;
	   endDate = aEndDate;
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
	
	public String getStartDate()
	{
		return startDate;
	}
	
	public void setStartDate(String aStartDate)
	{
		startDate = aStartDate;
	}
	
	public String getEndDate()
	{
		return endDate;
	}
	
	public void setEndDate(String aEndDate)
	{
		endDate = aEndDate;
	}
	
	@Override
	public String toString() 
	{
    return (this.getFlightNumber() + "," + this.getDepartingAirport() + "," 
			+ this.getArrivingAirport() + "," + this.getDepartureTime() + "," + this.getArrivalTime()
			+ "," + this.getDaysOfWeek() + "," + this.getStartDate() + "," + this.getEndDate());
	}
}
