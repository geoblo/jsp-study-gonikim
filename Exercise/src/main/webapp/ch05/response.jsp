<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDateTime"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Implicit Objects</title>
</head>
<body>
	<%
		response.setIntHeader("Refresh", 5);
		
		LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a"); 
    // hh -> 12시간제(01~12), a -> 오전/오후(AM/PM)
   	
		out.println("현재 접속 시각: " + now.format(formatter));
	%>
	
	<p>
		<a href="response_data.jsp">Google 홈페이지로 이동하기</a>
	</p>
</body>
</html>