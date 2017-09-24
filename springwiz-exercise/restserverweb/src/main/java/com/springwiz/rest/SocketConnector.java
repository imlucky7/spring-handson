package com.springwiz.rest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.springwiz.service.MessageQueue;

@Component
public class SocketConnector {
	
	@Autowired
	private MessageQueue messageQueue;
	
	public MessageQueue getMessageQueue() {
		return messageQueue;
	}

	public void setMessageQueue(MessageQueue messageQueue) {
		this.messageQueue = messageQueue;
	}

	@Value("${websocket.port}")
	private String webSocketPort;

	public void init() {
		SocketConnectorServer server = new SocketConnectorServer(Integer.valueOf(webSocketPort));
		server.start();
	}

	public class SocketConnectorServer extends Thread {
		int port;

		public SocketConnectorServer(int port) {
			this.port = port;
		}

		@Override
		public void run() {
			ServerSocket server = null;
			try {
				server = new ServerSocket(port);
				System.out.println("websocket server started and listening to port "+webSocketPort);
				while (true) {
					Socket clientSocket = server.accept();
					ConnectionHandler handler = new ConnectionHandler(clientSocket);
					(new Thread(handler)).start();
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					server.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	class ConnectionHandler implements Runnable {
		Socket clientSocket;

		public ConnectionHandler(Socket clientSocket) {
			this.clientSocket = clientSocket;
		}

		@Override
		public void run() {
			try {
				
				InputStream is = clientSocket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String msg = br.readLine();
                System.out.println("########  Message received from client is::: "+msg);
				
				//messageQueue.getMessage() - is a blocking call, will wait until data is present in queue
				if(messageQueue.getMessage() == Boolean.TRUE) {
					OutputStreamWriter osw = new OutputStreamWriter(clientSocket.getOutputStream());
					BufferedWriter bw = new BufferedWriter(osw);
					bw.write("Excel to CSV conversion is completed.");
					System.out.println("######### Message sent to the client");
					bw.flush();
				}

			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			finally {
				try {
					closeConnection();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		public void closeConnection() throws IOException {
			if (clientSocket != null)
				clientSocket.close();
		}
	}
}
