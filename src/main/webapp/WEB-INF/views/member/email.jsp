<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<script
	src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

</head>
<body>




	<span style="color: green; font-weight: bold;">입력한 이메일로 받은 인증번호를
		입력하세요. (인증번호가 맞아야 다음 단계로 넘어가실 수 있습니다.)</span>
	<br>
	<br>
	<br>
	<br>





	<form action="join_injeung.do${dice}" method="post">
		//받아온 인증코드를 컨트롤러로 넘겨서 일치하는지 확인 <br>
		<div>
			인증번호 입력 : <input type="number" name="email_injeung"
				placeholder="  인증번호를 입력하세요. ">
		</div>

		<br> <br>
		<button type="submit" name="submit">인증번호 전송</button>

	</form>









</body>
</html>