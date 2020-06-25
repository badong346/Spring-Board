<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="C"%>
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
<style>
* {
	box-sizing: border-box;
}

.wrapper div {
	border: 1px solid black;
	margin: 0px;
	padding: 1px;
}

.wrapper {
	width: 500px;
	margin: auto;
}

.right {
	text-align: right;
}
</style>
<script>
	window.onload = function() {
		
		$("#withdrawal").on("click", function() {
			if (confirm("정말 탈퇴하시겠습니까?")) {
				location.href = "withdrawal";
			}
		})
		
		
		document.getElementById("check").onsubmit = function() {

			var pw1 = document.getElementById("pw1").value;
			var regexpw1 = /.{4,12}/;
			if(pw1 != null){
				var resultpw1 = regexpw1.test(pw1);
				if (!resultpw1) {
					alert("비밀번호 입력값이 잘못되었습니다. 공백문자를 제외하고 4글자 이상 12글자 이하로 입력하세요")
				}
			}else{
				var resultpw1 = true;
			}
			var pw2 = document.getElementById("pw2").value;
			if (pw1 != pw2) {
				alert("비밀번호 입력값이 서로 다릅니다.")
			}

			var name = document.getElementById("name").value;
			var regexname = /[A-Za-z가-힣]{1,15}/;
			var resultname = regexname.test(name);
			if (!resultname) {
				alert("이름 입력값이 잘못되었습니다. 1~15글자의 알파벳 대소문자 및 한글로 입력하세요")
			}
			var phone = document.getElementById("phone").value;
			var regexphone = /\d{8,11}/;
			var resultphone = regexphone.test(phone);
			if (!resultphone) {
				alert("전화번호 입력값이 잘못되었습니다. 숫자 8~11자로 입력하세요")
			}

			var email = document.getElementById("email").value;
			var regexemail = /.+@[a-z]+\.[a-z]+/;
			var resultemail = regexemail.test(email);
			if (!resultemail) {
				alert("이메일 입력값이 잘못되었습니다. @와 . 을 포함하여 입력해주세요")
			}
			return resultpw1 && resultname && resultphone && email

		}
	}
</script>
</head>
<body>



	<div class="wrapper">
		<form id=check action=modify method="post">
			<div class="row">
				<div class="col-sm-12" align=center>회원가입</div>
			</div>

			<div class="row">
				<div class="col-3 right">아이디 :</div>
				<div class="col">
					<div>
						<input type="text" placeholder="${loginInfo.id}" id="id" readonly>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-3 right">패스워드 :</div>
				<div class="col">
					<input type="text" id="pw1" name="pw">
				</div>
			</div>
			<div class="row">
				<div class="col-3 right">패스워드 확인 :</div>
				<div class="col">
					<input type="text" id="pw2">
				</div>
				<div id="pwcheck"></div>
			</div>
			<div class="row">
				<div class="col-3 right">이름 :</div>
				<div class="col">
					<input type="text" id="name" name="name" value="${loginInfo.name}">
				</div>
			</div>
			<div class="row">
				<div class="col-3 right">전화번호 :</div>
				<div class="col">
					<input type="text" id="phone" name="phone"
						value="${loginInfo.phone}">
				</div>
			</div>
			<div class="row">
				<div class="col-3 right">이메일 :</div>
				<div class="col">
					<input type="text" id="email" name="email"
						value="${loginInfo.email}">
				</div>
			</div>
			<div class="row">
				<div class="col-3 right">우편번호 :</div>
				<div class="col">
					<input type="text" id="postcode" name="zipcode" readonly> <input
						type="button" value="찾기" onclick="sample4_execDaumPostcode()">
				</div>
			</div>
			<div class="row">
				<div class="col-3 right">주소1 :</div>
				<div class="col">
					<input type="text" id="address1" name="address1" readonly>
				</div>
			</div>
			<div class="row">
				<div class="col-3 right">주소2 :</div>
				<div class="col">
					<input type="text" id="address2" name="address2"
						value="${loginInfo.address2}">
				</div>
			</div>
			<div class="row">
				<div class="col" align=center>

			
			<div class="btn-group" role="group" aria-label="Basic example">
				<button type="submit" class="btn btn-secondary" id="logout">수정하기</button>
				<button type="button" class="btn btn-secondary" id="back">돌아가기</button>
				<button type="button" class="btn btn-dark" id="withdrawal">탈퇴하기</button>
			</div>
				
			</div>
			</div>
		</form>
	</div>

	<script>
		document.getElementById("pw2").onkeyup = function() {
			if (document.getElementById("pw1").value == document
					.getElementById("pw2").value) {
				document.getElementById("pwcheck").innerHTML = "패스워드가 일치합니다"
				document.getElementById("pwcheck").style.color = "blue";
			} else {
				document.getElementById("pwcheck").innerHTML = "패스워드가 일치하지 않습니다"
				document.getElementById("pwcheck").style.color = "red";
			}
		};
		function sample4_execDaumPostcode() {
			new daum.Postcode({
				oncomplete : function(data) {
					var roadAddr = data.roadAddress;
					document.getElementById('postcode').value = data.zonecode;
					document.getElementById("address1").value = roadAddr;
				}
			}).open();
		}

		document.getElementById('back').onclick = function() {
			location.href = "/";
		}
	</script>


</body>
</html>