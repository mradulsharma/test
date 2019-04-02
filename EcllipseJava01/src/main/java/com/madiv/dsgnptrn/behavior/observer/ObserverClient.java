package com.madiv.dsgnptrn.behavior.observer;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

/**
 * 
 * @author Maddy
 *
 * Ref : https://sourcemaking.com/design_patterns/observer/java/1
 * 
 */
public class ObserverClient {
	private AbstractApplicationContext  context = null;
	protected final Logger log = Logger.getLogger(this.getClass());
	
	
	public ObserverClient() {
		this.context = new AnnotationConfigApplicationContext(ObserverConfig.class);
		
		
		Logger rootLogger = Logger.getRootLogger();
		rootLogger.setLevel(Level.DEBUG);
		 
		//Define log pattern layout
		PatternLayout layout = new PatternLayout("%d{ISO8601} %c [%-5p] %x - %m%n");
		 
		//Add console appender to root logger
		rootLogger.addAppender(new ConsoleAppender(layout));		
	}
	
	
	public static void main(String[] args) throws Exception {
		
		ObserverClient client = new ObserverClient();
		client.doIt();

	}
	
	
	private void doIt() throws Exception {
		
		log.info("Start : Performing some operations in doIt() method");
		
		//Creating VO
		ObserverVO observerVO = new ObserverVO("Test Date for MyObservers");
		log.info("Notifying all observers.");
		MySubject.notifyAllObservers(observerVO);
		log.info("Notified.");
		
		log.info("End : Performing operations in doIt() method...");
		
	}


	@Override
	protected void finalize() throws Throwable {
		context.close();
		super.finalize();
	}
	
}
