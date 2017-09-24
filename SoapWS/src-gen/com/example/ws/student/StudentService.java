package com.example.ws.student;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.5.2
 * 2016-10-29T18:30:57.978+05:30
 * Generated source version: 2.5.2
 * 
 */
@WebServiceClient(name = "StudentService", 
                  wsdlLocation = "file:/C:/workstation/Pranab/eclipse-ws/Handson/lunaws/SoapWS/resources/wsdl/student.wsdl",
                  targetNamespace = "http://www.example.com/ws/student/") 
public class StudentService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://www.example.com/ws/student/", "StudentService");
    public final static QName StudentPort = new QName("http://www.example.com/ws/student/", "StudentPort");
    static {
        URL url = null;
        try {
            url = new URL("file:/C:/workstation/Pranab/eclipse-ws/Handson/lunaws/SoapWS/resources/wsdl/student.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(StudentService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "file:/C:/workstation/Pranab/eclipse-ws/Handson/lunaws/SoapWS/resources/wsdl/student.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public StudentService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public StudentService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public StudentService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public StudentService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public StudentService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public StudentService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     *
     * @return
     *     returns StudentPortType
     */
    @WebEndpoint(name = "StudentPort")
    public StudentPortType getStudentPort() {
        return super.getPort(StudentPort, StudentPortType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns StudentPortType
     */
    @WebEndpoint(name = "StudentPort")
    public StudentPortType getStudentPort(WebServiceFeature... features) {
        return super.getPort(StudentPort, StudentPortType.class, features);
    }

}
