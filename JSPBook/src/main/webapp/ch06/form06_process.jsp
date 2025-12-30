<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Form</title>
</head>
<body>
	<table border="1">
		<tr>
			<th>요청 파라미터 이름</th>
			<th>요청 파라미터 값</th>
		</tr>
		
		<%
			Enumeration<String> paramNames = request.getParameterNames();
			while (paramNames.hasMoreElements()) {
				String name = paramNames.nextElement();
				out.println("<tr>");
				out.println("<td>" + name + "</td>");
				
				String[] values = request.getParameterValues(name);
				
				out.println("<td>");
				if (values != null) {
					if (values.length == 1) { // 일반 파라미터
						out.println(values[0]);
					} else { // 체크박스 등 다중 값
						for (int i = 0; i < values.length; i++) {
	            out.println(values[i]);
	            if (i < values.length - 1) {
	              out.println(", ");
	            }
          	}
					}
				}
				out.println("</td>");
				out.println("</tr>");
			}
		%>
	</table>
</body>
</html>
