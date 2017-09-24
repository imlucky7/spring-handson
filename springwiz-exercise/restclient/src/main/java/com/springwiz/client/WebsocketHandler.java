package com.springwiz.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.CountDownLatch;

public class WebsocketHandler implements Runnable {
	
	private final CountDownLatch latch;
	
	public WebsocketHandler(CountDownLatch latch) {
		this.latch = latch;
	}
	@Override
	public void run() {
		String host = PropertyUtil.getProperty("server.ws.ip");
		int websocketPort = Integer.parseInt(PropertyUtil.getProperty("server.websocket.port"));
		Socket socket = null;
		try {
			socket = new Socket(host, websocketPort);
			// Send the message to the server
			OutputStream os = socket.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os);
			BufferedWriter bw = new BufferedWriter(osw);

			String sendMessage = "Let me know when data conversion is completed \n";
			bw.write(sendMessage);
			bw.flush();
			System.out.println("Message sent to the server: " + sendMessage);

			// Get the return message from the server
			InputStreamReader isr = new InputStreamReader(socket.getInputStream());
			BufferedReader br = new BufferedReader(isr);
			String message = null;
			while ((message = br.readLine()) != null) {
				System.out.println("Message received from the server : " + message);
				break;
			}
			
			latch.countDown();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	
		
	}

}
