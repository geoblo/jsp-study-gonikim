<%@ page import="java.util.Enumeration" %> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Form Processing</title>
</head>
<body>
	<%  
    Enumeration<String> paramNames = request.getParameterNames();
    while (paramNames.hasMoreElements()) {
        String name = paramNames.nextElement();
        String value = request.getParameter(name);
	%>
  	<p><%= name %>: <%= value %></p>
	<%
    }
	%>
</body>
</html>