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
		        <h1>Welcome to Spring4-Hibernate4 demo project </h1>
	        	<table border="1">
	        		<tr>
		        		<td>User Name</td>
						<td>${user.userName}</td>
		        	</tr>
		        	<tr>
		        		<td>User Address</td>
						<td><input type="text" name="address" value="${user.address}" /></td>							
		        	</tr>
		        	<tr>
		        		<td>User Mobileno</td>
						<td><input type="text" name="mobileNo" value="${user.mobileNo}" /></td>							
		        	</tr>
		        	<tr>
		        		<td colspan="2"><input type="submit" value="update"/></td>
		        	</tr>
	        	</table>
	        	<input type="hidden" name="userId" value="${user.userId}"/>
	        </div>
		</form>
    </body>
</html>
