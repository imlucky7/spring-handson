package com.example.ws.student;

import java.util.List;

import javax.jws.WebService;

@WebService(
        serviceName = "StudentService",
        portName = "StudentPort",
        targetNamespace = "http://www.example.com/ws/student/",
        endpointInterface = "com.example.ws.student.StudentPortType")
public class StudentPortTypeImpl implements StudentPortType {

	@Override
	public GetAllStudentResponse getAllStudent(GetAllStudentRequest parameters) {
		
		GetAllStudentResponse response = new GetAllStudentResponse();
		List<Student> studentList = response.getStudent();
		
		Student student1 = new Student();
		student1.setRollNo(1);
		student1.setName("Student1");
		student1.setPrimarySubject("Bengali");
		studentList.add(student1);
		
		Student student2 = new Student();
		student2.setRollNo(2);
		student2.setName("Student2");
		student2.setPrimarySubject("English");
		studentList.add(student2);
		
		Student student3 = new Student();
		student3.setRollNo(3);
		student3.setName("Student3");
		student3.setPrimarySubject("Math");
		studentList.add(student3);
		
		return response;
	}

	@Override
	public GetStudentByRollnoResponse getStudentByRollno(GetStudentByRollnoRequest parameters) {
		
		int rollNo = Integer.valueOf(parameters.getRollNo());
		
		GetStudentByRollnoResponse response = new GetStudentByRollnoResponse();
		Student s = new Student();
		s.setRollNo(rollNo);
		s.setName("Student_Name");
		s.setPrimarySubject("Math");
		response.setStudent(s);
		
		return response;
	}

}
