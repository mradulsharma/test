package com.madiv.dsgnptrn.behavior.chainofresposibility;

public class Dollar50Dispenser implements DispenseChain {

	private DispenseChain chain;
	
	@Override
	public void setNextChain(DispenseChain nextChain) {
		this.chain=nextChain;
	}

	@Override
	public void dispense(Amount amount) {
		if(amount.getAmount() >= 50){
			int num = amount.getAmount()/50;
			int remainder = amount.getAmount() % 50;
			System.out.println("Dispensing "+num+" 50$ note");
			if(remainder !=0) this.chain.dispense(new Amount(remainder));
		}else{
			this.chain.dispense(amount);
		}
	}

}