package com.madiv.dsgnptrn.behavior.chainofresposibility;

public interface DispenseChain {

	void setNextChain(DispenseChain nextChain);
	
	void dispense(Amount amount);
}