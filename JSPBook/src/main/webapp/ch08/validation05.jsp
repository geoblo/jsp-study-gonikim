<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Validation</title>
</head>
<body>
	<!-- 회원 가입 폼 페이지에 입력한 데이터 형식 유효성 검사하기 -->
	<form name="member" action="validation05_process.jsp" method="post">
		<p>아이디: <input type="text" name="id"></p>
		<p>비밀번호: <input type="password" name="passwd"></p>
		<p>이름: <input type="text" name="name"></p>
		<p>
			연락처: 
			<select name="phone1">
				<option value="010">010</option>
				<option value="011">011</option>
				<option value="016">016</option>
				<option value="017">017</option>
				<option value="019">019</option>
			</select> - 
			<input type="text" maxlength="4" size="4" name="phone2"> - 
			<input type="text" maxlength="4" size="4" name="phone3">
		</p>
		<p>이메일: <input type="text" name="email"></p>
		<button type="button" onclick="checkMember()">가입하기</button> 
	</form>
	
	<script type="text/javascript">
		function checkLogin(e) {
			const form = document.loginForm;
			
			for (let ch of form.id.value) {
				if (ch < 'a' || ch > 'z') {
					alert("아이디는 영문 소문자만 입력 가능합니다!");
					form.id.select();
					e.preventDefault(); // 기본 동작 막기(여기서는 폼 제출 차단)
					return;
				}				
			}
			
			if (isNaN(form.passwd.value)) {
				alert("비밀번호는 숫자만 입력 가능합니다!");
				form.passwd.select();
				e.preventDefault(); // 기본 동작 막기(여기서는 폼 제출 차단)
				return;
			}
			
			// form.submit(); // 일반 button인 경우 필요, submit 버튼에서는 불필요
		}
	</script>
</body>
</html>