<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>배송 정보 처리</title>
</head>
<body>
	<!-- 배송 정보 처리 페이지 -->
	<%
		// 사용자가 입력한 배송 정보를 쿠키에 저장
		// 쿠키는 원래 아스키 문자만 지원
		// 쿠키 값에 한글/공백/특수문자가 들어가면 URLEncoder.encode() 사용하는 것이 안전
		// 읽을 때는 URLDecoder.decode()로 원래 문자열 복원
		Cookie cartId = new Cookie("Shipping_cartId", URLEncoder.encode(request.getParameter("cartId"), "utf-8"));
		Cookie name = new Cookie("Shipping_name", URLEncoder.encode(request.getParameter("name"), "utf-8"));
		Cookie shippingDate = new Cookie("Shipping_shippingDate", URLEncoder.encode(request.getParameter("shippingDate"), "utf-8"));
		Cookie country = new Cookie("Shipping_country",	URLEncoder.encode(request.getParameter("country"), "utf-8"));
		Cookie zipCode = new Cookie("Shipping_zipCode", URLEncoder.encode(request.getParameter("zipCode"), "utf-8"));
		Cookie addressName = new Cookie("Shipping_addressName", URLEncoder.encode(request.getParameter("addressName"), "utf-8"));
		
	
	
	
	
	
	
	
	
	
	
	%>
</body>
</html>