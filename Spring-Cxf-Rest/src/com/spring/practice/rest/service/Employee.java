package com.spring.practice.rest.service;

import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.web.bind.annotation.RequestBody;

@Path("/")
@WebService(name="employeeServer")
public interface Employee {
	//url: http://localhost:8080/SpringCxfRest/api/employee/service/getallemployee
	@GET
	@Path("/service/getallemployee")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllEmployee(@Context HttpHeaders headers,
			@RequestBody String body);
	
	//url: http://localhost:8080/SpringCxfRest/api/employee/service/getemployeebyid/{empId}
	@POST
	@Path("/service/getemployeebyid/{empId}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_XML)
	public Response getEmployeeById(@Context HttpHeaders headers,
			@RequestBody String body, @PathParam("empId") String empId);
	
	//url: http://localhost:8080/SpringCxfRest/api/employee/service/addemployee
	@POST
	@Path("/service/addemployee")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_FORM_URLENCODED)
	public Response getEmployeeById(@Context HttpHeaders headers,
			@RequestBody String body);
}
