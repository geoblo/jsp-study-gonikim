package mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import mvc.database.DBConnection;
import mvc.dto.BoardDTO;

// DAO: 순수 JDBC/SQL
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
	
	// member 테이블에서 로그인된 id의 사용자명 가져오기
	public String getLoginNameById(String id) {
		String sql = "SELECT name FROM member WHERE id = ?";
		
		try (Connection conn = DBConnection.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, id);
			
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return rs.getString("name");
				}
			}
		} catch (Exception e) {
			System.out.println("getLoginNameById() 예외 발생: " + e);
		}
		
		return null;
	}
	
	// board 테이블에 새로운 글 삽입하기
	public void insertBoard(BoardDTO board) {
		String sql = "INSERT INTO board (id, name, subject, content, regist_day, hit, ip) "
				   + "VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		try (Connection conn = DBConnection.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, board.getId());
			pstmt.setString(2, board.getName());
			pstmt.setString(3, board.getSubject());
			pstmt.setString(4, board.getContent());
			pstmt.setString(5, board.getRegistDay());
			pstmt.setInt(6, board.getHit());
			pstmt.setString(7, board.getIp());

			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("insertBoard() 예외 발생: " + e);
		}
	}
	
	// 선택된 글의 조회 수 증가
	public void updateHit(Connection conn, int num) throws SQLException {
		String sql = "UPDATE board SET hit = hit + 1 WHERE num = ?";
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		}
	}
	
	// 선택된 글 상세 내용 가져오기
	public BoardDTO getBoardByNum(Connection conn, int num) throws SQLException {
		String sql = "SELECT * FROM board WHERE num = ?";
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, num);
			
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					BoardDTO board = new BoardDTO();
					board.setNum(rs.getInt("num"));
					board.setId(rs.getString("id"));
					board.setName(rs.getString("name"));
					board.setSubject(rs.getString("subject"));
					board.setContent(rs.getString("content"));
					board.setRegistDay(rs.getString("regist_day"));
					board.setHit(rs.getInt("hit"));
					board.setIp(rs.getString("ip"));
					return board;
//					throw new RuntimeException("특정 게시글 조회 실패"); // 테스트
				}
			}
		}
		
		return null;
	}
	
	
	
	
	
	
	
	
	
	
}
