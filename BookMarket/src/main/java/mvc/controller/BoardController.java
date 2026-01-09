package mvc.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

// 게시판 컨트롤러
@WebServlet("*.do") // 예제용
// @WebServlet("/board/*") // 실제 서비스 구현 시
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final int LIST_COUNT = 5; // 한 번에 표시할 게시글 수
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 조회 / 화면 출력
		
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath(); // /BookMarket
		String command = requestURI.substring(contextPath.length());
		
		// 기능별 하나의 컨트롤러에서 요청 URL로 분기하던가
		// 아니면 요청 URL 별로 컨트롤러를 여러 개 만들어야 됨
		if (command.equals("/BoardListAction.do")) { // 등록된 글 목록 페이지 출력하기
			requestBoardList(request);
		}
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	// Service 계층: 비즈니스 로직 부분
	// 등록된 글 목록 가져오기
	private void requestBoardList(HttpServletRequest request) {
		
	}
	
	
	
	
	
	
	
	
	
	
	

}
