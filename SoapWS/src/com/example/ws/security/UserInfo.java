package com.example.ws.security;

import java.util.ArrayList;
import java.util.List;

public class UserInfo {
	private String userName;
	private String encrypedPSW;
	private List<String> service = new ArrayList<String>();
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEncrypedPSW() {
		return encrypedPSW;
	}
	public void setEncrypedPSW(String encrypedPSW) {
		this.encrypedPSW = encrypedPSW;
	}
	public List<String> getService() {
		return service;
	}
	public void setService(List<String> service) {
		this.service = service;
	}
	
	
}
