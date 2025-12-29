package dto;

// 도서 클래스(도서 데이터를 표현하는 클래스로 자바빈으로 작성)
public class Book {
	private String bookId; 		 // 책 ID
	private String name;		 // 책이름
	private int unitPrice; 	     // 가격
	private String author;		 // 저자
	private String description;  // 설명
	private String publisher;	 // 출판사
	private String category; 	 // 분류
	private long unitsInStock;   // 재고개수
	private String releaseDate;  // 출판일(월/년)
	private String condition; 	 // 신제품 or 구제품 or 리퍼브제품
}
