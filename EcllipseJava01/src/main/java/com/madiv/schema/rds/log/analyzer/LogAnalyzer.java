package com.madiv.schema.rds.log.analyzer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.madiv.dsgnptrn.behavior.observer.ObserverConfig;

/**
 * 
 * @author Maddy
 * 
 * Usage : LogAnalyzer arg1 arg2 arg3
 * arg1 : LogFile 1 fully qualified path
 * arg2 : LogFile 2 fully qualified path
 * arg3 : Debug level => options DEBUG/INFO/WARN/ERROR/FATAL
 *
 */



enum LogLevel{
	
	DEBUG (Level.DEBUG), 
	INFO (Level.INFO),
	WARN (Level.WARN),
	ERROR (Level.ERROR),
	FATAL (Level.FATAL);

	private final Level level;
	
	
	LogLevel(Level level){
		this.level = level;
	}

	public Level getLog4jLevel() {
		return this.level;
	}
	
}




class LogContent{
	public LogContent(String schemaName, String tableName, Float size, String sizeUnit, Long rows) {
		super();
		this.schemaName = schemaName;
		this.tableName = tableName;
		this.size = size;
		this.sizeUnit = sizeUnit;
		this.rows = rows;
	}
	private String schemaName;
	private String tableName;
	private Float size;
	private String sizeUnit;
	private Long rows;
	
	
	public String getSchemaName() {
		return schemaName;
	}
	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public Float getSize() {
		return size;
	}
	public void setSize(Float size) {
		this.size = size;
	}
	public String getSizeUnit() {
		return sizeUnit;
	}
	public void setSizeUnit(String sizeUnit) {
		this.sizeUnit = sizeUnit;
	}
	public Long getRows() {
		return rows;
	}
	public void setRows(Long rows) {
		this.rows = rows;
	}
	@Override
	public String toString() {
		return "LogContent [schemaName=" + schemaName + ", tableName=" + tableName + ", size=" + size + ", sizeUnit=" + sizeUnit + ", rows=" + rows + "]";
	}
}



public class LogAnalyzer {
	private static String regExpression_01 = "^.*\"(.*)\"\\.\"(.*)\" *([0-9]*\\.[0-9]*) (..) *([0-9]*) rows";
	private static String regExpression_02 = "^.*\"(.*)\"\\.\"(.*)\" *([0-9]*) (..) *([0-9]*) rows";
	private String logFileDay1;
	private String logFileDay2;
	protected final Logger log = Logger.getLogger(this.getClass());
	private AbstractApplicationContext  context = null;
	private List<String> reportOutput = new ArrayList<String>();
	
	
	public static void main(String[] args) throws Exception {
		
		LogAnalyzer analyzer = new LogAnalyzer(args);
		analyzer.analyzeLogFiles(args);
		
		
	}
	
	public LogAnalyzer(String[] args) throws Exception {
		this.context = new AnnotationConfigApplicationContext(ObserverConfig.class);
		Logger rootLogger = Logger.getRootLogger();
		
		//Get debug level from argument.
		Level level = null;
		for (LogLevel value : LogLevel.values()) {
			if(value.name().equals(args[2])) {
				level = value.getLog4jLevel();
			}
		}
		
		if(level == null) {
			throw new Exception("Given debug level ["+args[2]+"] does not match with acceptable values :" + Stream.of(LogLevel.values()).map(LogLevel::name).collect(Collectors.toList()));
		} else {
			rootLogger.setLevel(level);
		}
		
		//Define log pattern layout
		PatternLayout layout = new PatternLayout("%d{ISO8601} %c [%-5p] %x - %m%n");
		 
		//Add console appender to root logger
		rootLogger.addAppender(new ConsoleAppender(layout));		
		
	}

	private void analyzeLogFiles(String[] args) throws IOException {
		//Read arguments and note file name.
		readArguments(args);
		
		//Load files1
		log.info("Loading log file 01"+logFileDay1);
		Map<String, LogContent> mapLogFileContentDay01 = loadFile(logFileDay1);
		
		//Load files2
		log.info("Loading log file 02"+logFileDay2);
		Map<String, LogContent> mapLogFileContentDay02 = loadFile(logFileDay2);
		
		//Analyze files
		analyzeLogDiff(mapLogFileContentDay01, mapLogFileContentDay02);
		
		//Generate report
		reportOutput.forEach(System.out::println);
		
	}

