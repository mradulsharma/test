package com.madiv.dsgnptrn.behavior.chainofresposibility;

public class Amount {
	private int amount;
	
	public Amount(int amt){
		this.amount=amt;
	}
	
	public int getAmount(){
		return this.amount;
	}
}
