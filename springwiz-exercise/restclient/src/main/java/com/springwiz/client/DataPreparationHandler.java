package com.springwiz.client;

import java.io.File;
import java.util.concurrent.CountDownLatch;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;

public class DataPreparationHandler implements Runnable {

	private final CountDownLatch latch;

	public DataPreparationHandler(CountDownLatch latch) {
		this.latch = latch;
	}

	@Override
	public void run() {
		String upload_file_path = PropertyUtil.getProperty("excelfile.path");
		File f = new File(upload_file_path);
		try {
			uploadData(f);
			latch.countDown();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void uploadData(File file) throws Exception {
//		HttpEntity httpEntity = null;
//		InputStream inputStream = null;
//		BufferedReader bufferedReader = null;
//		StringBuilder stringBuilder = null;
		try {
			String host = PropertyUtil.getProperty("server.ws.ip");
			String port = PropertyUtil.getProperty("server.ws.port");
			String BASE_URL = "http://"+host+":"+port+"/restserverweb/api/salesrecord/upload";
			
			HttpClient client = new DefaultHttpClient();
			HttpPost postRequest = new HttpPost(BASE_URL);
			postRequest.addHeader("Content-type", "multipart/form-data");

			FileBody fileBody = new FileBody(file,ContentType.MULTIPART_FORM_DATA);
			MultipartEntity multiPartEntity = new MultipartEntity();
			multiPartEntity.addPart("file_type", new StringBody("XLSX"));
			multiPartEntity.addPart("data", fileBody);

			// Set to request body
			postRequest.setEntity(multiPartEntity);

			// Send request
			HttpResponse httpResponse = client.execute(postRequest);
			
			System.out.println("POST method - Response code/message: " + httpResponse.getStatusLine());
			/*httpEntity = httpResponse.getEntity();

			inputStream = httpEntity.getContent();
			bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
			stringBuilder = new StringBuilder();
			String strReadLine = bufferedReader.readLine();

			// iterate to get the data and append in StringBuilder
			while (strReadLine != null) {
				stringBuilder.append(strReadLine);
				strReadLine = bufferedReader.readLine();
				if (strReadLine != null) {
					stringBuilder.append("\n");
				}
			}
			System.out.println(stringBuilder.toString());*/
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
		}
	}
}
