<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Form Processing</title>
</head>
<body>
	<form action="form02_process.jsp" name="form" method="post">
		<p>이름: <input type="text" name="name"></p>
		<p>주소: <input type="text" name="address"></p>
		<p>이메일: <input type="text" name="email"></p>
		<button type="submit">전송</button>
	</form>
</body>
</html>