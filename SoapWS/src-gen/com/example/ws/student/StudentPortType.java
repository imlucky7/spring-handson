package com.example.ws.student;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.5.2
 * 2016-10-29T18:30:57.925+05:30
 * Generated source version: 2.5.2
 * 
 */
@WebService(targetNamespace = "http://www.example.com/ws/student/", name = "StudentPortType")
@XmlSeeAlso({ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface StudentPortType {

    @WebResult(name = "getAllStudentResponse", targetNamespace = "http://www.example.com/ws/student/", partName = "parameters")
    @WebMethod(action = "urn:GetAllStudent")
    public GetAllStudentResponse getAllStudent(
        @WebParam(partName = "parameters", name = "getAllStudentRequest", targetNamespace = "http://www.example.com/ws/student/")
        GetAllStudentRequest parameters
    );

    @WebResult(name = "getStudentByRollnoResponse", targetNamespace = "http://www.example.com/ws/student/", partName = "parameters")
    @WebMethod(action = "urn:GetStudentByRollno")
    public GetStudentByRollnoResponse getStudentByRollno(
        @WebParam(partName = "parameters", name = "getStudentByRollnoRequest", targetNamespace = "http://www.example.com/ws/student/")
        GetStudentByRollnoRequest parameters
    );
}
