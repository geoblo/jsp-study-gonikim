<%@page import="java.sql.SQLException"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.BookRepository"%>
<%@page import="dto.Book"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>도서 삭제</title>
</head>
<body>
	<!--도서 삭제 페이지 만들기 -->
	<%@ include file="dbconn.jsp" %>
	<%
		// Quiz
		String bookId = request.getParameter("id");
	
		String sql = "DELETE FROM book WHERE b_id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bookId);
			int result = pstmt.executeUpdate();
			
			// SQL 쿼리문 실행 후 반환되는 결과값을 이용하여 오류 처리
			// 정상적으로 삭제 완료 시 "editBook.jsp?edit=delete"로 이동
			// 삭제된 결과가 없으면 "일치하는 도서가 없습니다." 출력
			if (result > 0) {
				response.sendRedirect("editBook.jsp?edit=delete");
			} else {
				out.println("일치하는 도서가 없습니다.");
			}
		} catch (SQLException e) {
				out.println("SQLException: " + e.getMessage());			
		} finally {
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		}
	%>
</body>
</html>