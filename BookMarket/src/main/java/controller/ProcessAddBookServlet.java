package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import dto.Book;

/**
 * Servlet implementation class ProcessAddBookServlet
 */
@WebServlet("/processAddBook")
@MultipartConfig(
	fileSizeThreshold = 1024 * 1024 * 1,   // 메모리 임시 저장 임계값(1MB) -> 이 크기 초과 시 디스크에 임시 저장
	maxFileSize = 1024 * 1024 * 10,        // 업로드 최대 파일 크기(10MB)
	maxRequestSize = 1024 * 1024 * 50      // 전체 요청 크기(50MB)
)
public class ProcessAddBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

        // ==== 일반 텍스트 데이터 처리 ====
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

        int price = 0;
        if (unitPrice != null && !unitPrice.isEmpty()) {
            price = Integer.parseInt(unitPrice);
        }

        long stock = 0;
        if (unitsInStock != null && !unitsInStock.isEmpty()) {
            stock = Long.parseLong(unitsInStock);
        }
        
        // ==== 파일 업로드 처리 ====
        
        
        // ==== Book 객체 생성 및 저장 ====
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
        newBook.setFilename(fileName); // 이미지 이름 저장(상대 경로로 JSP 페이지에서 접근하기 위해)
        // (참고) 보통은 이미지 경로도 같이 저장
		
		
	}

}
