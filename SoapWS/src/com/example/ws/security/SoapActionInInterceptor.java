package com.example.ws.security;

import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;

import org.apache.cxf.binding.soap.SoapFault;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.binding.soap.interceptor.EndpointSelectionInterceptor;
import org.apache.cxf.binding.soap.interceptor.ReadHeadersInterceptor;
import org.apache.cxf.headers.Header;
import org.apache.cxf.helpers.CastUtils;
import org.apache.cxf.helpers.DOMUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.ws.security.policy.SPConstants;
import org.apache.ws.security.WSConstants;
import org.apache.ws.security.WSSecurityException;
import org.apache.ws.security.message.token.UsernameToken;
import org.w3c.dom.Element;

public class SoapActionInInterceptor extends AbstractSoapInterceptor {

	private UserDB userDB;
	   
    public UserDB getUserDB() {
		return userDB;
	}

	public void setUserDB(UserDB userDB) {
		this.userDB = userDB;
	}
	
	/**
	 * Constructor
	 */
	public SoapActionInInterceptor() {
		super(Phase.READ);
		addAfter(ReadHeadersInterceptor.class.getName());
		addAfter(EndpointSelectionInterceptor.class.getName());
	}

	/**
	 * This method handles the specified SOAP message
	 * 
	 * @param message instance of {@link SoapMessage}
	 */
	public void handleMessage(SoapMessage message) throws Fault {
		System.out.println("[SoapActionInInterceptor] SoapActionInInterceptor class invoked..");
		String action = null;
		UsernameToken usernameToken = null;

		try {

			action = getSoapAction(message);
			System.out.println("[SoapActionInInterceptor] Action : [" + action + "]");
			usernameToken = getUsernameToken(message);
			if (null == usernameToken) {
				throw new SoapFault("UserName/Password is missing ::", message.getVersion().getSender());
			}
			else {
				UserInfo userInfo = userDB.getData(usernameToken.getName());
				if(userInfo == null){
					System.out.println("[SoapActionInInterceptor] User [" + usernameToken.getName() + "] does not exist in UserDB");
					throw new SoapFault("User [" + usernameToken.getName() + "] does not exist in UserDB", message.getVersion().getSender());
				}
				String userPassKey = userInfo.getEncrypedPSW();
				String pswEncpyt = getHashValue(usernameToken.getPassword(), userPassKey);
				if (null != pswEncpyt) {
					usernameToken.setPassword(userPassKey);
				}
			}
		} catch (WSSecurityException exception) {
			throw new Fault(exception);
		} catch (Exception exception) {
			throw new Fault(exception);
		}
	}
	/**
	 * Check if encrypted password matched with DB password
	 * @param origPsw
	 * @param userPassKey
	 * @return
	 */
	private String getHashValue(String origPsw, String userPassKey) {
		if(origPsw != null ) {
			String pswEncpyt = origPsw + origPsw;
			if(pswEncpyt.equals(userPassKey))
				return pswEncpyt;
		}
		
		return null;
	}
	/**
	 * method is responsible for extracting the header from the SOAP message and 
	 * getting the UsernameToken from it.
	 * 
	 * @param message instance of {@link SoapMessage}
	 * 
	 * @return usernameToken instance of {@link UsernameToken}
	 * 
	 * @throws WSSecurityException
	 */
	private UsernameToken getUsernameToken(SoapMessage message) throws WSSecurityException {
		UsernameToken usernameToken = null;
		Header header = findSecurityHeader(message);
		if (null != header) {
			Element element = (Element) header.getObject();
			Element child = DOMUtils.getFirstElement(element);
			while (child != null) {
				if (SPConstants.USERNAME_TOKEN.equals(child.getLocalName())) {
					usernameToken = new UsernameToken(child);
					System.out.println("Username is [" + usernameToken.getName() + "]");
					break;
				}
				child = DOMUtils.getNextElement(child);
			}
		}
		return usernameToken;
	}

	/**
	 * Method retrieves the SOAP action from the SOAP message
	 * 
	 * @param message instance of {@link SoapMessage}
	 * @return action instance of {@link String}
	 */
	private String getSoapAction(SoapMessage message) {
		String action = null;
		Map<String, List<String>> headers = CastUtils.cast((Map) message.get(Message.PROTOCOL_HEADERS));
		if (headers != null) {
			List<String> sa = headers.get("SOAPAction");
			if (sa != null && sa.size() > 0) {
				action = sa.get(0);

			}
			if (action.startsWith("\"")) {
				action = action.substring(1, action.length() - 1);
			}
		}
		return action;
	}

	/**
	 * This method fetches the header from the SOAP message.
	 * 
	 * @param message instance of {@link SoapMessage}
	 * @return securityHeader instance of {@link Header}
	 */
	private Header findSecurityHeader(SoapMessage message) {
		Header securityHeader = null;

		for (Header header : message.getHeaders()) {
			System.out.println("Header is [" + header.toString() + "]");
			QName qName = header.getName();
			System.out.println("Qname is [" + qName.toString() + "]");
			if (qName.getLocalPart().equals("Security")
					&& (qName.getNamespaceURI().equals(WSConstants.WSSE_NS) || qName.getNamespaceURI().equals(
							WSConstants.WSSE11_NS))) {
				securityHeader = header;
				break;
			}
		}
		return securityHeader;
	}

}
