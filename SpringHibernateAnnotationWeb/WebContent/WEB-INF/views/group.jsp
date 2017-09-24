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
        		<tr>
		        	<th>Doc ID</th>
		        	<th>Doc Title</th>
		        	<th>Doc Create By</th>
		        	<th>Doc Create Date</th>
		        	<th>Group ID</th>
	        	</tr>
				<c:forEach var="doc" items="${group.documents}" varStatus="status">
	        	<tr>
	        		<td><a href="./editDoc?docId=${doc.documentId}">${doc.documentId}</a></td>
					<td>${doc.title}</td>
					<td>${doc.crateBy}</td>
					<td>${doc.createDate}</td>
					<td>${group.groupId}</td>
							
	        	</tr>
				</c:forEach>	        	
        	</table>
        </div>
    </body>
</html>
