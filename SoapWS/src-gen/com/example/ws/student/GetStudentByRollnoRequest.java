
package com.example.ws.student;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getStudentByRollnoRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getStudentByRollnoRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="rollNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getStudentByRollnoRequest", propOrder = {
    "rollNo"
})
public class GetStudentByRollnoRequest {

    @XmlElement(required = true)
    protected String rollNo;

    /**
     * Gets the value of the rollNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRollNo() {
        return rollNo;
    }

    /**
     * Sets the value of the rollNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRollNo(String value) {
        this.rollNo = value;
    }

}
