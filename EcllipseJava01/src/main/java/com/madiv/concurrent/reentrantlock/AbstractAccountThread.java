package com.madiv.concurrent.reentrantlock;

public class AbstractAccountThread {
	protected String myName() {
		return Thread.currentThread().getName() + " : ";
	}
	
	protected void sleep(long timeInMiliSecond) throws InterruptedException {
		Thread.sleep(timeInMiliSecond);
	}
}
