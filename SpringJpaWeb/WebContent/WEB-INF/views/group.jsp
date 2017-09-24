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
    <form action="./editGroup" method="post"">
        <div align="center">
	        <h1>Welcome to Spring4-JPA demo project </h1>
	        <h1>Group INFO </h1>
        	<table border="1">
	        	<tr>
	        		<td>Group ID</td>
					<td>${group.groupId}</td>
	        	</tr>
	        	<tr>
	        		<td>Group Name</td>
					<td><input type="text" name="groupName" value="${group.groupName}"></td>
	        	</tr>
	        	<tr>
	        		<td>Description</td>
					<td><input type="text" name="description" value="${group.description}"></td>
	        	</tr>
        	</table>
        	<br> <br>
        	<input type="hidden" name="groupId" value="${group.groupId}">
        	<input type="submit" value="Update" />
        </div>
    </form>
    <br>
    <form action="./deleteGroup" method="post">
    	<div align="center">
	    	<input type="hidden" name="groupId" value="${group.groupId}">
	    	<input type="submit" value="delete" />
	    </div>
    </form>
    </body>
</html>
