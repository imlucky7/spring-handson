package com.findonnet.messaging.utils;



import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * Spring helper class.
 * 
 * @author Murali Kosaraju
 *
 */
public class SpringHelper {
	
	private static ApplicationContext context = null;
	private static String[] configurationFiles = null;

	public static ApplicationContext getContext() {
		return context;
	}

	/**
	 *  constructor
	 */
	private SpringHelper() {
		//
	}
	
	/**
	 * <pre>
	 * The method argument is overloaded and takes either of the following as arguments:
	 * 1. A system property key as string
	 * 2. A configuration file name.
	 * </pre>
	 * If it doesn't find the files in the classpath, it throws an exception and aborts.
	 */
	public static synchronized ApplicationContext getApplicationContext(String confProperty) {
		if(context != null) return context;
		String[] confFiles = null;
		if(confProperty != null) {
			if(System.getProperty(confProperty) != null) {
				String property = System.getProperty(confProperty);
				if(property.indexOf(",") > 0) {
					confFiles = property.split(",");
				} else {
					confFiles = new String[] {property};
				}
			} else {
				confFiles = new String[] {confProperty};
			}
			configurationFiles = confFiles;
			context = new ClassPathXmlApplicationContext(confFiles);
			return context;
		} else {
			 throw new IllegalArgumentException("Missing configuration file or property borting!!");
		}
    }
	
	public static String[] getConfiguredFiles() {
		return configurationFiles;
	}

	public static Object getBean(String beanName) {
		if(context == null) {
			throw new IllegalArgumentException("Context not initialized..yet!!");
		}
		return context.getBean(beanName);
	}

	public static void main (String[] args) {
		ApplicationContext ctx = SpringHelper.getApplicationContext("com.findonnet.messaging.conf");
//		ApplicationContext ctx = SpringHelper.getApplicationContext("springContext.xml");
		System.out.println("Ctx->" + ctx);
	}



}
