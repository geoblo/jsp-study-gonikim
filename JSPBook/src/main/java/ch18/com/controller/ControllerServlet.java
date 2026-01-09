package ch18.com.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import ch18.com.model.LoginDTO;

@WebServlet("/ch18/ControllerServlet")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// MVC를 적용한 로그인 인증하기
		String id = request.getParameter("id");
		String password = request.getParameter("passwd");
		
		LoginDTO loginDTO = new LoginDTO();
		loginDTO.setId(id);
		loginDTO.setPassword(password);
		
		// Service -> DAO 에서 처리하고 그 결과를 Model 객체에 담아 적절한 뷰 페이지(jsp)로 전달
		// 이 예제에서는 생략하고 간단히 처리
		boolean status = loginDTO.validate();
		
		// (중요)
		// 요청에 대한 처리 결과를 보여줄 응답 페이지로 이동하는 방법
		// GET요청(조회 / 화면 출력): 뷰 페이지에 요청 정보를 그대로 전달하고 처음에 요청된 URL을 계속 유지하기 위해 포워딩 방식을 사용
		// POST요청(등록 / 수정 / 삭제): 보통 로그인 후 처리 결과를 보여주는 것이 아니라 새롭게 리다이렉트(PRG 패턴)
		
		
	}

}
