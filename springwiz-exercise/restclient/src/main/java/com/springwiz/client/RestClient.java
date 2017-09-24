package com.springwiz.client;

import java.util.concurrent.CountDownLatch;

public class RestClient {

	public static void main(String[] args) throws Exception {
		PropertyUtil.init();
		final CountDownLatch latch = new CountDownLatch(2);
		
		new Thread(new WebsocketHandler(latch)).start();
		new Thread(new DataPreparationHandler(latch)).start();
		
		try {
			latch.await();
			System.out.println("Preparing to fetch sales records...");
			DataRetrieveHandler dataRetrieve = new DataRetrieveHandler();
			dataRetrieve.retrieveData();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}
