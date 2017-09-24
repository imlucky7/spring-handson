<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
    </head>
    <body>
    	<form action="./updateUser" method="post">
	        <div align="center">
		        <h1>Welcome to Spring4-JPA demo project </h1>
	        	<table border="1">
	        		<tr>
		        		<th>User ID</th>
		        		<th>User Name</th>
			        	<th>User Address</th>
			        	<th>User Mobileno</th>
		        	</tr>
		        	<c:forEach var="user" items="${userList}" varStatus="status">
		        		<tr>
		        			<td><a href="./groupInfo?userId=${user.userId}">${user.userId}</a></td>
		        			<td>${user.userName}</td>
							<td>${user.address}</td>							
							<td>${user.mobileNo}</td>
			        	</tr>
		        	</c:forEach>
	        	</table>
	        </div>
		</form>
    </body>
</html>
