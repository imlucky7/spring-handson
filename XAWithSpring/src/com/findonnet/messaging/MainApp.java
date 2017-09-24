package com.findonnet.messaging;


import java.io.BufferedReader;
import java.io.InputStreamReader;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;


import com.findonnet.messaging.utils.SpringHelper;

/**
 * Sample Spring based server class which starts up a JVM and waits for
 * console input. Type "shutdown" to shutdown the JVM.
 * 
 *  This class also runs the required use cases depending on what beans
 *  are configured in the spring configuration file.
 *  
 * @author Murali Kosaraju
 *
 */
public class MainApp {
	
    /** String constant to shut down Server */
    private static final String SHUTDOWN_CMD = "shutdown";
	private static final Log log = LogFactory.getLog(MainApp.class);
	public static ApplicationContext springCtx = null;
	
	
    public static String readConsoleInput() {
		String input = "";
		try {
			BufferedReader bin = new BufferedReader(new InputStreamReader(
					System.in));
			System.out.println("-->>> To shutdown the app...please type " +  SHUTDOWN_CMD);
			input = bin.readLine();
			System.out.println("consoleInput = " + input);
		} catch (Exception e) {
			log.error("Exception while reading the console", e);
		}
		return input;
	}

	
	public static void main(String[] args) throws Exception {
		
		MainApp app = new MainApp();
		if(args.length != 2) {
			throw new IllegalArgumentException("\n The first argument should be a spring context file\n and the 2nd argument should be either 'fail' or 'success' !!");
		}
		final String springConfigFile = args[0];
		log.debug("Testing applicatoin for: " + args[1]);
		boolean fail = true;
		if(args[1].equalsIgnoreCase("success")) {
			fail = false;
		}
		
		
		log.info("Using Spring configuraiton file name:" + springConfigFile);
		springCtx = SpringHelper.getApplicationContext(springConfigFile);
		if (springCtx == null) {
			throw new IllegalArgumentException(
					"Spring context not found, please make sure that the "
							+ springConfigFile + "exists in the classpath!!");
		}

		String startupMsg = "Server started successfully. Type " + SHUTDOWN_CMD
				+ " to terminate...\n";
		log.info(startupMsg);
		System.out.print(startupMsg);
		

		
		if (springCtx.containsBean("eventHandler")) {
			EventHandler sender = (EventHandler) springCtx.getBean("eventHandler");
			sender.setSpringContext(springCtx);
			if(springCtx.containsBean("appSenderTemplate")) {
			sender.setJmsTemplate((JmsTemplate) springCtx
					.getBean("appSenderTemplate"));
			}
			try {
				sender.handleEvent(fail);
			} catch (Exception e) {
				log.error("We have simulated a rollback...successfully!!", e);
			}
		}

		if (springCtx.containsBean("listenerContainer")) {
			//make sure we set the fail attribute in the MessageHandler bean
			if(springCtx.containsBean("msgHandler")) {
				MessageHandler handler = (MessageHandler) springCtx.getBean("msgHandler");
				handler.setFail(fail);
			}
			//now let us start the consumer
			DefaultMessageListenerContainer container = (DefaultMessageListenerContainer) springCtx.getBean("listenerContainer");
			container.start();
		}

		String consoleInput = "";

		while (!consoleInput.equalsIgnoreCase(SHUTDOWN_CMD)) {
			consoleInput = readConsoleInput();
		}

		String shuttingDownMsg = "Starting server SHUTDOWN...";
		log.info(shuttingDownMsg);

		app.shutdown();

		log.info("Exiting app...");
		System.exit(0);
	}


	private void shutdown() {
		if(springCtx.containsBean("listenerContainer")) {
			log.info("Trying to stop listenerContainer ...");
			DefaultMessageListenerContainer container = (DefaultMessageListenerContainer) springCtx.getBean("listenerContainer");
			container.shutdown();
			log.info("listenerContainer stopped successfully...");
		}
	}

}
