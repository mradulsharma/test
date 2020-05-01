package com.madiv.opencsv;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.madiv.generic.util.ResourceUtil;
import com.opencsv.CSVReader;

public class OpenCSVSimpleClient {
	private final String fileName = "com/madiv/opencsv/TestCsv.csv";
	
	
	public static void main(String[] args) {
		OpenCSVSimpleClient client = new OpenCSVSimpleClient();
		try {
			client.doIt();
		} catch (FileNotFoundException e) {
			System.out.println("Fiel not found !! "  + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IOException !! " + e.getMessage());
			e.printStackTrace();
		}
	}

	private void doIt() throws IOException {
    	int counter = 0;
		File file = ResourceUtil.getFileFromResources(fileName);
		CSVReader csvReader = new CSVReader(new FileReader(file));
		
		
		String[] inputLine = null;
		while ((inputLine = csvReader.readNext()) != null) {
			counter++;
			
	    	if(counter == 1) {
	    		//Header row
	    		printContentsToConsole(inputLine);
	    		
	    	} else {
	    		//Data row
	    		printContentsToConsole(inputLine);
				
			}

			
		} // End of For while
		
	} // end of doIt()

	private void printContentsToConsole(String[] inputLine) {
		for(String columnValue : inputLine) {
			System.out.println(columnValue);
		}
		System.out.println("\n\n\n\n");
	}
	
	
	
	
}
