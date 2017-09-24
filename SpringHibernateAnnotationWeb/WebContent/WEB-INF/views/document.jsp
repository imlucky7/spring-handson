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
    	<form action="./updateDoc" method="post">
	        <div align="center">
		        <h1>Welcome to Spring4-Hibernate4 demo project </h1>
	        	<table border="1">
		        	<tr>
		        		<td>Document Title</td>
						<td><input type="text" name="title" value="${document.title}" /></td>							
		        	</tr>
		        	<tr>
		        		<td>Document Create By</td>
						<td><input type="text" name="crateBy" value="${document.crateBy}" /></td>							
		        	</tr>
		        	<tr>
		        		<td>Document Create Date</td>
						<td>${document.createDate}</td>
		        	</tr>
		        	<tr>
		        		<td colspan="2"><input type="submit" value="update"/></td>
		        	</tr>
	        	</table>
	        	<input type="hidden" name="documentId" value="${document.documentId}"/>
	        </div>
		</form>
    </body>
</html>
