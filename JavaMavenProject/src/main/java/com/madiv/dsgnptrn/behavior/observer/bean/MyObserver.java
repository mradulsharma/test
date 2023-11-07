package com.madiv.dsgnptrn.behavior.observer.bean;

import com.madiv.dsgnptrn.behavior.observer.ObserverVO;


/**
 * @author Maddy 
 */
public interface MyObserver {
	public void notify(ObserverVO vo);
	public String getObserverName();
}
