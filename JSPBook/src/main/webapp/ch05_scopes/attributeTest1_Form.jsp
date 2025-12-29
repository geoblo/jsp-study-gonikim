<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Attribute Test</title>
</head>
<body>
	<!-- application 영역과 session 영역 비교 -->
	<h2>영역과 속성 테스트</h2>
	<form action="attributeTest1.jsp" method="post">
		<h3>Application 영역에 저장할 내용들</h3>
		<p>
			이름: <input type="text" name="name">
		</p>
		<p>
			아이디: <input type="text" name="id">
		</p>
		<button type="submit">전송</button>
	</form>
</body>
</html>
