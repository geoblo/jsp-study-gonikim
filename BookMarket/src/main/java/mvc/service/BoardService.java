package mvc.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import mvc.dao.BoardDAO;
import mvc.database.DBConnection;
import mvc.dto.BoardDTO;

// Service: 비즈니스 로직, 트랜잭션 관리
public class BoardService {
	private BoardDAO dao = BoardDAO.getInstance();
	
	// Singleton 패턴
	private static final BoardService instance = new BoardService();

    private BoardService() {}

    public static BoardService getInstance() {
        return instance;
    }
    
    // 게시글 목록 조회
    public ArrayList<BoardDTO> getBoardList(int pageNum, int limit, String items, String text) {
 		return dao.getBoardList(pageNum, limit, items, text);
 	}
 	
 	public int getTotalRecord(String items, String text) {
        return dao.getListCount(items, text);
    }
 	
 	// 로그인 사용자 이름 조회
    public String getLoginName(String id) {
        return dao.getLoginNameById(id);
    }

    // 게시글 등록
    public void writeBoard(BoardDTO board) {
        dao.insertBoard(board);
    }
    
    // 게시글 상세 조회 + 조회수 증가
 	// (중요) 게시글이 정상적으로 조회된 경우에만 조회수를 증가(조회 실패 -> 조회수 증가도 취소)
 	// => 트랜잭션으로 묶기
 	// 1. 트랜잭션 처리는 DAO 내부가 아니라 "서비스 로직"에 위치
 	//    DAO는 트랜잭션을 모름, 단지 주어진 Connection으로 SQL을 실행할 뿐
 	// 2. 하나의 Connection을 공유해야 함
 	// 3. autoCommit = false
    public BoardDTO viewBoard(int num) {
		// 트랜잭션 포맷
		try (Connection conn = DBConnection.getConnection()) {
			conn.setAutoCommit(false); // 자동 저장 끄기(트랜잭션 시작)

			try {
				// 여러 DB 작업 수행
				// 1. 게시글 조회
				BoardDTO board = dao.getBoardByNum(conn, num);
				if (board == null) {
					throw new RuntimeException("게시글이 존재하지 않습니다.");
				}
				
				// 2. 조회 수 증가
				dao.updateHit(conn, num);
				
				conn.commit(); // 작업 모두 성공 시 확정(DB에 최종 반영)
				return board;
			} catch (Exception e) {
				conn.rollback(); // 하나라도 예외 발생 시 전부 취소
				e.printStackTrace();
				return null;
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
    
    }
    
    
}
