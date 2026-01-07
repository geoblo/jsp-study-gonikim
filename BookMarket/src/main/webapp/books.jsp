<%@page import="java.sql.SQLException"%>
<%@page import="dao.BookRepository"%>
<%@page import="dto.Book"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- session이 연결되는 동안 도서 데이터를 공유하기 위해 사용 -->
<%-- <jsp:useBean id="bookDAO" class="dao.BookRepository" scope="session" /> --%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>도서 목록</title>
	<!-- 부트스트랩 연결 -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
	<!-- 로컬에서 직접 넣기 -->
	<!-- <link rel="stylesheet" href="resources/css/bootstrap.min.css"> -->
</head>
<body>
	<div class="container py-4">
		<%@ include file="menu.jsp" %>
    
    <jsp:include page="title.jsp">
    	<jsp:param value="도서목록" name="title"/>
    	<jsp:param value="BookList" name="sub"/>
    </jsp:include>
    
    <%@ include file="dbconn.jsp" %>
    
    <div class="row align-items-md-stretch text-center">
    	<%
    		// Quiz: book 테이블의 모든 데이터를 가져오도록 SELECT문 작성
    		try {
    			String sql = "SELECT * FROM book";
    			pstmt = conn.prepareStatement(sql);
    			rs = pstmt.executeQuery();
    			
    			// 가져온 레코드들을 반복하며 동적 바인딩
    		 	while (rs.next()) {
 		 	%>
      <div class="col-md-4">
        <div class="h-100 p-2">
        	<!-- 도서 정보 -->
        	<!-- 웹앱 내부 폴더 사용 시 -->
        	<%-- <img alt="도서이미지" src="./resources/images/<%= book.getFilename() %>" style="width: 250px; height: 350px;"> --%>
        	<!-- 외부 폴더 사용 시 -->
        	<img alt="도서이미지" src="<%= request.getContextPath() %>/images/<%= rs.getString("b_filename") %>" style="width: 250px; height: 350px;">
        	
        	<h5><b><%= rs.getString("b_name") %></b></h5>
        	<p>
        		<%= rs.getString("b_author") %>
        		<br>
        		<%= rs.getString("b_publisher") %> | <%= rs.getString("b_releaseDate") %>
        	</p>
        	<p><%= rs.getString("b_description").substring(0, 60) %>...</p>
        	<p><%= rs.getString("b_unitPrice") %>원</p>
        	<p>
						<a href="./book.jsp?id=<%= rs.getString("b_id") %>" class="btn btn-secondary" role="button">
							상세 정보 &raquo;
						</a>
        	</p>
        </div>
      </div>
      <%
				 	} // 반복문 종료
				} catch (SQLException e) {
					out.println("SQLException: " + e.getMessage());
				} finally {
					if (rs != null) rs.close(); 
					if (pstmt != null) pstmt.close(); 
					if (conn != null) conn.close(); 
				}
			%>
 		</div>
 		
   	<%@ include file="footer.jsp" %>
	</div>
</body>
</html>