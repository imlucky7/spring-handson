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
    <form action="./insertData">
        <div align="center">
	        <h1>Welcome to Spring4-Hibernate4 demo project </h1>
        	<table border="1">
	        	<tr>
	        		<td>Group ID</td>
					<td><input type="text" name="groupId" id="groupId"/></td>
	        	</tr>
	        	<tr>
	        		<td>User ID</td>
					<td><input type="text" name="userId" id="userId"/></td>
	        	</tr>
	        	<tr>
	        		<td>Document ID</td>
					<td><input type="text" name="docId" id="docId"/></td>
	        	</tr>
	        	<tr>
	        		<td colspan="2"><input type="submit" value="insert Record"/></td>
	        	</tr>
        	</table>
        </div>
    </form>
    </body>
</html>
