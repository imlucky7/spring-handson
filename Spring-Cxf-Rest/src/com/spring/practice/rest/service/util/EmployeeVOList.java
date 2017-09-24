package com.spring.practice.rest.service.util;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "EmployeeVOList")
public class EmployeeVOList {

	private List<EmployeeVO> employees;

	public List<EmployeeVO> getEmployees() {
		if(employees == null)
			employees = new ArrayList<>();
		return employees;
	}

	public void setEmployees(List<EmployeeVO> employees) {
		this.employees = employees;
	}
	
}
