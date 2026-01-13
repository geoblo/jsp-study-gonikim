package mvc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 게시판 데이터 클래스
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {
	private int num;           // 게시글 순번
	private String id;         // 작성자 아이디
	private String name;       // 작성자 이름
	private String subject;    // 제목
	private String content;    // 내용
	private String registDay;  // 등록 일자
	private int hit;           // 조회 수
	private String ip;         // IP 주소
}
