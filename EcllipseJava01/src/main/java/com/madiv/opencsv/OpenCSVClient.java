package com.madiv.opencsv;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.madiv.generic.util.StringUtil;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class OpenCSVClient {
//	private static String sourceFile = "C:\\Users\\Maddy\\AffirmSoftwareGroup\\TaskworkedUpon\\01.Issues\\29-PROD-ANALYSIS-CFL-RR-02\\Result_All_April12.csv";
//	private static String destinationFile = "C:\\Users\\Maddy\\AffirmSoftwareGroup\\TaskworkedUpon\\01.Issues\\29-PROD-ANALYSIS-CFL-RR-02\\Result_All_April12_marked.csv";
	
	private static String sourceFile = "C:\\Users\\Maddy\\AffirmSoftwareGroup\\TaskworkedUpon\\01.Issues\\29-PROD-ANALYSIS-CFL-RR-02\\Result_Missing2_April15.csv";
	private static String destinationFile = "C:\\Users\\Maddy\\AffirmSoftwareGroup\\TaskworkedUpon\\01.Issues\\29-PROD-ANALYSIS-CFL-RR-02\\Result_Missing2_April15_marked.csv";
	
	private static String previousRecordId = "00";
	private static int maxValueSizeForThisRecord = -1;
	
	private static List<String[]> myListInput = new ArrayList<>();
	private static List<String[]> myListOutput = new ArrayList<>();
	
	
    public static void main(String[] args) throws FileNotFoundException, IOException {
    	int counter = 0;
    	CSVWriter writer = new CSVWriter(new FileWriter(destinationFile));
    	
    	CSVReader csvReader = new CSVReader(new FileReader(sourceFile));
    		
    	    String[] inputLine = null;
    	    while ((inputLine = csvReader.readNext()) != null) {
    	    	counter++;
    	    	
    	    	if(counter == 1) {
        	    	String[] destinationLine = new String[inputLine.length + 1];
        	    	System.arraycopy(inputLine, 0, destinationLine, 0, inputLine.length);
    	    		destinationLine[inputLine.length] = "IS_THIS_THE_ONE";
    	    		writer.writeNext(destinationLine);
    	    	} else {
    	    		// Find the required data.
    	    		
    	    		
    	    		processLineAndWriteToDestination(inputLine, writer);
    	    		
//    	    		System.out.println("source["+inputLine+"] destination["+destinationLine+"]");
    	    	}
    	    	
    	    	
    	    	
    	    } // While end
    	    
    	    // Write last set of records
    		processLineAndWriteToDestination(null, writer);

    	    
    	    writer.close();    	    
    } // end of main

	private static void processLineAndWriteToDestination(String[] inputLine, CSVWriter writer) {
		
		if (inputLine == null || ! previousRecordId.equals(inputLine[3])) {
			// Mark intended records & Write output.
			boolean isMarked = false;
			for(String[] line : myListInput) {
				String[] lineOut = new String[line.length + 1];
				System.arraycopy(line, 0, lineOut, 0, line.length);
				
				if(!isMarked && getMaxColumnNumberHavingValues(line[6], line[7], line[8]) == maxValueSizeForThisRecord) {
					lineOut[line.length] = "Y";
					isMarked = true;
				} else {
					lineOut[line.length] = " ";
				}
				
				writer.writeNext(lineOut);
			}
			

			
			// Clear all constants
			maxValueSizeForThisRecord = -1;
			myListInput.clear();
			// Set new record id.
			previousRecordId = (inputLine == null)?"00":inputLine[3];
			
			if(inputLine == null) {
				return;
			}
			
			
			
			//Insert first value and go on.
			myListInput.add(inputLine);
			
			//Find max value
			if (getMaxColumnNumberHavingValues(inputLine[6], inputLine[7], inputLine[8]) > maxValueSizeForThisRecord) {
				maxValueSizeForThisRecord = getMaxColumnNumberHavingValues(inputLine[6], inputLine[7], inputLine[8]);
			}
			
			
		} else {
			//Existing record ids... 
			//Insert value and go on.
			myListInput.add(inputLine);
			
			//Find max value
			if (getMaxColumnNumberHavingValues(inputLine[6], inputLine[7], inputLine[8]) > maxValueSizeForThisRecord) {
				maxValueSizeForThisRecord = getMaxColumnNumberHavingValues(inputLine[6], inputLine[7], inputLine[8]);
			}
			
			
		}
	}

	private static int getMaxColumnNumberHavingValues(String col6, String col7, String col8) {
		
		if(!StringUtil.isEmpty(col6) && !StringUtil.isEmpty(col7) && !StringUtil.isEmpty(col8)) {
			return 3;
		}
		
		if((!StringUtil.isEmpty(col6) && !StringUtil.isEmpty(col7) && StringUtil.isEmpty(col8)) 
					|| 
		   (!StringUtil.isEmpty(col6) && StringUtil.isEmpty(col7) && !StringUtil.isEmpty(col8))
					|| 
		   (StringUtil.isEmpty(col6) && !StringUtil.isEmpty(col7) && !StringUtil.isEmpty(col8))
		) {
			return 2;
		}
		
		if((!StringUtil.isEmpty(col6) && StringUtil.isEmpty(col7) && StringUtil.isEmpty(col8)) 
					|| 
		   (StringUtil.isEmpty(col6) && !StringUtil.isEmpty(col7) && StringUtil.isEmpty(col8))
					|| 
		   (StringUtil.isEmpty(col6) && StringUtil.isEmpty(col7) && !StringUtil.isEmpty(col8))
		) {
			return 1;
		}
		
		if(StringUtil.isEmpty(col6) && StringUtil.isEmpty(col7) && StringUtil.isEmpty(col8)) {
			return 0;
		}
		
		return 0;
	}
	
	
    	
} // end of class.
