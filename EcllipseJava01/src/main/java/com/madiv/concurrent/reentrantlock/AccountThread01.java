package com.madiv.concurrent.reentrantlock;

public class AccountThread01 extends AbstractAccountThread implements Runnable {
	private Account account; 
	
	public AccountThread01(Account account) {
		super();
		this.account = account;
	}


	@Override
	public void run() {
		try {
			System.out.println(AccountUtil.getCurrentTime() + myName() +  "Started...");
			account.getLock().lock(); // Aquire lock before making any changes to object. 
			System.out.println(AccountUtil.getCurrentTime() + myName() +  "Aquired lock, and sleeping for 5000ms");
			sleep(5000l); // Sleep for 5 seconds after taking lock.
			
			account.setAudBalance(account.getAudBalance() + 100);
			account.setInrBalance(account.getInrBalance() + 200);
			
			System.out.println(AccountUtil.getCurrentTime() + myName() +  "Fin");
		} catch (Exception e) {
			System.out.println(AccountUtil.getCurrentTime() + myName() +  "Some exception occured." + e);
			e.printStackTrace();
		} finally {
			account.getLock().unlock(); // release the lock 
		}
	}

}
