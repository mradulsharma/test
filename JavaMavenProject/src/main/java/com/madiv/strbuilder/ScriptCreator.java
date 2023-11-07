package com.madiv.strbuilder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * 
 * @author Maddy
 *
 * This one creates *.sh script reading a 122 mb file. (3.1 million records)
 * Here it has been taken care that we are not doing loads of string reference deletion, else will cause loads of GC
 * and this code will crash. So StringBuilder is used at all the places.
 * 
 * 
 * File contents are :
 * <begining of file>
 * "DATA"
 * "43441329/43441336/14/1339043010045_0"
 * "43441329/43441336/77/1339043010089_0"
 * "43441329/43441336/79/1339043010283_0"
 * :
 * :
 * "43441329/43441336/79/1339043010283_0"
 * <end of file>
 * 
 * It generates *.sh file : 
 * <begining of file>
 * echo Starting script.
 * 
 * export FILE_NAME=/cc/revolve-prod/data/documentData/43441329/43441336/14/1339043010045_0
 * if [ ! -f $FILE_NAME ]; then echo file does not exists $FILE_NAME; fi
 * 
 * export FILE_NAME=/cc/revolve-prod/data/documentData/43441329/43441336/77/1339043010089_0
 * if [ ! -f $FILE_NAME ]; then echo file does not exists $FILE_NAME; fi
 * :
 * :
 * :
 * :
 * <end of file>
 *
 *
 *
 * When that *.sh will be generated, it will output as follows. Where 1 means file does not exists, and 2 means file exists.
 * <file_name> 2
 * <file_name> 2
 * :  
 * <file_name> 1
 *
 * 
 *
 *
 */
public class ScriptCreator {
//	private static String SOURCE_FILE = "C:\\Users\\Maddy\\AffirmSoftwareGroup\\TaskworkedUpon\\01.Issues\\30-PROD-MissingFileAnalysis\\export_sample.csv";
	private static String SOURCE_FILE = "C:\\Users\\Maddy\\AffirmSoftwareGroup\\TaskworkedUpon\\01.Issues\\30-PROD-MissingFileAnalysis\\export.csv";
	private static String DESTINATION_FILE = "C:\\Users\\Maddy\\AffirmSoftwareGroup\\TaskworkedUpon\\01.Issues\\30-PROD-MissingFileAnalysis\\findMissingFile_sample.sh";
	private static String LINE_PREFIX = "export FILE_NAME=/cc/revolve-prod/data/documentData/";
	private static String LINE_2 = "if [ ! -f $FILE_NAME ]; then echo file does not exists $FILE_NAME; fi";
	private static String LINE_START = "echo Starting script.";
	private static String LINE_END = "echo End script.";
	
	private static boolean isProcessingStarted = false;
	
	private static long lineCount=0l;
	
	private static long maxLine = 3140934;
	private static long sopEvery = 10000;
	
	
	
	public static void main(String[] args) {
    	//Initialize StringBuilder and Set initial capacity 128
    	StringBuilder strBuilder = new StringBuilder();
    	strBuilder.ensureCapacity(128);
		
		
		
	
    	BufferedReader inputBufferedReader;
    	BufferedWriter outputBufferedWriter;
    	
    	try {
			inputBufferedReader = new BufferedReader(new FileReader(SOURCE_FILE));
			System.out.println("Source File = ["+SOURCE_FILE+"]");
			outputBufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(DESTINATION_FILE))));
			System.out.println("Destination File = ["+DESTINATION_FILE+"]");
			
			
			
			
			
			strBuilder.append(inputBufferedReader.readLine());
			while (!(("null".equals(strBuilder.toString()) && strBuilder.length() == 4) || ("".equals(strBuilder.toString()) && strBuilder.length() == 4)) ) {
				
				
	    		if("\"DATA\"".equals(strBuilder.toString())) {
	    			System.out.println("Doing nothing, header DATA found.");
	    			
	    			
	    			//echo first line in script
	    			strBuilder.delete(0, strBuilder.length());
	    			strBuilder.append(LINE_START);
	    			writeBufferLine(strBuilder, outputBufferedWriter, true);
	    			
	    		} else {
	    			lineCount++;
	    			if(!isProcessingStarted) {
	    				System.out.print("Processing lines.");
	    				isProcessingStarted = true;
	    			} else {
	    				System.out.print(".");
					}
	    			
	    			strBuilder.replace(strBuilder.indexOf("\""), strBuilder.indexOf("\"")+1, "");
	    			strBuilder.replace(strBuilder.indexOf("\""), strBuilder.indexOf("\"")+1, "");
	    			
	    			//Line 1
	    			strBuilder.insert(0, LINE_PREFIX);
	    			
	    			//Write line.
	    			writeBufferLine(strBuilder, outputBufferedWriter, false);
	    			
	    			//Line 2
	    			strBuilder.delete(0, strBuilder.length());
	    			strBuilder.append(LINE_2);
	    			
	    			//Write line 2.
	    			writeBufferLine(strBuilder, outputBufferedWriter, true);
	    			
				} // End of else bluck if not DATA line
				
	    		
	    		
	    		if(lineCount % sopEvery == 0) {
	    			
	    		}
	    		
				
				
				
				// Clear string builder and read next line
    			strBuilder.delete(0, strBuilder.length());
	    		strBuilder.append(inputBufferedReader.readLine());
			} // End of while loop
			
			
			
			//echo end line in script
			strBuilder.delete(0, strBuilder.length());
			strBuilder.append(LINE_END);
			writeBufferLine(strBuilder, outputBufferedWriter, true);
			
			//Closing buffered reader and writer.
			inputBufferedReader.close();
			outputBufferedWriter.close();
		} catch (IOException e) {
			System.out.println("Exception : " + e.getMessage());
		}    	
		
    	
		System.out.println("");
		System.out.println("Processed "+lineCount+" line !!");

		
		
	}


	private static void writeBufferLine(StringBuilder strBuilder, BufferedWriter outputBufferedWriter, boolean isAdditionalNewLine) throws IOException {
		//Write line.
		outputBufferedWriter.write(strBuilder.toString());
		outputBufferedWriter.write("\n");
		if(isAdditionalNewLine) {
			outputBufferedWriter.write("\n");
		}
	}

}
