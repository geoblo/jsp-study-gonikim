package controller;

import java.io.File;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/processAddBook")
@MultipartConfig(
	maxFileSize = 1024 * 1024 * 10,
	maxRequestSize = 1024 * 1024 * 50
)
public class ProcessAddBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ProcessAddBookServlet() {
        super();
    }

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
		String fileName = "";
		
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
		
		// Book 객체 생성 및 저장
		
		
		// 등록 후 도서 목록 페이지로 리다이렉트
		
		
		
		
		
	}

}
