package com.madiv.concurrent.reentrantlock;

public class TestClient {
	public static void main(String[] args) {
		TestClient testClient = new TestClient();
		try {
			testClient.startConcurrencyTest();
		} catch (InterruptedException e) {
			System.out.println(AccountUtil.getCurrentTime() + "Something went wrong " + e);
			e.printStackTrace();
		}
	}

	private void startConcurrencyTest() throws InterruptedException {
		Account account = new Account();
		System.out.println(AccountUtil.getCurrentTime() + "account before = " + account);

		Thread t1 = new Thread(new AccountThread01(account),"A1");
		Thread t2 = new Thread(new AccountThread02(account),"A2");
		
		t1.start();
		Thread.sleep(200l);
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			System.out.println(AccountUtil.getCurrentTime() + ":" + "Error occured > " + e);
			e.printStackTrace();
		}
		
		System.out.println(AccountUtil.getCurrentTime() + "account after = " + account);
		System.out.println(AccountUtil.getCurrentTime() + "Fin !!");
		
	}
}
