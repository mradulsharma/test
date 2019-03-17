package com.madiv.dsgnptrn.behavior.chainofresposibility;

public class Dollar10Dispenser implements DispenseChain {

	private DispenseChain chain;
	
	@Override
	public void setNextChain(DispenseChain nextChain) {
		this.chain=nextChain;
	}

	@Override
	public void dispense(Amount amount) {
		if(amount.getAmount() >= 10){
			int num = amount.getAmount()/10;
			int remainder = amount.getAmount() % 10;
			System.out.println("Dispensing "+num+" 10$ note");
			if(remainder !=0) this.chain.dispense(new Amount(remainder));
		}else{
			this.chain.dispense(amount);
		}
	}

}