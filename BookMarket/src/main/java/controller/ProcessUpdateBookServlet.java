package controller;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dao.BookRepository;
import dto.Book;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import util.DBUtil;

@WebServlet("/processUpdateBook")
@MultipartConfig(
	maxFileSize = 1024 * 1024 * 10,
	maxRequestSize = 1024 * 1024 * 50
)
public class ProcessUpdateBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		// 일반 텍스트 데이터 처리
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
		
		// 파일 업로드 처리
		Part filePart = request.getPart("bookImage");
		String fileName = null;
		
		if (filePart != null && filePart.getSize() > 0) {
			// 파일 이름 가져오기
			fileName = filePart.getSubmittedFileName();
			
			// 업로드 폴더 경로
			// 외부 업로드 폴더 사용
			String uploadPath = "D:/upload";
			// JSP 페이지에서 /images/파일명으로 접근하려면 톰캣 설정(외부 폴더 매핑)을 추가해야 함
			// server.xml 또는 프로젝트별 context.xml에 설정
			
			// 업로드 폴더 없으면 생성
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdirs();
			}
			
			// 파일 저장
			filePart.write(uploadPath + File.separator + fileName);
		}
		
		// 도서 수정 처리 DB 연동
		// Quiz
		String sql = "UPDATE book "
	     		   + "SET b_name = ?, b_unitPrice = ?, b_author = ?, b_description = ?, "
	     		   + "b_publisher = ?, b_category = ?, b_unitsInStock = ?, "
	     		   + "b_releaseDate = ?, b_condition = ?, b_fileName = IFNULL(?, b_fileName) "
	     		   + "WHERE b_id = ?";
		// (참고) IFNULL(표현식1, 표현식2)
		// 표현식1: NULL인지 아닌지 검사할 컬럼이나 값
		// 표현식2: 표현식1이 NULL일 경우 대신 반환할 값
		
		// try-with-resources 적용
		try (Connection conn = DBUtil.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, name);
			pstmt.setInt(2, price);
			pstmt.setString(3, author);
			pstmt.setString(4, description);
			pstmt.setString(5, publisher);
			pstmt.setString(6, category);
			pstmt.setLong(7, stock);
			pstmt.setString(8, releaseDate);
			pstmt.setString(9, condition);
			pstmt.setString(10, fileName);
			pstmt.setString(11, bookId);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 수정 후 도서 편집 페이지로 리다이렉트
		response.sendRedirect("editBook.jsp?edit=update");
	}

}
