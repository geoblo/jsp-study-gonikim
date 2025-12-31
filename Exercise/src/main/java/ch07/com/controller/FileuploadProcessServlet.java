package ch07.com.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.UUID;

@WebServlet("/fileuploadProcess")
@MultipartConfig(
    maxFileSize = 1024 * 1024 * 10,        // 업로드 최대 파일 크기(10MB)
    maxRequestSize = 1024 * 1024 * 50      // 전체 요청 크기(50MB)
)
public class FileuploadProcessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		// 1. 업로드 경로 설정
		String uploadPath = "D:/upload";
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
		}
		
		// 2-1. 일반 데이터 가져오기
		String name = request.getParameter("name");
		String subject = request.getParameter("subject");
		
		// 2-2. 여러 개의 파일 데이터 가져오기
		// 서블릿 3.0 표준 API 사용
		Collection<Part> fileParts = request.getParts();
		
		int fileCount = 1;
		for (Part part : fileParts) {
			// 파일만 필터링 - 방법1
			if (part.getName().equals("uploadFiles")) {
				String fileName = part.getSubmittedFileName(); // 원본 파일 이름
				
				// 파일만 필터링 - 방법2
				// fileName은 파일 필드가 아닌 일반 폼 필드에서는 항상 null
				// 파일 input이지만 파일을 선택하지 않은 경우 ""(빈 문자열)
				if (fileName == null || fileName.isEmpty()) continue; 
				
				// 파일명이 중복될 가능성이 있으니 새로운 이름으로 변경
				// 이때 원본 파일 이름은 새로운 파일 이름과 함께 DB에 보관
				String uuid = UUID.randomUUID().toString();
				String newFileName = uuid + "_" + fileName;
				
				part.write(uploadPath + File.separator + newFileName); // 파일 저장
				out.println("업로드된 파일 " + fileCount + ": " + fileName + " => " + newFileName + "<br>");
				fileCount++;
			}
		}
		
		// (참고) multiple 속성을 사용하여 하나의 input으로 여러 개의 파일을 업로드 시
		// 같은 name(예: uploadFiles)으로 서버에 전송됨
		// getParts()로 모든 Part를 받고, 파일 항목들을 순회하면서 저장
	}

}
