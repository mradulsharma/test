package com.madiv.dsgnptrn.behavior.observer.bean;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.madiv.dsgnptrn.behavior.observer.MySubject;
import com.madiv.dsgnptrn.behavior.observer.ObserverVO;

@Component
public class ObserverTwo implements MyObserver {
	protected final Logger log = Logger.getLogger(this.getClass());
	
	private String observerName;

	@Override
	public void notify(ObserverVO vo) {
		log.info("===========================================");
		log.info("Got notified : " + (this.getClass().getName()));
		log.info("vo : " + vo);
		log.info("Dividing by zero");
		int i = (689 - 47) / (8-8);
		
	}
	
	@PostConstruct
	public void init() {
		this.observerName = "ObserverTwo";

		log.info(this.getClass().getName()+" initialized !!");
		MySubject.registerObservers(this);

	}

	@Override
	public String getObserverName() {
		return this.observerName;
	}

}
