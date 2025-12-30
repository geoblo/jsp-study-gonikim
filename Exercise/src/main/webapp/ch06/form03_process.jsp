<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Implicit Objects</title>
</head>
<body>
	<h4>선택한 과일</h4>
	<%
		String message = " ";
		String[] fruits = request.getParameterValues("fruits");
		if (fruits != null) {
			for (String fruit : fruits) {
				message += fruit + " ";
			}
		}
	%>
	<%= message %>
</body>
</html>