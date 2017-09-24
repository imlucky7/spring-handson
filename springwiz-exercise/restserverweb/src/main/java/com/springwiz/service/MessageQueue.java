package com.springwiz.service;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.springframework.stereotype.Component;

@Component
public class MessageQueue {
	private BlockingQueue<Boolean> messageQueue = new ArrayBlockingQueue<Boolean>(1);
	
	public Boolean getMessage() throws InterruptedException {
		return messageQueue.take();
	}
	
	public void setMessage(Boolean bool) throws InterruptedException {
		messageQueue.put(bool);
	}
}
