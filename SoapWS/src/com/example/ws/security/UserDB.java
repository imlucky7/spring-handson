package com.example.ws.security;

import java.util.HashMap;
import java.util.Map;

public class UserDB {
	
	Map<String, UserInfo> userDB = null;
	
	public void init() {
		userDB = new HashMap<String, UserInfo>();
		
		UserInfo info = new UserInfo();
		info.setUserName("admin");
		info.setEncrypedPSW("adminadmin");
		//info.set
		userDB.put("admin", info);
		
		UserInfo info1 = new UserInfo();
		info1.setUserName("pranab");
		info1.setEncrypedPSW("pranabpranab");
		//info.set
		userDB.put("pranab", info1);
	}
	
	public UserInfo getData(String userName) {
		return userDB.get(userName);
	}
}
