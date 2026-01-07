<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Database SQL</title>
</head>
<body>
	<!-- JDBC API로 데이터베이스 접속하기 -->
	<%
		Connection conn = null;
	
		String url = "jdbc:mysql://localhost:3306/jspbookdb";
		String user = "root";
		String password = "mysql1234";
		
		try {
			// JDBC 드라이버 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Connection 객체 얻기(JDBC 드라이버 -> DB 연결)
			DriverManager.getConnection(url, user, password);
			out.println("데이터베이스 연결에 성공했습니다.<br>");			
		} catch (Exception e) {
			out.println("데이터베이스 연결에 실패했습니다.<br>");
			out.println("Exception: " + e.getMessage());			
		} finally {
			if (conn != null) conn.close();
			out.println("데이터베이스 연결을 종료합니다.");
		}
	%>
</body>
</html>