	private void analyzeLogDiff(Map<String, LogContent> mapLogFileContentDay01, Map<String, LogContent> mapLogFileContentDay02) {
		log.debug("Starting analyzing log diff...");
		//Local variables
    	StringBuilder strReportLine = new StringBuilder();
    	StringBuilder strReportItem = new StringBuilder();
		
    	Long connxSizeIncrease = 0l;
    	Long revolveSizeIncrease = 0l;
		
		//Add header
    	addHeader(reportOutput);
		
		//Get sorted list of table name from first day log.
		List<String> firstMapKeyList = new ArrayList<String>(mapLogFileContentDay01.keySet());
		Collections.sort(firstMapKeyList);
		
		//Get sorted list of table name from second day log.
		List<String> secondMapKeyList = new ArrayList<String>(mapLogFileContentDay02.keySet());
		Collections.sort(secondMapKeyList);
		
		//Compare length of both lists and find max length
		int iterateCount = firstMapKeyList.size();
		if(secondMapKeyList.size() > iterateCount) iterateCount = secondMapKeyList.size();
		
		log.debug("firstMapKeyList = " + firstMapKeyList);
		log.debug("secondMapKeyList = " + secondMapKeyList);

		//Iterate from zero to iterateCount
		for(int i=0; i<iterateCount; i++) {
			// Clear string builder
			strReportItem.delete(0, strReportItem.length()); 
			strReportLine.delete(0, strReportLine.length());

			LogContent logContentDay01 = mapLogFileContentDay01.get(firstMapKeyList.get(i));
			LogContent logContentDay02 = mapLogFileContentDay02.get(secondMapKeyList.get(i));
			processCurrentTable(strReportLine, strReportItem, logContentDay01, logContentDay02);
			reportOutput.add(strReportLine.toString());

		
			//Find total size increased.
			if(logContentDay01 != null && logContentDay02 != null) {
				if("REVOLVE_PROD".equals(logContentDay01.getSchemaName())) {
					revolveSizeIncrease += getTableSizeDiffInBytes(logContentDay02.getSize(), logContentDay01.getSize(), logContentDay01.getSizeUnit());
				} else if ("CONNX_PROD".equals(logContentDay01.getSchemaName())) {
					connxSizeIncrease += getTableSizeDiffInBytes(logContentDay02.getSize(), logContentDay01.getSize(), logContentDay01.getSizeUnit());
				}
			}
			
		}
		
		
		//Add size diff to report
		addSummaryOfDiff(reportOutput, revolveSizeIncrease, connxSizeIncrease);
		
	}
	
	
	private void addSummaryOfDiff(List<String> reportOutput2, Long revolveSizeIncrease, Long connxSizeIncrease) {
		reportOutput.add(" ");
		reportOutput.add(" ");
		reportOutput.add("#############");
		reportOutput.add("Diff Summary.");
		reportOutput.add("-------------");
		reportOutput.add("Revolve size increased : "+StringUtils.leftPad(new Long((revolveSizeIncrease / 1000000000)).toString(), 12)+" GB / "+StringUtils.leftPad(new Long((revolveSizeIncrease / 1000000)).toString(), 12)+" MB / "+StringUtils.leftPad(new Long((revolveSizeIncrease / 1000)).toString(), 12)+" KB.");
		reportOutput.add("Connx size increased   : "+StringUtils.leftPad(new Long((connxSizeIncrease / 1000000000)).toString(), 12)+" GB / "+StringUtils.leftPad(new Long((connxSizeIncrease / 1000000)).toString(), 12)+" MB / "+StringUtils.leftPad(new Long((connxSizeIncrease / 1000)).toString(), 12)+" KB.");
		reportOutput.add("################################################################################################################################################################");		
		reportOutput.add("################################################################################################################################################################");		
	}

	private Long getTableSizeDiffInBytes(Float sizeDay02, Float sizeDay01, String sizeUnit) {
		
		Long retValue = null;
		
		if("GB".equals(sizeUnit)) {
			Float diff = ((sizeDay02 - sizeDay01) * 1000000000f);
			retValue = diff.longValue(); 
		} else if ("MB".equals(sizeUnit)) {
			Float diff = ((sizeDay02 - sizeDay01) * 1000000f);
			retValue = diff.longValue(); 
		} else if ("KB".equals(sizeUnit)) {
			Float diff = ((sizeDay02 - sizeDay01) * 1000f);
			retValue = diff.longValue(); 
		}
		return retValue;
	}

