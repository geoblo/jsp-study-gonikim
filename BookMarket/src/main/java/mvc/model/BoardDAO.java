package mvc.model;

public class BoardDAO {
	// 싱글톤 패턴
	private static final BoardDAO instance = new BoardDAO(); 
			
	private BoardDAO() {
	}
	
	public static BoardDAO getInstance() {
		return instance;
	}
	
	
}
