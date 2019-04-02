package com.madiv.dsgnptrn.behavior.observer;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.madiv.dsgnptrn.behavior.observer.bean.MyObserver;

/**
 * @author Maddy 
 */
public class MySubject {
	protected static final Logger log = Logger.getLogger(MySubject.class);
	private static Map<String, MyObserver> observers = new HashMap<String, MyObserver>();
	
	public MySubject() {
		super();
	}
	
	
	public static void notifyAllObservers(ObserverVO observerVO) {
		
		for (Map.Entry<String, MyObserver> observerEntrySet : observers.entrySet()) {
			if (observerEntrySet.getValue() != null) {
				try {
					observerEntrySet.getValue().notify(observerVO);
				} catch (Throwable e) {
					log.error("Some error occured for observer "+observerEntrySet.getValue().getObserverName()+" : " + e.getMessage(), e);
				} 
			}
		}
		
		
		log.info("MySubject->notifyAllObservers finished, returning..");
		
	}
	
	public static void registerObservers(MyObserver observer) {
		if(!observers.containsKey(observer.getObserverName())) {
			observers.put(observer.getObserverName(), observer);
		}
	}
	
	public static void listObservers() {
		log.info("Registered observers : " + observers);
	}
	
	
}