	private void processCurrentTable(StringBuilder strReportLine, StringBuilder strReportItem, LogContent logContentDay01, LogContent logContentDay02) {
    	boolean rightPad = false;
    	boolean leftPad = true;
    	Integer currentPostion = 0;
    	boolean isDay01Exists = (logContentDay01 != null)? true:false;
    	boolean isDay02Exists = (logContentDay02 != null)? true:false;
    	boolean isFindDiff = (isDay01Exists && isDay02Exists)? true:false;
    	
    	
    	//Day one logContents
    	String tableName = null;
    	Float d01Size = null;
    	String d01SizeUnit = null;
    	Long d01Rows = null;
    	if(isDay01Exists) {
    		tableName = logContentDay01.getSchemaName()+"."+logContentDay01.getTableName();
    		d01Size = logContentDay01.getSize();
    		d01SizeUnit = logContentDay01.getSizeUnit();
    		d01Rows = logContentDay01.getRows();
    	}
    	
    	//Day two logContents
    	Float d02Size = null;
    	String d02SizeUnit = null;
    	Long d02Rows = null;
    	if(isDay02Exists) {
    		tableName = logContentDay02.getSchemaName()+"."+logContentDay02.getTableName();
    		d02Size = logContentDay02.getSize();
    		d02SizeUnit = logContentDay02.getSizeUnit();
    		d02Rows = logContentDay02.getRows();
    	}
    	
    	
    	//Table name
    	strReportItem.append(tableName);
    	currentPostion = addItemToStringBuilder(strReportLine, strReportItem, 60, currentPostion, rightPad);

    	
    	
    	//Tab between table name and Day 01
    	strReportItem.append("\t\t");
    	currentPostion = addItemToStringBuilder(strReportLine, strReportItem, 2, currentPostion, rightPad);
    	
    	
    	
    	//Day 1 size
    	strReportItem.append((d01Size != null)?d01Size:"-");
    	currentPostion = addItemToStringBuilder(strReportLine, strReportItem, 6, currentPostion, rightPad);
    	
    	//Day 1 size unit
    	strReportItem.append((d01SizeUnit != null)?d01SizeUnit:"-");
    	currentPostion = addItemToStringBuilder(strReportLine, strReportItem, 4, currentPostion, rightPad);
    	
    	//Day 1 Rows
    	strReportItem.append((d01Rows != null)?d01Rows:"-");
    	currentPostion = addItemToStringBuilder(strReportLine, strReportItem, 10, currentPostion, leftPad);
    	
    	
    	
    	//Tab between Day 01 and Day 02
    	strReportItem.append("\t\t");
    	currentPostion = addItemToStringBuilder(strReportLine, strReportItem, 2, currentPostion, rightPad);

    	
    	
    	//Day 2 size
    	strReportItem.append((d02Size != null)?d02Size:"-");
    	currentPostion = addItemToStringBuilder(strReportLine, strReportItem, 6, currentPostion, rightPad);
    	
    	//Day 2 size unit
    	strReportItem.append((d02SizeUnit != null)?d02SizeUnit:"-");
    	currentPostion = addItemToStringBuilder(strReportLine, strReportItem, 4, currentPostion, rightPad);
    	

    	//Day 2 Rows
    	strReportItem.append((d02Rows != null)?d02Rows:"-");
    	currentPostion = addItemToStringBuilder(strReportLine, strReportItem, 10, currentPostion, leftPad);
    	
    	
    	
    	//Tab between Day 02 and Diff
    	strReportItem.append("\t\t");
    	currentPostion = addItemToStringBuilder(strReportLine, strReportItem, 2, currentPostion, rightPad);
    	
    	
    	
    	//Diff size
    	if(isFindDiff) {
        	Float diffSize = new Float(d02Size - d01Size);
        	diffSize = org.apache.commons.math3.util.Precision.round(diffSize, 3);
        	strReportItem.append(diffSize);
        	currentPostion = addItemToStringBuilder(strReportLine, strReportItem, 6, currentPostion, rightPad);
        	
        	
        	//Diff size unit
        	strReportItem.append(d02SizeUnit);
        	currentPostion = addItemToStringBuilder(strReportLine, strReportItem, 4, currentPostion, rightPad);
        	

        	//Diff Rows
        	strReportItem.append(d02Rows - d02Rows);
        	currentPostion = addItemToStringBuilder(strReportLine, strReportItem, 10, currentPostion, rightPad);
    	} else {
        	strReportItem.append("-");
        	currentPostion = addItemToStringBuilder(strReportLine, strReportItem, 6, currentPostion, rightPad);
        	
        	
        	//Diff size unit
        	strReportItem.append("-");
        	currentPostion = addItemToStringBuilder(strReportLine, strReportItem, 4, currentPostion, rightPad);
        	

        	//Diff Rows
        	strReportItem.append("-");
        	currentPostion = addItemToStringBuilder(strReportLine, strReportItem, 10, currentPostion, rightPad);
    	}
		
	}
	
	
	private Integer addItemToStringBuilder(StringBuilder strReportLine, StringBuilder strReportItem, int currentFieldSize, Integer currentPostion, boolean isLeftAlligned) {
    	strReportLine.insert(currentPostion, (isLeftAlligned)?StringUtils.leftPad(strReportItem.toString(), currentFieldSize):StringUtils.rightPad(strReportItem.toString(), currentFieldSize));
    	strReportItem.delete(0, strReportItem.length()); // Clear string builder
    	
    	return currentPostion + currentFieldSize;
	}
	

