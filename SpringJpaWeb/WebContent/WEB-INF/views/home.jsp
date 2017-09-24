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
	        <h1>Welcome to Spring4-JPA demo project </h1>
        	<table border="1">
        		<tr>
		        	<th>Doc Id</th>
		        	<th>Doc Title</th>
		        	<th>Crate By</th>
		        	<th>Create Date</th>
		        </tr>
	        	
				<c:forEach var="doc" items="${listDocs}" varStatus="status">
		        	<tr>
		        		<td><a href="./userList?docId=${doc.documentId}">${doc.documentId}</a></td>
						<td>${doc.title}</td>
						<td>${doc.crateBy}</td>
						<td>${doc.createDate}</td>
		        	</tr>
				</c:forEach>	        	
        	</table>

        </div>
    </body>
</html>
