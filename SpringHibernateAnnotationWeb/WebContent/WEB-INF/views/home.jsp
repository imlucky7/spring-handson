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
        <div align="center">
	        <h1>Welcome to Spring4-Hibernate4 demo project </h1>
        	<table border="1">
	        	<th>User Id</th>
	        	<th>Username</th>
	        	<th>Address</th>
	        	<th>Mobile No</th>
	        	<th>Group ID</th>
	        	
				<c:forEach var="user" items="${userList}" varStatus="status">
	        	<tr>
	        		<td><a href="./editUser?userId=${user.userId}">${user.userId}</a></td>
					<td>${user.userName}</td>
					<td>${user.address}</td>
					<td>${user.mobileNo}</td>
					<td><a href="./groupPage?groupId=${user.group.groupId}">${user.group.groupId}</a></td>
							
	        	</tr>
				</c:forEach>	        	
        	</table>
        	
        	<br> <br>
        	
        	<a href="./insertDataPage">Insert Data</a>
        </div>
    </body>
</html>
