package com.springwiz.rest;

import java.util.List;

import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.springframework.web.bind.annotation.RequestBody;

@Path("/")
@WebService(name = "salesRecordServer")
public interface ISalesRecordService {

	// url: http://localhost:8080/restserverweb/api/salesrecord/all
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllSalesRecord();
	
	// url: http://localhost:8080/restserverweb/api/salesrecord/upload
	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile(List<Attachment> attachments, @Context HttpServletRequest request);
}
