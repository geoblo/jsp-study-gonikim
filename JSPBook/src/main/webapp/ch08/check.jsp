<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Validation</title>
</head>
<body>
	<!-- 입력 데이터의 첫 문자가 숫자인지 검사하는 예 -->
	<form name="frm">
		<p>이름: <input type="text" name="name"></p>
		<button type="submit" onclick="checkForm(event)">전송</button>
	</form>
	
	<script type="text/javascript">
		function checkForm(e) {
			const str = document.frm.name.value;
			
			const regExp = /^[a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
			// 의미?
			// ^: 문자열의 시작
			// []: 범위 중에 한 문자
			// a~z, |(파이프 문자), A~Z, |, ㄱ~ㅎ, |, ㅏ~ㅣ, |, 가~힣 중 하나의 문자와 매치
			// 끝에 $가 없음 => 문자열의 첫 글자만 검사하고, 그 뒤는 신경 쓰지 않음
			
			
			
			
			
			
			
		}
	</script>
</body>
</html>