import java.text.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
public class CS4043Project4Group10
{
	public static ArrayList<Flight> flights;
	public static ArrayList<Flight> flightsSorted;
	public static ArrayList<Airport> airports;
	public static int totalFlights;
	public static void main(String [] args) throws IOException
	{
		boolean readFile;
		readFile = readFilesIntoArrayLists();
		if (readFile)
		{
			
		}
	}
	public static boolean readFilesIntoArrayLists() throws IOException
	{
		String filename1 = "Airports.txt";
		String filename2 = "Flights.txt";
		
		String fileElements[];
		File inputFile1 = new File(filename1);
		File inputFile2 = new File(filename2);
		
		airports = new ArrayList<Airport>();
		
		flights = new ArrayList<Flight>();
		flightsSorted = new ArrayList<Flight>();
		
		if(inputFile1.exists() && inputFile2.exists())
		{
			Scanner in;
			Airport a;
			in = new Scanner(inputFile1);
			while(in.hasNext())
			{
				fileElements = (in.nextLine()).split(",");
				a = new Airport(fileElements[0], fileElements[1]);
				airports.add(a);
	        }
		    in.close();
			
			in = new Scanner(inputFile2);
			String aLine,aChar,airportCode = "",question;
			String wordArray [];
			while(in.hasNext())
			{
				aLine = in.nextLine();
				Flight f = new Flight("", "", "", "", "", "", "", "");
				wordArray = aLine.split(",");
				aChar = wordArray[1];
				for(int i = 0; i < airports.size(); i++)
				{ 
					airportCode = airports.get(i).getAirportCode();
					if(airportCode.equals(aChar)) 
					{
						f = new Flight(wordArray[0], wordArray[1], wordArray[2], wordArray[3], wordArray[4], wordArray[5], wordArray[6], wordArray[7]);
						flights.add(f);
						totalFlights++;
					}						
					
				}
				flightsSorted.add(f);
			}
			in.close();
			return true;
		}
		else
			return false;
	}
}