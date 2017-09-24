package com.springwiz.client;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DataRetrieveHandler {
	public void retrieveData() throws Exception {
		String host = PropertyUtil.getProperty("server.ws.ip");
		String port = PropertyUtil.getProperty("server.ws.port");
		String BASE_URL = "http://"+host+":"+port+"/restserverweb/api/salesrecord/all";
		
		HttpClient client = null;
		try {
			client = new DefaultHttpClient();
			HttpGet getRequest = new HttpGet(BASE_URL);
			HttpResponse response = client.execute(getRequest);
			System.out.println("");
			System.out.println("GET method - Response code/message: " + response.getStatusLine());
			System.out.println("");
			if (response != null && response.getStatusLine().getStatusCode() == 200) {
				String jsonStr = EntityUtils.toString(response.getEntity());
				JSONArray jsonArr = new JSONArray(jsonStr);
				displayDataToConsole(jsonArr);
			}
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		finally {
			
		}
	}
	
	private void displayDataToConsole(JSONArray jsonArr) throws JSONException {
		System.out.println("");
		System.out.println("============Sales Record============");
		System.out.println("");
		if(jsonArr == null) {
			System.out.println("No Sales Record found");
		} else {
			for (int i = 0; i < jsonArr.length(); i++) {
				JSONObject jsonobject = jsonArr.getJSONObject(i);
				System.out.println("Product :: " + jsonobject.getString("product"));
				System.out.println("Target :: " + jsonobject.getString("target"));
				System.out.println("-----Weekly Data-----");
				JSONArray arr = jsonobject.getJSONArray("weeklyData");
				if(arr != null) {
					for (int j = 0; j < arr.length(); j++) {
						JSONObject obj = arr.getJSONObject(j);
						System.out.println("Sales :: " + obj.getString("sales"));
						System.out.println("Achievement :: " + obj.getString("achievement"));
						System.out.println("------------------------");
					}
				}
				System.out.println("---------------------");
			}
		}
	}
}
