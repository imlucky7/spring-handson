package com.example.ws.security;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.ws.security.WSPasswordCallback;

public class ServerPasswordCallback implements CallbackHandler {
    
    private UserDB userDB;
   
    public UserDB getUserDB() {
		return userDB;
	}

	public void setUserDB(UserDB userDB) {
		this.userDB = userDB;
	}

	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
	System.out.println("[ServerPasswordCallback] ServerPasswordCallback class invoked..");
	WSPasswordCallback wsPasswordCallback = null;
	String password = null;
	for (int count = 0; count < callbacks.length; count++) {
	    wsPasswordCallback = (WSPasswordCallback) callbacks[count];
	    String identifier = wsPasswordCallback.getIdentifier();
	    System.out.println("[ServerPasswordCallback] identifier = " + identifier);
	    switch (wsPasswordCallback.getUsage()) {
			case WSPasswordCallback.USERNAME_TOKEN:
				try {
					password = userDB.getData(identifier).getEncrypedPSW();
					System.out.println("[ServerPasswordCallback] password = " + password);
				} catch (Exception exp) {
					exp.printStackTrace();
				}
				wsPasswordCallback.setPassword(password);
				break;
			/*case WSPasswordCallback.DECRYPT:
				break;
			case WSPasswordCallback.SIGNATURE:
				break;*/
	    }
	}

    }

}