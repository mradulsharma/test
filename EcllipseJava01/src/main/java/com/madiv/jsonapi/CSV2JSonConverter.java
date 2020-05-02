package com.madiv.jsonapi;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import com.madiv.generic.util.DateUtil;
import com.madiv.generic.util.ResourceUtil;
import com.opencsv.CSVReader;

public class CSV2JSonConverter {
	private final String sourceFileName = "com/madiv/jsonapi/SampleTestBoardRiders.csv";
	private final String destinationDirectoryLocation = "C:\\Users\\Maddy\\AffirmSoftwareGroup\\TaskworkedUpon\\01.Issues\\77-BoardRiderProdCovid19-JO\\FinalFiles\\";
	private BufferedWriter currentFileBW = null;    
	
	private Map<Integer, ColumnProcessor> columnProcessorMap = null; 
	private Map<Integer, String> jsonKeyNameMap = null; 
	private String[] columnNames = new String[13];
	
	
	private int fileNameCounter = 0;
	
	private StringBuilder singleLine = null;
	
	
	public CSV2JSonConverter() {
		DefaultColumnProcessor defaultColumnProcessor = new DefaultColumnProcessor();
		DateColumnProcessor dateColumnProcessor = new DateColumnProcessor();
		columnProcessorMap = new HashMap<Integer, ColumnProcessor>();
		columnProcessorMap.put(1, defaultColumnProcessor); 	//TEMPLATE_name
		columnProcessorMap.put(2, defaultColumnProcessor);  //candidate_first_name
		columnProcessorMap.put(3, defaultColumnProcessor);  //candidate_last_name
		columnProcessorMap.put(4, defaultColumnProcessor);  //candidate_mobile_no
		columnProcessorMap.put(5, defaultColumnProcessor);  //candidate_email
		columnProcessorMap.put(6, defaultColumnProcessor);  //candidate_addr_street_covid19
		columnProcessorMap.put(7, defaultColumnProcessor);  //candidate_addr_suburb_covid19
		columnProcessorMap.put(8, defaultColumnProcessor);  //candidate_addr_state_covid19
		columnProcessorMap.put(9, defaultColumnProcessor);  //candidate_addr_postcode_covid19
		columnProcessorMap.put(10, defaultColumnProcessor); //candidate_addr_country_covid19
		columnProcessorMap.put(11, dateColumnProcessor);    //start_date
		columnProcessorMap.put(12, defaultColumnProcessor); //current_salary_covid19
		columnProcessorMap.put(13, defaultColumnProcessor); //new_salary_covid19
		
		
		
		jsonKeyNameMap = new HashMap<Integer, String>();
		jsonKeyNameMap.put(1, "template");	//TEMPLATE_name
		jsonKeyNameMap.put(2, "applicant_firstName");   //candidate_first_name
		jsonKeyNameMap.put(3, "applicant_lastName");   //candidate_last_name
		jsonKeyNameMap.put(4, "applicant_mobilePhone");   //candidate_mobile_no
		jsonKeyNameMap.put(5, "applicant_email");   //candidate_email
		jsonKeyNameMap.put(6, "candidate_addr_street_covid19");   //candidate_addr_street_covid19
		jsonKeyNameMap.put(7, "candidate_addr_suburb_covid19");   //candidate_addr_suburb_covid19
		jsonKeyNameMap.put(8, "candidate_addr_state_covid19");   //candidate_addr_state_covid19
		jsonKeyNameMap.put(9, "candidate_addr_postcode_covid19");   //candidate_addr_postcode_covid19
		jsonKeyNameMap.put(10, "candidate_addr_country_covid19");  //candidate_addr_country_covid19
		jsonKeyNameMap.put(11, "job_startDate");  //start_date
		jsonKeyNameMap.put(12, "current_salary_covid19");  //current_salary_covid19
		jsonKeyNameMap.put(13, "new_salary_covid19");  //new_salary_covid19
		
		
		//Initialize StringBuilder
		singleLine = new StringBuilder();
		singleLine.ensureCapacity(128);
		
		
	}
	
	
	public static void main(String[] args) {
		
		CSV2JSonConverter converter = new CSV2JSonConverter();
		try {
			converter.doIt();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (ParseException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
	}

	private void doIt() throws Exception {
    	Integer lineCounterInFile = 0;
		File file = ResourceUtil.getFileFromResources(sourceFileName);
		CSVReader csvReader = new CSVReader(new FileReader(file));
		
		
		String[] inputLine = null;
		while ((inputLine = csvReader.readNext()) != null) {
			
	    	if(lineCounterInFile == 0) {
	    		//Header row
	    		populateColumnName(inputLine);
	    		
	    	} else {
	    		//Data row
	    		//printContentsToConsole(inputLine);
	    		
	    		
	    		if((lineCounterInFile-1)%5 == 0) { // New file about to start
	    			// Close old file
	    			closeIfNotNull(currentFileBW);
	    			
	    			
	    			
	    			// Create new file
	    			String fileName = getNewFileName();
	    			System.out.println("Writing new file : ["+fileName+"]");
	    			currentFileBW = new BufferedWriter(new FileWriter(new File(destinationDirectoryLocation, fileName)));
	    			
	    			createBeginingOfFileEntries(currentFileBW);
	    			
	    		} else { //  Not new file, lines are processed in same file
	    			// hence we should put comma at end of previous line written.
	    			currentFileBW.write("\t\t,");
	    			currentFileBW.newLine();
	    		}
	    		
	    		
	    		processSingleCsvLine(currentFileBW, inputLine, lineCounterInFile);
	    		
	    		
				
			}

			
	    	lineCounterInFile++;
		} // End of For while
		
		
		//Print columnNames 
		//System.out.println("Headers :=> " + Arrays.asList(columnNames));
		
		
		
		System.out.println("Total lines processed " + lineCounterInFile);
		
		//Close Resources
		csvReader.close();
		// Close old file
		closeIfNotNull(currentFileBW);
		
	}// end of doIt()
	
	

	private void createBeginingOfFileEntries(BufferedWriter currentFileBufferedWriter) throws IOException, ParseException {
		singleLine.delete(0, singleLine.length());// Empty line if any contents are there.
		
		singleLine.append("{");
		writeSingleLineToBuffer(singleLine, currentFileBufferedWriter);
		
		singleLine.append("\t\"name\":\"COVID19_feed_BoardRiders\",");
		writeSingleLineToBuffer(singleLine, currentFileBufferedWriter);
		
		singleLine.append("\t\"timestamp\":\"genie-timestamp\",");
		writeSingleLineToBuffer(singleLine, currentFileBufferedWriter);
		
		singleLine.append("\t\"unique_id\":\"BoardRidersCovid19_"+DateUtil.currentDateInFormat("yyyyMMdd")+"\",");
		writeSingleLineToBuffer(singleLine, currentFileBufferedWriter);
		
		singleLine.append("\t\"template\":\"COVID19 Letter\",");
		writeSingleLineToBuffer(singleLine, currentFileBufferedWriter);
		
		singleLine.append("\t\"division\":\"\",");
		writeSingleLineToBuffer(singleLine, currentFileBufferedWriter);
		
		singleLine.append("\t\"applicants\":[");
		writeSingleLineToBuffer(singleLine, currentFileBufferedWriter);
		
		singleLine.delete(0, singleLine.length());// Empty line if any contents are there.
	}


	private void writeSingleLineToBuffer(StringBuilder singleLine, BufferedWriter currentFileBufferedWriter) throws IOException {
		currentFileBufferedWriter.write(singleLine.toString());
		currentFileBufferedWriter.newLine();
		singleLine.delete(0, singleLine.length());
	}


	private void closeIfNotNull(BufferedWriter currentFileBufferedWritter) throws IOException {
		if(currentFileBufferedWritter != null) {
			
			singleLine.delete(0, singleLine.length());// Empty line if any contents are there.
			
			singleLine.append("\t]");
			writeSingleLineToBuffer(singleLine, currentFileBufferedWritter);
			
			singleLine.append("}");
			writeSingleLineToBuffer(singleLine, currentFileBufferedWritter);
			
			singleLine.delete(0, singleLine.length());// Empty line if any contents are there.
			
			
			currentFileBufferedWritter.flush();
			currentFileBufferedWritter.close();
		}
		
	}


	private void processSingleCsvLine(BufferedWriter currentFileBufferedWriter, String[] inputLine, Integer lineCounterInFile) throws Exception {
		
		processSingleRecordStartingLines(currentFileBufferedWriter, lineCounterInFile);
		
		
		//MainRecordContents
		Integer localColumnCounter = 0;
		for(String columnValue : inputLine) {
			localColumnCounter++;
			
			if (localColumnCounter == 1) continue; // First one is template name, do not process it.
			
			
			ColumnProcessor columnProcessor = columnProcessorMap.get(localColumnCounter); // get appropriate column value processor
			
			singleLine.delete(0, singleLine.length());
			
			if(localColumnCounter < 13) {
				singleLine.append("\t\t\t"+columnProcessor.processColumnValue(columnValue, jsonKeyNameMap.get(localColumnCounter))+",");
			} else {
				singleLine.append("\t\t\t"+columnProcessor.processColumnValue(columnValue, jsonKeyNameMap.get(localColumnCounter)));
			}

			writeSingleLineToBuffer(singleLine, currentFileBufferedWriter);
			
		}
		
		
		processSingleRecordEndingLines(currentFileBufferedWriter);
		
	}


	private void processSingleRecordEndingLines(BufferedWriter currentFileBufferedWriter) throws IOException {
		singleLine.delete(0, singleLine.length());// Empty line if any contents are there.
		
		singleLine.append("\t\t}");
		writeSingleLineToBuffer(singleLine, currentFileBufferedWriter);
		
		singleLine.delete(0, singleLine.length());// Empty line if any contents are there.
	}


	private void processSingleRecordStartingLines(BufferedWriter currentFileBufferedWriter, Integer lineCounterInFile) throws IOException {
		singleLine.delete(0, singleLine.length());// Empty line if any contents are there.
		
		singleLine.append("\t\t{");
		writeSingleLineToBuffer(singleLine, currentFileBufferedWriter);
		
		singleLine.append("\t\t\t\"template\": \"\",");
		writeSingleLineToBuffer(singleLine, currentFileBufferedWriter);
		
		singleLine.append("\t\t\t\"division\": \"\",");
		writeSingleLineToBuffer(singleLine, currentFileBufferedWriter);
		
		singleLine.append("\t\t\t\"applicant_id\": \""+lineCounterInFile+"\",");
		writeSingleLineToBuffer(singleLine, currentFileBufferedWriter);
		
		
		
		singleLine.delete(0, singleLine.length());// Empty line if any contents are there.
	}


	private String getNewFileName() throws ParseException {
		return "BoardRidersProdRun" + DateUtil.currentDateInFormat("yyyyMMdd") + "-" + (((fileNameCounter + 1) < 10) ? "0"+(++fileNameCounter) : ++fileNameCounter);
	}


	private void populateColumnName(String[] inputLine) {
		int i = 0;
		for(String runtimeColumnName : inputLine) {
			columnNames[i++] = runtimeColumnName.trim(); 	
		}
	}


	private void printContentsToConsole(String[] inputLine) {
		for(String columnValue : inputLine) {
			System.out.println(columnValue);
		}
		System.out.println("\n\n\n\n");
	}

}
