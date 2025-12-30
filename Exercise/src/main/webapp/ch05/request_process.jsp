<%@page import="java.net.URLDecoder"%>
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
		/* 폼(입력양식)에서 입력한 한글을 처리하도록 request 내장 객체의 메소드에 문자 인코딩 설정 */
		// request.setCharacterEncoding("utf-8"); // 이것은 post 요청에만 영향
		// 요청 본문(body)에 적용되고, GET 방식은 본문이 아니라 URL의 쿼리 스트링으로 데이터가 전달되기 때문
		
		String value = request.getQueryString();
		out.println("전송된 요청 파라미터: " + value);
	%>
	<br>
	<%
		// 디코딩 된 값으로 출력하고 싶으면
		String decodedValue = URLDecoder.decode(request.getQueryString(), "UTF-8");
	  out.println("전송된 요청 파라미터: " + decodedValue);
	%>
	
	<!-- GET, POST 상관없이 서버로 전달된 요청 파라미터는 getParameter()로 얻음 -->
	<p>아이디: <%= request.getParameter("id") %></p>
	<p>비밀번호: <%= request.getParameter("passwd") %></p>
</body>
</html>