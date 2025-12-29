<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Action Tag</title>
</head>
<body>
	<jsp:useBean id="gugudan" class="ch04.com.dao.GuGuDan" scope="request" />
	
	<h4>구구단 출력하기</h4>
	<%
		int num = 5;
		
		for (int i = 1; i <= 9; i++) {
			out.print(num +" * "+ i+ " = " + gugudan.process(num, i) + "<br>");
		}
	%>
</body>
</html>