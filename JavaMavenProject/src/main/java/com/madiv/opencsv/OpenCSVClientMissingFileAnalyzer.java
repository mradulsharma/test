package com.madiv.opencsv;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class OpenCSVClientMissingFileAnalyzer {
	private static String sourceFile = "C:\\Users\\Maddy\\AffirmSoftwareGroup\\TaskworkedUpon\\01.Issues\\30-PROD-MissingFileAnalysis\\prod_document_v_01.csv";
	private static String destinationFile = "C:\\Users\\Maddy\\AffirmSoftwareGroup\\TaskworkedUpon\\01.Issues\\30-PROD-MissingFileAnalysis\\prod_document_v_01_out.csv";
	
	private static List<Integer> distinctLength = new ArrayList<>();
	private static String[] fixedLine = new String[8];
	
    public static void main(String[] args) throws FileNotFoundException, IOException {
    	int counterRead = 0;
    	int counterWritten = 0;
    	CSVWriter writer = new CSVWriter(new FileWriter(destinationFile));
    	CSVReader csvReader = new CSVReader(new FileReader(sourceFile));
    		
    	    String[] inputLine = null;
    	    while ((inputLine = csvReader.readNext()) != null) {
    	    	counterRead++;
    	    	
    	    	if(counterRead != 1 && inputLine.length != 8) {
    	    		if(!distinctLength.contains(inputLine.length)) {
    	    			distinctLength.add(inputLine.length);
    	    		}
    	    		
    	    		if(inputLine.length == 9) {
    	    			fixedLine[0] = inputLine[0]; 	    			
    	    			fixedLine[1] = inputLine[1]; 	    			
    	    			fixedLine[2] = inputLine[2]; 	    			
    	    			fixedLine[3] = inputLine[3]; 	    			
    	    			fixedLine[4] = inputLine[4] + "," + inputLine[5]; 	    			
    	    			fixedLine[5] = inputLine[6]; 	    			
    	    			fixedLine[6] = inputLine[7]; 	    			
    	    			fixedLine[7] = inputLine[8]; 	    			
    	    			
        	    		writer.writeNext(fixedLine);
        	    		counterWritten++;
    	    			
    	    		} else if (inputLine.length == 10) {
    	    			// 20140124 Hoyle,  Hannah PD.pdf
    	    			fixedLine[0] = inputLine[0]; 	    			
    	    			fixedLine[1] = inputLine[1]; 	    			
    	    			fixedLine[2] = inputLine[2]; 	    			
    	    			fixedLine[3] = inputLine[3]; 	    			
    	    			fixedLine[4] = inputLine[4] + "," + inputLine[5]; 	    			
    	    			fixedLine[5] = inputLine[6] + "," + inputLine[7]; 	    			
    	    			fixedLine[6] = inputLine[8]; 	    			
    	    			fixedLine[7] = inputLine[9]; 	    			
    	    			
    	    			
        	    		writer.writeNext(fixedLine);
        	    		counterWritten++;
    	    		} else if (inputLine.length == 12) {
    	    			// NIGHTINGALE,  Annette - Cert 3 in Individual support Ageing,  home & com.pdf
    	    			fixedLine[0] = inputLine[0]; 	    			
    	    			fixedLine[1] = inputLine[1]; 	    			
    	    			fixedLine[2] = inputLine[2]; 	    			
    	    			fixedLine[3] = inputLine[3]; 	    			
    	    			fixedLine[4] = inputLine[4] + "," + inputLine[5] + "," + inputLine[6]; 	    			
    	    			fixedLine[5] = inputLine[7] + "," + inputLine[8] + "," + inputLine[9]; 	    			
    	    			fixedLine[6] = inputLine[10]; 	    			
    	    			fixedLine[7] = inputLine[11]; 	    			
    	    			
        	    		writer.writeNext(fixedLine);
        	    		counterWritten++;
    	    		} else if (inputLine.length == 14) {
    	    			//HALL,  Steven - Birth certificate,  drivers licence,  blue card.pdf
    	    			fixedLine[0] = inputLine[0]; 	    			
    	    			fixedLine[1] = inputLine[1]; 	    			
    	    			fixedLine[2] = inputLine[2]; 	    			
    	    			fixedLine[3] = inputLine[3]; 	    			
    	    			fixedLine[4] = inputLine[4] + "," + inputLine[5] + "," + inputLine[6] + "," + inputLine[7]; 	    			
    	    			fixedLine[5] = inputLine[8] + "," + inputLine[9] + "," + inputLine[10] + "," + inputLine[11]; 	    			
    	    			fixedLine[6] = inputLine[12]; 	    			
    	    			fixedLine[7] = inputLine[13]; 	    			
    	    			
    	    			
        	    		writer.writeNext(fixedLine);
        	    		counterWritten++;
    	    		} else if (inputLine.length == 16) {
    	    			// Disability Certificate lessons 4, 5, 6, 7, 8.pdf
    	    			fixedLine[0] = inputLine[0]; 	    			
    	    			fixedLine[1] = inputLine[1]; 	    			
    	    			fixedLine[2] = inputLine[2]; 	    			
    	    			fixedLine[3] = inputLine[3]; 	    			
    	    			fixedLine[4] = inputLine[4] + "," + inputLine[5] + "," + inputLine[6] + "," + inputLine[7] + "," + inputLine[8]; 	    			
    	    			fixedLine[5] = inputLine[9] + "," + inputLine[10] + "," + inputLine[11] + "," + inputLine[12] + "," + inputLine[13]; 	    			
    	    			fixedLine[6] = inputLine[14]; 	    			
    	    			fixedLine[7] = inputLine[15]; 	    	    	    			
    	    			
    	    			
        	    		writer.writeNext(fixedLine);
        	    		counterWritten++;
    	    		}
    	    		
    	    	} else {
    	    		writer.writeNext(inputLine);
    	    		counterWritten++;
	    		}  
    	    	
    	    } // While end
    	    
    	    writer.close();    
    	    
    	    
    	    
    	    System.out.println("counterRead["+counterRead+"], counterWritten["+counterWritten+"]");
    	    
    } // end of main


    	
} // end of class.
