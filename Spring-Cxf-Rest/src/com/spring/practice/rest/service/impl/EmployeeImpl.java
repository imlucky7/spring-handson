package com.spring.practice.rest.service.impl;

import java.util.List;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Service;

import com.spring.practice.rest.service.Employee;
import com.spring.practice.rest.service.util.EmployeeVO;
import com.spring.practice.rest.service.util.EmployeeVOList;

@Service("employeeServices")
public class EmployeeImpl implements Employee {
	
	@Override
	public Response getAllEmployee(HttpHeaders headers, String body) {
//		JSONObject response = new JSONObject();
		EmployeeVOList empLVOist = new EmployeeVOList();
		List<EmployeeVO> empList = empLVOist.getEmployees();
//		EmployeeVO emp = new EmployeeVO();
		try {
			/*JSONObject emp1 = new JSONObject();
			emp1.put("name", "emp1"); emp1.put("id", "1"); emp1.put("address", "add1");
			
			JSONObject emp2 = new JSONObject();
			emp2.put("name", "emp2"); emp2.put("id", "2"); emp2.put("address", "add2");
			
			
			JSONArray jArr = new JSONArray(); jArr.put(emp1); jArr.put(emp2);
			response.put("EmpDetail", jArr);*/
			
			
			EmployeeVO emp = new EmployeeVO();
			emp.setId("1"); emp.setName("pranab"); emp.setAddress("kolkata");
			empList.add(emp);
			
			EmployeeVO emp1 = new EmployeeVO();
			emp1.setId("2"); emp1.setName("dutta"); emp1.setAddress("howrah");
			empList.add(emp1);
			
		} /*catch (JSONException e) {
			e.printStackTrace();
		} */ catch (Exception e) {
			e.printStackTrace();
		}
		/*return Response.ok(response.toString())
			.header("Access-Control-Allow-Origin","*")
			.build();*/
		
		return Response.ok(empLVOist)/*.location(uriInfo.getAbsolutePath())*/
				.header("Access-Control-Allow-Origin", "*").build();
	}
	
	@Override
	public Response getEmployeeById(HttpHeaders headers, String body, String empId) {
		System.out.println("empId: "+empId);
		
		EmployeeVO emp = new EmployeeVO();
		emp.setId(empId);
		emp.setAddress("test address");
		emp.setName("pranab");
		
		return Response.ok(emp)
		.header("Access-Control-Allow-Origin","*")
		.build();
	}
	
	@Override
	public Response getEmployeeById(HttpHeaders headers, String body) {
		System.out.println(body);
		return Response.ok("information saved successfully with empid "+333)
		.header("Access-Control-Allow-Origin","*")
		.build();
	}
}
