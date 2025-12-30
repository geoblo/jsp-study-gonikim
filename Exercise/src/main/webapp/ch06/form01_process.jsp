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
		request.setCharacterEncoding("UTF-8");
		
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String email = request.getParameter("email");
		
		/* StringBuffer buffer = new StringBuffer();
		buffer.append("아이디 : " + name);
	  buffer.append("<br/>");
	  buffer.append("주소 : " + address);
	  buffer.append("<br/>");
	  buffer.append("이메일 : " + email);
	  buffer.append("<br/>"); */  
	%>    
  <%-- <%= buffer.toString() %> --%>
  
  <p>아이디 : <%= name %></p>
	<p>주소 : <%= address %></p>
	<p>이메일 : <%= email %></p>
	
	<p>아이디 : ${param.name}</p>
	<p>주소 : ${param.address}</p>
	<p>이메일 : ${param.email}</p>
</body>
</html>