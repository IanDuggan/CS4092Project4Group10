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
	public static void main(String [] args) throws IOException
	{
		boolean readFile;
		readFile = readFilesIntoArrayLists();
		if (readFile)
		{
			bubbleSort("a");
			bubbleSort("f");
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
				if(!(aLine.equals(",,,,,,,"))){
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
						if (airportName == null){
							airportFoundOrEdited = false;
						}
						if (airportName != null && airportCode != null)
						{
							Airport a = new Airport(airportName, airportCode);
							airports.set(indexCode,a);
							airportFoundOrEdited = true;
							bubbleSort("a");
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
		}
		menu();
	}
	
	public static void deleteAnAirport() throws IOException
	{
		String airportCode = "";
		boolean airportCodeFound = false;
		String pattern = "[A-Z]{3}";
		int index = 0;
		while ((airportCode != null) && ((!(airportCode.matches(pattern)))))
		{
			airportCode = JOptionPane.showInputDialog(null, "Please enter the airport code of the airport you would like to remove.");
			while (airportCode != null && (!(airportCode.matches(pattern))))
			{
				JOptionPane.showMessageDialog(null, "Invalid airport code selected. \n\nNote: enter the code capitalised with 3 characters, eg 'DUB'.");
				airportCode = JOptionPane.showInputDialog(null, "Please enter the airport code.");
			}
			if (airportCode != null && airportCode.matches(pattern))
			{
				for (int i = 1; i < airports.size() && !airportCodeFound; i++)
				{
					if (airportCode.equals(airports.get(i).getAirportCode()))
					{
						airportCodeFound = true;
						index = i;	
					}
				}
				if (airportCodeFound)
				{
					airports.remove(index);
					writeToFile("a");
					JOptionPane.showMessageDialog(null,"The airport " + airportCode + " was successfully deleted.");
				}
				else 
				{
					JOptionPane.showMessageDialog(null,"Airport code not found.");
					deleteAnAirport();
				}
			}
		}
		menu();
	}
	
	public static void editFlightDetails() throws IOException
	{
		boolean valid = false, errMsg = true, flightFoundOrEdited = false, validDates = false;
		String flightCode = " ", editedDays = " ", date = " ", codePattern = "[A-Z]{2}[0-9]{4}";
		String dateInput[] = {"start", "end"}, dates[] = new String[2];
		
		while(flightCode != null && (!flightFoundOrEdited || !validDates))
		{
			flightCode = JOptionPane.showInputDialog(null, "Enter the flight code for the details you wish to edit.");
			if(flightCode != null)
				flightCode = flightCode.trim();
			while(flightCode != null && (!(flightCode.matches(codePattern))))
			{
				JOptionPane.showMessageDialog(null, "Invalid flight code supplied. \n\nNote: enter two capitalised letters \nfollowed by the four digits, eg 'EI3000'.");
				flightCode = JOptionPane.showInputDialog(null, "Enter the flight code for the details you wish to edit.");
				if(flightCode != null)
					flightCode = flightCode.trim();
			}
			if(flightCode != null && flightCode.matches(codePattern))
			{
				int index = 0;
				for(int i=0; i<flightsSorted.size() && !flightFoundOrEdited; i++){
					if(flightsSorted.get(i).getFlightNumber().equals(flightCode)){
						flightFoundOrEdited = true;
						index = i;
					}
				}
				if(flightFoundOrEdited)
				{
					editedDays = " ";
					while(editedDays != null && !validDates)
					{
						editedDays = JOptionPane.showInputDialog(null, "Enter the new active days of flight " + flightCode + ".");
						if(editedDays != null){
							editedDays = editedDays.trim();
							valid = validateDetail(5, editedDays);
						}
						while(editedDays != null && !valid){
							JOptionPane.showMessageDialog(null, "Invalid format of days entered. \n\nNote: enter in format MTWTFSS with a '-'\n to represent an inactive day.");
							editedDays = JOptionPane.showInputDialog(null, "Enter the new active days of flight " + flightCode + ".");
							if(editedDays != null){
								editedDays = editedDays.trim();
								valid = validateDetail(5, editedDays);
							}
						}
						if(editedDays != null){
							int count = 0;
							date = " ";
							while(date != null && !validDates)
							{
								date = JOptionPane.showInputDialog(null, "Enter the new "+ dateInput[count] +" date flight is active.");
								if(date != null){
									date = date.trim();
									valid = validateDetail(6, date);
								}
								while(date != null && !valid){
									JOptionPane.showMessageDialog(null, "Invalid " + dateInput[count] + " date entered. \n\nNote: enter in format dd/mm/yyyy.");
									date = JOptionPane.showInputDialog(null, "Enter the new "+ dateInput[count] +" date flight is active.");
									if(date != null){
										date = date.trim();
										valid = validateDetail(6, date);
									}
								}
								if(date != null)
								{
									dates[count] = date;
									count++;
									if(count == 2)
									{
										validDates = dates(dates[0], dates[0], dates[1]);
										if(!validDates)
										{
											JOptionPane.showMessageDialog(null, "Dates given are invalid. Please ensure \nthat start date comes before end date.");
											count = 0;
											date = " ";
											dates[1] = "";
										}
									}
								}
								else if(date == null && count == 1){
									count = 0;
									date = " ";
								}
								else
									flightFoundOrEdited = false;
							}
						}
						else{
							flightFoundOrEdited = false;
						}
						if(flightCode != null && editedDays != null && validDates){
							flightsSorted.get(index).setDaysOfWeek(editedDays);
							flightsSorted.get(index).setStartDate(dates[0]);
							flightsSorted.get(index).setEndDate(dates[1]);
							writeToFile("f");
							JOptionPane.showMessageDialog(null, "The details of flight " + flightCode + " have successfully\n been changed to the following details:\n\n" + 
									editedDays + " " + dates[0] + " " + dates[1]);
						}
						flightFoundOrEdited = true;
					}
				}
				else
					JOptionPane.showMessageDialog(null,"The flight selected does not exist in file.");
			}
		}
		menu();
	}
	
	public static void addFlight() throws IOException
	{
		boolean triesToExit = false, flightNumberFound = false;
		String timeFormat = "\n\nNote: enter in 24hr format with numbers only, \neg 1300 for 1:00pm.";
		String daysFormat = "\n\nNote: enter in format 'MTWTFSS' and enter a '-' \nfor inactive flight days, eg a flight only travelling on Tuesday\nis represented as '-T-----'.";
		String dateFormat = "\n\nNote: enter in format dd/mm/yyyy.";
		//Made a string array of flight details to cycle through
		String inputs[] = {" flight code.", " start airport code.", " destination airport code.", " start time." + timeFormat,
							" arrival time." + timeFormat, " days of travel." + daysFormat, " start date flight is active." + dateFormat, " end date flight is active." + dateFormat};
		String flightDetails[] = new String[8];
		int i = 0;
		boolean valid = true;
		
		String input = " ";
		while(input != null && flightDetails[7] == null) 
		{
			//Every input box
			flightNumberFound = false;
			input = JOptionPane.showInputDialog(null, "Enter" + inputs[i]);
			if(input != null)
			{
				input = input.trim();
				valid = validateDetail(i, input);
				if(valid && i==0){
					valid = validateDetail(8, input);	//Checking if flight code is already in file
					if(!valid){
						JOptionPane.showMessageDialog(null, "Flight entered already exists in file.");
						flightNumberFound = true;
					}
				}
				if(valid && (i==1 || i==2)){
					valid = validateDetail(9, input);	//Checking if start airport code exists in file
					if(!valid){
						JOptionPane.showMessageDialog(null, "Airport does not exist in file.");
						flightNumberFound = true;
					}
				}
				if(valid && i==7){
					valid = dates(flightDetails[6], flightDetails[6], input);	//Checking if dates are valid and if start date comes before end date
					if(!valid){
						JOptionPane.showMessageDialog(null, "One or both dates given are invalid, either the dates \nthemselves or the order they are in is incorrect.");
						flightNumberFound = true;
						i=6;
					}
				}
			}				
			else{
				triesToExit = true;
			}
			if(!valid)
			{	
				while(input != null && !valid)		//Loop for invalid input
				{
					if(!flightNumberFound){
						JOptionPane.showMessageDialog(null, "Invalid" + inputs[i] + " \nPlease try again.");
					}
					input = JOptionPane.showInputDialog(null, "Enter" + inputs[i]);
					if(input != null)
					{
						input = input.trim();
						valid = validateDetail(i, input);
						if(valid && i==0){
							valid = validateDetail(8, input);
							if(!valid){
								JOptionPane.showMessageDialog(null, "Flight entered already exists in file.");
								flightNumberFound = true;
							}
						}
						if(valid && (i==1 || i==2)){
							valid = validateDetail(9, input);
							if(!valid){
								JOptionPane.showMessageDialog(null, "Airport does not exist in file.");
								flightNumberFound = true;
							}
						}
						if(valid && i==7){
							valid = dates(flightDetails[6], flightDetails[6], input);
							if(!valid){
								JOptionPane.showMessageDialog(null, "One or both dates given are invalid, either the dates \nthemselves or the order they are in is incorrect.");
								flightNumberFound = true;
								i=6;
							}
						}
					}
					else{
						triesToExit = true;
					}
				}
			} //Adds input to array
			if(input != null){
				flightDetails[i] = input;
			}
			if(flightDetails[i] != null)
				i++;
		}
		//Assigns details to flight object, adds to arrayList and writes to file
		if(input != null && flightDetails[7] != null)
		{
			Flight f = new Flight(flightDetails[0], flightDetails[1], flightDetails[2], flightDetails[3], flightDetails[4], flightDetails[5], flightDetails[6], flightDetails[7]);
			flightsSorted.add(f);
			bubbleSort("f");
			writeToFile("f");
			JOptionPane.showMessageDialog(null, "Flight " + flightDetails[0] + " has successfully been added to flights list.");
			menu();		
		}
		if(triesToExit && i == 0){		//Determines where to go when user wants to exit
			triesToExit = false;		//Exiting from any point in the input menus bring up the first input menu 
			menu();						//unless exiting from the first input menu.
		}
		else if(triesToExit){
			addFlight();
		}
	}
	
	public static void displayFlightDetails(boolean withDate) throws IOException
	{
		String airportCode1 = "";
		String airportCode2 = "";
		String date = "";
		String tempArray [];
		boolean airportCode1Found = false;
		boolean airportCode2Found = false;
		String pattern = "[A-Z]{3}";
		String input = "";
		while ((airportCode1 != null && airportCode2 != null && date != null) && ((!(airportCode1.matches(pattern))) || (!(airportCode2.matches(pattern)))))
		{
			if (!(airportCode1.matches(pattern)))
			{
				airportCode1 = JOptionPane.showInputDialog(null, "Please enter the code of the departing airport.");
			}
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
				if (airportCode2 == null){
					airportCode1 = "";
					airportCode2 = "";
				}	
				else if (airportCode2 != null && airportCode1.matches(pattern) && (airportCode2.matches(pattern)))
				{
					for (int i = 0; i < airports.size() && !(airportCode1Found && airportCode2Found); i++) // Searching for the airports.
					{
						if (airportCode1.equals(airports.get(i).getAirportCode()))
						{
							airportCode1Found = true;
						}
						if (airportCode2.equals(airports.get(i).getAirportCode()))
						{
							airportCode2Found = true;
						}
					}
					if (airportCode1Found && airportCode2Found && date != null)
					{
						if (withDate)
						{
							date = JOptionPane.showInputDialog(null,"Please enter the date of the flight(s).");
							boolean validDate = isValidDate(date);
							while (date != null && !validDate)
							{
								JOptionPane.showMessageDialog(null,"Invalid date entered. Please use the format dd/mm/yyyy.");
								date = JOptionPane.showInputDialog(null,"Please enter the date of the flight(s).");
							}
							if (date == null){
								airportCode2 = "";
								date = "";
							}
							if (validDate)
							{
								boolean allFound = false;
								String display = "";
								display += "FNo.     Dep   Arr  DepT  ArrT   Days     DDate        ADate\n";
								for (int i = 0; i < flights.size(); i++)
								{
									if (airportCode1.equals(flights.get(i).getDepartingAirport()) && airportCode2.equals(flights.get(i).getArrivingAirport()) && doesDateExist(date,i))
									{
										allFound = true;
										display += flights.get(i).toString() + "\n";
									}
								}
								if (!allFound){
									display += "There are no records currently of any flights with the airports you entered on that date.\n";
								}
								JOptionPane.showMessageDialog(null,display);
							}
						}
						else{
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
							if (!bothFound){
								display += "There are no records currently of any flights with the airports you entered.\n";
							}
							JOptionPane.showMessageDialog(null,display);
						}
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
			while (flightCode != null && (!(flightCode.matches(pattern)))){
				JOptionPane.showMessageDialog(null, "Invalid flight code selected. Please enter the code capitalised with two characters and 4 digits, eg 'EI3000'.");
				flightCode = JOptionPane.showInputDialog(null, "Please enter the flight code number.");
			}
			if (flightCode != null && flightCode.matches(pattern)){
				for (int i = 0; i < flightsSorted.size() && !flightCodeFound; i++)
				{
					if (flightCode.equals(flightsSorted.get(i).getFlightNumber()))
					{
						flightCodeFound = true;
						index = i;	
					}
				}
				if (flightCodeFound)
				{
					flightsSorted.remove(index); // Removes the flight from the arraylist.
					totalFlights--;
					bubbleSort("f");
					writeToFile("f");
					JOptionPane.showMessageDialog(null,"The flight " + flightCode + " was successfully deleted.");
				}
				else{
					JOptionPane.showMessageDialog(null,"Flight code does not exist.");
					deleteAFlight();
				}
			}
		}
		menu();
	}
	
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
			case 0: 
				pattern = "[A-Z]{2}[0-9]{4}"; 
				if(detail.matches(pattern))
					validInput = true;
				else
					validInput = false;
				break;
			case 1: 
				pattern = "[A-Z]{3}"; 
				if(detail.matches(pattern))
					validInput = true;
				else
					validInput = false;
				break;
			case 3: 
				pattern = "[0-1]{1}[0-9]{1}[0-5]{1}[0-9]{1}|2[0-3]{1}[0-5]{1}[0-9]{1}"; 
				if(detail.matches(pattern))
					validInput = true;
				else
					validInput = false; 
				break;
			case 5: 
				String days = "MTWTFSS"; String aChar; detail = detail.toUpperCase(); validInput = true;
				if (detail.length() == 7)
				{
					if(detail.matches("-------"))	// No days is now invalid
						validInput = false;
					else{
						for (int i = 0; i < days.length() && validInput; i++) {
							aChar = detail.substring(i, i + 1);
							if ((!(aChar.equals("-"))) && (!(aChar.equals(days.substring(i, i + 1))))) 
								validInput = false; 	
						}								// ^If the next character does not equal "-" and does 
					}									// not equal the relevant day, then return false.
				}
				else 
					validInput = false; 	
				break;
			case 6: 
				validInput = isValidDate(detail);
				break;
			case 8:
				boolean aMatch; validInput = true;
				for(int k=0; k<flights.size() && validInput; k++)
				{
					if(flights.get(k).getFlightNumber().equals(detail)){
						validInput = false;
					}
				}
				break;
			case 9:
				validInput = false;
				for(int i=0; i<airports.size() && !validInput; i++){
					if(airports.get(i).getAirportCode().equals(detail))
						validInput = true;
				}
				break;
			default:
				validInput = false;
				break;
		}
		return validInput;
	}
	
	public static boolean isValidDate(String userInput)
	{
		if (userInput == null)
			return false;
		String pattern = "[0-9]{2}/[0-9]{2}/[0-9]{4}";
		String dateElements[];
		int ddInt = 0, mmInt = 0, yyInt = 0;
		int [] daysArray = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		boolean dateIsValid = true;
		userInput = userInput.trim();
		dateElements = userInput.split("/");
		if (userInput.matches(pattern))
		{
			ddInt = Integer.parseInt(dateElements[0]);
			mmInt = Integer.parseInt(dateElements[1]);
			yyInt = Integer.parseInt(dateElements[2]);
		}
		if (!(userInput.matches(pattern)))
			dateIsValid = false;
		else if ((ddInt == 0) || (mmInt == 0) || (yyInt == 0))
			dateIsValid = false;
		else if (mmInt > 12)
			dateIsValid = false;
		else if (ddInt > daysArray[mmInt - 1])
			dateIsValid = false;
		else if ((ddInt == 29) && (mmInt == 2) && (((yyInt % 4 == 0) && (yyInt % 100 != 0)) || (yyInt % 400 == 0)))
			dateIsValid = true;
		return dateIsValid;
	}
	
	public static boolean doesDateExist(String dateChosen, int flightIndex)
	{
		boolean dateExists = false;
		String [] dateParts = dateChosen.split("/");
		int d = Integer.parseInt(dateParts[0]);
		int m = Integer.parseInt(dateParts[1]);
		int y = Integer.parseInt(dateParts[2]);
		if (dates(dateChosen,flights.get(flightIndex).getStartDate(),flights.get(flightIndex).getEndDate()))
		{
			int dayOfWeek = convertDateToWeekDay(d,m,y);
			String weekDays = flights.get(flightIndex).getDaysOfWeek();
			switch (dayOfWeek)
			{
				case 0: if (weekDays.substring(dayOfWeek,dayOfWeek + 1).equals("M")) dateExists = true; break;
				case 1: if (weekDays.substring(dayOfWeek,dayOfWeek + 1).equals("T")) dateExists = true; break;
				case 2: if (weekDays.substring(dayOfWeek,dayOfWeek + 1).equals("W")) dateExists = true; break;
				case 3: if (weekDays.substring(dayOfWeek,dayOfWeek + 1).equals("T")) dateExists = true; break;
				case 4: if (weekDays.substring(dayOfWeek,dayOfWeek + 1).equals("F")) dateExists = true; break;
				case 5: if (weekDays.substring(dayOfWeek,dayOfWeek + 1).equals("S")) dateExists = true; break;
				case 6: if (weekDays.substring(dayOfWeek,dayOfWeek + 1).equals("S")) dateExists = true; break;
			}
		}
		return dateExists;
	}
	
	public static boolean dates(String dateChosen, String startDate, String endDate)
	{
		boolean validStartDate = false, validEndDate = false;
		boolean validDates = false;
		if (isValidDate(startDate))
			validStartDate = true;
		else
			return false;
		if (isValidDate(endDate))
			validEndDate = true;
		else
			return false;
		
		if (validStartDate && validEndDate)
		{
			GregorianCalendar aCalendar = new GregorianCalendar();
			Date actualStartDate, actualEndDate, dateGiven;
			DateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
			
			try
			{
				dateGiven = (Date) dateFormatter.parse(dateChosen);
				actualStartDate = (Date) dateFormatter.parse(startDate);
				actualEndDate = (Date) dateFormatter.parse(endDate);
				
				if (actualEndDate.compareTo(dateGiven) >= 0 && actualStartDate.compareTo(dateGiven) <= 0) // Ensures the ending date is after the starting date otherwise the dates are false.
				{
					validDates = true;
				}	
				else
				{
					return false;
				}
			}
			catch (ParseException pe)
			{
				JOptionPane.showMessageDialog(null, "Unable to format one or more dates.");
				return false;
			}
		}
		return validDates;
	}
	
	public static int convertDateToWeekDay(int d, int m, int y)
	{
		String result = ""; 
		int a, b, dayOfWeek;
		if (m == 1 || m == 2)
		{
			m += 12; 
			y -=  1;
		}
		a = y % 100;  
		b = y / 100;
		dayOfWeek = ((d + (((m + 1) * 26) / 10) + a + (a / 4) + (b / 4)) + (5 * b)) % 7;
		switch(dayOfWeek)
		{
			case 0: dayOfWeek = 5;  break;  case 1: dayOfWeek = 6;    break;
			case 2: dayOfWeek = 0;  break;  case 3: dayOfWeek = 1;   break;
			case 4: dayOfWeek = 2;  break;  case 5: dayOfWeek = 3;  break;
			case 6: dayOfWeek = 4;  break;
		}
		return dayOfWeek;  
	}	 
	
	public static void menu() throws IOException
	{
		readFilesIntoArrayLists();
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
					deleteAnAirport();
					break;
				case 4:
					editFlightDetails();
					break;
				case 5:
					deleteAFlight();
					break;
				case 6:
					addFlight();
					break;
				case 7:
					displayFlightDetails(false);
					break;
				case 8:
					displayFlightDetails(true);
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