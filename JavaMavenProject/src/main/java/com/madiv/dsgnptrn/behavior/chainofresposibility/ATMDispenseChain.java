package com.madiv.dsgnptrn.behavior.chainofresposibility;

public class ATMDispenseChain {
	private DispenseChain c1;

	public ATMDispenseChain() {
		// initialize the chain
		this.c1 = new Dollar50Dispenser();

		// set the chain of responsibility
		DispenseChain c2 = new Dollar20Dispenser();
		c1.setNextChain(c2);

		
		DispenseChain c3 = new Dollar10Dispenser();
		c2.setNextChain(c3);
	}
	
	public void processChain(Amount amount) {
		this.c1.dispense(amount);
	}
}
