
package com.example.ws.student;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.example.ws.student package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetAllStudentResponse_QNAME = new QName("http://www.example.com/ws/student/", "getAllStudentResponse");
    private final static QName _GetStudentByRollnoRequest_QNAME = new QName("http://www.example.com/ws/student/", "getStudentByRollnoRequest");
    private final static QName _GetStudentByRollnoResponse_QNAME = new QName("http://www.example.com/ws/student/", "getStudentByRollnoResponse");
    private final static QName _GetAllStudentRequest_QNAME = new QName("http://www.example.com/ws/student/", "getAllStudentRequest");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.example.ws.student
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetAllStudentRequest }
     * 
     */
    public GetAllStudentRequest createGetAllStudentRequest() {
        return new GetAllStudentRequest();
    }

    /**
     * Create an instance of {@link GetStudentByRollnoResponse }
     * 
     */
    public GetStudentByRollnoResponse createGetStudentByRollnoResponse() {
        return new GetStudentByRollnoResponse();
    }

    /**
     * Create an instance of {@link GetStudentByRollnoRequest }
     * 
     */
    public GetStudentByRollnoRequest createGetStudentByRollnoRequest() {
        return new GetStudentByRollnoRequest();
    }

    /**
     * Create an instance of {@link GetAllStudentResponse }
     * 
     */
    public GetAllStudentResponse createGetAllStudentResponse() {
        return new GetAllStudentResponse();
    }

    /**
     * Create an instance of {@link Student }
     * 
     */
    public Student createStudent() {
        return new Student();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllStudentResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.com/ws/student/", name = "getAllStudentResponse")
    public JAXBElement<GetAllStudentResponse> createGetAllStudentResponse(GetAllStudentResponse value) {
        return new JAXBElement<GetAllStudentResponse>(_GetAllStudentResponse_QNAME, GetAllStudentResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetStudentByRollnoRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.com/ws/student/", name = "getStudentByRollnoRequest")
    public JAXBElement<GetStudentByRollnoRequest> createGetStudentByRollnoRequest(GetStudentByRollnoRequest value) {
        return new JAXBElement<GetStudentByRollnoRequest>(_GetStudentByRollnoRequest_QNAME, GetStudentByRollnoRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetStudentByRollnoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.com/ws/student/", name = "getStudentByRollnoResponse")
    public JAXBElement<GetStudentByRollnoResponse> createGetStudentByRollnoResponse(GetStudentByRollnoResponse value) {
        return new JAXBElement<GetStudentByRollnoResponse>(_GetStudentByRollnoResponse_QNAME, GetStudentByRollnoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllStudentRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.com/ws/student/", name = "getAllStudentRequest")
    public JAXBElement<GetAllStudentRequest> createGetAllStudentRequest(GetAllStudentRequest value) {
        return new JAXBElement<GetAllStudentRequest>(_GetAllStudentRequest_QNAME, GetAllStudentRequest.class, null, value);
    }

}
