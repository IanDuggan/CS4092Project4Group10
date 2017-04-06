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
			writeToFile("a");
			writeToFile("f");
			menu();
			
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
	
	public static void addAnAirport() throws IOException
	{
		String filename1 = "Airports.txt";
		File inputFile1 = new File(filename1);
		String airportName = "";
		String airportCode = "";
		String pattern1 = "[A-Za-z]+|([A-Za-z]+\\s)+[A-Za-z]+";
		String pattern2 = "[A-Z]{3}";
		boolean airportFoundOrAdded = false;
		if (!(inputFile1.exists()))
		{
			PrintWriter printer = new PrintWriter("Airports.txt", "UTF-8");
			//printer.print(a[1] + "," + a[2]);
			printer.close();
		}
		else
		{
			while (airportName != null && ((!(airportName.matches(pattern1))) || !airportFoundOrAdded))
			{
				airportName = JOptionPane.showInputDialog(null,"Enter the name of the airport you would like to add.");
				while (airportName != null && (!(airportName.matches(pattern1))))
				{
					JOptionPane.showMessageDialog(null,"Invalid airport name selected. Please enter one or more words with spaces as long as you finish with a word.");
					airportName = JOptionPane.showInputDialog(null,"Enter the name of the airport you would like to add.");
				}
				if (airportName != null && airportName.matches(pattern1))
				{
					String aLine;
					Scanner in;
					in = new Scanner(inputFile1);
					while (in.hasNext() && !airportFoundOrAdded)
					{
						aLine = in.nextLine();
						if (aLine.indexOf(airportName) != -1)
							airportFoundOrAdded = true;
					}
					in.close();
					if (airportFoundOrAdded)
						System.out.println("The airport selected already exists.");
					else
					{		
						airportCode = JOptionPane.showInputDialog(null,"Enter the code of the airport you would like to add.");
						while (airportCode != null && (!(airportCode.matches(pattern2))))
						{
							JOptionPane.showMessageDialog(null,"Invalid airport code selected. Please enter a three character long code with capital letters. eg BHD");
							airportCode = JOptionPane.showInputDialog(null,"Enter the code of the airport you would like to add.");
						}
						if (airportName != null && airportCode != null)
						{
							Airport a;
							a = new Airport(airportName, airportCode);
							airports.add(0,a);
							airportFoundOrAdded = true;
							//bubbleSort("a");
							writeToFile("a");
						}
						
					}
				}
			}
			if (airportName == null)
			{
				menu();
			}
		}
	}
	
	public static void editAnAirport() throws IOException
	{
		String filename1 = "Airports.txt";
		File inputFile1 = new File(filename1);
		String airportName = "";
		String airportCode = "";
		String pattern1 = "[A-Za-z]+|([A-Za-z]+\\s)+[A-Za-z]+";
		String pattern2 = "[A-Z]{3}";
		boolean airportFoundOrEdited = false;
		if (!(inputFile1.exists()))
		{
			PrintWriter printer = new PrintWriter("Airports.txt", "UTF-8");
			//printer.print(a[1] + "," + a[2]);
			printer.close();
		}
		else
		{
			while (airportCode != null && ((!(airportCode.matches(pattern2))) || !airportFoundOrEdited))
			{
				airportCode = JOptionPane.showInputDialog(null,"Enter the code of the airport you would like to edit.");
				while (airportCode != null && (!(airportCode.matches(pattern2))))
				{
					JOptionPane.showMessageDialog(null,"Invalid airport code selected. Please enter a three character long code with capital letters. eg BHD");
					airportCode = JOptionPane.showInputDialog(null,"Enter the code of the airport you would like to add.");
				}
				if (airportCode != null && airportCode.matches(pattern2))
				{
					int indexCode = 0;
					for (int i = 0; i < airports.size() && !airportFoundOrEdited; i++) // Searching the arraylist to see if this airport exists.
					{
						if (airports.get(i).getAirportCode().equals(airportCode))
						{
							airportFoundOrEdited = true;
							indexCode = i;
						}
					}
					if (airportFoundOrEdited)
					{
						airportName = JOptionPane.showInputDialog(null,"Enter the name of the airport you would like to edit.");
						while (airportName != null && (!(airportName.matches(pattern1))))
						{
							JOptionPane.showMessageDialog(null,"Invalid airport name selected. Please enter one or more words with spaces as long as you finish with a word.");
							airportName = JOptionPane.showInputDialog(null,"Enter the name of the airport you would like to edit.");
						}
						if (airportName == null)
						{
							airportFoundOrEdited = false;
						}
						if (airportName != null && airportCode != null)
						{
							Airport a;
							a = new Airport(airportName, airportCode);
							airports.set(indexCode,a);
							airportFoundOrEdited = true;
							//bubbleSort("a");
							writeToFile("a");
						}
					}	
					else
					{		
						System.out.println("The airport selected already exists.");
						airportFoundOrEdited = false;
					}
				}
			}
			if (airportCode == null)
			{
				menu();
			}
		}
	}
	
	public static void menu() throws IOException
	{
		boolean valid = false;
		String pattern = "[0-7]{1}";
		String input = "";
		while (input != null && !valid)
		{
			input = JOptionPane.showInputDialog("Enter an input code please.");
			if (input != null && (input.matches(pattern)))
			{
				valid = true;
			}
		}
		if (valid)
		{
			int number = Integer.parseInt(input);
			switch (number) 
					{
						case 1:
							addAnAirport();
							break;
						case 2:
							editAnAirport();
							break;
						case 3:
							//deleteAnAirport(args);
							break;
						case 4:
							//editFlightDetails(args);
							System.out.print("4");
							break;
						case 5:
							//deleteAFlight(args);
							System.out.print("5");
							break;
						case 6:
							//displayFlightDetails(args);
							System.out.print("6");
							break;
						case 7:
							//displayFlightDetailsDate(args);
							System.out.print("7");
							break;
						case 0:
							System.out.print("you will be exited.");
							break;
						default:
							System.out.println("Invalid input code entered.");
							menu();
							break;
					}
		}
	}
	
	public static void writeToFile(String fileChosen) throws IOException
	{
		boolean airportsChosen = false;
		boolean flightsChosen = false;
		String file = "";
		if (fileChosen.equals("a"))
		{
			airportsChosen = true;
			file = "Airports";
		}
		else if (fileChosen.equals("f"))
		{
			flightsChosen = true;
			file = "Flights";
		}
		PrintWriter printer = new PrintWriter(file + ".txt");
		file = file.toLowerCase();
		if (flightsChosen)
		{
			for (int i = 0; i < flightsSorted.size(); i++)
			{
				printer.print(flightsSorted.get(i));
				if (i != flightsSorted.size() - 1)
					printer.println();
			}
		}
		else if (airportsChosen)
		{
			for (int i = 0; i < airports.size(); i++)
			{
				printer.print(airports.get(i));
				if (i != airports.size() - 1)
					printer.println();
			}
		}
		printer.close();
	}
}
