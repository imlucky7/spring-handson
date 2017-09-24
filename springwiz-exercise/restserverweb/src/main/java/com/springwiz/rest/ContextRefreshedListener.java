package com.springwiz.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class ContextRefreshedListener implements ApplicationListener<ContextRefreshedEvent>{

	@Autowired
	SocketConnector socketConnector;
	
	public void setSocketConnector(SocketConnector socketConnector) {
		this.socketConnector = socketConnector;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		socketConnector.init();
	}

}
