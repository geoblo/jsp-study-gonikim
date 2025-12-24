<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Action Tag</title>
</head>
<body>
	<!-- include 시 동적으로 데이터를 넘길 수 있음 -->
	<h2>include 액션 테스트</h2>
	<jsp:include page="includeTest2.jsp">
		<jsp:param name="name" value="홍길동" />
	</jsp:include>
</body>
</html>