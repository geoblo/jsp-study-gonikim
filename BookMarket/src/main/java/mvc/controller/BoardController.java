package mvc.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mvc.dao.BoardDAO;
import mvc.database.DBConnection;
import mvc.dto.BoardDTO;
import mvc.service.BoardService;

//Controller: 요청 파라미터 추출, Service 호출, View 선택
@WebServlet("*.do") // 예제용
// @WebServlet("/board/*") // 실제 서비스 구현 시
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int LIST_COUNT = 5; // 한 번에 표시할 게시글 수
	private BoardService service = BoardService.getInstance();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 조회 / 화면 출력
		String command = getCommand(request);
		
		// 기능별 하나의 컨트롤러에서 요청 URL로 분기하던가
		// 아니면 요청 URL 별로 컨트롤러를 여러 개 만들어야 됨
		if (command.equals("/BoardListAction.do")) { // 글 목록 페이지 출력하기
			int pageNum = 1; // 현재 페이지 번호
			
			if (request.getParameter("pageNum") != null && 
			   !request.getParameter("pageNum").isEmpty()) {
				pageNum = Integer.parseInt(request.getParameter("pageNum"));
			}
			
			String items = request.getParameter("items"); // 검색 조건(제목, 본문, 글쓴이)
			String text = request.getParameter("text"); // 검색어
			
			// 전체 레코드 수
			int totalRecord = service.getTotalRecord(items, text);
			
			// 게시글 목록
			ArrayList<BoardDTO> boardList = service.getBoardList(pageNum, LIST_COUNT, items, text);
			
			// 총 페이지 수 계산법: 11.0 / 5.0 = 2.2 -> 3.0 -> 3페이지
			int totalPage = (int) Math.ceil((double) totalRecord / LIST_COUNT);
			
			// 뷰에 전달하기 위한 데이터 저장
	   		request.setAttribute("pageNum", pageNum);
	   		request.setAttribute("total_page", totalPage);
			request.setAttribute("total_record", totalRecord);
			request.setAttribute("boardList", boardList);
			
			// MVC 패턴 권장 구조: JSP 직접 접근 차단(WEB-INF 하위는 URL로 접근 불가)
			// forward 경로 기준: 웹앱 루트 기준
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/board/list.jsp");
			rd.forward(request, response);
		} else if (command.equals("/BoardWriteForm.do")) { // 글 등록 페이지 출력하기
			// 로그인 인증된 사용자명 가져오기
			String id = request.getParameter("id");
            request.setAttribute("name", service.getLoginName(id));
            
			request.getRequestDispatcher("/WEB-INF/views/board/writeForm.jsp").forward(request, response);	
		} else if (command.equals("/BoardViewAction.do")) { // 글 상세 페이지 출력하기
			int num = Integer.parseInt(request.getParameter("num"));
			BoardDTO board = service.viewBoard(num);
			
			request.setAttribute("num", request.getParameter("num"));
			request.setAttribute("page", request.getParameter("pageNum"));
			request.setAttribute("board", board);
			
			request.getRequestDispatcher("/WEB-INF/views/board/view.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 등록 / 수정 / 삭제
		String command = getCommand(request);
		
		if (command.equals("/BoardWriteAction.do")) { // 새로운 글 등록하기
			BoardDTO board = new BoardDTO();
			board.setId(request.getParameter("id"));
			board.setName(request.getParameter("name"));
			board.setSubject(request.getParameter("subject"));
			board.setContent(request.getParameter("content"));
			board.setHit(0);
			board.setIp(request.getRemoteAddr());

			String registDay = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
			board.setRegistDay(registDay);
			
			service.writeBoard(board);
			
			// redirect 경로 기준: 브라우저 기준
			response.sendRedirect(request.getContextPath() + "/BoardListAction.do");
		}
	}
	
	private String getCommand(HttpServletRequest request) {
        String uri = request.getRequestURI();
        String ctx = request.getContextPath(); // /BookMarket
        return uri.substring(ctx.length()); // contextPath를 제외한 나머지 부분
    }
	

}
