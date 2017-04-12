import java.text.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
public class CS4092Project4Group10
{
	public static ArrayList<Flight> flights;
	public static ArrayList<Flight> flightsSorted;
	public static ArrayList<Airport> airports;
	public static int totalFlights;
	public static boolean triesToExit;
	public static void main(String [] args) throws IOException
	{
		triesToExit = false;
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
				if(airportName != null)
				{
					airportName = airportName.trim();
				}	
				while (airportName != null && (!(airportName.matches(pattern1))))
				{
					JOptionPane.showMessageDialog(null,"Invalid airport name selected. Please enter one or more words with spaces as long as you finish with a word.");
					airportName = JOptionPane.showInputDialog(null,"Enter the name of the airport you would like to add."); 
					if(airportName != null) 
					{
						airportName = airportName.trim();
					}
						
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
					{
						JOptionPane.showMessageDialog(null, "The airport selected already exists.");
						airportFoundOrAdded = false; 
					}
					else
					{		
						airportCode = JOptionPane.showInputDialog(null,"Enter the code of the airport you would like to add."); 
						if(airportCode != null) 
						{
							airportCode = airportCode.trim();
						}
						while (airportCode != null && (!(airportCode.matches(pattern2))))
						{
							JOptionPane.showMessageDialog(null,"Invalid airport code selected. Please enter a three character long code with capital letters. eg BHD");
							airportCode = JOptionPane.showInputDialog(null,"Enter the code of the airport you would like to add.");
							if(airportCode != null) 
							{
								airportCode = airportCode.trim();
							}
						}
						if (airportName != null && airportCode != null)
						{
							Airport a;
							a = new Airport(airportName, airportCode);
							airports.add(0,a);
							airportFoundOrAdded = true;
							bubbleSort("a");
							writeToFile("a");
							JOptionPane.showMessageDialog(null, "The airport '" + airportName + "' with code '" + airportCode + "' has successfully been added to the file.");
						}
					}
				}
			}
			/*if (airportName == null)
			{
				menu();
			}*/
		}
		menu();
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
			printer.close();
		}
		else
		{
			while (airportCode != null && ((!(airportCode.matches(pattern2))) || !airportFoundOrEdited))
			{
				airportCode = JOptionPane.showInputDialog(null,"Enter the code of the airport you would like to edit."); 
				if(airportCode != null)
				{
					airportCode = airportCode.trim();
				}	
				while (airportCode != null && (!(airportCode.matches(pattern2))))
				{
					JOptionPane.showMessageDialog(null,"Invalid airport code selected. Please enter the code capitalised and three characters long, eg 'BHD'.");
					airportCode = JOptionPane.showInputDialog(null,"Enter the code of the airport you would like to add."); 
					if(airportCode != null) 
					{
						airportCode = airportCode.trim();
					}
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
						airportName = JOptionPane.showInputDialog(null,"Enter the new name of the airport for code '" + airportCode + "'."); 
						if(airportName != null)
						{
							airportName = airportName.trim(); 
						}							
						while (airportName != null && (!(airportName.matches(pattern1))))
						{
							JOptionPane.showMessageDialog(null,"Invalid airport name selected. Please enter one or more words with spaces and end input with a word.");
							airportName = JOptionPane.showInputDialog(null,"Enter the new name of the airport for code '" + airportCode + "'."); 
							if(airportName != null)
							{
								airportName = airportName.trim();
							}								
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
							JOptionPane.showMessageDialog(null, "The airport name of code '" + airportCode + "' has successfully been changed to '" + airportName + "'.");
						}
					}	
					else
					{		
						JOptionPane.showMessageDialog(null,"The airport selected does not exist.");
						airportFoundOrEdited = false;
					}
				}
			}
			/*if (airportCode == null)
			{
				menu();
			}*/
		}
		menu();
	}
	
	public static void addFlight() throws IOException
	{
		triesToExit = false;
		String timeFormat = "\n\nNote: enter in 24hr format with numbers only, \neg 1300 for 1:00pm.";
		String daysFormat = "\n\nNote: enter in format 'MTWTFSS' and enter a '-' \nfor inactive flight days, eg a flight only travelling on Tuesday\nis represented as '-T-----'.";
		String dateFormat = "\n\nNote: enter in format dd/mm/yyyy.";
		//Made a string array of flight details to cycle through
		String inputs[] = {" flight code.", " start airport code.", " destination airport code.", " start time." + timeFormat,
							" arrival time.", " days of travel." + daysFormat, " start date flight is active." + dateFormat, " end date flight is active." + dateFormat};
		String flightDetails[] = new String[8];
		int i = 0;
		boolean valid = true;
		
		String input = " ";
		while(input != null && flightDetails[7] == null)
		{
			//Every input box
			input = JOptionPane.showInputDialog(null, "Enter" + inputs[i]);
			if (input != null)
			{
				valid = validateDetail(i, input);
			}				
			else
			{
				triesToExit = true;
			}				
			if(!valid)
			{	//Loop for invalid input
				while(input != null && !valid)
				{
					JOptionPane.showMessageDialog(null, "Invalid" + inputs[i] + " \nPlease try again.");
					input = JOptionPane.showInputDialog(null, "Enter" + inputs[i]);
					if(input != null) 
					{
						valid = validateDetail(i, input);
					}
					else
					{
						triesToExit = true;
					}
				}
			} //Adds input to array
			while(input != null && flightDetails[i] == null)
			{
				if(input != null)
				{
					input = input.trim();
				}
				else
				{
					triesToExit = true;
				}
				if(valid)
				{
					flightDetails[i] = input;
				}
			}
			if(flightDetails[i] != null)
				i++;
		}
		//Assigns details to flight object, adds to arrayList and writes to file
		if(input != null && flightDetails[7] != null && !triesToExit)
		{
			Flight f = new Flight(flightDetails[0], flightDetails[1], flightDetails[2], flightDetails[3], flightDetails[4], flightDetails[5], flightDetails[6], flightDetails[7]);
			flightsSorted.add(f);
			writeToFile("f");
			JOptionPane.showMessageDialog(null, "Flight " + flightDetails[0] + " has successfully been added to flights list.");
			menu();
		}
		//Determines where to go when user wants to exit
		//Exiting from any point in the input menus bring up the first input menu 
		//unless exiting from the first input menu.
		if(triesToExit && i == 0)
		{
			triesToExit = false;
			menu();
		}
		else if(triesToExit)
		{
			addFlight();
		}
	}
	
	public static void displayFlightDetails() throws IOException
	{
		String airportCode1 = "";
		String airportCode2 = "";
		String tempArray [];
		boolean airportCode1Found = false;
		boolean airportCode2Found = false;
		String pattern = "[A-Z]{3}";
		String input = "";
		while ((airportCode1 != null && airportCode2 != null) && ((!(airportCode1.matches(pattern))) || (!(airportCode2.matches(pattern)))))
		{
			airportCode1 = JOptionPane.showInputDialog(null, "Please enter the code of the departing airport.");
			while (airportCode1 != null && (!(airportCode1.matches(pattern))))
			{
				JOptionPane.showMessageDialog(null, "Invalid airport code selected. Please enter the code capitalised and three characters long, eg 'BHD'.");
				airportCode1 = JOptionPane.showInputDialog(null, "Please enter the code of the departing airport.");
			}
			if (airportCode1 != null && airportCode2 != null && airportCode1.matches(pattern))
			{
				airportCode2 = JOptionPane.showInputDialog(null, "Please enter the code of the arriving airport.");
				while (airportCode2 != null && (!(airportCode2.matches(pattern))))
				{
					JOptionPane.showMessageDialog(null, "Invalid airport code selected. Please enter the code capitalised and three characters long, eg 'BHD'.");
					airportCode2 = JOptionPane.showInputDialog(null, "Please enter the code of the departing airport.");
				}
				if (airportCode2 == null)
				{
					airportCode2 = "";
				}	
				else if (airportCode2 != null && airportCode1.matches(pattern) && (airportCode2.matches(pattern)))
				{
					System.out.println("Entered if statement.");
					for (int i = 0; i < airports.size() && !(airportCode1Found && airportCode2Found); i++) // Searching for the airports.
					{
						System.out.println(airports.get(i).getAirportCode());
						if (airportCode1.equals(airports.get(i).getAirportCode()))
						{
							airportCode1Found = true;
							System.out.println("Airport code 1 was found.");
						}
						if (airportCode2.equals(airports.get(i).getAirportCode()))
						{
							airportCode2Found = true;
							System.out.println("Airport code 2 was found.");
						}
					}
					if (airportCode1Found && airportCode2Found)
					{
						System.out.print("both codes were found.");
						boolean bothFound = false;
						String display = "";
						display += "FNo.     Dep   Arr  DepT  ArrT   Days     DDate        ADate\n";
						for (int i = 0; i < flights.size(); i++)
						{
							if (airportCode1.equals(flights.get(i).getDepartingAirport()) && airportCode2.equals(flights.get(i).getArrivingAirport()))
							{
								bothFound = true;
								display += flights.get(i).toString() + "\n";
							}
						}
						if (!bothFound)
						{
							display += "There are no records currently of any flights with the airports you entered.\n";
						}
						JOptionPane.showMessageDialog(null,display);
					}
				}
			}
		}
		menu();
	}
	
	
	public static void deleteAFlight() throws IOException
	{
			
			String flightCode = "";
			boolean flightCodeFound = false;
			String pattern = "([A-Z].*[0-9]+)|([0-9].*[A-Z]+)";
			int index=0;
			while ((flightCode != null) && ((!(flightCode.matches(pattern)))))
			{
				flightCode = JOptionPane.showInputDialog(null, "Please enter the flight code number.");
				while (flightCode != null && (!(flightCode.matches(pattern))))
				{
					JOptionPane.showMessageDialog(null, "Invalid flight code selected. Please enter the code capitalised with two characters and 4 digits, eg 'EI3000'.");
					flightCode = JOptionPane.showInputDialog(null, "Please enter the flight code number.");
				}
				if (flightCode != null && flightCode.matches(pattern))
					{
						//System.out.print(flightCode);
						for (int i = 0; i < flights.size() && !flightCodeFound; i++)
						{
							if (flightCode.equals(flights.get(i).getFlightNumber()))
								{
									//System.out.println(flights.get(i).getFlightNumber());
									flightCodeFound = true;
									index = i;	
								}
						}
					if (flightCodeFound)
						{
						flightsSorted.remove(index); // Removes the flight from the arraylist.
						System.out.print(flightsSorted);
						totalFlights--;
						bubbleSort("f");
						writeToFile("f");
						JOptionPane.showMessageDialog(null,"The flight " + flightCode + " was successfully deleted.");
						}
					else 
						{
						JOptionPane.showMessageDialog(null,"Flight code does not exist.");
						deleteAFlight();
						}

					}
			}
	}
	
	//Gross validation
	public static boolean validateDetail(int whichDetail, String detail) throws IOException
	{
		boolean validInput = false;
		String pattern = "";
		if(whichDetail == 2)
			whichDetail = 1;
		else if(whichDetail == 4)
			whichDetail = 3;
		else if(whichDetail == 7)
			whichDetail = 6;
		switch(whichDetail)
		{
			case 0: pattern = "[A-Z]{2}[0-9]{4}"; if(detail.matches(pattern)) {validInput = true;} else {validInput = false;}
					break;
			case 1: pattern = "[A-Z]{3}"; if(detail.matches(pattern)) {validInput = true;} else {validInput = false;}
					break;
			case 3: pattern = "[0-1]{1}[0-9]{1}[0-5]{1}[0-9]{1}|2[0-3]{1}[0-5]{1}[0-9]{1}"; if(detail.matches(pattern)) {validInput = true;} else {validInput = false; }
					break;
			//Pardon the grossness tried saving lines
			case 5: String days = "MTWTFSS"; String aChar; detail = detail.toUpperCase(); validInput = true;
					if (detail.length() == 7){ 
						if(detail.matches("-------")) validInput = false;
						else{for (int i = 0; i < days.length() && validInput; i++) {
							aChar = detail.substring(i, i + 1);
							if ((!(aChar.equals("-"))) && (!(aChar.equals(days.substring(i, i + 1))))) // If the next character does not equal "-" and does not equal the relevant day, then return false.
							validInput = false; 
							}
						}
					}
					else validInput = false; 	
					break;
			case 6: pattern = "[0-9]{2}/[0-9]{2}/[0-9]{4}"; if(detail.matches(pattern)) {validInput = true;} else {validInput = false;}
					break;
			default:validInput = false;
					break;
		}
		return validInput;
	}
	
	public static void menu() throws IOException
	{
		triesToExit = false;
		boolean valid = false;
		String pattern = "[0-8]{1}";
		String input = "";
		String message = "Enter the number corresponding to desired action.";
		message += "\n\n0. Exit.";
		message += "\n1. Add an airport.";
		message += "\n2. Edit an airport.";
		message += "\n3. Delete an airport.";
		message += "\n4. Edit flight details.";
		message += "\n5. Delete a flight.";
		message += "\n6. Add a flight.";
		message += "\n7. Display flight details.";
		message += "\n8. Display flight details on a date.";
		message += "\n";
		
		while (input != null && !valid)
		{
			input = JOptionPane.showInputDialog(message);
			if(input != null)
			{
				input = input.trim();
				if(input.matches(pattern))
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
					deleteAFlight();
					//System.out.print("5");
					break;
				case 6:
					addFlight();
					break;
				case 7:
					displayFlightDetails();
					break;
				case 8:
					//displayFlightDetailsDate(args);
					System.out.print("8");
					break;
				case 0:
					JOptionPane.showMessageDialog(null, "Exiting.");
					break;
				default:
					JOptionPane.showMessageDialog(null, "Invalid option.");
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
	
	
	
	public static void bubbleSort(String fileChosen) throws IOException
	{
		int pass, comparison;
		Flight temp;
		Airport temp2;
		boolean airportsChosen = false;
		boolean flightsChosen = false;
		if (fileChosen.equals("a"))
		{
			airportsChosen = true;
		}
		else if (fileChosen.equals("f"))
		{
			flightsChosen = true;
		}
		if (flightsChosen)
		{
			for (pass = 1; pass <= (flightsSorted.size() - 1); pass++)
			{
				for (comparison = 1; comparison <= (flightsSorted.size() - pass); comparison++)
				{
					if (((flightsSorted.get(comparison-1).getFlightNumber()).compareToIgnoreCase(flightsSorted.get(comparison).getFlightNumber()) > 0))
					{
						temp = flightsSorted.get(comparison -1);
						flightsSorted.set((comparison - 1),flightsSorted.get(comparison));
						flightsSorted.set((comparison), temp);
					}
				}
			}
		}
		else if (airportsChosen)
		{
			for (pass = 1; pass <= (airports.size() - 1); pass++)
			{
				for (comparison = 1; comparison <= (airports.size() - pass); comparison++)
				{
					if ((airports.get(comparison - 1).getAirportName()).compareToIgnoreCase(airports.get(comparison).getAirportName()) > 0)
					{
						temp2 = airports.get(comparison - 1);
						airports.set((comparison - 1), airports.get(comparison));
						airports.set((comparison), temp2);
					}
				}
			}
		}
	}	
}
