<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Apache CXF JAX-WS Spring based</title>
</head>
<body>
	<h3>Apache CXF JAX-WS: Web Service using Top-Down approach + Adding <br>
	WS-Security policy using UsernameToken profile (using spring framework) </h3><br>
	wsdl URL - http://(host):(port)/(context_path)/services/student/StudentService?wsdl <br>
	<b>http://localhost:8080/SoapWS/services/student/StudentService?wsdl</b> <br>
	List of all services available in : <b>http://localhost:8080/SoapWS/services</b> <br><br>
	
	endpoint is defined in apache-cxf-services.xml file <br><br><br>
	Run the ant target, SoapWS.war will be generated. deploy the war file in server <br>
	
	Web service is security protected..<br>
	Available Userid/Password :: <b>admin/admin & pranab/pranab</b>
</body>
</html>