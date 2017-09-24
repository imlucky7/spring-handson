package com.spring.practice.rest.client;

import java.io.StringWriter;
import java.util.Arrays;

import javax.xml.transform.stream.StreamResult;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.client.RestTemplate;
import org.springframework.xml.transform.StringSource;

public class RestClient {
	
	private static ApplicationContext context = new ClassPathXmlApplicationContext("rest-context.xml");
	
	public static void main(String[] args) {
		
		RestClient restClient = new RestClient();
		

		RestTemplate restTemplate = (RestTemplate) context.getBean("restTemplate");
		restClient.getAllEmployee(restTemplate);
		restClient.getEmployeeById(restTemplate);
		restClient.addEmployee(restTemplate);
	}
	
	/*
	 * Response in json format
	 */
	private void getAllEmployee(RestTemplate restTemplate) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON)); // Set the response type as JSON
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED); // set the request type
		
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		
		ResponseEntity<String> res = restTemplate
		.exchange("http://localhost:8080/SpringCxfRest/api/employee/service/getallemployee",
				HttpMethod.GET, entity, String.class);
		
		System.out.println(res.getBody());
	}
	
	/*
	 * Response in xml format
	 */
	private void getEmployeeById(RestTemplate restTemplate) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_XML)); // Set the response type as XML
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED); // set the request type
		
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		
		ResponseEntity<String> res = restTemplate
		.exchange("http://localhost:8080/SpringCxfRest/api/employee/service/getemployeebyid/12",
				HttpMethod.POST, entity, String.class);
		
		String xmlStr = res.getBody();
		System.out.println(xmlStr);
		
		/*
		 * Xml to object conversion
		 */
		
		Jaxb2Marshaller jaxb2Marshaller = (Jaxb2Marshaller)context.getBean("jaxbMarshallerBean");
		EmployeeVO emp = (EmployeeVO) jaxb2Marshaller.unmarshal(new StringSource(xmlStr));
		System.out.println("Emp name: "+emp.getName());
	}
	
	private void addEmployee(RestTemplate restTemplate) {
		
		Jaxb2Marshaller jaxb2Marshaller = (Jaxb2Marshaller)context.getBean("jaxbMarshallerBean");
		EmployeeVO emp = new EmployeeVO();
		emp.setId("100");
		emp.setAddress("test address");
		emp.setName("pranab");
		
		StringWriter out = new StringWriter();
		jaxb2Marshaller.marshal(emp, new StreamResult(out));
		String reqBody = out.toString(); System.out.println("reqBody - "+reqBody);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_FORM_URLENCODED)); // Set the response type as String
		headers.setContentType(MediaType.APPLICATION_XML); // set the request type as XML Media Type
		
		HttpEntity<String> entity = new HttpEntity<String>(reqBody, headers);
		
		ResponseEntity<String> res = restTemplate
		.exchange("http://localhost:8080/SpringCxfRest/api/employee/service/addemployee",
				HttpMethod.POST, entity, String.class);
		
		String output = res.getBody();
		System.out.println(output);
	}
}
