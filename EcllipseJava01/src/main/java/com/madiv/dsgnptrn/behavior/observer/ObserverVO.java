package com.madiv.dsgnptrn.behavior.observer;

/**
 * @author Maddy 
 */

public class ObserverVO {
	// Some bunch of data which each observers might be interested to know about.
	// for test class, just using single String variable called data here.
	private String data;
	
	public ObserverVO(String data) {
		super();
		this.data = data;
	}

	@Override
	public String toString() {
		return "ObserverVO [data=" + data + "]";
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
