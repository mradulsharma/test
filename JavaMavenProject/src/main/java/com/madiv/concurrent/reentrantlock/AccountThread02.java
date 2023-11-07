package com.madiv.concurrent.reentrantlock;

public class AccountThread02 extends AbstractAccountThread implements Runnable {
	
	private Account account; 

	public AccountThread02(Account account) {
		super();
		this.account = account;
	}

	@Override
	public void run() {
		try {
			System.out.println(AccountUtil.getCurrentTime() + myName() +  "Started...");
			System.out.println(AccountUtil.getCurrentTime() + myName() +  "account.getUsdBalance() = " + account.getUsdBalance());
			

			//System.out.println(AccountUtil.getCurrentTime() + myName() +  "Aquiring lock and continue.");
			//account.getLock().lock();
			
			account.setUsdBalance(account.getUsdBalance() + 250);
			account.setAudBalance(account.getAudBalance() + 50);
			account.setInrBalance(account.getInrBalance() + 150);
			
			System.out.println(AccountUtil.getCurrentTime() + myName() +  "Fin");
		} catch (Exception e) {
			System.out.println(AccountUtil.getCurrentTime() + myName() +  "Some exception occured." + e);
			e.printStackTrace();
		} finally {
			//account.getLock().unlock();
		}
		
	}

}
