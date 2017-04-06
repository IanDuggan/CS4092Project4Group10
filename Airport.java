public class Airport
{
	public String airportName;
	public String airportCode;
	Airport(String aAirportName, String aAirportCode)
	{
		airportName = aAirportName;
		airportCode = aAirportCode;
	}
	public String getAirportName()
	{
		return airportName;
	}	
	public void setAirportName(String aAirportName)
	{
		airportName = aAirportName;
	}
	public String getAirportCode()
	{
		return airportCode;
	}
	public void setAirportCode(String aAirportCode)
	{
		airportCode = aAirportCode;
	}
	
	@Override
	public String toString()
	{
		return (this.getAirportName() + "," + this.getAirportCode());
	}
}
