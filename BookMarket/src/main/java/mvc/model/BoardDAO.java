package mvc.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import mvc.database.DBConnection;

public class BoardDAO {
	// 싱글톤 패턴
	private static final BoardDAO instance = new BoardDAO(); 
			
	private BoardDAO() {
	}
	
	public static BoardDAO getInstance() {
		return instance;
	}
	
	// board 테이블의 레코드 개수
	public int getListCount(String items, String text) {
		int totalCount = 0;
		
		String sql = "SELECT COUNT(*) AS cnt FROM board";
		
		// 검색 조건이 있을 때만 WHERE 추가
		if (items != null && text != null && !items.isEmpty() && !text.isEmpty()) {
			sql += " WHERE " + items + " LIKE ?";
		}
		
		try (Connection conn = DBConnection.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			// 검색 조건이 있을 때만 동적 바인딩
			if (items != null && text != null && !items.isEmpty() && !text.isEmpty()) {
				pstmt.setString(1, "%" + text + "%");
			}
			
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					totalCount = rs.getInt("cnt");
				}
			}
		} catch (Exception e) {
			System.out.println("getListCount() 예외 발생: " + e);
		}
		
		return totalCount;
	}
	
	// board 테이블의 레코드 가져오기
	public ArrayList<BoardDTO> getBoardList(int page, int limit, String items, String text) {
		int offset = (page - 1) * limit; // 페이징 처리 offset 계산(몇 개를 건너뛸지)
		
		String sql = "SELECT * FROM board";
		
		// 검색 조건이 있을 때만 WHERE 추가
		if (items != null && text != null && !items.isEmpty() && !text.isEmpty()) {
			sql += " WHERE " + items + " LIKE ?";
		}
		
		sql += " ORDER BY num DESC LIMIT ? OFFSET ?";
		
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		
		try (Connection conn = DBConnection.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			int paramIndex = 1;
			
			// 검색 조건이 있을 때만 동적 바인딩
			if (items != null && text != null && !items.isEmpty() && !text.isEmpty()) {
				pstmt.setString(paramIndex++, "%" + text + "%");
			}
			
			pstmt.setInt(paramIndex++, limit);
			pstmt.setInt(paramIndex, offset);
			
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					BoardDTO board = new BoardDTO();
					board.setNum(rs.getInt("num"));
					board.setId(rs.getString("id"));
					board.setName(rs.getString("name"));
					board.setSubject(rs.getString("subject"));
					board.setContent(rs.getString("content"));
					board.setRegistDay(rs.getString("regist_day"));
					board.setHit(rs.getInt("hit"));
					board.setIp(rs.getString("ip"));
					list.add(board);
				}
			}
		} catch (Exception e) {
			System.out.println("getBoardList() 예외 발생: " + e);
		}
		
		return list;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