	private void addHeader(List<String> reportOutput) {
		reportOutput.add("################################################################################################################################################################");		
		reportOutput.add("################################################################################################################################################################");		
		reportOutput.add("Table Name\t\t\t\t\t\t\t\tDay-01\t\t\t\tDay-02\t\t\t\tDiff");
		reportOutput.add("\t\t\t\t\t\t\t\t\tSize\t  Rows\t\t\tSize\t  Rows\t\t\tSize\t  Rows");
		reportOutput.add("------------------------------------------------------------\t\t-------- -----------\t\t-------- -----------\t\t-------- -----------");
	}
	

	private Map<String, LogContent>  loadFile(String logFile) throws IOException {
		Map<String, LogContent> retValue = new HashMap<String, LogContent>(); 
		
		BufferedReader inputBufferedReader = null;
    	
		//Initialize StringBuilder and Set initial capacity 128
    	StringBuilder strBuilder = new StringBuilder();
    	strBuilder.ensureCapacity(128);
    	
    	
    	Pattern pattern_01 = Pattern.compile(regExpression_01);
    	Pattern pattern_02 = Pattern.compile(regExpression_02);
    	int lineNumber = 0;
		
		try {
			inputBufferedReader = new BufferedReader(new FileReader(logFile));
			strBuilder.append(inputBufferedReader.readLine());
			while (!(("null".equals(strBuilder.toString()) && strBuilder.length() == 4) || ("".equals(strBuilder.toString()) && strBuilder.length() == 4)) ) {
				lineNumber++;
				log.debug("Line read ["+strBuilder.toString()+"]");
				
				
				Matcher matcher = pattern_01.matcher(strBuilder.toString());
				
				if (matcher.matches()) {
					log.info("lineNumber "+lineNumber+" read. Pattern matched.");
					LogContent content = new LogContent(matcher.group(1), matcher.group(2), Float.parseFloat(matcher.group(3)), matcher.group(4), Long.parseLong(matcher.group(5)));
					log.debug(content);
					retValue.put(matcher.group(1)+"."+matcher.group(2), content); //key = schema_name.table_name
					
				} else {
					Matcher matcher2 = pattern_02.matcher(strBuilder.toString());
					
					if(matcher2.matches()) {
						log.info("lineNumber "+lineNumber+" read. Pattern matched.");
						LogContent content = new LogContent(matcher2.group(1), matcher2.group(2), Float.parseFloat(matcher2.group(3)), matcher2.group(4), Long.parseLong(matcher2.group(5)));
						log.debug(content);
						retValue.put(matcher2.group(1)+"."+matcher2.group(2), content); //key = schema_name.table_name
					} else {
						log.info("lineNumber "+lineNumber+" read. Pattern does not matched.");
						log.info("line ["+strBuilder.toString()+"]");
					}
					
				}
				
				// Clear string builder and read next line
    			strBuilder.delete(0, strBuilder.length());
	    		strBuilder.append(inputBufferedReader.readLine());
				
			}
			
		} catch (Exception e) {
			throw e;
		} finally {
			inputBufferedReader.close();
		}
		
		
		
		return retValue;
	}

	private void readArguments(String[] args) {
		//Get file name from argument.
		logFileDay1 = args[0];
		logFileDay2 = args[1];
		log.debug("logFileDay1=["+logFileDay1+"] & logFileDay2=["+logFileDay2+"]");
		
	}
	
	@Override
	protected void finalize() throws Throwable {
		context.close();
		super.finalize();
	}
	
}
