package com.madiv.dsgnptrn.behavior.chainofresposibility;

public class Dollar20Dispenser implements DispenseChain{

	private DispenseChain chain;
	
	@Override
	public void setNextChain(DispenseChain nextChain) {
		this.chain=nextChain;
	}

	@Override
	public void dispense(Amount amount) {
		if(amount.getAmount() >= 20){
			int num = amount.getAmount()/20;
			int remainder = amount.getAmount() % 20;
			System.out.println("Dispensing "+num+" 20$ note");
			if(remainder !=0) this.chain.dispense(new Amount(remainder));
		}else{
			this.chain.dispense(amount);
		}
	}

}