package com.springwiz.rest;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.activation.DataHandler;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.springwiz.service.MessageQueue;
import com.springwiz.service.SalesRecordService;
import com.springwiz.vo.SalesRecordVO;

@Service("salesReportServices")
public class SalesRecordServiceRest implements ISalesRecordService {
	
	@Autowired
	private SalesRecordService salesRecordService;
	
	@Autowired
	private MessageQueue messageQueue;
	
	public MessageQueue getMessageQueue() {
		return messageQueue;
	}

	public void setMessageQueue(MessageQueue messageQueue) {
		this.messageQueue = messageQueue;
	}
	
	public SalesRecordService getSalesRecordService() {
		return salesRecordService;
	}

	public void setSalesRecordService(SalesRecordService salesRecordService) {
		this.salesRecordService = salesRecordService;
	}



	@Value("${csvfile.path}")
	private String csvPath;
	
	@Override
	public Response getAllSalesRecord() {
		try {
			List<SalesRecordVO> records = salesRecordService.getAllSalesRecord(csvPath);
			return Response.ok(records).header("Access-Control-Allow-Origin", "*").build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).header("Access-Control-Allow-Origin", "*").build();
		}
	}

	@Override
	public Response uploadFile(List<Attachment> attachments, HttpServletRequest request) {

		System.out.println("##### csvPath :: " + csvPath);
		System.out.println("##### attachments :: " + attachments.size());
		DataHandler dataHandler = null;
		MultivaluedMap<String, String> multivaluedMap = null;
		String fileName = null;
		InputStream inputStream = null;
		String response = "";
		try {
			for (Attachment attachment : attachments) {
				dataHandler = attachment.getDataHandler();

				multivaluedMap = attachment.getHeaders();
				fileName = getFileName(multivaluedMap);
				System.out.println("### filename :: " + fileName);
				if (null != fileName && !"".equals(fileName.trim()) && !"Unknown".equalsIgnoreCase(fileName.trim())) {
					inputStream = dataHandler.getInputStream();
					response = salesRecordService.prepareSalesRecord(inputStream, csvPath);
					messageQueue.setMessage(Boolean.TRUE);
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage())
					.header("Access-Control-Allow-Origin", "*").build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage())
					.header("Access-Control-Allow-Origin", "*").build();
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return Response.ok(response).header("Access-Control-Allow-Origin", "*").build();
	}
	
	private String getFileName(MultivaluedMap<String, String> multivaluedMap) {
		 
        String[] contentDisposition = multivaluedMap.getFirst("Content-Disposition").split(";");
        for (String filename : contentDisposition) {
 
            if ((filename.trim().startsWith("filename"))) {
                String[] name = filename.split("=");
                String exactFileName = name[1].trim().replaceAll("\"", "");
                return exactFileName;
            }
        }
        return "Unknown";
    }
}
