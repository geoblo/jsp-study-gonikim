<%@page import="dao.BookRepository"%>
<%@page import="dto.Book"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>도서 등록</title>
</head>
<body>
	<!-- 신규 도서 등록 처리 페이지 만들기 -->
	<%-- <%
		String bookId = request.getParameter("bookId");
		String name = request.getParameter("name");
		String unitPrice = request.getParameter("unitPrice");
		String author = request.getParameter("author");
		String publisher = request.getParameter("publisher");
		String releaseDate = request.getParameter("releaseDate");	
		String description = request.getParameter("description");	
		String category = request.getParameter("category");
		String unitsInStock = request.getParameter("unitsInStock");
		String condition = request.getParameter("condition");
	
		// 간단한 기본 유효성 검사 추가
		// 도서 가격이 입력되지 않은 경우 0으로, 입력된 경우 정수형으로 변경
		int price;
		if (unitPrice.isEmpty()) { // 빈 문자열인지 확인
			price = 0;
		} else {
			price = Integer.parseInt(unitPrice);
		}
		
		// 도서 재고 수가 입력되지 않은 경우 0으로, 입력된 경우 정수형으로 변경
		long stock;
		if (unitsInStock.isEmpty()) {
			stock = 0;
		} else {
			stock = Long.parseLong(unitsInStock);
		}
		
		Book newBook = new Book();
		newBook.setBookId(bookId);
		newBook.setName(name);
		newBook.setUnitPrice(price);
		newBook.setAuthor(author);
		newBook.setPublisher(publisher);
		newBook.setReleaseDate(releaseDate);
		newBook.setDescription(description);
		newBook.setCategory(category);
		newBook.setUnitsInStock(stock);
		newBook.setCondition(condition);
		
		BookRepository dao = BookRepository.getInstance();
		dao.addBook(newBook);
		
		// books.jsp 페이지로 강제 이동하도록 작성
		response.sendRedirect("books.jsp");
		
		// forward 시 문제점 테스트
		// RequestDispatcher dispatcher = request.getRequestDispatcher("books.jsp");
		// dispatcher.forward(request, response);
	%> --%>
	
	<!-- 자바빈 사용 시 -->
	<jsp:useBean id="bookDTO" class="dto.Book" scope="request" />
	
	<%-- <jsp:setProperty name="bookDTO" property="*" /> --%>
	<jsp:setProperty name="bookDTO" property="bookId" />
	<jsp:setProperty name="bookDTO" property="name" />
	<jsp:setProperty name="bookDTO" property="unitPrice" />
	<jsp:setProperty name="bookDTO" property="author" />
	<jsp:setProperty name="bookDTO" property="publisher" />
	<jsp:setProperty name="bookDTO" property="releaseDate" />
	<jsp:setProperty name="bookDTO" property="description" />
	<jsp:setProperty name="bookDTO" property="category" />
	<jsp:setProperty name="bookDTO" property="unitsInStock" />
	<jsp:setProperty name="bookDTO" property="condition" />
	
	<%
		BookRepository dao = BookRepository.getInstance();
		dao.addBook(bookDTO);
		response.sendRedirect("books.jsp");
	%>
</body>
</html>