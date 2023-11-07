package com.madiv.concurrent.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

public class Account {

	public Account() {
		super();
		this.audBalance = 0;
		this.usdBalance = 0;
		this.inrBalance = 0;
	}
	
	
	public Account(int audBalance, int usdBalance, int inrBalance) {
		super();
		this.audBalance = audBalance;
		this.usdBalance = usdBalance;
		this.inrBalance = inrBalance;
	}


	private final ReentrantLock lock = new ReentrantLock();
	
	private int audBalance;
	private int usdBalance;
	private int inrBalance;
	
	
	public int getAudBalance() {
		return audBalance;
	}
	public void setAudBalance(int audBalance) {
		this.audBalance = audBalance;
	}
	public int getUsdBalance() {
		return usdBalance;
	}
	public void setUsdBalance(int usdBalance) {
		this.usdBalance = usdBalance;
	}
	public int getInrBalance() {
		return inrBalance;
	}
	public void setInrBalance(int inrBalance) {
		this.inrBalance = inrBalance;
	}

	
	
	
	public ReentrantLock getLock() {
		return lock;
	}
	
	
	@Override
	public String toString() {
		return "Account [audBalance=" + audBalance + ", usdBalance=" + usdBalance + ", inrBalance=" + inrBalance + "]";
	}
}
