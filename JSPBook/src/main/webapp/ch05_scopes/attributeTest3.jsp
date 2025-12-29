<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Attribute Test</title>
</head>
<body>
	<h2>영역과 속성 테스트</h2>
	
	<h3>Application 영역에 저장된 내용들</h3>
	<p>
		이름: <%= application.getAttribute("name") %>
	</p>
	<p>
		아이디: <%= application.getAttribute("id") %>
	</p>
	
	<hr>
	
	<h3>Session 영역에 저장된 내용들</h3>
	
	<%
		Enumeration<String> en = session.getAttributeNames();
		
		while (en.hasMoreElements()) {
			String attributeName = en.nextElement();
			String attributeValue = (String) session.getAttribute(attributeName);
	%>
			<p>
				<%= attributeName %>: <%= attributeValue %>
			</p>
	<%
		}
	%>
</body>
</html>







